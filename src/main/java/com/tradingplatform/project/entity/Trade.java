package com.tradingplatform.project.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="trades")
public class Trade {

    @Id
    @GeneratedValue(
        strategy =
        GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Asset asset;

    private Integer quantity;

    private Double price;

    private String type;

    private LocalDateTime timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user=user;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset){
        this.asset=asset;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price){
        this.price=price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp=timestamp;
    }
}