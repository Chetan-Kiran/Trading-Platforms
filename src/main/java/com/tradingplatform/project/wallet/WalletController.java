package com.tradingplatform.project.wallet;

import org.springframework.web.bind.annotation.*;
import com.tradingplatform.project.entity.Wallet;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(
        WalletService walletService
    ){
        this.walletService =
            walletService;
    }

    @GetMapping("/{userId}")
    public Wallet getWallet(
        @PathVariable Long userId
    ){
        return walletService
            .getWallet(userId);
    }
}
