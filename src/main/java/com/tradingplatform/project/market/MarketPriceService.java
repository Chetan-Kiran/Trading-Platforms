package com.tradingplatform.project.market;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketPriceService {

    @Autowired
    private RestTemplate restTemplate;

    private final String API_KEY = "YOUR_KEY";

    public double getPrice(String symbol) {

        String url =
            "https://www.alphavantage.co/query"
            + "?function=GLOBAL_QUOTE"
            + "&symbol=" + symbol
            + "&apikey=" + API_KEY;

        ResponseEntity<AlphaResponse> response =
            restTemplate.getForEntity(
                url,
                AlphaResponse.class
            );

        AlphaResponse body =
            response.getBody();

        if(body == null){

            throw new RuntimeException(
                "Empty API response"
            );
        }

        if(body.getGlobalQuote() == null){

            throw new RuntimeException(
                "No quote returned for symbol: "
                + symbol
            );
        }

        Double price = body.getGlobalQuote().getPrice();

        if (price == null) {
            throw new RuntimeException(
                "No price available for: "
                + symbol
            );
        }

        System.out.println(response.getBody());
        
        return price.doubleValue();
    }
}