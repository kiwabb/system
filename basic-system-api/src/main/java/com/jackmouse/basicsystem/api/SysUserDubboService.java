package com.jackmouse.basicsystem.api;

import com.jackmouse.basicsystem.entity.SysUser;
import com.jackmouse.basicsystem.vo.SysUserVO;

import java.util.Optional;

/**
 * @ClassName SysUserDubboService
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/23 09:22
 * @Version 1.0
 **/
public interface SysUserDubboService {
    SysUserVO loadUserByUsername(String username);
}
