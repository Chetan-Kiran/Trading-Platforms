package com.tradingplatform.project.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy=
        GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @OneToMany(mappedBy="basket")
    private List<BasketAsset> assets;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(
        String name
    ) {
        this.name=name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(
        User user
    ) {
        this.user=user;
    }

    public List<BasketAsset> getAssets(){
        return assets;
    }
    public void setAssets(List<BasketAsset> assets){
        this.assets = assets;
    }
}
