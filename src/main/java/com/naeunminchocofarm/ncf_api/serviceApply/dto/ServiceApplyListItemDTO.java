package com.naeunminchocofarm.ncf_api.serviceApply.dto;

public record ServiceApplyListItemDTO(
        Integer id,
        String memberLoginId,
        String memberName,
        String memberEmail,
        String memberTell,
        String contactTell,
        String type,
        String status,
        String applicationAt
) {
}
