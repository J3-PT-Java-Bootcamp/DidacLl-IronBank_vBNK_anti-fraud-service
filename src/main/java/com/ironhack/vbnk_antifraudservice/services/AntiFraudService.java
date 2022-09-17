package com.ironhack.vbnk_antifraudservice.services;

import com.ironhack.vbnk_antifraudservice.model.AFRequest;
import com.ironhack.vbnk_antifraudservice.model.AFResponse;
import com.ironhack.vbnk_antifraudservice.model.AFTransactionDTO;

import java.util.List;

public interface AntiFraudService {

    AFTransactionDTO registerTransaction(AFRequest request);

    AFResponse validateByAccount(AFRequest request);

    AFResponse validateByUser(AFRequest request);
//    List<AFTransactionDTO> getLast48HoursTransactions(String userId, String accountRef);
//    List<AFTransactionDTO> getLast24HoursUserTransactions





}
