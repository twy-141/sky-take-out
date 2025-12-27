package com.sky.service;

import java.util.List;

import com.sky.dto.dish.DishDTO;
import com.sky.dto.dish.DishPageQueryDTO;
import com.sky.dto.category.DishStatusDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

public interface DishService {
    /**
     * 分页查询菜品
     */
    PageResult<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 修改菜品
     */
    void update(DishDTO dishDTO);

    /**
     * 根据ID查询菜品
     */
    DishVO getById(Long id);

    /**
     * 根据分类ID查询菜品
     */
    List<DishVO> selectByCategoryId(Long categoryId);

    /**
     * 菜品启售停售
     */
    void updateStatus(DishStatusDTO dishStatusDTO);

    /**
     * 新增菜品
     */
    void save(DishDTO dishDTO);

    /**
     * 批量删除菜品
     */
    void deleteBatch(List<Long> ids);
}
