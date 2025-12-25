package com.sky.controller.admin;

import com.sky.dto.category.CategoryPageQueryDTO;
import com.sky.dto.category.CategoryStatusDto;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询分类
     */
    @GetMapping("/page")
    @ApiOperation("分页查询分类")
    public Result<PageResult<Category>> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询分类，参数：{}", categoryPageQueryDTO);
        PageResult<Category> pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用分类
     */
    @PostMapping("/status")
    @ApiOperation("启用禁用分类")
    public Result<Void> enableOrDisable(@RequestBody CategoryStatusDto categoryStatusDto) {
        log.info("启用禁用分类，参数：{}", categoryStatusDto);
        categoryService.enableOrDisable(categoryStatusDto);
        return Result.success();
    }

    /**
     * 根据ID查询分类
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询分类")
    public Result<Category> getById(@PathVariable Long id) {
        log.info("根据ID查询分类，参数：{}", id);
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    /**
     * 根据类型查询分类
     */
    @GetMapping("/type")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> getByType(@RequestParam Integer type) {
        log.info("根据类型查询分类，参数：{}", type);
        List<Category> categoryList = categoryService.getByType(type);
        return Result.success(categoryList);
    }

    /**
     * 修改分类
     */
    @PutMapping
    @ApiOperation("修改分类")
    public Result<Void> update(@RequestBody Category category) {
        log.info("修改分类，参数：{}", category);
        categoryService.update(category);
        return Result.success();
    }

    /**
     * 添加分类
     */
    @PostMapping
    @ApiOperation("添加分类")
    public Result<Void> add(@RequestBody Category category) {
        log.info("添加分类，参数：{}", category);
        categoryService.add(category);
        return Result.success();
    }

    /**
     * 批量删除分类
     */
    @DeleteMapping
    @ApiOperation("批量删除分类")
    public Result<Void> delete(@RequestParam List<Long> ids) {
        log.info("删除分类，参数：{}", ids);
        categoryService.deleteBatch(ids);
        return Result.success();
    }
}
