package com.jackmouse.basicsystem.service;

import com.jackmouse.basicsystem.dto.SysUserDTO;
import com.mybatisflex.core.service.IService;
import com.jackmouse.basicsystem.entity.SysRoleUser;

import java.util.List;

/**
 *  服务层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
public interface SysRoleUserService extends IService<SysRoleUser> {

    List<Long> getUserIdByRoleId(Long roleId);

    Boolean assignUser(SysUserDTO sysMenu);

    Boolean deleteRoleUser(SysUserDTO sysMenu);
}
