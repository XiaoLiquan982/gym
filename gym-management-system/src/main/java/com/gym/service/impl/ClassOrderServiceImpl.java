package com.gym.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gym.mapper.ClassOrderMapper;
import com.gym.pojo.ClassOrder;
import com.gym.service.ClassOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassOrderServiceImpl implements ClassOrderService {

    private final ClassOrderMapper classOrderMapper;

    public ClassOrderServiceImpl(ClassOrderMapper classOrderMapper) {
        this.classOrderMapper = classOrderMapper;
    }

    @Override
    public List<ClassOrder> findAll() {
        return classOrderMapper.selectList(null);
    }

    @Override
    public Boolean insertClassOrder(ClassOrder classOrder) {
        return classOrderMapper.insert(classOrder) > 0;
    }

    @Override
    public List<ClassOrder> selectClassOrderByMemberAccount(Integer memberAccount) {
        return classOrderMapper.selectList(Wrappers.<ClassOrder>lambdaQuery()
                .eq(ClassOrder::getMemberAccount, memberAccount));
    }

    @Override
    public Boolean deleteByClassOrderId(Integer classOrderId) {
        return classOrderMapper.deleteById(classOrderId) > 0;
    }

    @Override
    public int deleteByClassOrderIdAndMemberAccount(Integer classOrderId, Integer memberAccount) {
        return classOrderMapper.delete(Wrappers.<ClassOrder>lambdaQuery()
                .eq(ClassOrder::getClassOrderId, classOrderId)
                .eq(ClassOrder::getMemberAccount, memberAccount));
    }

    @Override
    public ClassOrder selectMemberByClassIdAndMemberAccount(Integer classId, Integer memberAccount) {
        return classOrderMapper.selectOne(Wrappers.<ClassOrder>lambdaQuery()
                .eq(ClassOrder::getClassId, classId)
                .eq(ClassOrder::getMemberAccount, memberAccount));
    }

    @Override
    public List<ClassOrder> selectMemberOrderList(Integer classId) {
        return classOrderMapper.selectList(Wrappers.<ClassOrder>lambdaQuery()
                .eq(ClassOrder::getClassId, classId));
    }
}
