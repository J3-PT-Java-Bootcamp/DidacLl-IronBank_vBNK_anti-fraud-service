package com.ironhack.vbnk_antifraudservice.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AntiFraudRepositoryTest {
    @Autowired
    AntiFraudRepository repository;
    @Test
    void findAllBySrcAccountNumberOrderByTransactionDateDesc_test() {
        var list = repository.findAllBySrcAccountNumberOrderByTransactionDateDesc("9b0ce13a-4e8f-4568-b935-c8e34ac1f219");
        for(var elem: list) System.out.println(elem.toString());
    }

    @Test
    void findAllBySenderIdOrderByTransactionDateDesc_test() {
    }

    @Test
    void findAllByTransactionDateBefore_test() {
    }
}