package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.TarifDTO;
import org.backend.gcmd.entity.TarifEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TarifMapper implements Mapper<TarifDTO, TarifEntity> {

    @Override
    public Page<TarifDTO> convertToPageDto(Page<TarifEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public TarifDTO convertToDto(TarifEntity entity) {
        TarifDTO dto = new TarifDTO();
        dto.setId(entity.getId());
        dto.setTarifHt(entity.getTarifHt());
        dto.setTarifTtc(entity.getTarifTtc());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public TarifEntity convertToEntity(TarifDTO dto) {
        TarifEntity entity = new TarifEntity();
        entity.setId(dto.getId());
        entity.setTarifHt(dto.getTarifHt());
        entity.setTarifTtc(dto.getTarifTtc());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<TarifDTO> convertToDtoList(List<TarifEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<TarifEntity> convertToEntitiesList(List<TarifDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
