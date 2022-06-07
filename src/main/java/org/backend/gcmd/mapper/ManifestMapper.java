package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.ManifestDTO;
import org.backend.gcmd.entity.ManifestEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ManifestMapper implements Mapper<ManifestDTO, ManifestEntity> {

    @Override
    public Page<ManifestDTO> convertToPageDto(Page<ManifestEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public ManifestDTO convertToDto(ManifestEntity entity) {
        ManifestDTO dto = new ManifestDTO();
        dto.setId(entity.getId());
        dto.setDesignation(entity.getDesignation());
        dto.setQuantite(entity.getQuantite());
        dto.setReference(entity.getReference());
        dto.setIsDeleted(entity.getIsDeleted());
        if (entity.getEscale() != null)
            dto.setEscaleId(entity.getEscale().getId());
        return dto;
    }

    @Override
    public ManifestEntity convertToEntity(ManifestDTO dto) {
        ManifestEntity entity = new ManifestEntity();
        entity.setId(dto.getId());
        entity.setDesignation(dto.getDesignation());
        entity.setQuantite(dto.getQuantite());
        entity.setReference(dto.getReference());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<ManifestDTO> convertToDtoList(List<ManifestEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<ManifestEntity> convertToEntitiesList(List<ManifestDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
