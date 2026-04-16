package com.gym.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("class_table")
public class ClassTable {

    @TableId(value = "class_id", type = IdType.INPUT)
    private Integer classId;
    private String className;
    private String classBegin;
    private String classTime;
    private String coach;
}
