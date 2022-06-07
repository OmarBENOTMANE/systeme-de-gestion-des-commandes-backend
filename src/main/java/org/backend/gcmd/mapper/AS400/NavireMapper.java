package org.backend.gcmd.mapper.AS400;

import org.backend.gcmd.dto.AS400.NavireDTO;
import org.backend.gcmd.entity.AS400.NavireEntity;
import org.backend.gcmd.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("navireMapperAS400")
public class NavireMapper implements Mapper<NavireDTO, NavireEntity> {

    @Override
    public Page<NavireDTO> convertToPageDto(Page<NavireEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public NavireDTO convertToDto(NavireEntity entity) {
        NavireDTO dto = new NavireDTO();
        dto.setId(entity.getId());
        dto.setNumeroLloyd(entity.getNumeroLloyd());
        dto.setNomNavire(entity.getNomNavire());
        dto.setLongeurNavire(entity.getLongeurNavire());
        dto.setTirantEau(entity.getTirantEau());
        dto.setTypeNavire(entity.getTypeNavire());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public NavireEntity convertToEntity(NavireDTO dto) {
        NavireEntity entity = new NavireEntity();
        entity.setId(dto.getId());
        entity.setNumeroLloyd(dto.getNumeroLloyd());
        entity.setNomNavire(dto.getNomNavire());
        entity.setLongeurNavire(dto.getLongeurNavire());
        entity.setTirantEau(dto.getTirantEau());
        entity.setTypeNavire(dto.getTypeNavire());
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