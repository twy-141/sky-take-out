package com.sky.service;

import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

public interface DishService {
    /**
     * 分页查询菜品
     */
    PageResult<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);
}
