package com.ironhack.vbnk_antifraudservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
public class AFResponse {
    int validationSpamBot=-1;
    int validationSpamHuman=-1;
    int validationReiterateTrans=-1;
    int validationAmountAVG=-1;
    int validationLegalReq=-1;

    boolean allValidated;
}
