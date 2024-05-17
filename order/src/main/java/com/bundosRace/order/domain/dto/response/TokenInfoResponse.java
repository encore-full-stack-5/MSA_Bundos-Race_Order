package com.bundosRace.order.domain.dto.response;

import java.util.UUID;

public record TokenInfoResponse (
        UUID id,
        String nickname
){
}
