package com.gym.controller;

import com.gym.common.ApiResponse;
import com.gym.common.ResultCode;
import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.pojo.Member;
import com.gym.service.ClassOrderService;
import com.gym.service.ClassTableService;
import com.gym.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    private final ClassTableService classTableService;
    private final MemberService memberService;
    private final ClassOrderService classOrderService;

    public ApiUserController(
            ClassTableService classTableService,
            MemberService memberService,
            ClassOrderService classOrderService) {
        this.classTableService = classTableService;
        this.memberService = memberService;
        this.classOrderService = classOrderService;
    }

    @GetMapping("/toUserInfo")
    public ApiResponse toUserInfo(HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        return ApiResponse.ok().add("member", member);
    }

    @GetMapping("/toUpdateInfo")
    public ApiResponse toUpdateInfo(HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        return ApiResponse.ok().add("member", member);
    }

    @PostMapping("/updateInfo")
    public ResponseEntity<ApiResponse> updateUserInformation(HttpSession session, Member member) {
        Member member1 = (Member) session.getAttribute("user");
        if (member1 != null && member != null) {
            member.setMemberAccount(member1.getMemberAccount());
            member.setCardClass(member1.getCardClass());
            member.setCardTime(member1.getCardTime());
            member.setCardNextClass(member1.getCardNextClass());

            memberService.updateMemberByMemberAccount(member);
            session.setAttribute("user", member);
        }

        return ResponseEntity.ok(ApiResponse.ok());
    }

    @GetMapping("/toUserClass")
    public ApiResponse toUserClass(HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        Integer memberAccount = member == null ? null : member.getMemberAccount();
        List<ClassOrder> classOrderList = classOrderService.selectClassOrderByMemberAccount(memberAccount);

        return ApiResponse.ok()
                .add("member", member)
                .add("classOrderList", classOrderList);
    }

    @PostMapping("/delUserClass")
    public ResponseEntity<ApiResponse> deleteUserClass(Integer classOrderId, HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        Integer memberAccount = member == null ? null : member.getMemberAccount();
        if (memberAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.fail(ResultCode.UNAUTHORIZED, "未登录或会话已失效"));
        }

        int deleted = classOrderService.deleteByClassOrderIdAndMemberAccount(classOrderId, memberAccount);
        if (deleted > 0) {
            return ResponseEntity.ok(ApiResponse.ok());
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.fail(ResultCode.FORBIDDEN, "课程不存在，或不属于当前用户"));
    }

    @GetMapping("/toApplyClass")
    public ApiResponse toUserApplyClass(HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        List<ClassTable> classList = classTableService.findAll();

        return ApiResponse.ok()
                .add("member", member)
                .add("classList", classList);
    }

    @PostMapping("/applyClass")
    public ResponseEntity<ApiResponse> userApplyClass(Integer classId, HttpSession session) {
        ClassTable classTable = classTableService.selectByClassId(classId);
        Member member = (Member) session.getAttribute("user");

        if (classTable == null || member == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.fail(ResultCode.BAD_REQUEST, "会话已失效或课程不存在"));
        }

        String className = classTable.getClassName();
        String coach = classTable.getCoach();
        String classBegin = classTable.getClassBegin();
        String memberName = member.getMemberName();
        Integer memberAccount = member.getMemberAccount();

        ClassOrder classOrder = new ClassOrder(classId, className, coach, memberName, memberAccount, classBegin);
        ClassOrder existingOrder = classOrderService.selectMemberByClassIdAndMemberAccount(classId, memberAccount);
        if (existingOrder == null) {
            classOrderService.insertClassOrder(classOrder);
        }

        return ResponseEntity.ok(ApiResponse.ok());
    }
}
