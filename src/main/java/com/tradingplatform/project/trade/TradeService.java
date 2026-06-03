package com.tradingplatform.project.trade;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Service;

import com.tradingplatform.project.entity.Asset;
import com.tradingplatform.project.entity.Trade;
import com.tradingplatform.project.entity.User;
import com.tradingplatform.project.market.MarketPriceService;
import com.tradingplatform.project.repository.AssetRepository;
import com.tradingplatform.project.repository.TradeRepository;
import com.tradingplatform.project.repository.UserRepository;
import com.tradingplatform.project.trade.dto.PortfolioDTO;
import com.tradingplatform.project.trade.dto.TradeRequestDTO;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;
    
    private final AssetRepository assetRepository;
    private final MarketPriceService marketPriceService;
    

    public TradeService(
        TradeRepository tradeRepository,
        UserRepository userRepository,
        AssetRepository assetRepository,
        MarketPriceService marketPriceService
    ){

        this.tradeRepository= tradeRepository;

        this.userRepository= userRepository;

        this.assetRepository= assetRepository;

        this.marketPriceService= marketPriceService;
    }

    public Trade buy(
    TradeRequestDTO dto,
    double livePrice
){

    User user =
        userRepository
            .findById(
                dto.getUserId()
            )
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "User not found"
                    )
            );

    Asset asset =
        assetRepository
            .findById(
                dto.getAssetId()
            )
            .orElseThrow(
                () ->
                    new RuntimeException(
                        "Asset not found"
                    )
            );

    Trade trade =
        new Trade();

    trade.setUser(user);

    trade.setAsset(asset);

    trade.setQuantity(
        dto.getQuantity()
    );

    trade.setPrice(
        livePrice
    );

    trade.setType(
        "BUY"
    );

    trade.setTimestamp(
        LocalDateTime.now()
    );

    return tradeRepository
        .save(trade);
}

    public Trade buy(
    TradeRequestDTO dto
){

    Asset asset =
        assetRepository
            .findById(
                dto.getAssetId()
            )
            .orElseThrow();

    double livePrice =

        marketPriceService
            .getPrice(
                asset.getSymbol()
            );

    return buy(
        dto,
        livePrice
    );
}

    public Trade sell(
        TradeRequestDTO dto
){

    User user =

        userRepository
            .findById(
                dto.getUserId()
            )
            .orElseThrow();

    Asset asset =

        assetRepository
            .findById(
                dto.getAssetId()
            )
            .orElseThrow();

    int owned =

        getOwnedQuantity(

            user.getId(),

            asset.getSymbol()
        );

    if(

        dto.getQuantity()
        > owned

    ){

        throw new RuntimeException(

            "Insufficient holdings. Owned: "
            + owned
        );
    }

    double livePrice =

        marketPriceService
            .getPrice(
                asset.getSymbol()
            );

    Trade trade =
        new Trade();

    trade.setUser(user);

    trade.setAsset(asset);

    trade.setQuantity(
        dto.getQuantity()
    );

    trade.setPrice(
        livePrice
    );

    trade.setType(
        "SELL"
    );

    trade.setTimestamp(
        LocalDateTime.now()
    );

    return tradeRepository
        .save(trade);
}
    public List<Trade>
    getTrades(
        Long userId
    ){

        return tradeRepository
            .findByUserId(
                userId
            );
    }

    public List<PortfolioDTO>
getPortfolio(Long userId){

    List<Trade> trades =

        tradeRepository
            .findByUserId(
                userId
            );

    Map<String,Integer> qtyMap =
        new HashMap<>();

    Map<String,Double> costMap =
        new HashMap<>();

    Map<String,Double> realizedMap =
        new HashMap<>();

    for(
        Trade trade : trades
    ){

        String symbol =

            trade.getAsset()
                .getSymbol();

        int qty =
            trade.getQuantity();

        double price =
            trade.getPrice();

        qtyMap.putIfAbsent(
            symbol,
            0
        );

        costMap.putIfAbsent(
            symbol,
            0.0
        );

        realizedMap.putIfAbsent(
            symbol,
            0.0
        );

        if(
            trade.getType()
            .equals("BUY")
        ){

            qtyMap.put(

                symbol,

                qtyMap.get(symbol)
                + qty
            );

            costMap.put(

                symbol,

                costMap.get(symbol)

                +

                qty*price
            );
        }

        else{

            int currentQty =

                qtyMap.get(symbol);

            double avgPrice =

                currentQty==0

                ? 0

                :

                costMap.get(symbol)
                / currentQty;

            double realized =

                (price
                - avgPrice)

                * qty;

            realizedMap.put(

                symbol,

                realizedMap
                    .get(symbol)

                +

                realized
            );

            qtyMap.put(

                symbol,

                currentQty
                - qty
            );

            costMap.put(

                symbol,

                costMap
                    .get(symbol)

                -

                avgPrice*qty
            );
        }
    }

    List<PortfolioDTO>
        result =
            new ArrayList<>();

    for(
        String symbol :
        qtyMap.keySet()
    ){

        int quantity =
            qtyMap.get(symbol);

        if(quantity<=0){

            continue;
        }

        double avgPrice =

            costMap.get(symbol)
            / quantity;

        double livePrice =

            marketPriceService
                .getPrice(
                    symbol
                );

        double unrealized =

            (livePrice
            - avgPrice)

            * quantity;

        double realized =
            realizedMap
                .get(symbol);

        result.add(

            new PortfolioDTO(

                symbol,

                quantity,

                avgPrice,

                livePrice,

                realized
                + unrealized,

                realized,

                unrealized
            )
        );
    }

    return result;
}

    private int getOwnedQuantity(

        Long userId,

        String symbol
){

    List<Trade> trades =

        tradeRepository
            .findByUserId(userId);

    int owned = 0;

    for(Trade trade : trades){

        if(

            !trade.getAsset()
                .getSymbol()
                .equals(symbol)

        ){
            continue;
        }

        if(
            trade.getType()
            .equals("BUY")
        ){

            owned +=
                trade.getQuantity();
        }

        else{

            owned -=
                trade.getQuantity();
        }
    }

    return owned;
}
}