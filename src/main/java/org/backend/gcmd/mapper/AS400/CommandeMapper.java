package org.backend.gcmd.mapper.AS400;


import org.backend.gcmd.dto.AS400.CommandeDTO;
import org.backend.gcmd.entity.AS400.CommandeEntity;
import org.backend.gcmd.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component("commandeMapperAS400")
public class CommandeMapper implements Mapper<CommandeDTO, CommandeEntity> {

    @Override
    public Page<CommandeDTO> convertToPageDto(Page<CommandeEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public CommandeDTO convertToDto(CommandeEntity entity) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(entity.getId());
        dto.setCodeCpa(entity.getCodeCpa());
        dto.setNumeroDossier(entity.getNumeroDossier());
        dto.setNumeroBonCommande(entity.getNumeroBonCommande());
        dto.setDateCreationCommande(entity.getDateCreationCommande());
        dto.setDateExecutionCommande(entity.getDateExecutionCommande());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public CommandeEntity convertToEntity(CommandeDTO dto) {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(dto.getId());
        entity.setCodeCpa(dto.getCodeCpa());
        entity.setNumeroDossier(dto.getNumeroDossier());
        entity.setNumeroBonCommande(dto.getNumeroBonCommande());
        entity.setDateCreationCommande(dto.getDateCreationCommande());
        entity.setDateExecutionCommande(dto.getDateExecutionCommande());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<CommandeDTO> convertToDtoList(List<CommandeEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<CommandeEntity> convertToEntitiesList(List<CommandeDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
