package com.github.fabiomqs.stockui.client;

import com.github.fabiomqs.stockui.domain.StockPrice;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

import java.io.IOException;

import static java.time.Duration.ofSeconds;
import static reactor.util.retry.Retry.backoff;

//The client that gonna consume the reactive stock-service
@Log4j2
public class WebClientStockClient {

    private WebClient webClient;

    public WebClientStockClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<StockPrice> pricesFor(String symbol) {
        return this.webClient.get()
                .uri("http://localhost:8080/stocks/{symbol}", symbol)
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .retryWhen(backoff(5, ofSeconds(1)).maxBackoff(ofSeconds(20)))
                .doOnError(IOException.class, e -> log.error(e.getMessage()));

    }
}
