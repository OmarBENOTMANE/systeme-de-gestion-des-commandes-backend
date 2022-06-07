package org.backend.gcmd.mapper.AS400;

import org.backend.gcmd.dto.AS400.LibellePrestationDTO;
import org.backend.gcmd.entity.AS400.LibellePrestationEntity;
import org.backend.gcmd.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("libellePrestationMapperAS400")
public class LibellePrestationMapper implements Mapper<LibellePrestationDTO, LibellePrestationEntity> {

    @Override
    public Page<LibellePrestationDTO> convertToPageDto(Page<LibellePrestationEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public LibellePrestationDTO convertToDto(LibellePrestationEntity entity) {
        LibellePrestationDTO dto = new LibellePrestationDTO();
        dto.setId(entity.getId());
        dto.setCodePrestation(entity.getCodePrestation());
        dto.setDesignationPrestation(entity.getDesignationPrestation());
        dto.setCodeTVA(entity.getCodeTVA());
        dto.setCodeActivite(entity.getCodeActivite());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public LibellePrestationEntity convertToEntity(LibellePrestationDTO dto) {
        LibellePrestationEntity entity = new LibellePrestationEntity();
        entity.setId(dto.getId());

        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<LibellePrestationDTO> convertToDtoList(List<LibellePrestationEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<LibellePrestationEntity> convertToEntitiesList(List<LibellePrestationDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
