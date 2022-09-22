package com.ironhack.vbnk_antifraudservice.services;

import com.ironhack.vbnk_antifraudservice.model.AFRequest;
import com.ironhack.vbnk_antifraudservice.model.AFResponse;
import com.ironhack.vbnk_antifraudservice.model.AFTransaction;
import com.ironhack.vbnk_antifraudservice.repositories.AntiFraudRepository;
import com.ironhack.vbnk_antifraudservice.utils.VBNKConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

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
                .setValidationLegalReq(validateLegalRequirements(request))
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
        var list =afRepository.findAllBySrcAccountNumberOrderByTransactionDateDesc(ref);
        if (list.isEmpty())list=afRepository.findAllBySenderIdOrderByTransactionDateDesc(ref);
        if(list.isEmpty())return 0;
        int val=0;
        for (int i = 0; i < Math.min(list.size(), 3); i++) {
            if(list.get(i).compare(request))val++;
        }
        if(val>=2) return 1;
        return 0;
    }

    private int validateSpamHuman(AFRequest request, String ref) {
        var list =afRepository.findAllBySrcAccountNumberOrderByTransactionDateDesc(ref);
        if (list.isEmpty())list=afRepository.findAllBySenderIdOrderByTransactionDateDesc(ref);
        int val=0;
        for (int i = 0; i < Math.min(list.size(), 3); i++) {
            if(list.get(i).compareSimilarity(request)>80)val++;
        }
        if(val>=2)return 1;
        return 0;
    }

    private int validateSpamBot(AFRequest request, String ref) {
        var list =afRepository.findAllBySrcAccountNumberOrderByTransactionDateDesc(ref);
        if (list.isEmpty())list=afRepository.findAllBySenderIdOrderByTransactionDateDesc(ref);
        if(!list.isEmpty()&&!(list.get(0).getTransactionDate().plus(1, ChronoUnit.SECONDS).isBefore(Instant.now())))
            return 3;
        return 0;
    }

    private int validateLegalRequirements(AFRequest request) {
        if(request.getAmount().compareTo(VBNKConfig.VBNK_LEGAL_MAX_TRANSFER_AMOUNT)>0)return 1;
        var list =afRepository.findAllBySrcAccountNumberOrderByTransactionDateDesc(request.getSrcAccountNumber());
        if (list.isEmpty())list=afRepository.findAllBySenderIdOrderByTransactionDateDesc(request.getSenderId());
        if(list.size()>VBNKConfig.VBNK_LEGAL_MAX_TRANSACTIONS)return 2;
        return 0;
    }
}
