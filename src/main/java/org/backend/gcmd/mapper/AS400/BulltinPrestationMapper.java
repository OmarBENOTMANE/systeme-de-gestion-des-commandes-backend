package org.backend.gcmd.mapper.AS400;

import org.backend.gcmd.dto.AS400.BulltinPrestationDTO;
import org.backend.gcmd.entity.AS400.BulltinPrestationEntity;
import org.backend.gcmd.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("bulltinPrestationMapperAS400")
public class BulltinPrestationMapper implements Mapper<BulltinPrestationDTO, BulltinPrestationEntity> {

    @Override
    public Page<BulltinPrestationDTO> convertToPageDto(Page<BulltinPrestationEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public BulltinPrestationDTO convertToDto(BulltinPrestationEntity entity) {
        BulltinPrestationDTO dto = new BulltinPrestationDTO();
        dto.setId(entity.getId());
        dto.setNumeroBulletinPrestation(entity.getNumeroBulletinPrestation());
        dto.setCodeCpa(entity.getCodeCpa());
        dto.setCodeOperation(entity.getCodeOperation());
        dto.setCodeClient(entity.getCodeClient());
        dto.setNomClient(entity.getNomClient());
        dto.setNumeroEscale(entity.getNumeroEscale());
        dto.setCodeOperation1(entity.getCodeOperation1());
        dto.setCodeOperation2(entity.getCodeOperation2());
        dto.setCodeOperation3(entity.getCodeOperation3());
        dto.setCodeNaturePrestation(entity.getCodeNaturePrestation());
        dto.setBulltinAnnule(entity.getBulltinAnnule());
        dto.setCodeCauseAnnulation(entity.getCodeCauseAnnulation());
        dto.setNumeroFacture(entity.getNumeroFacture());
        dto.setDateLimiteFacture(entity.getDateLimiteFacture());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    @Override
    public BulltinPrestationEntity convertToEntity(BulltinPrestationDTO dto) {
        BulltinPrestationEntity entity = new BulltinPrestationEntity();
        entity.setId(dto.getId());
        entity.setNumeroBulletinPrestation(dto.getNumeroBulletinPrestation());
        entity.setCodeCpa(dto.getCodeCpa());
        entity.setCodeOperation(dto.getCodeOperation());
        entity.setCodeClient(dto.getCodeClient());
        entity.setNomClient(dto.getNomClient());
        entity.setNumeroEscale(dto.getNumeroEscale());
        entity.setCodeOperation1(dto.getCodeOperation1());
        entity.setCodeOperation2(dto.getCodeOperation2());
        entity.setCodeOperation3(dto.getCodeOperation3());
        entity.setCodeNaturePrestation(dto.getCodeNaturePrestation());
        entity.setBulltinAnnule(dto.getBulltinAnnule());
        entity.setCodeCauseAnnulation(dto.getCodeCauseAnnulation());
        entity.setNumeroFacture(dto.getNumeroFacture());
        entity.setDateLimiteFacture(dto.getDateLimiteFacture());
        entity.setIsDeleted(dto.getIsDeleted());
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
