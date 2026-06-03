package com.tradingplatform.project.automation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tradingplatform.project.market.MarketPriceService;
import com.tradingplatform.project.trade.TradeService;
import com.tradingplatform.project.trade.dto.TradeRequestDTO;
import com.tradingplatform.project.repository.AssetRepository;
import com.tradingplatform.project.entity.Asset;

import com.tradingplatform.project.entity.AutomationRule;
import com.tradingplatform.project.repository.AutomationRuleRepository;

@Service
public class AutomationService {

    private final
    AutomationRuleRepository repo;

    private final
    MarketPriceService market;

    private final TradeService tradeService;

    private final AssetRepository assetRepository;

    public AutomationService(
        AutomationRuleRepository repo,
        MarketPriceService market,
        TradeService tradeService,
        AssetRepository assetRepository
    ){

        this.repo=repo;
        this.market=market;
        this.tradeService = tradeService;
        this.assetRepository = assetRepository;
    }

    public AutomationRule save(
        AutomationRule rule
    ){

        return repo.save(rule);
    }

    public List<AutomationRule>
    getRules(){

        return repo.findAll();
    }

    public String run(){

        List<AutomationRule>
            rules =
            repo.findAll();

        StringBuilder result =
            new StringBuilder();

        for(AutomationRule rule : rules){
            try{
                double price =
                    market
                        .getPrice(
                            rule.getSymbol()
                        );

                boolean matched =
                    rule.getConditionType()
                        .equals("LESS_THAN")
                    &&
                    price < rule.getThreshold();

                if(matched){
                    System.out.println(
                        "RULE MATCHED : "
                        + rule.getSymbol()
                    );

                    Asset asset =
                        assetRepository
                            .findBySymbol(
                                rule.getSymbol()
                            )
                            .orElseThrow();

                    TradeRequestDTO dto =
                        new TradeRequestDTO();

                    dto.setUserId(
                        rule.getUserId()
                    );

                    dto.setAssetId(
                        asset.getId()
                    );

                    dto.setQuantity(
                        rule.getQuantity()
                    );

                    tradeService.buy(dto, price);

                    System.out.println(
                        "AUTO BUY EXECUTED : "
                        + rule.getSymbol()
                    );

                    result.append("AUTO BUY EXECUTED : ")
                        .append(rule.getSymbol())
                        .append("\n");
                }
            }
            catch(Exception e){
                System.out.println(
                    "RULE FAILED : "
                    + rule.getSymbol()
                    + " -> "
                    + e.getMessage()
                );

                result.append("RULE FAILED : ")
                    .append(rule.getSymbol())
                    .append(" -> ")
                    .append(e.getMessage())
                    .append("\n");
            }
        }

        return result.toString();
    }
}