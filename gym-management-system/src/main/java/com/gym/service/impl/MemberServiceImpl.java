package com.gym.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gym.mapper.MemberMapper;
import com.gym.pojo.Member;
import com.gym.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.selectList(null);
    }

    @Override
    public Boolean insertMember(Member member) {
        return memberMapper.insert(member) > 0;
    }

    @Override
    public Boolean updateMemberByMemberAccount(Member member) {
        return memberMapper.updateById(member) > 0;
    }

    @Override
    public Member userLogin(Member member) {
        return memberMapper.selectOne(Wrappers.<Member>lambdaQuery()
                .eq(Member::getMemberAccount, member.getMemberAccount())
                .eq(Member::getMemberPassword, member.getMemberPassword()));
    }

    @Override
    public Boolean deleteByMemberAccount(Integer memberAccount) {
        return memberMapper.deleteById(memberAccount) > 0;
    }

    @Override
    public Integer selectTotalCount() {
        return Math.toIntExact(memberMapper.selectCount(null));
    }

    @Override
    public List<Member> selectByMemberAccount(Integer memberAccount) {
        return memberMapper.selectList(Wrappers.<Member>lambdaQuery()
                .eq(Member::getMemberAccount, memberAccount));
    }
}
