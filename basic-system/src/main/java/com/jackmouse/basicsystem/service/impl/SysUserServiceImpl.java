package com.jackmouse.basicsystem.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jackmouse.basicsystem.entity.SysMenu;
import com.jackmouse.basicsystem.service.SysMenuService;
import com.jackmouse.basicsystem.vo.SysUserVO;
import com.jackmouse.basicsystem.utils.LoginUserUtils;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysUser;
import com.jackmouse.basicsystem.mapper.SysUserMapper;
import com.jackmouse.basicsystem.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        List<SysMenu> sysMenus = sysMenuService.listByIds(parentMenuIds);
        menuList.addAll(sysMenus);
        return sysUserVO;
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
