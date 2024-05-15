package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Option;

public record CreateOptionRequest(
        Long optionGroupId,
        String optionGroupName,
        Long optionId,
        String optionName,
        int amount
) {
}
