package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.sky.context.BaseContext;
import com.sky.dto.dish.DishDTO;
import com.sky.dto.dish.DishPageQueryDTO;
import com.sky.dto.category.DishStatusDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.BaseException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishMapper dishMapper;
    
    @Autowired
    DishFlavorMapper dishFlavorMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分页查询菜品
     */
    public PageResult<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        List<DishVO> dishVOList = dishMapper.pageQuery(dishPageQueryDTO);
        Page<DishVO> page = (Page<DishVO>) dishVOList;
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    /**
     * 修改菜品
     */
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 根据ID查询菜品
     */
    public Dish getById(Long id) {
        Dish dish = dishMapper.selectById(id);
        if (dish == null) {
            throw new BaseException("菜品不存在");
        }
        return dish;
    }

    /**
     * 根据分类ID查询菜品
     */
    public List<DishDTO> selectByCategoryId(Long categoryId) {
        return dishMapper.selectByCategoryId(categoryId);
    }

    /**
     * 菜品启售停售
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(DishStatusDTO dishStatusDTO) {
        // 校验菜品是否存在
        Dish existDish = dishMapper.selectById(dishStatusDTO.getId());
        if (existDish == null) {
            throw new BaseException("菜品不存在");
        }
        existDish.setStatus(dishStatusDTO.getStatus());
        existDish.setUpdateTime(LocalDateTime.now());
        existDish.setUpdateUser(BaseContext.getCurrentId());
        dishMapper.update(existDish);
    }

    /**
     * 新增菜品
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(DishDTO dishDTO) {
        Category category = categoryMapper.selectById(dishDTO.getCategoryId());
        if (category == null) {
            throw new BaseException("分类不存在");
        }
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dish.setCreateUser(BaseContext.getCurrentId());
        dish.setUpdateUser(BaseContext.getCurrentId());
        dishMapper.insert(dish);
        // 新增菜品口味
        List<DishFlavor> dishFlavorList = dishDTO.getFlavors();
        if (CollectionUtils.isNotEmpty(dishFlavorList)) {
            dishFlavorList.forEach(dishFlavor -> {
                dishFlavor.setDishId(dish.getId());
            });
            dishFlavorMapper.insertBatch(dishFlavorList);
        }
    }
}
