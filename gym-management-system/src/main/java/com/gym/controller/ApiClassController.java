package com.gym.controller;

import com.gym.common.ApiResponse;
import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.service.ClassOrderService;
import com.gym.service.ClassTableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class ApiClassController {

    private final ClassTableService classTableService;
    private final ClassOrderService classOrderService;

    public ApiClassController(ClassTableService classTableService, ClassOrderService classOrderService) {
        this.classTableService = classTableService;
        this.classOrderService = classOrderService;
    }

    @GetMapping("/selClass")
    public ApiResponse selectClass() {
        List<ClassTable> classList = classTableService.findAll();
        return ApiResponse.ok().add("classList", classList);
    }

    @GetMapping("/selClassOrder")
    public ApiResponse selectClassOrder(Integer classId) {
        List<ClassOrder> classOrderList = classOrderService.selectMemberOrderList(classId);
        return ApiResponse.ok().add("classOrderList", classOrderList);
    }

    @GetMapping("/toAddClass")
    public ApiResponse toAddClass() {
        return ApiResponse.ok();
    }

    @PostMapping("/addClass")
    public ResponseEntity<ApiResponse> addClass(ClassTable classTable) {
        classTableService.insertClass(classTable);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PostMapping("/delClass")
    public ResponseEntity<ApiResponse> deleteClass(Integer classId) {
        classTableService.deleteClassByClassId(classId);
        classTableService.deleteOrderByClassId(classId);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
