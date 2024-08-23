package com.pangility.schwab.example;

import com.pangility.schwab.api.client.marketdata.EnableSchwabMarketDataApi;
import com.pangility.schwab.api.client.marketdata.SchwabMarketDataApiClient;
import com.pangility.schwab.api.client.marketdata.model.movers.MoversRequest;
import com.pangility.schwab.api.client.marketdata.model.movers.Screener;
import com.pangility.schwab.api.client.marketdata.model.quotes.QuoteResponse;
import com.pangility.schwab.api.client.oauth2.SchwabAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@EnableSchwabMarketDataApi
public class SchwabApiClientHandler {
    @Autowired
    private SchwabMarketDataApiClient schwabMarketDataApiClient;

    private final String schwabUserId = "<YOUR-SCHWAB-ACCOUNT-USER-ID-GOES-HERE>";

    public void init() {
        if(!schwabMarketDataApiClient.isInitialized()) {
            ClientTokenHandler clientTokenHandler = new ClientTokenHandler();
            SchwabAccount schwabAccount = new SchwabAccount();
            schwabAccount.setUserId(schwabUserId);
            // If you have saved your refresh token, pass it to the API client service.
            // Otherwise, you will have to generate one each time you run this example.`
            // schwabAccount.setRefreshToken(schwabRefreshToken);
            // schwabAccount.setRefreshExpiration(schwabRefreshExpiration);
            schwabMarketDataApiClient.init(schwabAccount, clientTokenHandler);
        }
    }

    @GetMapping("/quote")
    public Mono<QuoteResponse> callQuote(@RequestParam String symbol) {
        this.init();
        return schwabMarketDataApiClient.fetchQuoteToMono(symbol);
    }

    @GetMapping("/movers/nyse")
    public Flux<Screener> callMovers() {
        this.init();
        MoversRequest moversRequest = MoversRequest.builder()
                .withIndexSymbol(MoversRequest.IndexSymbol.NYSE)
                .build();
        return schwabMarketDataApiClient.fetchMoversToFlux(moversRequest);
    }
}