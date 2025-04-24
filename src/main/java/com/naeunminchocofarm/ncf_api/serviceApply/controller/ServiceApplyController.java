package com.naeunminchocofarm.ncf_api.serviceApply.controller;

import com.naeunminchocofarm.ncf_api.lib.security.AuthInfo;
import com.naeunminchocofarm.ncf_api.lib.security.AuthUser;
import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
import com.naeunminchocofarm.ncf_api.serviceApply.dto.ServiceApplyDetailDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.dto.ServiceApplyListItemDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.dto.SimpleServiceApplyDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceApply;
import com.naeunminchocofarm.ncf_api.serviceApply.service.ServiceApplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceApplyController {
    private final ServiceApplyService serviceApplyService;

    public ServiceApplyController(ServiceApplyService serviceApplyService) {
        this.serviceApplyService = serviceApplyService;
    }

    @PostMapping("/apply")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void applyService(@AuthInfo AuthUser authUser, @RequestBody SimpleServiceApplyDTO serviceApplyDto) {
        serviceApplyService.insertServiceApply(authUser.getId(), serviceApplyDto);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ServiceApplyListItemDTO> getAllServiceApplies() {
        return serviceApplyService.selectServiceApplyList();
    }

    // 특정 신청 상세 조회
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<ServiceApplyDetailDTO> getServiceApply(@PathVariable Integer id) {
        return serviceApplyService.selectServiceApplyDetail(id);
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