package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.SetmealDish;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id批量查询套餐
     * @param ids 菜品id列表
     * @return 套餐id列表
     */
    List<Long> selectSetmealIdsByDishIds(List<Long> ids);

    /**
     * 批量新增套餐菜品
     * @param setmealDishes 套餐菜品列表
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id删除套餐菜品关系
     * @param setmealId 套餐id
     */
    void deleteBySetmealId(Long setmealId);

    /**
     * 根据套餐id批量删除套餐菜品关系
     * @param setmealIds 套餐id列表
     */
    void deleteBySetmealIds(List<Long> setmealIds);
}
