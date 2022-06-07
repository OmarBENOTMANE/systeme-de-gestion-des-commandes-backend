package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.EscaleDTO;
import org.backend.gcmd.entity.EscaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
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
        dto.setDateArrivee(entity.getDateArrivee());
        dto.setNumeroLlyod(entity.getNumeroLlyod());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setIsfactured(entity.getIsfactured());
        if (entity.getNavire() != null)
            dto.setNavireId(entity.getNavire().getId());
        return dto;
    }

    @Override
    public EscaleEntity convertToEntity(EscaleDTO dto) {
        EscaleEntity entity = new EscaleEntity();
        entity.setId(dto.getId());
        entity.setNumeroEscale(dto.getNumeroEscale());
        entity.setNumeroLlyod(dto.getNumeroLlyod());
        entity.setDateArrivee(dto.getDateArrivee());
        entity.setIsDeleted(dto.getIsDeleted());
        entity.setIsfactured(dto.getIsfactured());
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
