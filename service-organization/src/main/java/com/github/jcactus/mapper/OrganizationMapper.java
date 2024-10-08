package com.github.jcactus.mapper;

import com.github.jcactus.dto.OrganizationDto;
import com.github.jcactus.model.Organization;
import com.github.jcactus.model.OrganizationType;

public class OrganizationMapper {

    public static Organization toModel(final OrganizationDto dto) {
        if (dto == null) {
            return null;
        }
        Organization model = new Organization();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setFullName(dto.getFullName());
        model.setShortName(dto.getShortName());
        model.setInternationalName(dto.getInternationalName());
        model.setType(Enum.valueOf(OrganizationType.class, dto.getType()));
        model.setTaxNumber(dto.getTaxNumber());
        model.setRegNumber(dto.getRegNumber());
        model.setRegDate(dto.getRegDate());
        model.setDescription(dto.getDescription());
        return model;
    }

    public static OrganizationDto toDto(final Organization model) {
        if (model == null) {
            return null;
        }
        OrganizationDto dto = new OrganizationDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setFullName(model.getFullName());
        dto.setShortName(model.getShortName());
        dto.setInternationalName(model.getInternationalName());
        dto.setType(model.getType().toString());
        dto.setTaxNumber(model.getTaxNumber());
        dto.setRegNumber(model.getRegNumber());
        dto.setRegDate(model.getRegDate());
        dto.setDescription(model.getDescription());
        return dto;
    }

}
