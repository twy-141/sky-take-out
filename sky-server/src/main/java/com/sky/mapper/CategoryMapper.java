package com.sky.mapper;

import com.sky.dto.category.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 分页查询分类
     */
    List<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 更新分类状态
     */
    void update(Category category);

    /**
     * 根据ID查询分类
     */
    @Select("select * from category where id = #{id}")
    Category selectById(Long id);

    /**
     * 根据类型查询分类
     */
    @Select("select * from category where type = #{type} order by sort")
    List<Category> getByType(Integer type);

    /**
     * 添加分类
     */
    void insert(Category category);

    /**
     * 批量删除分类
     */
    void deleteBatch(List<Long> ids);
}
