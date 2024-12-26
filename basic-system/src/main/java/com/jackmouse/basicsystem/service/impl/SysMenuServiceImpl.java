package com.jackmouse.basicsystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.jackmouse.basicsystem.dto.SysMenuDTO;
import com.jackmouse.basicsystem.entity.SysRoleMenu;
import com.jackmouse.basicsystem.vo.SysMenuVO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysMenu;
import com.jackmouse.basicsystem.mapper.SysMenuMapper;
import com.jackmouse.basicsystem.service.SysMenuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.jackmouse.basicsystem.entity.table.SysMenuTableDef.SYS_MENU;
import static com.jackmouse.basicsystem.entity.table.SysRoleMenuTableDef.SYS_ROLE_MENU;
import static com.jackmouse.basicsystem.entity.table.SysRoleTableDef.SYS_ROLE;

/**
 * 菜单表 服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>  implements SysMenuService{

    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> menuTypeOne() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                        .select(SYS_MENU.ALL_COLUMNS).where(SYS_MENU.TYPE.eq(1));
        return sysMenuMapper.selectListByQuery(queryWrapper);
    }

    @Override
    public List<SysMenuVO> getMenuByRoleId(Long roleId) {
        // 查询角色id对应的菜单信息
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(SYS_MENU.ID)
                .from(SYS_ROLE.as("r"))
                .leftJoin(SYS_ROLE_MENU.as("rm")).on(SYS_ROLE_MENU.ROLE_ID.eq(SYS_ROLE.ID))
                .leftJoin(SYS_MENU.as("m")).on(SYS_MENU.ID.eq(SYS_ROLE_MENU.MENU_ID))
                .where(SYS_ROLE.ID.eq(roleId));
        Set<Long> menuIdSet = Optional.of(sysMenuMapper.selectListByQuery(queryWrapper))
                .map(
                        sysMenus -> sysMenus.stream()
                                .filter(Objects::nonNull)
                                .map(SysMenu::getId)
                                .collect(Collectors.toSet())
                )
                .orElse(new HashSet<>());

        List<SysMenu> sysMenus = sysMenuMapper.selectAll();
        if (CollectionUtil.isEmpty(menuIdSet)) {
            return BeanUtil.copyToList(sysMenus, SysMenuVO.class);
        }
        return sysMenus.stream().map(sysMenu -> {
            SysMenuVO sysMenuVO = BeanUtil.copyProperties(sysMenu, SysMenuVO.class);
            if (menuIdSet.contains(sysMenu.getId())) {
                sysMenuVO.setChecked(true);
            }
            return sysMenuVO;
        }).toList();
    }


}
