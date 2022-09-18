package com.ironhack.vbnk_antifraudservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter @Setter
@NoArgsConstructor
@Entity
public class AFTransaction {
    @Id
    @GeneratedValue(generator = "uuid2") @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column( updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    private BigDecimal amount;

    private String srcAccountNumber,senderId;
    @Embedded
    private AFResponse result;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant transactionDate;

    public static AFTransaction fromDTO(AFTransactionDTO dto){
        return new AFTransaction()
                .setTransactionDate(dto.getTransactionDate())
                .setAmount(dto.getAmount())
                .setSenderId(dto.getSenderId())
                .setSrcAccountNumber(dto.getSrcAccountNumber());
    }
}