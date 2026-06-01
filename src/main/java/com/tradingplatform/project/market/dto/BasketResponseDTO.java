package com.tradingplatform.project.market.dto;

import java.util.List;

public class BasketResponseDTO {

    private Long id;

    private String name;

    private List<String> assets;

    public BasketResponseDTO(
            Long id,
            String name,
            List<String> assets
    ){

        this.id=id;
        this.name=name;
        this.assets=assets;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public List<String> getAssets(){
        return assets;
    }

}