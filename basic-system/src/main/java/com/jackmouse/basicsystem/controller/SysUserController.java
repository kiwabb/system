package com.jackmouse.basicsystem.controller;

import com.jackmouse.basicsystem.dto.SysMenuDTO;
import com.jackmouse.basicsystem.dto.SysUserDTO;
import com.jackmouse.basicsystem.dto.UserSearchDTO;
import com.jackmouse.basicsystem.service.SysRoleMenuService;
import com.jackmouse.basicsystem.service.SysRoleUserService;
import com.jackmouse.basicsystem.vo.SysUserVO;
import com.jackmouse.common.model.PageResult;
import com.jackmouse.common.model.Result;
import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.jackmouse.basicsystem.entity.SysUser;
import com.jackmouse.basicsystem.service.SysUserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.List;

/**
 *  控制层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-19
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleUserService sysRoleUserService;

    /**
     * 添加。
     *
     * @param sysUser 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody SysUser sysUser) {
        sysUser.setPassword("123456");
        return Result.succeed(sysUserService.save(sysUser));
    }

    /**
     * 绑定角色用户
     *
     * @param sysMenu 角色对应的菜单
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("assignUser")
    public Result<Boolean> assignMenu(@RequestBody SysUserDTO sysMenu) {
        return Result.succeed(sysRoleUserService.assignUser(sysMenu));
    }

    /**
     * 删除绑定角色用户
     *
     * @param sysMenu 角色对应的菜单
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @DeleteMapping("deleteRoleUser")
    public Result<Boolean> deleteRoleUser(@RequestBody SysUserDTO sysMenu) {
        return Result.succeed(sysRoleUserService.deleteRoleUser(sysMenu));
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public Result<Boolean> remove(@PathVariable("id") Long id) {
        return Result.succeed(sysUserService.removeById(id));
    }

    /**
     * 根据主键更新。
     *
     * @param sysUser 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody SysUser sysUser) {
        return Result.succeed(sysUserService.updateById(sysUser));
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public Result<List<SysUser>> list(HttpServletRequest request) {

        return Result.succeed(sysUserService.list());
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public Result<SysUser> getInfo(@PathVariable("id") Long id) {
        return Result.succeed(sysUserService.getById(id));
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public PageResult<SysUser> page(Page<SysUser> page) {
        return PageResult.succeed(sysUserService.page(page));
    }

    /**
     * 获取未分配角色userid 的用户列表
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("getUnAssignUser")
    public PageResult<SysUser> getUnAssignUser(Page<SysUser> page, UserSearchDTO user) {
        return PageResult.succeed(sysUserService.getUnAssignUser(page, user));
    }

    /**
     * 获取已分配角色userid 的用户列表
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("getAssignUser")
    public PageResult<SysUser> getAssignUser(Page<SysUser> page, UserSearchDTO user) {
        return PageResult.succeed(sysUserService.getAssignUser(page, user));
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return 用户信息
     */
    @GetMapping("current")
    public Result<SysUserVO> current() {
        return Result.succeed(sysUserService.getCurrentUserInfo());
    }

}
