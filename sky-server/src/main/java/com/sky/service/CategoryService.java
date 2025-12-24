package com.sky.service;

import com.sky.dto.category.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

public interface CategoryService {
    /**
     * 分页查询分类
     */
    PageResult<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
}
