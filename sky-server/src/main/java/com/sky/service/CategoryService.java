package com.sky.service;

import com.sky.dto.category.CategoryPageQueryDTO;
import com.sky.dto.category.CategoryStatusDto;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    /**
     * 分页查询分类
     */
    PageResult<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 启用禁用分类
     */
    void enableOrDisable(CategoryStatusDto categoryStatusDto);

    /**
     * 根据ID查询分类
     */
    Category getById(Long id);

    /**
     * 根据类型查询分类
     */
    List<Category> getByType(Integer type);

    /**
     * 修改分类
     */
    void update(Category category);

    /**
     * 添加分类
     */
    void add(Category category);

    /**
     * 批量删除分类
     */
    void deleteBatch(List<Long> ids);
}
