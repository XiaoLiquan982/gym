package com.gym.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gym.mapper.EmployeeMapper;
import com.gym.pojo.Employee;
import com.gym.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.selectList(null);
    }

    @Override
    public Boolean deleteByEmployeeAccount(Integer employeeAccount) {
        return employeeMapper.deleteById(employeeAccount) > 0;
    }

    @Override
    public Boolean insertEmployee(Employee employee) {
        return employeeMapper.insert(employee) > 0;
    }

    @Override
    public Boolean updateMemberByEmployeeAccount(Employee employee) {
        return employeeMapper.updateById(employee) > 0;
    }

    @Override
    public List<Employee> selectByEmployeeAccount(Integer employeeAccount) {
        return employeeMapper.selectList(Wrappers.<Employee>lambdaQuery()
                .eq(Employee::getEmployeeAccount, employeeAccount));
    }

    @Override
    public Integer selectTotalCount() {
        return Math.toIntExact(employeeMapper.selectCount(null));
    }
}
