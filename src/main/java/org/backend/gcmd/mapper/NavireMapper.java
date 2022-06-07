package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.NavireDTO;
import org.backend.gcmd.entity.NavireEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NavireMapper implements Mapper<NavireDTO, NavireEntity> {

    @Override
    public Page<NavireDTO> convertToPageDto(Page<NavireEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public NavireDTO convertToDto(NavireEntity entity) {
        NavireDTO dto = new NavireDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNumeroLlyod(entity.getNumeroLlyod());
        dto.setLongeur(entity.getLongeur());
        dto.setTiranteau(entity.getTiranteau());
        dto.setTypeNavire(entity.getTypeNavire());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public NavireEntity convertToEntity(NavireDTO dto) {
        NavireEntity entity = new NavireEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setNumeroLlyod(dto.getNumeroLlyod());
        entity.setLongeur(dto.getLongeur());
        entity.setTypeNavire(dto.getTypeNavire());
        entity.setTiranteau(dto.getTiranteau());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<NavireDTO> convertToDtoList(List<NavireEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<NavireEntity> convertToEntitiesList(List<NavireDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
