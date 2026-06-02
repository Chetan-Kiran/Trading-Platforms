package com.tradingplatform.project.trade;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.tradingplatform.project.entity.Trade;
import com.tradingplatform.project.trade.dto.PortfolioDTO;
import com.tradingplatform.project.trade.dto.TradeRequestDTO;

@RestController
@RequestMapping("/trade")
public class TradeController {

    private final
    TradeService tradeService;

    public TradeController(
        TradeService tradeService
    ){

        this.tradeService=
            tradeService;
    }

    @PostMapping("/buy")
    public Trade buy(
        @RequestBody
        TradeRequestDTO dto
    ){

        return tradeService
            .buy(dto);
    }

    @PostMapping("/sell")
    public Trade sell(
        @RequestBody
        TradeRequestDTO dto
    ){

        return tradeService
            .sell(dto);
    }

    @GetMapping(
        "/transactions/{userId}"
    )
    public List<Trade>
    getTrades(
        @PathVariable
        Long userId
    ){
        return tradeService.getTrades(userId);
    }

    @GetMapping("/portfolio/{userId}")
    public List<PortfolioDTO> portfolio(@PathVariable Long userId){

        return tradeService.getPortfolio(userId);
    }

}