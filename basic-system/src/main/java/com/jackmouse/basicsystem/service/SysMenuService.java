package com.jackmouse.basicsystem.service;

import com.jackmouse.basicsystem.dto.SysMenuDTO;
import com.jackmouse.basicsystem.vo.SysMenuVO;
import com.mybatisflex.core.service.IService;
import com.jackmouse.basicsystem.entity.SysMenu;

import java.util.List;

/**
 * 菜单表 服务层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> menuTypeOne();

    List<SysMenuVO> getMenuByRoleId(Long roleId);

}
