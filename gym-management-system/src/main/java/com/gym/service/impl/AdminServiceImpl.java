package com.gym.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gym.mapper.AdminMapper;
import com.gym.pojo.Admin;
import com.gym.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin adminLogin(Admin admin) {
        return adminMapper.selectOne(Wrappers.<Admin>lambdaQuery()
                .eq(Admin::getAdminAccount, admin.getAdminAccount())
                .eq(Admin::getAdminPassword, admin.getAdminPassword()));
    }
}
