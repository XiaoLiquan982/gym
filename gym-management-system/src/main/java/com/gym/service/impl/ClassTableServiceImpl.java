package com.gym.service.impl;

import com.gym.mapper.ClassOrderMapper;
import com.gym.mapper.ClassTableMapper;
import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.service.ClassTableService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassTableServiceImpl implements ClassTableService {

    private final ClassTableMapper classTableMapper;
    private final ClassOrderMapper classOrderMapper;

    public ClassTableServiceImpl(ClassTableMapper classTableMapper, ClassOrderMapper classOrderMapper) {
        this.classTableMapper = classTableMapper;
        this.classOrderMapper = classOrderMapper;
    }

    @Override
    public List<ClassTable> findAll() {
        return classTableMapper.selectList(null);
    }

    @Override
    public Boolean deleteClassByClassId(Integer classId) {
        return classTableMapper.deleteById(classId) > 0;
    }

    @Override
    public Boolean insertClass(ClassTable classTable) {
        return classTableMapper.insert(classTable) > 0;
    }

    @Override
    public ClassTable selectByClassId(Integer classId) {
        return classTableMapper.selectById(classId);
    }

    @Override
    public Boolean deleteOrderByClassId(Integer classId) {
        return classOrderMapper.deleteByMap(java.util.Map.of("class_id", classId)) >= 0;
    }
}
