package com.jackmouse.basicsystem.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName SysUserDTO
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/26 16:31
 * @Version 1.0
 **/
@Data
public class SysUserDTO {
    private Long roleId;
    private List<Long>userIds;
}
