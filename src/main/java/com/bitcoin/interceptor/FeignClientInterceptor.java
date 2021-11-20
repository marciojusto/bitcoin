package com.bitcoin.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                                                   .orElseThrow(() -> new RuntimeException("RequestAttributes is null"))).getRequest()
                                                                                                                         .getHeader(AUTHORIZATION_HEADER);
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(AUTHORIZATION_HEADER, getBearerTokenHeader());
    }
}
