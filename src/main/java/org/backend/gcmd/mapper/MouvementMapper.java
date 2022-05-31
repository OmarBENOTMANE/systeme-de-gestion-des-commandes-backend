package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.MouvementDTO;
import org.backend.gcmd.entity.MouvementEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MouvementMapper implements Mapper<MouvementDTO, MouvementEntity> {

    @Override
    public Page<MouvementDTO> convertToPageDto(Page<MouvementEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public MouvementDTO convertToDto(MouvementEntity entity) {
        MouvementDTO dto = new MouvementDTO();
        dto.setId(entity.getId());
        dto.setDateMouvement(entity.getDateMouvement());
        dto.setDescription(entity.getDescription());
        dto.setIsDeleted(entity.getIsDeleted());
        if (entity.getEscale() != null)
            dto.setEscaleId(entity.getEscale().getId());
        return dto;
    }

    @Override
    public MouvementEntity convertToEntity(MouvementDTO dto) {
        MouvementEntity entity = new MouvementEntity();
        entity.setId(dto.getId());
        entity.setDateMouvement(dto.getDateMouvement());
        entity.setDescription(dto.getDescription());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<MouvementDTO> convertToDtoList(List<MouvementEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<MouvementEntity> convertToEntitiesList(List<MouvementDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
