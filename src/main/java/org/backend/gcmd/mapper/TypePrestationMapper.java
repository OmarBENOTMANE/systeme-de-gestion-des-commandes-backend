package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.TypePrestationDTO;
import org.backend.gcmd.entity.TypePrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypePrestationMapper implements Mapper<TypePrestationDTO, TypePrestationEntity> {

    @Override
    public Page<TypePrestationDTO> convertToPageDto(Page<TypePrestationEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public TypePrestationDTO convertToDto(TypePrestationEntity entity) {
        TypePrestationDTO dto = new TypePrestationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public TypePrestationEntity convertToEntity(TypePrestationDTO dto) {
        TypePrestationEntity entity = new TypePrestationEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<TypePrestationDTO> convertToDtoList(List<TypePrestationEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<TypePrestationEntity> convertToEntitiesList(List<TypePrestationDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
