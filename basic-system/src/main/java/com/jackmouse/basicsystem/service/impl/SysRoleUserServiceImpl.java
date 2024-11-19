package com.jackmouse.basicsystem.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysRoleUser;
import com.jackmouse.basicsystem.mapper.SysRoleUserMapper;
import com.jackmouse.basicsystem.service.SysRoleUserService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser>  implements SysRoleUserService{

}
