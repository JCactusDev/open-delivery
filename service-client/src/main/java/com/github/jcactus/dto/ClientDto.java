package com.github.jcactus.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String fullName;

    @NotNull
    private String shortName;

    private String internationalName;

    @NotNull
    private String type;

    private String description;

}
