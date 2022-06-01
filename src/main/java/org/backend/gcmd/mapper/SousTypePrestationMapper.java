package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.SousTypePrestationDTO;
import org.backend.gcmd.entity.SousTypePrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SousTypePrestationMapper implements Mapper<SousTypePrestationDTO, SousTypePrestationEntity> {

    @Override
    public Page<SousTypePrestationDTO> convertToPageDto(Page<SousTypePrestationEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public SousTypePrestationDTO convertToDto(SousTypePrestationEntity entity) {
        SousTypePrestationDTO dto = new SousTypePrestationDTO();
        dto.setId(entity.getId());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIsDeleted(entity.getIsDeleted());
        if (entity.getTypePrestation() != null) {
            dto.setTypePrestationId(entity.getTypePrestation().getId());
        }
        return dto;
    }

    @Override
    public SousTypePrestationEntity convertToEntity(SousTypePrestationDTO dto) {
        SousTypePrestationEntity entity = new SousTypePrestationEntity();
        entity.setId(dto.getId());
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<SousTypePrestationDTO> convertToDtoList(List<SousTypePrestationEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<SousTypePrestationEntity> convertToEntitiesList(List<SousTypePrestationDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
