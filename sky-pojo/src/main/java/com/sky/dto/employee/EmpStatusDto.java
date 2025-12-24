package com.sky.dto.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "启用禁用员工账号请求参数")
public class EmpStatusDto {
    @ApiModelProperty(value = "员工状态", required = true)
    private Integer status;
    @ApiModelProperty(value = "员工ID", required = true)
    private Long id;
}
