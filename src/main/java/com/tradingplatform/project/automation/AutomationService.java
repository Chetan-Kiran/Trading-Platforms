package com.tradingplatform.project.automation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tradingplatform.project.entity.AutomationRule;
import com.tradingplatform.project.market.MarketPriceService;
import com.tradingplatform.project.repository.AutomationRuleRepository;

@Service
public class AutomationService {

    private final
    AutomationRuleRepository repo;

    private final
    MarketPriceService market;

    public AutomationService(
        AutomationRuleRepository repo,
        MarketPriceService market
    ){

        this.repo=repo;
        this.market=market;
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

        for(
            AutomationRule rule
            : rules
        ){

            double price =
                market.getPrice(
                    rule.getSymbol()
                );

            if(
                rule.getConditionType()
                .equals("LESS_THAN")
                &&
                price <
                rule.getThreshold()
            ){

                result.append(
                    "RULE MATCHED : "
                );

                result.append(
                    rule.getSymbol()
                );

                result.append("\n");
            }
        }

        return result.toString();
    }
}