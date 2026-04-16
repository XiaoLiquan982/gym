package com.gym.controller;

import com.gym.common.ApiResponse;
import com.gym.common.ResultCode;
import com.gym.pojo.Admin;
import com.gym.pojo.Member;
import com.gym.service.AdminService;
import com.gym.service.EmployeeService;
import com.gym.service.EquipmentService;
import com.gym.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiLoginController {

    private static final String SESSION_ADMIN = "admin";
    private static final String SESSION_USER = "user";

    private final MemberService memberService;
    private final AdminService adminService;
    private final EmployeeService employeeService;
    private final EquipmentService equipmentService;

    public ApiLoginController(
            MemberService memberService,
            AdminService adminService,
            EmployeeService employeeService,
            EquipmentService equipmentService) {
        this.memberService = memberService;
        this.adminService = adminService;
        this.employeeService = employeeService;
        this.equipmentService = equipmentService;
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<ApiResponse> adminLogin(Admin admin, HttpSession session) {
        Admin loggedIn = adminService.adminLogin(admin);
        if (loggedIn == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.fail(ResultCode.UNAUTHORIZED, "账号或密码有误"));
        }

        putAdminMainDataInSession(session, loggedIn);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PostMapping("/userLogin")
    public ResponseEntity<ApiResponse> userLogin(Member member, HttpSession session) {
        Member loggedIn = memberService.userLogin(member);
        if (loggedIn == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.fail(ResultCode.UNAUTHORIZED, "账号或密码有误"));
        }

        session.setAttribute(SESSION_USER, loggedIn);
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(ApiResponse.ok());
    }

    @GetMapping("/toAdminMain")
    public ResponseEntity<ApiResponse> toAdminMain(HttpSession session) {
        ApiResponse body = ApiResponse.ok()
                .add("memberTotal", session.getAttribute("memberTotal"))
                .add("employeeTotal", session.getAttribute("employeeTotal"))
                .add("humanTotal", session.getAttribute("humanTotal"))
                .add("equipmentTotal", session.getAttribute("equipmentTotal"));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/toUserMain")
    public ResponseEntity<ApiResponse> toUserMain(HttpSession session) {
        return ResponseEntity.ok(ApiResponse.ok().add("member", session.getAttribute(SESSION_USER)));
    }

    private void putAdminMainDataInSession(HttpSession session, Admin admin) {
        session.setAttribute(SESSION_ADMIN, admin);
        Integer memberTotal = memberService.selectTotalCount();
        Integer employeeTotal = employeeService.selectTotalCount();
        Integer humanTotal = memberTotal + employeeTotal;
        Integer equipmentTotal = equipmentService.selectTotalCount();
        session.setAttribute("memberTotal", memberTotal);
        session.setAttribute("employeeTotal", employeeTotal);
        session.setAttribute("humanTotal", humanTotal);
        session.setAttribute("equipmentTotal", equipmentTotal);
    }
}
