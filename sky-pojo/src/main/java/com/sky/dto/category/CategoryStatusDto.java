package com.sky.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "分类状态DTO")
public class CategoryStatusDto {

    @ApiModelProperty(value = "分类状态", required = true)
    private Integer status;

    @ApiModelProperty(value = "分类ID", required = true)
    private Long id;
}
