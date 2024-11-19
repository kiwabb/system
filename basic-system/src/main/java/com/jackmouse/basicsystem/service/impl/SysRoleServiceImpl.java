package com.jackmouse.basicsystem.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysRole;
import com.jackmouse.basicsystem.mapper.SysRoleMapper;
import com.jackmouse.basicsystem.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>  implements SysRoleService{

}
