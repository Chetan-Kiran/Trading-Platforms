package com.tradingplatform.project.automation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tradingplatform.project.market.MarketPriceService;
import com.tradingplatform.project.trade.TradeService;
import com.tradingplatform.project.trade.dto.TradeRequestDTO;
import com.tradingplatform.project.repository.AssetRepository;
import com.tradingplatform.project.Pricehistory.PriceHistoryService;
import com.tradingplatform.project.entity.Asset;

import com.tradingplatform.project.entity.AutomationRule;
import com.tradingplatform.project.indicators.EMAIndicator;
import com.tradingplatform.project.indicators.RSIIndicator;
import com.tradingplatform.project.repository.AutomationRuleRepository;
import com.tradingplatform.project.strategy.RuleEvaluator;
import com.tradingplatform.project.strategy.StrategyContext;
import com.tradingplatform.project.entity.Position;
import com.tradingplatform.project.repository.PositionRepository;

@Service
public class AutomationService {

    private final
    AutomationRuleRepository repo;

    private final
    MarketPriceService market;

    private final TradeService tradeService;

    private final AssetRepository assetRepository;

    // private final StrategyContext strategyContext;

    private final RSIIndicator rsiIndicator;

    private final EMAIndicator emaIndicator;

    private final PriceHistoryService priceHistoryService;

    private final RuleEvaluator ruleEvaluator;
    
    private final PositionRepository positionRepository;
    
    public AutomationService(
        AutomationRuleRepository repo,
        MarketPriceService market,
        TradeService tradeService,
        AssetRepository assetRepository,
        StrategyContext strategyContext,
        RSIIndicator rsiIndicator,
        EMAIndicator emaIndicator,
        PriceHistoryService priceHistoryService,
        RuleEvaluator ruleEvaluator,
        PositionRepository positionRepository
    ){

        this.repo=repo;
        this.market=market;
        this.tradeService = tradeService;
        this.assetRepository = assetRepository;
        // this.strategyContext = strategyContext;
        this.rsiIndicator = rsiIndicator;
        this.emaIndicator = emaIndicator;
        this.priceHistoryService = priceHistoryService;
        this.ruleEvaluator = ruleEvaluator;
        this.positionRepository = positionRepository;
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
                // boolean matched =strategyContext.evaluate(

                // rule.getStrategy(),

                // price,

                // rule.getThreshold());

                boolean matched = false;

                if("PRICE_DROP".equals(rule.getStrategy())){
                    matched = price < rule.getThreshold();
                }
                
                else if(
    "RSI"
    .equals(
        rule.getStrategy()
    )
){

    List<Double> prices =
        priceHistoryService
            .getPrices(
                rule.getSymbol()
            );

    double rsi =
        rsiIndicator
            .calculate(
                prices
            );

    matched =
        rsi <
        rule.getThreshold();
}else if(
    "EMA"
    .equals(
        rule.getStrategy()
    )
){

    List<Double> prices =
        priceHistoryService
            .getPrices(
                rule.getSymbol()
            );

    double ema =
        emaIndicator
            .calculate(
                prices,
                20
            );

    matched =
        price > ema;
}else if(
    "RSI_EMA"
    .equals(
        rule.getStrategy()
    )
){

    List<Double> prices =
        priceHistoryService
            .getPrices(
                rule.getSymbol()
            );

    double rsi =
        rsiIndicator
            .calculate(
                prices
            );

    double ema =
        emaIndicator
            .calculate(
                prices,
                20
            );

    matched =
        ruleEvaluator.evaluate(

            rsi,

            rule.getThreshold(),

            price,

            ema
        );
}

                if(matched){
                    System.out.println("RULE MATCHED : "+ rule.getSymbol());

                    Asset asset = assetRepository.findBySymbol(rule.getSymbol()).orElseThrow();

                    TradeRequestDTO dto = new TradeRequestDTO();

                    dto.setUserId(rule.getUserId());

                    dto.setAssetId(asset.getId());

                    dto.setQuantity(rule.getQuantity());

                    if("BUY".equals(rule.getAction())){

    tradeService.buy(dto, price);

    Position position = new Position();

    position.setUserId(rule.getUserId());

    position.setSymbol(rule.getSymbol());

    position.setQuantity(rule.getQuantity());

    position.setEntryPrice(price);

    position.setStopLoss(rule.getStopLoss());

    position.setTakeProfit(rule.getTakeProfit());

    positionRepository.save(position);

    System.out.println(
        "AUTO BUY EXECUTED : "
        + rule.getSymbol()
    );
}
else if("SELL".equals(rule.getAction())){

    tradeService.sell(dto, price);

    System.out.println(
        "AUTO SELL EXECUTED : "
        + rule.getSymbol()
    );
}

                    rule.setActive(false);

repo.save(rule);

result.append("AUTO ")
    .append(rule.getAction())
    .append(" EXECUTED : ")
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