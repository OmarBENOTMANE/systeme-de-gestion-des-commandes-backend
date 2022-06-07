package org.backend.gcmd.mapper.AS400;

import org.backend.gcmd.dto.AS400.TypeNavireDTO;
import org.backend.gcmd.entity.AS400.TypeNavireEntity;
import org.backend.gcmd.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("typeNavireMapperAS400")
public class TypeNavireMapper implements Mapper<TypeNavireDTO, TypeNavireEntity> {

    @Override
    public Page<TypeNavireDTO> convertToPageDto(Page<TypeNavireEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public TypeNavireDTO convertToDto(TypeNavireEntity entity) {
        TypeNavireDTO dto = new TypeNavireDTO();
        dto.setId(entity.getId());
        dto.setTypeNavire(entity.getTypeNavire());
        dto.setLibelle17(entity.getLibelle17());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public TypeNavireEntity convertToEntity(TypeNavireDTO dto) {
        TypeNavireEntity entity = new TypeNavireEntity();
        entity.setId(dto.getId());
        entity.setTypeNavire(dto.getTypeNavire());
        entity.setLibelle17(dto.getLibelle17());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<TypeNavireDTO> convertToDtoList(List<TypeNavireEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<TypeNavireEntity> convertToEntitiesList(List<TypeNavireDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }

}

