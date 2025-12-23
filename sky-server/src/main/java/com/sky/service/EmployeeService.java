package com.sky.service;

import com.sky.dto.employee.EmployeeDTO;
import com.sky.dto.employee.EmployeeLoginDTO;
import com.sky.dto.employee.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     */
    void save(EmployeeDTO employeeDTO);
     /**
     * 分页查询员工
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);
}
