package com.jackmouse.basicsystem.vo;

import com.jackmouse.basicsystem.entity.SysMenu;
import com.jackmouse.basicsystem.entity.SysRole;
import com.jackmouse.basicsystem.entity.SysUser;
import com.jackmouse.common.entity.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.RelationManyToMany;
import com.mybatisflex.annotation.TableRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SysUserVO
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/23 16:18
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableRef(SysUser.class)
public class SysUserVO extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;


    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 是否可用
     */
    private Integer enabled;

    /**
     * 用户类型
     */
    private Integer userType;

    private List<SysRole> roleList;

    private Set<SysMenu> menuList;

}
