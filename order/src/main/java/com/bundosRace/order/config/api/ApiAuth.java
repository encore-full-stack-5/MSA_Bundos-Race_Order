package com.bundosRace.order.config.api;

import com.bundosRace.order.domain.dto.response.TokenInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAuth {
    private final FeignAuth feignAuth;

    public TokenInfoResponse parseToken(String token) {
        return feignAuth.parseToken(token);
    }
}
