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
public class AFLog {
    @Id
    @GenericGenerator(name = "date_based_id_generator", strategy =
            "com.ironhack.vbnk_antifraudservice.utils.AFLogIdGenerator")
    @GeneratedValue(generator = "date_based_id_generator")
    private String id;

    private BigDecimal amount;

    private String srcAccountNumber,senderId;
    @Embedded
    private AFResponse result;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant transactionDate;

    public static AFLog fromDTO(AFTransactionDTO dto){
        return new AFLog()
                .setTransactionDate(dto.getTransactionDate())
                .setAmount(dto.getAmount())
                .setSenderId(dto.getSenderId())
                .setSrcAccountNumber(dto.getSrcAccountNumber());
    }
}
