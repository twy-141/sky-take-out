package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.DishFlavor;

@Mapper
public interface DishFlavorMapper {
    /**
     * 批量新增菜品口味
     * @param dishFlavorList
     */
    void insertBatch(List<DishFlavor> dishFlavorList);

    /**
     * 根据菜品id批量删除菜品口味
     * @param ids 菜品id列表
     */
    void deleteBatchByDishIds(List<Long> ids);
}
