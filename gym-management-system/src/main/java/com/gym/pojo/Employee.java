package com.gym.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("employee")
public class Employee {

    @TableId(value = "employee_account", type = IdType.INPUT)
    private Integer employeeAccount;
    private String employeeName;
    private String employeeGender;
    private Integer employeeAge;
    private String entryTime;
    private String staff;
    private String employeeMessage;
}
