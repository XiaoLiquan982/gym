package com.gym.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gym.mapper.EquipmentMapper;
import com.gym.pojo.Equipment;
import com.gym.service.EquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentMapper equipmentMapper;

    public EquipmentServiceImpl(EquipmentMapper equipmentMapper) {
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentMapper.selectList(null);
    }

    @Override
    public Boolean deleteByEquipmentId(Integer equipmentId) {
        return equipmentMapper.deleteById(equipmentId) > 0;
    }

    @Override
    public Boolean insertEquipment(Equipment equipment) {
        return equipmentMapper.insert(equipment) > 0;
    }

    @Override
    public Boolean updateEquipmentByEquipmentId(Equipment equipment) {
        return equipmentMapper.updateById(equipment) > 0;
    }

    @Override
    public List<Equipment> selectByEquipmentId(Integer equipmentId) {
        return equipmentMapper.selectList(Wrappers.<Equipment>lambdaQuery()
                .eq(Equipment::getEquipmentId, equipmentId));
    }

    @Override
    public Integer selectTotalCount() {
        return Math.toIntExact(equipmentMapper.selectCount(null));
    }
}
