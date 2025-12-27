package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.category.CategoryPageQueryDTO;
import com.sky.dto.category.CategoryStatusDto;
import com.sky.vo.DishVO;
import com.sky.exception.BaseException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    /**
     * 分页查询分类
     */
    public PageResult<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        // 分页查询分类
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        List<Category> categoryList = categoryMapper.pageQuery(categoryPageQueryDTO);
        // 分页信息
        Page<Category> page = (Page<Category>) categoryList;
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    /**
     * 根据ID查询分类
     */
    public Category getById(Long id) {
        return categoryMapper.selectById(id);
    }

    /**
     * 启用禁用分类
     */
    @Transactional(rollbackFor = Exception.class)
    public void enableOrDisable(CategoryStatusDto categoryStatusDto) {
        Category category = getById(categoryStatusDto.getId());
        if (category == null) {
            throw new BaseException("分类不存在");
        }
        Category categoryToUpdate = new Category();
        categoryToUpdate.setId(categoryStatusDto.getId());
        categoryToUpdate.setStatus(categoryStatusDto.getStatus());
        categoryToUpdate.setUpdateTime(LocalDateTime.now());
        categoryToUpdate.setUpdateUser(BaseContext.getCurrentId());
        // 更新分类状态
        categoryMapper.update(categoryToUpdate);
    }

    /**
     * 根据类型查询分类
     */
    public List<Category> getByType(Integer type) {
        return categoryMapper.getByType(type);
    }

    /**
     * 修改分类
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(Category category) {
        // 检查分类是否存在
        Category existingCategory = getById(category.getId());
        if (existingCategory == null) {
            throw new BaseException("分类不存在");
        }
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        // 更新分类
        categoryMapper.update(category);
    }

    /**
     * 添加分类
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        // 添加分类
        categoryMapper.insert(category);
    }

    /**
     * 批量删除分类
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Long> ids) {
        for (Long id : ids) {
            Category existingCategory = getById(id);
            if (existingCategory == null) {
                throw new BaseException("分类不存在");
            }
            List<DishVO> existingDishes = dishMapper.selectByCategoryId(id);
            if (!existingDishes.isEmpty()) {
                throw new BaseException("分类关联了菜品或套餐，不能删除");
            }
        }
        // 批量删除分类
        categoryMapper.deleteBatch(ids);
    }
}
