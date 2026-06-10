package com.tradingplatform.project.Pricehistory;

import org.springframework.stereotype.Service;
import com.tradingplatform.project.repository.PriceHistoryRepository;
import com.tradingplatform.project.entity.PriceHistory;


@Service
public class PriceHistoryService {

    private final
    PriceHistoryRepository repo;

    public PriceHistoryService(
        PriceHistoryRepository repo
    ){
        this.repo = repo;
    }

    public void savePrice(
        String symbol,
        double price
    ){

        PriceHistory p = new PriceHistory();

        p.setSymbol(symbol);

        p.setPrice(price);

        p.setTimestamp(
            System.currentTimeMillis()
        );

        repo.save(p);
    }
}
