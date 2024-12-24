package com.jackmouse.basicsystem.service;

import com.jackmouse.basicsystem.vo.SysUserVO;
import com.mybatisflex.core.service.IService;
import com.jackmouse.basicsystem.entity.SysUser;

/**
 *  服务层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
public interface SysUserService extends IService<SysUser> {

    SysUserVO loadUserByUsername(String username);
}
