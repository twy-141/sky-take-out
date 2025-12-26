package com.sky.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "菜品启售停售参数")
public class DishStatusDTO {
    
    @ApiModelProperty(value = "状态 0表示禁用 1表示启用")
    private Integer status;

    @ApiModelProperty(value = "菜品ID")
    private Long id;
}
