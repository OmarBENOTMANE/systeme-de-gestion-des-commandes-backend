package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.BulltinPrestationDTO;
import org.backend.gcmd.entity.BulltinPrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BulltinPrestationMapper implements Mapper<BulltinPrestationDTO, BulltinPrestationEntity> {

    @Override
    public Page<BulltinPrestationDTO> convertToPageDto(Page<BulltinPrestationEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public BulltinPrestationDTO convertToDto(BulltinPrestationEntity entity) {
        BulltinPrestationDTO dto = new BulltinPrestationDTO();
        dto.setId(entity.getId());
        dto.setCodeClient(entity.getCodeClient());
        dto.setNomClient(entity.getNomClient());
        dto.setNumeroCmd(entity.getNumeroCmd());
        dto.setNumeroDossierPrestation(entity.getNumeroDossierPrestation());
        dto.setNumeroEscale(entity.getNumeroEscale());
        dto.setPreValidation(entity.getPreValidation());
        dto.setText(entity.getText());
        dto.setCodeNature(entity.getCodeNature());
        dto.setTypePaiement(entity.getTypePaiement());
        dto.setDate(entity.getDate());
        dto.setDateDepot(entity.getDateDepot());
        dto.setDateProbableExecution(entity.getDateProbableExecution());
        dto.setDescription(entity.getDescription());
        dto.setHeure(entity.getHeure());
        dto.setMoyenOdepClient(entity.getMoyenOdepClient());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setValidated(entity.getValidated());
        dto.setIsfactured(entity.getIsfactured());
        return dto;
    }

    @Override
    public BulltinPrestationEntity convertToEntity(BulltinPrestationDTO dto) {
        BulltinPrestationEntity entity = new BulltinPrestationEntity();
        entity.setId(dto.getId());
        entity.setCodeClient(dto.getCodeClient());
        entity.setCodeNature(dto.getCodeNature());
        entity.setDate(dto.getDate());
        entity.setDateDepot(dto.getDateDepot());
        entity.setDateProbableExecution(dto.getDateProbableExecution());
        entity.setDescription(dto.getDescription());
        entity.setHeure(dto.getHeure());
        entity.setMoyenOdepClient(dto.getMoyenOdepClient());
        entity.setNomClient(dto.getNomClient());
        entity.setNumeroCmd(dto.getNumeroCmd());
        entity.setNumeroDossierPrestation(dto.getNumeroDossierPrestation());
        entity.setNumeroEscale(dto.getNumeroEscale());
        entity.setPreValidation(dto.getPreValidation());
        entity.setText(dto.getText());
        entity.setTypePaiement(dto.getTypePaiement());
        entity.setIsDeleted(dto.getIsDeleted());
        entity.setValidated(dto.getValidated());
        entity.setIsfactured(dto.getIsfactured());
        return entity;
    }

    @Override
    public List<BulltinPrestationDTO> convertToDtoList(List<BulltinPrestationEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<BulltinPrestationEntity> convertToEntitiesList(List<BulltinPrestationDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
