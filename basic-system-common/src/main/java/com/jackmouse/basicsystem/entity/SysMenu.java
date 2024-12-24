package com.jackmouse.basicsystem.entity;

import com.jackmouse.common.entity.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 菜单表 实体类。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("sys_menu")
public class SysMenu extends BaseEntity implements Serializable {

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMenu sysMenu = (SysMenu) o;
        return Objects.equals(parentId, sysMenu.parentId) && Objects.equals(name, sysMenu.name) && Objects.equals(path, sysMenu.path) && Objects.equals(component, sysMenu.component) && Objects.equals(componentName, sysMenu.componentName) && Objects.equals(type, sysMenu.type) && Objects.equals(hidden, sysMenu.hidden) && Objects.equals(icon, sysMenu.icon) && Objects.equals(sort, sysMenu.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, name, path, component, componentName, type, hidden, icon, sort);
    }




}
