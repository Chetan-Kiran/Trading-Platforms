package com.tradingplatform.project.automation;

import org.springframework.stereotype.Service;
import java.util.List;
import com.tradingplatform.project.repository.PositionRepository;
import com.tradingplatform.project.entity.Position;
import com.tradingplatform.project.market.MarketPriceService;

@Service
public class PositionMonitorService {

    private final
    PositionRepository positionRepository;

    private final
    MarketPriceService marketPriceService;

    public PositionMonitorService(
        PositionRepository positionRepository,
        MarketPriceService marketPriceService
    ){
        this.positionRepository =
            positionRepository;

        this.marketPriceService =
            marketPriceService;
    }

    public void monitorPositions(){

        List<Position> positions =
            positionRepository.findAll();

        for(Position p : positions){

            double price =
                marketPriceService
                    .getPrice(
                        p.getSymbol()
                    );

            if(
                p.getStopLoss() != null
                &&
                price <= p.getStopLoss()
            ){

                System.out.println(
                    "STOP LOSS HIT : "
                    + p.getSymbol()
                );
            }

            if(
                p.getTakeProfit() != null
                &&
                price >= p.getTakeProfit()
            ){

                System.out.println(
                    "TAKE PROFIT HIT : "
                    + p.getSymbol()
                );
            }
        }
    }
}