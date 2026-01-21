package com.finflow.wallet.controller;

import com.finflow.wallet.dto.*;
import com.finflow.wallet.entity.AccountEntity;
import com.finflow.wallet.entity.UserEntity;
import com.finflow.wallet.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    private AccountResponse toAccountResponse(AccountEntity account) {
        return new AccountResponse
                (
                account.getAccountNumber(),
                account.getBalance(),
                account.getCurrency()
        );
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@RequestBody TransferRequest request){
        walletService.transfer
                (
                    request.getFromAccount(),
                    request.getToAccount(),
                    request.getAmount()
        );
    }
}
