package com.sky.service;

import java.util.List;

import com.sky.dto.setmeal.SetmealDTO;
import com.sky.dto.setmeal.SetmealPageQueryDTO;
import com.sky.dto.setmeal.SetmealStatusDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

public interface SetmealService {
    /**
     * 新增套餐
     */
    void save(SetmealDTO setmealDTO);

    /**
     * 套餐分页查询
     */
    PageResult<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 套餐启用/停用
     */
    void updateStatus(SetmealStatusDTO setmealStatusDTO);

    /**
     * 根据id查询套餐
     */
    SetmealVO getById(Long id);

    /**
     * 修改套餐
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 批量删除套餐
     */
    void deleteBatch(List<Long> ids);
}
