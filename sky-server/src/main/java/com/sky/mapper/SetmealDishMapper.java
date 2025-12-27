package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id批量查询套餐
     * @param ids 菜品id列表
     * @return 套餐id列表
     */
    List<Long> selectSetmealIdsByDishIds(List<Long> ids);
}
