package com.jackmouse.basicsystem.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysMenu;
import com.jackmouse.basicsystem.mapper.SysMenuMapper;
import com.jackmouse.basicsystem.service.SysMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jackmouse.basicsystem.entity.table.SysMenuTableDef.SYS_MENU;

/**
 * 菜单表 服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>  implements SysMenuService{

    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> menuTypeOne() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                        .select(SYS_MENU.ALL_COLUMNS).where(SYS_MENU.TYPE.eq(1));
        return sysMenuMapper.selectListByQuery(queryWrapper);
    }
}
