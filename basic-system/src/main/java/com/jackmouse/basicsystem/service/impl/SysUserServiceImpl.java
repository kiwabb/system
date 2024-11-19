package com.jackmouse.basicsystem.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysUser;
import com.jackmouse.basicsystem.mapper.SysUserMapper;
import com.jackmouse.basicsystem.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>  implements SysUserService{

}
