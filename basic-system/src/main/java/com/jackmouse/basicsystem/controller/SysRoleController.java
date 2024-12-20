package com.jackmouse.basicsystem.controller;

import com.jackmouse.common.model.PageResult;
import com.jackmouse.common.model.Result;
import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jackmouse.basicsystem.entity.SysRole;
import com.jackmouse.basicsystem.service.SysRoleService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 角色表 控制层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 添加角色表。
     *
     * @param sysRole 角色表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysRole sysRole) {
        return Result.succeed(sysRoleService.save(sysRole));
    }

    /**
     * 根据主键删除角色表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public Result<Boolean> remove(@PathVariable("id") Long id) {
        return Result.succeed(sysRoleService.removeById(id));
    }

    /**
     * 根据主键更新角色表。
     *
     * @param sysRole 角色表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysRole sysRole) {
        return Result.succeed(sysRoleService.updateById(sysRole));
    }

    /**
     * 查询所有角色表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public Result<List<SysRole>> list() {
        return Result.succeed(sysRoleService.list());
    }

    /**
     * 根据角色表主键获取详细信息。
     *
     * @param id 角色表主键
     * @return 角色表详情
     */
    @GetMapping("getInfo/{id}")
    public Result<SysRole> getInfo(@PathVariable("id") Long id) {
        return Result.succeed(sysRoleService.getById(id));
    }

    /**
     * 分页查询角色表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public PageResult<SysRole> page(Page<SysRole> page) {
        return PageResult.succeed(sysRoleService.page(page));
    }

}
