package com.ironhack.vbnk_antifraudservice.controllers;

import com.ironhack.vbnk_antifraudservice.model.AFRequest;
import com.ironhack.vbnk_antifraudservice.model.AFResponse;
import com.ironhack.vbnk_antifraudservice.services.AntiFraudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/af")
public class AntiFraudControllerImpl implements AntiFraudController {
    @Autowired
    AntiFraudService service;
    @Override
    @PostMapping("/public/validate")
    public AFResponse validateTransaction(AFRequest request) {
        service.validateByAccount(request);
       var res= service.validateByUser(request);
        service.registerTransaction(request);
        return res;
    }
}
