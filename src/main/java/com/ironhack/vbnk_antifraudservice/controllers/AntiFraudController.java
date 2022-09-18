package com.ironhack.vbnk_antifraudservice.controllers;

import com.ironhack.vbnk_antifraudservice.model.AFRequest;
import com.ironhack.vbnk_antifraudservice.model.AFResponse;
import org.springframework.web.bind.annotation.GetMapping;

public interface AntiFraudController{


AFResponse validateTransaction(AFRequest request);


    @GetMapping("/v1/af/service/update")
    void updateService();
}
