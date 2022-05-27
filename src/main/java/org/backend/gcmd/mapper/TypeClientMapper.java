package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.TypeClientDTO;
import org.backend.gcmd.entity.TypeClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypeClientMapper implements Mapper<TypeClientDTO, TypeClientEntity> {

    @Override
    public Page<TypeClientDTO> convertToPageDto(Page<TypeClientEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public TypeClientDTO convertToDto(TypeClientEntity entity) {
        TypeClientDTO dto = new TypeClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public TypeClientEntity convertToEntity(TypeClientDTO dto) {
        TypeClientEntity entity = new TypeClientEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<TypeClientDTO> convertToDtoList(List<TypeClientEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<TypeClientEntity> convertToEntitiesList(List<TypeClientDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
