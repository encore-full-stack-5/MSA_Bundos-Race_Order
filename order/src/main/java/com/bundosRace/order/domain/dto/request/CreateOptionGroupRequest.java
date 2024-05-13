package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.OptionGroup;

import java.util.List;

public record CreateOptionGroupRequest(
        Long id,
        String name,
        List<CreateOptionRequest> options
) {
    public OptionGroup toEntity() {
        return OptionGroup.builder()
                .name(name)
                .build();
    }

}
