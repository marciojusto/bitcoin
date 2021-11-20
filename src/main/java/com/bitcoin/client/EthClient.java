package com.bitcoin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "ethClient", url = "http://kong:8000")
public interface EthClient {

    @PostMapping("/eth/{value}")
    String buyETH(@PathVariable double value);
}
