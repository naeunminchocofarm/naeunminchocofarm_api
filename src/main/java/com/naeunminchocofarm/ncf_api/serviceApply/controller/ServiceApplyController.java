package com.naeunminchocofarm.ncf_api.serviceApply.controller;

import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceApply;
import com.naeunminchocofarm.ncf_api.serviceApply.service.ServiceApplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class ServiceApplyController {

    private final ServiceApplyService serviceApplyService;

    public ServiceApplyController(ServiceApplyService serviceApplyService) {
        this.serviceApplyService = serviceApplyService;
    }

    // 신청합니다
    @PostMapping("/web/apply")
    public ResponseEntity<Void> apply(@RequestBody ServiceApply serviceApply) {
        serviceApplyService.serviceApplication(serviceApply); // 내부에서 status, date 자동처리
        return ResponseEntity.ok().build();
    }

    // 목록 조회
    @GetMapping("/admin/list")
    public ResponseEntity<List<ServiceApply>> getList() {
        return ResponseEntity.ok(serviceApplyService.getAllServiceApplies());
    }

    // 상세
    @GetMapping("/admin/detail/{id}")
    public ResponseEntity<ServiceApply> getDetail(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceApplyService.selectServiceApplyDetail(id));
    }
}