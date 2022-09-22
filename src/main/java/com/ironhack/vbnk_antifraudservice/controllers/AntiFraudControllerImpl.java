package com.ironhack.vbnk_antifraudservice.controllers;

import com.ironhack.vbnk_antifraudservice.model.AFRequest;
import com.ironhack.vbnk_antifraudservice.model.AFResponse;
import com.ironhack.vbnk_antifraudservice.services.AntiFraudService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/af")
public class AntiFraudControllerImpl implements AntiFraudController {
    final AntiFraudService service;

    public AntiFraudControllerImpl(AntiFraudService service) {
        this.service = service;
    }

    @Override @PatchMapping("/client/validate")
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
    @Hidden @Override
    @GetMapping("/client/test/{ping}")
    public String ping(@PathVariable(name = "ping") String ping) {
        return ping.replace('i','o');
    }

    @Override @GetMapping("/v1/af/service/update")
    public void updateAFLog(){

    }
}
