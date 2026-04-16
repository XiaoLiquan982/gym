package com.gym.controller;

import com.gym.common.ApiResponse;
import com.gym.common.ResultCode;
import com.gym.pojo.ClassOrder;
import com.gym.pojo.Member;
import com.gym.service.ChatService;
import com.gym.service.ClassOrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
public class ApiChatController {

    private final ChatService chatService;
    private final ClassOrderService classOrderService;

    public ApiChatController(ChatService chatService, ClassOrderService classOrderService) {
        this.chatService = chatService;
        this.classOrderService = classOrderService;
    }

    @PostMapping("/query")
    public ResponseEntity<ApiResponse> query(
            @RequestParam("content") String content,
            @RequestParam(required = false) String model,
            HttpSession session
    ) {
        try {
            Member member = (Member) session.getAttribute("user");
            String enhancedContent = buildContentWithMemberClasses(content, member);
            String reply = chatService.queryChat(enhancedContent, model);
            return ResponseEntity.ok(ApiResponse.ok().add("reply", reply));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.fail(ResultCode.INTERNAL_ERROR, e.getMessage()));
        }
    }

    private String buildContentWithMemberClasses(String originalContent, Member member) {
        if (member == null || member.getMemberAccount() == null) {
            return originalContent;
        }

        List<ClassOrder> classOrders = classOrderService.selectClassOrderByMemberAccount(member.getMemberAccount());
        if (classOrders == null || classOrders.isEmpty()) {
            return originalContent;
        }

        String classInfo = classOrders.stream()
                .map(order -> String.format(
                        "课程名称：%s，教练：%s，上课时间：%s",
                        order.getClassName(),
                        order.getCoach(),
                        order.getClassBegin()
                ))
                .collect(Collectors.joining("; "));

        String memberInfo = String.format("会员姓名：%s，会员账号：%s",
                member.getMemberName(), member.getMemberAccount());

        StringBuilder sb = new StringBuilder();
        sb.append(originalContent == null ? "" : originalContent.trim());
        sb.append("\n\n");
        sb.append("【系统补充信息】下面是该会员当前已报名的课程信息，请先根据这些信息，明确地告诉用户“我报名了哪些课程”，然后再结合课程安排回答后续问题：\n");
        sb.append(memberInfo).append("\n");
        sb.append(classInfo);
        return sb.toString();
    }
}
