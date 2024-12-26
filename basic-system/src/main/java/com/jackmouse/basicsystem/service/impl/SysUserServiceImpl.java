package com.jackmouse.basicsystem.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.jackmouse.basicsystem.dto.SysUserDTO;
import com.jackmouse.basicsystem.dto.UserSearchDTO;
import com.jackmouse.basicsystem.entity.SysMenu;
import com.jackmouse.basicsystem.entity.SysRoleUser;
import com.jackmouse.basicsystem.service.SysMenuService;
import com.jackmouse.basicsystem.service.SysRoleUserService;
import com.jackmouse.basicsystem.vo.SysUserVO;
import com.jackmouse.basicsystem.utils.LoginUserUtils;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysUser;
import com.jackmouse.basicsystem.mapper.SysUserMapper;
import com.jackmouse.basicsystem.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.jackmouse.basicsystem.entity.table.SysMenuTableDef.SYS_MENU;
import static com.jackmouse.basicsystem.entity.table.SysRoleMenuTableDef.SYS_ROLE_MENU;
import static com.jackmouse.basicsystem.entity.table.SysRoleTableDef.SYS_ROLE;
import static com.jackmouse.basicsystem.entity.table.SysRoleUserTableDef.SYS_ROLE_USER;
import static com.jackmouse.basicsystem.entity.table.SysUserTableDef.SYS_USER;

/**
 *  服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>  implements SysUserService{

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysMenuService sysMenuService;

    @Resource
    private SysRoleUserService sysRoleUserService;

    @Override
    public SysUserVO loadUserByUsername(String username) {
        QueryWrapper queryWrapper = getUserInfoWrapper()
                .where(SYS_USER.USERNAME.eq(username));
        return sysUserMapper.selectOneByQueryAs(queryWrapper, SysUserVO.class);
    }

    @Override
    public SysUserVO getCurrentUserInfo() {
        Long loginUserId = LoginUserUtils.getLoginUserId();
        QueryWrapper queryWrapper = getUserInfoWrapper()
                .where(SYS_USER.ID.eq(loginUserId));
        SysUserVO sysUserVO = sysUserMapper.selectOneByQueryAs(queryWrapper, SysUserVO.class);
        sysUserVO.setPassword(null);
        Set<SysMenu> menuList = sysUserVO.getMenuList();
        Set<Long> menuIdSet = menuList.stream()
                .map(SysMenu::getId)
                .collect(Collectors.toSet());
        Set<Long> parentMenuIds = menuList.stream().map(SysMenu::getParentId)
                .filter(parentId -> !menuIdSet.contains(parentId) && parentId != -1)
                .collect(Collectors.toSet());
        if (CollectionUtil.isNotEmpty(parentMenuIds)) {
            List<SysMenu> sysMenus = sysMenuService.listByIds(parentMenuIds);
            menuList.addAll(sysMenus);
        }
        return sysUserVO;
    }

    @Override
    public Page<SysUser> getUnAssignUser(Page<SysUser> page, UserSearchDTO user) {

        List<Long> roleUserIds = sysRoleUserService.getUserIdByRoleId(user.getRoleId());
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(SYS_USER.ID, SYS_USER.USERNAME, SYS_USER.NICKNAME)
                .from(SYS_USER);
        if (CollectionUtil.isNotEmpty(roleUserIds)) {
            queryWrapper.where(SYS_USER.ID.notIn(roleUserIds));
        }
        if (StrUtil.isNotEmpty(user.getNickname())) {
            queryWrapper.where(SYS_USER.NICKNAME.like(user.getNickname()));
        }
        return sysUserMapper.paginate(page, queryWrapper);
    }

    @Override
    public Page<SysUser> getAssignUser(Page<SysUser> page, UserSearchDTO user) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(SYS_USER.ID, SYS_USER.USERNAME, SYS_USER.NICKNAME)
                .from(SYS_USER)
                .where(SYS_USER.ID.in(
                        QueryWrapper.create()
                                .select(SYS_ROLE_USER.USER_ID)
                                .from(SYS_ROLE_USER)
                                .where(SYS_ROLE_USER.ROLE_ID.eq(user.getRoleId()))
                ));
        if (StrUtil.isNotEmpty(user.getNickname())) {
            queryWrapper.where(SYS_USER.NICKNAME.like(user.getNickname()));
        }
        return sysUserMapper.paginate(page,queryWrapper
        );
    }



    private static QueryWrapper getUserInfoWrapper() {
        return QueryWrapper.create()
                .select(SYS_USER.DEFAULT_COLUMNS, SYS_ROLE.DEFAULT_COLUMNS, SYS_MENU.DEFAULT_COLUMNS)
                .from(SYS_USER)
                .leftJoin(SYS_ROLE_USER.as("ru")).on(SYS_USER.ID.eq(SYS_ROLE_USER.USER_ID))
                .leftJoin(SYS_ROLE.as("r")).on(SYS_ROLE_USER.ROLE_ID.eq(SYS_ROLE.ID))
                .leftJoin(SYS_ROLE_MENU.as("rm")).on(SYS_ROLE_MENU.ROLE_ID.eq(SYS_ROLE.ID))
                .leftJoin(SYS_MENU.as("m")).on(SYS_MENU.ID.eq(SYS_ROLE_MENU.MENU_ID));
    }
}
