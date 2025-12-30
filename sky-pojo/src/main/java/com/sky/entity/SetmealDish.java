package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 套餐菜品关系
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("套餐菜品关系")
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("套餐id")
    private Long setmealId;

    @ApiModelProperty("菜品id")
    private Long dishId;

    @ApiModelProperty("菜品名称 （冗余字段）")
    private String name;

    @ApiModelProperty("菜品原价")
    private BigDecimal price;

    @ApiModelProperty("份数")
    private Integer copies;
}
