package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sky.dto.setmeal.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.SetmealVO;

@Mapper
public interface SetmealMapper {

    /**
     * 新增套餐
     *
     * @param setmeal
     */
    void insert(Setmeal setmeal);

    /**
     * 套餐分页查询
     *
     * @param setmealPageQueryDTO
     * @return 套餐分页查询结果
     */
    List<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 更新套餐
     *
     * @param setmeal
     */
    void update(Setmeal setmeal);

    /**
     * 根据id查询套餐
     *
     * @param id 套餐id
     * @return 套餐详情
     */
    SetmealVO selectById(Long id);

    /**
     * 批量查询
     * 
     * @param ids 套餐id列表
     * @return 套餐列表
     */
    List<Setmeal> selectBatch(List<Long> ids);


    /**
     * 批量删除套餐
     * 
     * @param ids 套餐id列表
     */
    void deleteBatch(List<Long> ids);
}
