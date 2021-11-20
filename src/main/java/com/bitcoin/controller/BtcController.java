package com.bitcoin.controller;

import co.elastic.apm.api.ElasticApm;
import com.bitcoin.client.EthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/btc")
@Slf4j
@RequiredArgsConstructor
public class BtcController {

    private final EthClient ethClient;

    @PostMapping("/{value}")
    public String buyBTC(@PathVariable double value) {
        log.info("transactionId: {} - traceId: {}, Buying BTC for {}", ElasticApm.currentTransaction().getId(), ElasticApm.currentTransaction().getTraceId(), value);
        return "you bought $" + value + " in BTC";
    }

    @GetMapping
    public String getBTCValue() {
        log.info("transactionId: {} - traceId: {}, Showing the BTC price", ElasticApm.currentTransaction().getId(), ElasticApm.currentTransaction().getTraceId());
        return "BTC today is $66.000";
    }

    @PostMapping("/buy/eth/{btcQtd}")
    public ResponseEntity<String> buyETHWithBTC(@PathVariable double btcQtd) {
        log.info("transactionId: {} - traceId: {}, Buying ETH with {} BTC", ElasticApm.currentTransaction().getId(), ElasticApm.currentTransaction().getTraceId(), btcQtd);
        return ResponseEntity.ok(ethClient.buyETH(btcQtd));
    }
}
