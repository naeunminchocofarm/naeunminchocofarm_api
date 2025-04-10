package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.FarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SectionDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.service.UuidService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/uuid")
public class UuidController {

  private final UuidService uuidService;

  public UuidController(UuidService uuidService) {
    this.uuidService = uuidService;
  }

  /**
   * 스마트팜 + 구역 + 센서 등록을 한 번에 처리하는 엔드포인트
   * @param farmDTO    스마트팜 DTO
   * @param sectionDTO 구역 DTO
   * @param sensorDTO  센서 DTO
   */
  @PostMapping("/register")
  public void registerSmartFarm(
          @RequestBody FarmSectionSensorRequest request
  ) {
    uuidService.registerSmartFarm(
            request.getFarmDTO(),
            request.getSectionDTO(),
            request.getSensorDTO()
    );
  }

  // 내부에서 복합 요청 데이터를 받기 위한 DTO 정의
  public static class FarmSectionSensorRequest {
    private FarmDTO farmDTO;
    private SectionDTO sectionDTO;
    private SensorDTO sensorDTO;

    public FarmDTO getFarmDTO() {
      return farmDTO;
    }

    public void setFarmDTO(FarmDTO farmDTO) {
      this.farmDTO = farmDTO;
    }

    public SectionDTO getSectionDTO() {
      return sectionDTO;
    }

    public void setSectionDTO(SectionDTO sectionDTO) {
      this.sectionDTO = sectionDTO;
    }

    public SensorDTO getSensorDTO() {
      return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
      this.sensorDTO = sensorDTO;
    }
  }
}
