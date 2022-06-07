package org.backend.gcmd.mapper.AS400;


import org.backend.gcmd.dto.AS400.CodeMouvementDTO;
import org.backend.gcmd.entity.AS400.CodeMouvementEntity;
import org.backend.gcmd.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component("codeMouvementMapperAS400")
public class CodeMouvementMapper implements Mapper<CodeMouvementDTO, CodeMouvementEntity> {

    @Override
    public Page<CodeMouvementDTO> convertToPageDto(Page<CodeMouvementEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public CodeMouvementDTO convertToDto(CodeMouvementEntity entity) {
        CodeMouvementDTO dto = new CodeMouvementDTO();
        dto.setId(entity.getId());
        dto.setCodeMouvementNavire(entity.getCodeMouvementNavire());
        dto.setLibelle(entity.getLibelle());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public CodeMouvementEntity convertToEntity(CodeMouvementDTO dto) {
        CodeMouvementEntity entity = new CodeMouvementEntity();
        entity.setId(dto.getId());
        entity.setCodeMouvementNavire(dto.getCodeMouvementNavire());
        entity.setLibelle(dto.getLibelle());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<CodeMouvementDTO> convertToDtoList(List<CodeMouvementEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<CodeMouvementEntity> convertToEntitiesList(List<CodeMouvementDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}