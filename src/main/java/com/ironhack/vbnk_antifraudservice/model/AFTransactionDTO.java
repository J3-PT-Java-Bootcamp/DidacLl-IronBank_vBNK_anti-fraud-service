package com.ironhack.vbnk_antifraudservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
@Getter @Setter
@NoArgsConstructor
public class AFTransactionDTO {
    private String id;
    private BigDecimal amount;
    private String srcAccountNumber,senderId;
    private Instant transactionDate;

    public static AFTransactionDTO fromEntity(AFTransaction entity){
        return new AFTransactionDTO().setId(entity.getId())
                .setTransactionDate(entity.getTransactionDate())
                .setAmount(entity.getAmount())
                .setSenderId(entity.getSenderId())
                .setSrcAccountNumber(entity.getSrcAccountNumber());
    }
}
