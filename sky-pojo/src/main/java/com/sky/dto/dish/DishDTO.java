package com.sky.dto.dish;

import com.sky.entity.DishFlavor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "菜品数据传输对象")
public class DishDTO implements Serializable {

    @ApiModelProperty(value = "菜品ID")
    private Long id;

    @ApiModelProperty(value = "菜品名称")
    private String name;

    @ApiModelProperty(value = "菜品分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "菜品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "菜品图片")
    private String image;

    @ApiModelProperty(value = "菜品描述信息")
    private String description;

    @ApiModelProperty(value = "菜品状态（0停售 1起售）")
    private Integer status;
    
    @ApiModelProperty(value = "菜品口味列表")
    private List<DishFlavor> flavors = new ArrayList<>();

}
