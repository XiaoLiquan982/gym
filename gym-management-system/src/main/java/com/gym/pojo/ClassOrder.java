package com.gym.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("class_order")
public class ClassOrder {

    @TableId(value = "class_order_id", type = IdType.AUTO)
    private Integer classOrderId;
    private Integer classId;
    private String className;
    private String coach;
    private String memberName;
    private Integer memberAccount;
    private String classBegin;

    public ClassOrder(Integer classId, String className, String coach, String memberName, Integer memberAccount, String classBegin) {
        this.classId = classId;
        this.className = className;
        this.coach = coach;
        this.memberName = memberName;
        this.memberAccount = memberAccount;
        this.classBegin = classBegin;
    }
}
