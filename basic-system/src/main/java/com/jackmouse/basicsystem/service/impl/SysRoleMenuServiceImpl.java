package com.jackmouse.basicsystem.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jackmouse.basicsystem.dto.SysMenuDTO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysRoleMenu;
import com.jackmouse.basicsystem.mapper.SysRoleMenuMapper;
import com.jackmouse.basicsystem.service.SysRoleMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jackmouse.basicsystem.entity.table.SysRoleMenuTableDef.SYS_ROLE_MENU;

/**
 *  服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>  implements SysRoleMenuService{
    @Resource
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Boolean assignMenu(SysMenuDTO sysMenuDTO) {
        sysRoleMenuMapper.deleteByQuery(QueryWrapper.create().where(SYS_ROLE_MENU.ROLE_ID.eq(sysMenuDTO.getRoleId())));
        if (CollectionUtil.isNotEmpty(sysMenuDTO.getMenuIds())) {
            List<SysRoleMenu> sysMenuList = sysMenuDTO.getMenuIds().stream().map(menuId -> {
                SysRoleMenu sysMenu = new SysRoleMenu();
                sysMenu.setRoleId(sysMenuDTO.getRoleId());
                sysMenu.setMenuId(menuId);
                return sysMenu;
            }).toList();
            sysRoleMenuMapper.insertBatch(sysMenuList);
        }
        return true;
    }
}
