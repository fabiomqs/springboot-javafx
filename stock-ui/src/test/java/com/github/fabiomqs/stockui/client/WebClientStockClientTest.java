package com.github.fabiomqs.stockui.client;

import com.github.fabiomqs.stockui.domain.StockPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

class WebClientStockClientTest {

    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveStockPricesFromTheService() {
        // given
        WebClientStockClient webClientStockClient = new WebClientStockClient(webClient);

        // when
        Flux<StockPrice> prices = webClientStockClient.pricesFor("SYMBOL");

        // then
        Assertions.assertNotNull(prices);
        Flux<StockPrice> fivePrices = prices.take(5);
        Assertions.assertEquals(5, fivePrices.count().block());
        Assertions.assertEquals("SYMBOL", fivePrices.blockFirst().getSymbol());
    }
}