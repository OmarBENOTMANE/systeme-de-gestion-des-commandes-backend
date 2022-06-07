package org.backend.gcmd.mapper.AS400;

import org.backend.gcmd.dto.AS400.EscaleDTO;
import org.backend.gcmd.entity.AS400.EscaleEntity;
import org.backend.gcmd.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("EscaleMapperAS400")
public class EscaleMapper implements Mapper<EscaleDTO, EscaleEntity> {

    @Override
    public Page<EscaleDTO> convertToPageDto(Page<EscaleEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public EscaleDTO convertToDto(EscaleEntity entity) {
        EscaleDTO dto = new EscaleDTO();
        dto.setId(entity.getId());
        dto.setNumeroEscale(entity.getNumeroEscale());
        dto.setNomNavire(entity.getNomNavire());
        dto.setNumeroLloyd(entity.getNumeroLloyd());
        dto.setTypeNavire(entity.getTypeNavire());
        dto.setDateArrivéeEstimative(entity.getDateArrivéeEstimative());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public EscaleEntity convertToEntity(EscaleDTO dto) {
        EscaleEntity entity = new EscaleEntity();
        entity.setId(dto.getId());
        entity.setNumeroEscale(dto.getNumeroEscale());
        entity.setNomNavire(dto.getNomNavire());
        entity.setNumeroLloyd(dto.getNumeroLloyd());
        entity.setTypeNavire(dto.getTypeNavire());
        entity.setDateArrivéeEstimative(dto.getDateArrivéeEstimative());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<EscaleDTO> convertToDtoList(List<EscaleEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<EscaleEntity> convertToEntitiesList(List<EscaleDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}

