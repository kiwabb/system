package com.jackmouse.basicsystem.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysMenu;
import com.jackmouse.basicsystem.mapper.SysMenuMapper;
import com.jackmouse.basicsystem.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单表 服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>  implements SysMenuService{

}
