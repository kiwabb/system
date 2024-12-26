package com.jackmouse.basicsystem.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName SysMenuDTO
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/26 09:00
 * @Version 1.0
 **/
@Data
public class SysMenuDTO {
    private Long roleId;
    private List<Long> menuIds;
}
