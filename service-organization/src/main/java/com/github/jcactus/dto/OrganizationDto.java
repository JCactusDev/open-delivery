package com.github.jcactus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrganizationDto {
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

    @NotNull
    private String taxNumber;

    @NotNull
    private String regNumber;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate regDate;

    private String description;
}
