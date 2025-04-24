package com.naeunminchocofarm.ncf_api.serviceApply.dto;

public record ServiceApplyListItemDTO(
        Integer id,
        String proposerLoginId,
        String proposerName,
        String proposerEmail,
        String proposerTell,
        String type,
        String status,
        String applicationAt
) {
}
