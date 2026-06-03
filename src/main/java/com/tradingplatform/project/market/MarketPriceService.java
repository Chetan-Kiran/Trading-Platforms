package com.tradingplatform.project.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketPriceService {

    @Autowired
    private RestTemplate restTemplate;

    private final String API_KEY =
        "JQP7X52DHIF8C9KJ";

    public double getPrice(
        String symbol
    ){

        System.out.println(
            "Fetching price for : "
            + symbol
        );

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

        System.out.println(
            "API RESPONSE : "
            + body
        );

        if(body == null){

            throw new RuntimeException(
                "Empty API response"
            );
        }

        if(
            body.getGlobalQuote()
            == null
        ){

            throw new RuntimeException(
                "No quote returned for symbol: "
                + symbol
            );
        }

        Double price =
            body
                .getGlobalQuote()
                .getPrice();

        if(price == null){

            throw new RuntimeException(
                "No price available for: "
                + symbol
            );
        }

        return price;
    }
}