package com.sky.mapper;

import com.sky.dto.dish.DishDTO;
import com.sky.dto.dish.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据id批量查询菜品
     */
    List<Dish> selectBatch(List<Long> ids);

    /**
     * 根据id批量删除菜品
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据ID查询菜品
     */
    @Select("select * from dish where id = #{id}")
    Dish selectById(Long id);

    /**
     * 根据分类ID查询菜品
     */
    List<DishDTO> selectByCategoryId(Long categoryId);

    /**
     * 分页查询菜品
     */
    List<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 更新菜品
     */
    void update(Dish dish);

    /**
     * 新增菜品
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into dish (name, category_id, price, image, description, status, create_time, update_time, create_user, update_user) " +
            "values (#{name}, #{categoryId}, #{price}, #{image}, #{description}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Dish dish);
}
