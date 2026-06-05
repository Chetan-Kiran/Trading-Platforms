package com.tradingplatform.project.wallet;

import org.springframework.stereotype.Service;
import com.tradingplatform.project.repository.WalletRepository;
import com.tradingplatform.project.entity.Wallet;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(
        WalletRepository walletRepository
    ){
        this.walletRepository =
            walletRepository;
    }

    public Wallet getWallet(
        Long userId
    ){
        return walletRepository
            .findById(userId)
            .orElseThrow();
    }

    public void debit(
        Long userId,
        double amount
    ){

        Wallet wallet =
            getWallet(userId);

        if(
            wallet.getBalance()
            < amount
        ){
            throw new RuntimeException(
                "Insufficient funds"
            );
        }

        wallet.setBalance(
            wallet.getBalance()
            - amount
        );

        walletRepository.save(wallet);
    }

    public void credit(
        Long userId,
        double amount
    ){

        Wallet wallet =
            getWallet(userId);

        wallet.setBalance(
            wallet.getBalance()
            + amount
        );

        walletRepository.save(wallet);
    }
}
