package org.backend.gcmd.mapper.AS400;

import org.backend.gcmd.dto.AS400.ClientDTO;
import org.backend.gcmd.entity.AS400.ClientEntity;
import org.backend.gcmd.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component("clientMapperAS400")
public class ClientMapper implements Mapper<ClientDTO, ClientEntity> {

    @Override
    public Page<ClientDTO> convertToPageDto(Page<ClientEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public ClientDTO convertToDto(ClientEntity entity) {
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setCodeClient(entity.getCodeClient());
        dto.setNomClient(entity.getNomClient());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public ClientEntity convertToEntity(ClientDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setId(dto.getId());
        entity.setCodeClient(dto.getCodeClient());
        entity.setNomClient(dto.getNomClient());
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
