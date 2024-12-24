package com.jackmouse.basicsystem.service.dubbo;

import com.jackmouse.basicsystem.api.SysUserDubboService;
import com.jackmouse.basicsystem.entity.SysUser;
import com.jackmouse.basicsystem.service.SysUserService;
import com.jackmouse.basicsystem.vo.SysUserVO;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

import static com.jackmouse.basicsystem.entity.table.SysMenuTableDef.SYS_MENU;
import static com.jackmouse.basicsystem.entity.table.SysRoleMenuTableDef.SYS_ROLE_MENU;
import static com.jackmouse.basicsystem.entity.table.SysRoleTableDef.SYS_ROLE;
import static com.jackmouse.basicsystem.entity.table.SysRoleUserTableDef.SYS_ROLE_USER;
import static com.jackmouse.basicsystem.entity.table.SysUserTableDef.SYS_USER;

/**
 * @ClassName SysUserDubboServiceImpl
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/23 09:33
 * @Version 1.0
 **/
@DubboService
public class SysUserDubboServiceImpl implements SysUserDubboService {

    @Resource
    private SysUserService sysUserService;
    @Override
    public SysUserVO loadUserByUsername(String username) {
        return sysUserService.loadUserByUsername(username);
    }

}
