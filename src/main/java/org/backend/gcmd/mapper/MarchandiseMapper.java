package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.MarchandiseDTO;
import org.backend.gcmd.entity.MarchandiseEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarchandiseMapper implements Mapper<MarchandiseDTO, MarchandiseEntity> {

    @Override
    public Page<MarchandiseDTO> convertToPageDto(Page<MarchandiseEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public MarchandiseDTO convertToDto(MarchandiseEntity entity) {
        MarchandiseDTO dto = new MarchandiseDTO();
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
    public MarchandiseEntity convertToEntity(MarchandiseDTO dto) {
        MarchandiseEntity entity = new MarchandiseEntity();
        entity.setId(dto.getId());
        entity.setDesignation(dto.getDesignation());
        entity.setQuantite(dto.getQuantite());
        entity.setReference(dto.getReference());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<MarchandiseDTO> convertToDtoList(List<MarchandiseEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MarchandiseEntity> convertToEntitiesList(List<MarchandiseDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
