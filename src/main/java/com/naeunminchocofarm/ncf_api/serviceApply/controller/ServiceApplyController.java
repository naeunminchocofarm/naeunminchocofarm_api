package com.naeunminchocofarm.ncf_api.serviceApply.controller;

import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceApply;
import com.naeunminchocofarm.ncf_api.serviceApply.service.ServiceApplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceApplyController {

    private final ServiceApplyService serviceApplyService;

    public ServiceApplyController(ServiceApplyService serviceApplyService) {
        this.serviceApplyService = serviceApplyService;
    }

    // 신청합니다
    @PostMapping("/apply")
    public ResponseEntity<?> applyService(@RequestBody ServiceApply serviceApply,
                                          @RequestAttribute("loginMember")LoginInfo loginInfo) {
        try {
            if (serviceApply.getMemberId() == null) {
                serviceApply.setMemberId(loginInfo.getId());
            }
            serviceApplyService.insertServiceApply(serviceApply);

            return ResponseEntity.ok("신청이 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("신청에 실패했습니다.");
        }

    }

    @GetMapping("/list")
    public ResponseEntity<List<ServiceApply>> getAllServiceApplies() {
        System.out.println("서비스 신청 전체 목록 좀 왜 안나옴");
        return ResponseEntity.ok(serviceApplyService.selectServiceApplyList());
    }

    // 특정 신청 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ServiceApply> getServiceApply(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceApplyService.selectServiceApplyDetail(id));
    }

    // 내 신청 목록 조회 (사용자)
    @GetMapping("/my")
    public ResponseEntity<List<ServiceApply>> getMyApplies(@RequestAttribute("loginMember") LoginInfo loginInfo) {
        return ResponseEntity.ok(serviceApplyService.showMyServiceApplyList(loginInfo.getId()));
    }

    // 신청 수정
    @PutMapping("/update")
    public ResponseEntity<?> updateServiceApply(@RequestBody ServiceApply serviceApply) {
        try {
            serviceApplyService.updateServiceApply(serviceApply);
            return ResponseEntity.ok("수정 완료");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 실패");
        }
    }

    // 신청 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteServiceApply(@PathVariable int id) {
        try {
            serviceApplyService.deleteServiceApply(id);
            return ResponseEntity.ok("삭제 완료");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }
}