package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.ClientDTO;
import org.backend.gcmd.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper implements Mapper<ClientDTO, ClientEntity> {

    @Override
    public Page<ClientDTO> convertToPageDto(Page<ClientEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public ClientDTO convertToDto(ClientEntity entity) {
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setIsDeleted(entity.getIsDeleted());
        if (entity.getTypeClient() != null)
            dto.setTypeClientId(entity.getTypeClient().getId());
        return dto;
    }

    @Override
    public ClientEntity convertToEntity(ClientDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<ClientDTO> convertToDtoList(List<ClientEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<ClientEntity> convertToEntitiesList(List<ClientDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
