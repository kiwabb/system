package com.jackmouse.basicsystem.vo;

import com.jackmouse.basicsystem.entity.SysMenu;
import com.jackmouse.common.entity.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.TableRef;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SysMenuVO
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/25 15:26
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableRef(SysMenu.class)
public class SysMenuVO extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 父菜单id
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单访问路径
     */
    private String path;

    /**
     * 前端组件地址
     */
    private String component;

    private String componentName;
    /**
     * 菜单类型
     */
    private Integer type;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    private String icon;

    /**
     * 排序
     */
    private Long sort;

    private Boolean checked;
}
