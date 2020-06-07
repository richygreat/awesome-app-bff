package com.github.richygreat.aab.buyer.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BuyerCreationDto {
    @NotEmpty
    private String emailAddress;
    @NotEmpty
    private String password;
}
