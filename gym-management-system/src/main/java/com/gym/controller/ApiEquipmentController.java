package com.gym.controller;

import com.gym.common.ApiResponse;
import com.gym.pojo.Equipment;
import com.gym.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class ApiEquipmentController {

    private final EquipmentService equipmentService;

    public ApiEquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/selEquipment")
    public ApiResponse selectEquipment() {
        List<Equipment> equipmentList = equipmentService.findAll();
        return ApiResponse.ok().add("equipmentList", equipmentList);
    }

    @PostMapping("/delEquipment")
    public ResponseEntity<ApiResponse> deleteEquipment(Integer equipmentId) {
        equipmentService.deleteByEquipmentId(equipmentId);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @GetMapping("/toUpdateEquipment")
    public ApiResponse toUpdateEquipment(Integer equipmentId) {
        List<Equipment> equipmentList = equipmentService.selectByEquipmentId(equipmentId);
        return ApiResponse.ok().add("equipmentList", equipmentList);
    }

    @GetMapping("/toAddEquipment")
    public ApiResponse toAddEquipment() {
        return ApiResponse.ok();
    }

    @PostMapping("/updateEquipment")
    public ResponseEntity<ApiResponse> updateEquipment(Equipment equipment) {
        equipmentService.updateEquipmentByEquipmentId(equipment);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PostMapping("/addEquipment")
    public ResponseEntity<ApiResponse> addEquipment(Equipment equipment) {
        equipmentService.insertEquipment(equipment);
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
