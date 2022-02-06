package com.example.pdpspringsecurity4thlessonhomework.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeDto {

    @NotNull(message = "Carta raqam bo'sh bo'lmasligi kerak")
    private long toCardId;
    @NotNull(message = "summa bo'sh bo'lmasligi kerak")
    private double amount;
}
