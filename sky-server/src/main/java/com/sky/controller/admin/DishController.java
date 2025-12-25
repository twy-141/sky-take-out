package com.sky.controller.admin;

import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    DishService dishService;

    /**
     * 分页查询菜品
     */
     @GetMapping("/page")
     @ApiOperation("分页查询菜品")
     public Result<PageResult<DishVO>> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
         PageResult<DishVO> pageResult = dishService.pageQuery(dishPageQueryDTO);
         return Result.success(pageResult);
     }
}
