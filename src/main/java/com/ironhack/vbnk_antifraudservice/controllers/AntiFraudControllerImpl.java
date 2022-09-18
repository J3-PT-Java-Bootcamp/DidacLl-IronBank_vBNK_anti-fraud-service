package com.ironhack.vbnk_antifraudservice.controllers;

import com.ironhack.vbnk_antifraudservice.model.AFRequest;
import com.ironhack.vbnk_antifraudservice.model.AFResponse;
import com.ironhack.vbnk_antifraudservice.services.AntiFraudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/af")
public class AntiFraudControllerImpl implements AntiFraudController {
    @Autowired
    AntiFraudService service;
    @Override
    @PatchMapping("/client/validate")
    public AFResponse validateTransaction(@RequestBody AFRequest request) {
        if((request.getAmount()==null||request.getAmount().compareTo(BigDecimal.ZERO)<=0)||
                ((request.getSenderId()==null|| request.getSenderId().equalsIgnoreCase("")))&&
                        ((request.getSrcAccountNumber()==null|| request.getSrcAccountNumber().equalsIgnoreCase(""))))
            return new AFResponse();

        AFResponse res=  request.getSenderId()==null?
                service.validateByAccount(request):
                service.validateByUser(request);

        return service.registerTransaction(request,res);
    }
    @Override
    @GetMapping("/v1/af/service/update")
    public void updateService(){
        // TODO: 18/09/2022 check >48h Transactions and make a log
    }
}
