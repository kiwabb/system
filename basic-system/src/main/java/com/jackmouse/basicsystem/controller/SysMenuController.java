package com.jackmouse.basicsystem.controller;

import com.jackmouse.basicsystem.dto.SysMenuDTO;
import com.jackmouse.basicsystem.service.SysRoleMenuService;
import com.jackmouse.basicsystem.service.SysUserService;
import com.jackmouse.basicsystem.vo.SysMenuVO;
import com.jackmouse.common.model.PageResult;
import com.jackmouse.common.model.Result;
import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jackmouse.basicsystem.entity.SysMenu;
import com.jackmouse.basicsystem.service.SysMenuService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 菜单表 控制层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 添加菜单表。
     *
     * @param sysMenu 菜单表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysMenu sysMenu) {
        return Result.succeed(sysMenuService.save(sysMenu));
    }

    /**
     * 绑定角色菜单
     *
     * @param sysMenu 角色对应的菜单
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("assignMenu")
    public Result<Boolean> assignMenu(@RequestBody SysMenuDTO sysMenu) {
        return Result.succeed(sysRoleMenuService.assignMenu(sysMenu));
    }

    /**
     * 根据主键删除菜单表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public Result<Boolean> remove(@PathVariable("id") Long id) {
        return Result.succeed(sysMenuService.removeById(id));
    }

    /**
     * 根据主键更新菜单表。
     *
     * @param sysMenu 菜单表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysMenu sysMenu) {
        return Result.succeed(sysMenuService.updateById(sysMenu));
    }

    /**
     * 查询所有菜单表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public Result<List<SysMenu>> list() {
        return Result.succeed(sysMenuService.list());
    }

    /**
     * 查询所有菜单表。
     *
     * @return 所有数据
     */
    @GetMapping("menuTypeOne")
    public Result<List<SysMenu>> menuTypeOne() {
        return Result.succeed(sysMenuService.menuTypeOne());
    }

    /**
     * 查询角色对应的菜单
     *
     * @return 所有数据
     */
    @GetMapping("getMenuByRoleId/{roleId}")
    public Result<List<SysMenuVO>> getMenuByRoleId(@PathVariable("roleId") Long roleId) {
        return Result.succeed(sysMenuService.getMenuByRoleId(roleId));
    }

    /**
     * 根据菜单表主键获取详细信息。
     *
     * @param id 菜单表主键
     * @return 菜单表详情
     */
    @GetMapping("getInfo/{id}")
    public Result<SysMenu> getInfo(@PathVariable("id") Long id) {
        return Result.succeed(sysMenuService.getById(id));
    }

    /**
     * 分页查询菜单表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public PageResult<SysMenu> page(Page<SysMenu> page) {
        return PageResult.succeed(sysMenuService.page(page));
    }

}
