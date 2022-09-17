package com.ironhack.vbnk_antifraudservice.services;

import com.ironhack.vbnk_antifraudservice.model.AFRequest;
import com.ironhack.vbnk_antifraudservice.model.AFResponse;
import com.ironhack.vbnk_antifraudservice.model.AFTransactionDTO;
import org.springframework.stereotype.Service;

@Service
public class AntiFraudServiceImpl implements AntiFraudService {
    @Override
    public AFTransactionDTO registerTransaction(AFRequest request) {
        // TODO: 17/09/2022
        return null;
    }

    @Override
    public AFResponse validateByAccount(AFRequest request) {
        // TODO: 17/09/2022
        return null;
    }

    @Override
    public AFResponse validateByUser(AFRequest request) {
        // TODO: 17/09/2022
        return null;
    }
}
