package com.github.jcactus.serviceclient.mapper;

import com.github.jcactus.serviceclient.dto.ClientDto;
import com.github.jcactus.serviceclient.model.Client;
import com.github.jcactus.serviceclient.model.OrganizationType;

public class ClientMapper {

    public static Client toModel(final ClientDto dto) {
        if (dto == null) {
            return null;
        }
        Client model = new Client();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setFullName(dto.getFullName());
        model.setShortName(dto.getShortName());
        model.setInternationalName(dto.getInternationalName());
        model.setType(Enum.valueOf(OrganizationType.class, dto.getType()));
        model.setDescription(dto.getDescription());
        return model;
    }

    public static ClientDto toDto(final Client model) {
        if (model == null) {
            return null;
        }
        ClientDto dto = new ClientDto();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setFullName(model.getFullName());
        dto.setShortName(model.getShortName());
        dto.setInternationalName(model.getInternationalName());
        dto.setType(model.getType().toString());
        dto.setDescription(model.getDescription());
        return dto;
    }

}
