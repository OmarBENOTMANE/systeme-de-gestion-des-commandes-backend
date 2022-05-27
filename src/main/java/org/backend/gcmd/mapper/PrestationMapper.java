package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.PrestationDTO;
import org.backend.gcmd.entity.PrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrestationMapper implements Mapper<PrestationDTO, PrestationEntity> {

    @Override
    public Page<PrestationDTO> convertToPageDto(Page<PrestationEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public PrestationDTO convertToDto(PrestationEntity entity) {
        PrestationDTO dto = new PrestationDTO();
        dto.setId(entity.getId());
        dto.setDesignation(entity.getDesignation());
        dto.setTypePrestation(entity.getTypePrestation());
        dto.setTypeTarif(entity.getTypeTarif());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public PrestationEntity convertToEntity(PrestationDTO dto) {
        PrestationEntity entity = new PrestationEntity();
        entity.setId(dto.getId());
        entity.setDesignation(dto.getDesignation());
        entity.setTypePrestation(dto.getTypePrestation());
        entity.setTypeTarif(dto.getTypeTarif());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<PrestationDTO> convertToDtoList(List<PrestationEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<PrestationEntity> convertToEntitiesList(List<PrestationDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
