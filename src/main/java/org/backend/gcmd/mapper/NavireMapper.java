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
        dto.setConsignataire(entity.getConsignataire());
        dto.setEtat(entity.getEtat());
        dto.setNavireName(entity.getNavireName());
        dto.setNumeroEscale(entity.getNumeroEscale());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public NavireEntity convertToEntity(NavireDTO dto) {
        NavireEntity entity = new NavireEntity();
        entity.setId(dto.getId());
        entity.setConsignataire(dto.getConsignataire());
        entity.setEtat(dto.getEtat());
        entity.setNavireName(dto.getNavireName());
        entity.setNumeroEscale(dto.getNumeroEscale());
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
