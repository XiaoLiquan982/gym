package com.gym.controller;

import com.gym.common.ApiResponse;
import com.gym.pojo.Member;
import com.gym.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/member")
public class ApiMemberController {

    private final MemberService memberService;

    public ApiMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/selMember")
    public ApiResponse selectMember() {
        List<Member> memberList = memberService.findAll();
        return ApiResponse.ok().add("memberList", memberList);
    }

    @GetMapping("/toAddMember")
    public ApiResponse toAddMember() {
        return ApiResponse.ok();
    }

    @PostMapping("/addMember")
    public ResponseEntity<ApiResponse> addMember(Member member) {
        Random random = new Random();
        String account1 = "2021";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);

        String password = "123456";
        String nowDay = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Integer nextClass = member.getCardClass();

        member.setMemberAccount(account);
        member.setMemberPassword(password);
        member.setCardTime(nowDay);
        member.setCardNextClass(nextClass);

        memberService.insertMember(member);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PostMapping("/delMember")
    public ResponseEntity<ApiResponse> deleteMember(Integer memberAccount) {
        memberService.deleteByMemberAccount(memberAccount);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @GetMapping("/toUpdateMember")
    public ApiResponse toUpdateMember(Integer memberAccount) {
        List<Member> memberList = memberService.selectByMemberAccount(memberAccount);
        return ApiResponse.ok().add("memberList", memberList);
    }

    @PostMapping("/updateMember")
    public ResponseEntity<ApiResponse> updateMember(Member member) {
        memberService.updateMemberByMemberAccount(member);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @GetMapping("/toSelByCard")
    public ApiResponse toSelByCard() {
        return ApiResponse.ok();
    }

    @PostMapping("/selByCard")
    public ApiResponse selectByCardId(Integer memberAccount) {
        List<Member> memberList = memberService.selectByMemberAccount(memberAccount);
        ApiResponse response = ApiResponse.ok();
        if (memberList != null) {
            response.add("memberList", memberList);
        } else {
            response.add("noMessage", "会员卡号不存在");
        }
        return response;
    }
}
