package com.sky.dto.setmeal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "套餐启用/停用DTO")
public class SetmealStatusDTO {
    
    @ApiModelProperty(value = "套餐状态", required = true)
    private Integer status;

    @ApiModelProperty(value = "套餐ID", required = true)
    private Long id;
}
