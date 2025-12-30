package com.sky.controller.admin;

import com.sky.dto.dish.DishDTO;
import com.sky.dto.dish.DishPageQueryDTO;
import com.sky.dto.category.DishStatusDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    DishService dishService;

    /**
     * 分页查询菜品
     */
    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public Result<PageResult<DishVO>> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageResult<DishVO> pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据ID查询菜品
     */
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        DishVO dish = dishService.getById(id);
        return Result.success(dish);
    }

    /**
     * 根据分类ID查询菜品
     */
    @GetMapping("/categoryList")
    @ApiOperation("根据分类ID查询菜品")
    public Result<List<DishVO>> getByCategoryId(Long categoryId) {
        List<DishVO> dishList = dishService.selectByCategoryId(categoryId);
        return Result.success(dishList);
    }

    /**
     * 修改菜品
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        dishService.update(dishDTO);
        return Result.success();
    }

    /**
     * 菜品启售停售
     */
    @PutMapping("/status")
    @ApiOperation("菜品启售停售")
    public Result updateStatus(@RequestBody DishStatusDTO dishStatusDTO) {
        log.info("菜品启售停售，dishStatusDTO={}", dishStatusDTO);
        dishService.updateStatus(dishStatusDTO);
        return Result.success();
    }

    /**
     * 新增菜品
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        dishService.save(dishDTO);
        return Result.success();
    }

    /**
     * 批量删除菜品
     */
    @DeleteMapping
    @ApiOperation("批量删除菜品")
    public Result deleteBatch(@RequestParam List<Long> ids) {
        log.info("批量删除菜品，ids={}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }
}
