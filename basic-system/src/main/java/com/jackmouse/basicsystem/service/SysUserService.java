package com.jackmouse.basicsystem.service;

import com.jackmouse.basicsystem.dto.SysUserDTO;
import com.jackmouse.basicsystem.dto.UserSearchDTO;
import com.jackmouse.basicsystem.vo.SysUserVO;
import com.mybatisflex.core.paginate.Page;
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

    SysUserVO getCurrentUserInfo();

    Page<SysUser> getUnAssignUser(Page<SysUser> page, UserSearchDTO user);

    Page<SysUser> getAssignUser(Page<SysUser> page, UserSearchDTO user);

}
