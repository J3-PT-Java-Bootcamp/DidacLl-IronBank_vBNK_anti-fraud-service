package com.ironhack.vbnk_antifraudservice.services;

import com.ironhack.vbnk_antifraudservice.model.AFRequest;
import com.ironhack.vbnk_antifraudservice.model.AFResponse;
import com.ironhack.vbnk_antifraudservice.model.AFTransaction;
import com.ironhack.vbnk_antifraudservice.repositories.AntiFraudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AntiFraudServiceImpl implements AntiFraudService {
    @Autowired
    AntiFraudRepository afRepository;
    
    @Override
    public AFResponse registerTransaction(AFRequest request, AFResponse res) {
        afRepository.save(
                new AFTransaction()
                        .setResult(res)
                        .setSenderId(request.getSenderId())
                        .setAmount(request.getAmount())
                        .setSrcAccountNumber(request.getSrcAccountNumber())
        );
        return res;
    }

    @Override
    public AFResponse validateByAccount(AFRequest request) {
        return mainValidation(request,request.getSrcAccountNumber());
    }

    @Override
    public AFResponse validateByUser(AFRequest request) {
        return mainValidation(request,request.getSenderId());
    }
    
    @Override
    public AFResponse mainValidation(AFRequest request, String ref){
        return new AFResponse()
                .setValidationLegalReq(validateLegalRequeriments(request))
                .setValidationSpamBot(validateSpamBot(request,ref))
                .setValidationSpamHuman(validateSpamHuman(request,ref))
                .setValidationReiterateTrans(validateReiterateTransactions(request,ref))
                .setValidationAmountAVG(validateAmountAVG(request,ref))
                .setAllValidated(true);
    }

    private int validateAmountAVG(AFRequest request, String ref) {
        // TODO: 18/09/2022  
        return 0;
    }

    private int validateReiterateTransactions(AFRequest request, String ref) {
        // TODO: 18/09/2022  
        return 0;
    }

    private int validateSpamHuman(AFRequest request, String ref) {
        // TODO: 18/09/2022  
        return 0;
    }

    private int validateSpamBot(AFRequest request, String ref) {
        // TODO: 18/09/2022  
        return 0;
    }

    private int validateLegalRequeriments(AFRequest request) {
        // TODO: 18/09/2022  
        return 0;
    }
}
