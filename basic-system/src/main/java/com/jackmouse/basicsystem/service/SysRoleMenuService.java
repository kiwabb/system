package com.jackmouse.basicsystem.service;

import com.jackmouse.basicsystem.dto.SysMenuDTO;
import com.mybatisflex.core.service.IService;
import com.jackmouse.basicsystem.entity.SysRoleMenu;

/**
 *  服务层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    Boolean assignMenu(SysMenuDTO sysMenu);
}
