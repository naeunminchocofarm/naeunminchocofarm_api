package com.naeunminchocofarm.ncf_api.smart_farm.dto;

import java.time.OffsetDateTime;

public record SimpleFarmDTO (
        Integer id,
        Integer memberId,
        String name,
        String uuid,
        OffsetDateTime useDate,
        String cropName,
        String address,
        String status,
        Integer roleFlag,
        String roleName
) {}
