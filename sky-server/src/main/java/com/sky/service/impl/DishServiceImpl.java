package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.exception.BaseException;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishMapper dishMapper;

    /**
     * 分页查询菜品
     */
    public PageResult<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        List<DishVO> dishVOList = dishMapper.pageQuery(dishPageQueryDTO);
        Page< DishVO> page = (Page< DishVO>) dishVOList;
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    /**
     * 修改菜品
     */
    public void update(Dish dish) {
        // 校验菜品是否存在
        Dish existDish = dishMapper.selectById(dish.getId());
        if (existDish == null) {
            throw new BaseException("菜品不存在");
        }
        dish.setUpdateTime(LocalDateTime.now());
        dish.setUpdateUser(BaseContext.getCurrentId());
        dishMapper.update(dish);
    }
}
