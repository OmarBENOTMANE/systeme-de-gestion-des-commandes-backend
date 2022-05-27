package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.UniteOrganisationelDTO;
import org.backend.gcmd.entity.UniteOrganisationelEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UniteOrganisationelMapper implements Mapper<UniteOrganisationelDTO, UniteOrganisationelEntity> {

    @Override
    public Page<UniteOrganisationelDTO> convertToPageDto(Page<UniteOrganisationelEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public UniteOrganisationelDTO convertToDto(UniteOrganisationelEntity entity) {
        UniteOrganisationelDTO dto = new UniteOrganisationelDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setLabel(entity.getLabel());
        dto.setType(entity.getType());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public UniteOrganisationelEntity convertToEntity(UniteOrganisationelDTO dto) {
        UniteOrganisationelEntity entity = new UniteOrganisationelEntity();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setLabel(dto.getLabel());
        entity.setType(dto.getType());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<UniteOrganisationelDTO> convertToDtoList(List<UniteOrganisationelEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<UniteOrganisationelEntity> convertToEntitiesList(List<UniteOrganisationelDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
