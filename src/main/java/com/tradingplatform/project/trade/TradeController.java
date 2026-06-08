package com.tradingplatform.project.trade;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.tradingplatform.project.entity.Trade;
import com.tradingplatform.project.entity.User;
import com.tradingplatform.project.auth.CurrentUserService;
import com.tradingplatform.project.trade.dto.PortfolioDTO;
import com.tradingplatform.project.trade.dto.TradeRequestDTO;

@RestController
@RequestMapping("/trade")
public class TradeController {

    private final
    TradeService tradeService;

    private final
    CurrentUserService currentUserService;

    public TradeController(
        TradeService tradeService,
        CurrentUserService currentUserService
    ){

        this.tradeService=
            tradeService;
        this.currentUserService =
            currentUserService;
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

    @GetMapping("/transactions/{userId}")
    public List<Trade> getTrades(@PathVariable Long userId){
        return tradeService.getTrades(userId);
    }

    @GetMapping("/portfolio")
    public List<PortfolioDTO> getPortfolio(){

    User user = currentUserService.getCurrentUser();

    return tradeService.getPortfolio(user.getId());
}

}