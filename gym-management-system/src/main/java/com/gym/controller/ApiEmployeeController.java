package com.gym.controller;

import com.gym.common.ApiResponse;
import com.gym.pojo.Employee;
import com.gym.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/employee")
public class ApiEmployeeController {

    private final EmployeeService employeeService;

    public ApiEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/selEmployee")
    public ApiResponse selectEmployee() {
        List<Employee> employeeList = employeeService.findAll();
        return ApiResponse.ok().add("employeeList", employeeList);
    }

    @GetMapping("/toAddEmployee")
    public ApiResponse toAddEmployee() {
        return ApiResponse.ok();
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<ApiResponse> addEmployee(Employee employee) {
        Random random = new Random();
        String account1 = "1010";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);
        String nowDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        employee.setEmployeeAccount(account);
        employee.setEntryTime(nowDay);
        employeeService.insertEmployee(employee);

        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PostMapping("/delEmployee")
    public ResponseEntity<ApiResponse> deleteEmployee(Integer employeeAccount) {
        employeeService.deleteByEmployeeAccount(employeeAccount);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @GetMapping("/toUpdateEmployee")
    public ApiResponse toUpdateEmployee(Integer employeeAccount) {
        List<Employee> employeeList = employeeService.selectByEmployeeAccount(employeeAccount);
        return ApiResponse.ok().add("employeeList", employeeList);
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<ApiResponse> updateEmployee(Employee employee) {
        employeeService.updateMemberByEmployeeAccount(employee);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
