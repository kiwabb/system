package com.jackmouse.basicsystem.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jackmouse.basicsystem.dto.SysUserDTO;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.jackmouse.basicsystem.entity.SysRoleUser;
import com.jackmouse.basicsystem.mapper.SysRoleUserMapper;
import com.jackmouse.basicsystem.service.SysRoleUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.jackmouse.basicsystem.entity.table.SysRoleUserTableDef.SYS_ROLE_USER;

/**
 *  服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser>  implements SysRoleUserService{

    @Resource
    SysRoleUserMapper sysRoleUserMapper;

    @Override
    public List<Long> getUserIdByRoleId(Long roleId) {
        return sysRoleUserMapper.selectListByQueryAs(
                QueryWrapper.create()
                        .select(SYS_ROLE_USER.USER_ID)
                        .where(SYS_ROLE_USER.ROLE_ID.eq(roleId)),
                Long.class
        );
    }



    @Override
    public Boolean assignUser(SysUserDTO sysMenu) {
        if (CollectionUtil.isNotEmpty(sysMenu.getUserIds())) {
            sysRoleUserMapper.insertBatch(sysMenu.getUserIds().stream().map(userId -> {
                SysRoleUser sysRoleUser = new SysRoleUser();
                sysRoleUser.setRoleId(sysMenu.getRoleId());
                sysRoleUser.setUserId(userId);
                return sysRoleUser;
            }).collect(Collectors.toList()));
        }
        return true;
    }

    @Override
    public Boolean deleteRoleUser(SysUserDTO sysMenu) {
        if (CollectionUtil.isNotEmpty(sysMenu.getUserIds())) {
            sysRoleUserMapper.deleteByQuery(
                    QueryWrapper.create()
                            .where(SYS_ROLE_USER.ROLE_ID.eq(sysMenu.getRoleId()))
                            .and(SYS_ROLE_USER.USER_ID.in(sysMenu.getUserIds()))
            );
        }
        return true;
    }
}
