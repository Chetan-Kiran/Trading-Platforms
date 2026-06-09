package com.tradingplatform.project.market.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tradingplatform.project.market.AlphaResponse;

@Component
public class AlphaVantageProvider
implements MarketProvider {

    @Autowired
    private RestTemplate restTemplate;

    private final String API_KEY =
        "YOUR_API_KEY";

    @Override
    public double getPrice(
        String symbol
    ){

        String url =
            "https://www.alphavantage.co/query"
            + "?function=GLOBAL_QUOTE"
            + "&symbol=" + symbol
            + "&apikey=" + API_KEY;

        ResponseEntity<AlphaResponse>
            response =

            restTemplate.getForEntity(
                url,
                AlphaResponse.class
            );

        AlphaResponse body =
            response.getBody();

        if(body == null
           || body.getGlobalQuote() == null
           || body.getGlobalQuote().getPrice() == null){

            throw new RuntimeException(
                "No quote returned for "
                + symbol
            );
        }

        return body
            .getGlobalQuote()
            .getPrice();
    }
}