package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.setmeal.SetmealDTO;
import com.sky.dto.setmeal.SetmealPageQueryDTO;
import com.sky.dto.setmeal.SetmealStatusDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.BaseException;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 新增套餐
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        // 公共字段
        setmeal.setStatus(StatusConstant.DISABLE);
        setmeal.setCreateUser(BaseContext.getCurrentId());
        setmeal.setUpdateUser(BaseContext.getCurrentId());
        setmeal.setCreateTime(LocalDateTime.now());
        setmeal.setUpdateTime(LocalDateTime.now());
        
        // 新增套餐
        setmealMapper.insert(setmeal);

        // 新增套餐菜品关系
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        // 获取套餐id
        Long setmealId = setmeal.getId();
        if(CollectionUtils.isEmpty(setmealDishes)){
            throw new BaseException("套餐菜品不能为空");
        }
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });
        // 插入套餐菜品关系数据
        setmealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * 套餐分页查询
     */
    public PageResult<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        // 分页查询
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        List<SetmealVO> setmealVOList = setmealMapper.pageQuery(setmealPageQueryDTO);
        // 分页信息
        Page<SetmealVO> pageInfo = (Page<SetmealVO>) setmealVOList;
        return new PageResult<>(pageInfo.getTotal(), setmealVOList);
    }

    /**
     * 套餐启用/停用
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(SetmealStatusDTO setmealStatusDTO) {
        Setmeal setmeal = Setmeal.builder()
                .id(setmealStatusDTO.getId())
                .status(setmealStatusDTO.getStatus())
                .updateUser(BaseContext.getCurrentId())
                .updateTime(LocalDateTime.now())
                .build();
        setmealMapper.update(setmeal);
    }

    /**
     * 根据id查询套餐
     */
    public SetmealVO getById(Long id) {
        SetmealVO setmealVO = setmealMapper.selectById(id);
        if(setmealVO == null){
            throw new BaseException("套餐不存在");
        }
        return setmealVO;
    }

    /**
     * 修改套餐
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        // 公共字段
        setmeal.setUpdateUser(BaseContext.getCurrentId());
        setmeal.setUpdateTime(LocalDateTime.now());
        
        // 更新套餐
        setmealMapper.update(setmeal);

        // 更新套餐菜品关系
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if(CollectionUtils.isEmpty(setmealDishes)){
            throw new BaseException("套餐菜品不能为空");
        }
        // 获取套餐id
        Long setmealId = setmeal.getId();
        // 设置套餐id
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });
        // 删除原有的套餐菜品关系数据
        setmealDishMapper.deleteBySetmealId(setmealId);
        // 插入新的套餐菜品关系数据
        setmealDishMapper.insertBatch(setmealDishes);
    }

    /**
     * 批量删除套餐
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)){
            throw new BaseException("套餐id不能为空");
        }
        // 检查套餐是否存在
        List<Setmeal> setmealList = setmealMapper.selectBatch(ids);
        if(CollectionUtils.isEmpty(setmealList)){
            throw new BaseException("套餐不存在");
        }

        for(Setmeal setmeal : setmealList){
            if(setmeal.getStatus() == StatusConstant.ENABLE){
                throw new BaseException(MessageConstant.SETMEAL_ON_SALE);
            }
        }

        // 删除套餐菜品关系数据
        setmealDishMapper.deleteBySetmealIds(ids);
        // 删除套餐数据
        setmealMapper.deleteBatch(ids);
    }
}
