package com.tradingplatform.project.trade.dto;

public class TradeRequestDTO {

    private Long userId;

    private Long assetId;

    private Integer quantity;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(
        Long userId
    ){
        this.userId=userId;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(
        Long assetId
    ){
        this.assetId=assetId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(
        Integer quantity
    ){
        this.quantity=quantity;
    }

}