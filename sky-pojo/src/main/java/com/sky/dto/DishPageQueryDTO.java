package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "菜品分页查询参数")
public class DishPageQueryDTO implements Serializable {

    @ApiModelProperty("页码")
    private int page = 1;

    @ApiModelProperty("每页记录数")
    private int pageSize = 10;

    @ApiModelProperty("菜品名称")
    private String name;

    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("状态 0表示禁用 1表示启用")
    private Integer status;

}
