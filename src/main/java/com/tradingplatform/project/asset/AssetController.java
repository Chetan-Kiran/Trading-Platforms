package com.tradingplatform.project.asset;

import org.springframework.web.bind.annotation.*;
import com.tradingplatform.project.repository.AssetRepository;
import com.tradingplatform.project.entity.Asset;

import java.util.*;

@RestController
@RequestMapping("/asset")
public class AssetController {

    private final AssetRepository assetRepository;

    public AssetController(
        AssetRepository assetRepository
    ){
        this.assetRepository = assetRepository;
    }

    @GetMapping
    public List<Asset> getAll(){
        return assetRepository.findAll();
    }
}
