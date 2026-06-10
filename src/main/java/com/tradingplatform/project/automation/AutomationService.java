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
import com.tradingplatform.project.strategy.StrategyContext;

@Service
public class AutomationService {

    private final
    AutomationRuleRepository repo;

    private final
    MarketPriceService market;

    private final TradeService tradeService;

    private final AssetRepository assetRepository;

    private final StrategyContext strategyContext;
    
    public AutomationService(
        AutomationRuleRepository repo,
        MarketPriceService market,
        TradeService tradeService,
        AssetRepository assetRepository,
        StrategyContext strategyContext
    ){

        this.repo=repo;
        this.market=market;
        this.tradeService = tradeService;
        this.assetRepository = assetRepository;
        this.strategyContext = strategyContext;
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
            repo.findByActiveTrue();

        StringBuilder result = new StringBuilder();

        for(AutomationRule rule : rules){
            try{
                double price =market.getPrice(rule.getSymbol());

                // boolean matched =
                //     rule.getConditionType()
                //         .equals("LESS_THAN")
                //     &&
                //     price < rule.getThreshold();
                boolean matched =strategyContext.evaluate(

                rule.getStrategy(),

                price,

                rule.getThreshold());
                
                if(matched){
                    System.out.println("RULE MATCHED : "+ rule.getSymbol());

                    Asset asset = assetRepository.findBySymbol(rule.getSymbol()).orElseThrow();

                    TradeRequestDTO dto = new TradeRequestDTO();

                    dto.setUserId(rule.getUserId());

                    dto.setAssetId(asset.getId());

                    dto.setQuantity(rule.getQuantity());

                    tradeService.buy(dto, price);
                    rule.setActive(false);
                    repo.save(rule);

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