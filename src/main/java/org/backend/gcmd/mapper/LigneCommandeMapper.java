package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.LigneCommandeDTO;
import org.backend.gcmd.entity.LigneCommandeEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LigneCommandeMapper implements Mapper<LigneCommandeDTO, LigneCommandeEntity> {

    @Override
    public Page<LigneCommandeDTO> convertToPageDto(Page<LigneCommandeEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public LigneCommandeDTO convertToDto(LigneCommandeEntity entity) {
        LigneCommandeDTO dto = new LigneCommandeDTO();
        dto.setId(entity.getId());
        dto.setTcConv(entity.getTcConv());
        dto.setDesignationPrestation(entity.getDesignationPrestation());
        dto.setDescription(entity.getDescription());
        dto.setDate(entity.getDate());
        dto.setHeure(entity.getHeure());
        dto.setNombre(entity.getNombre());
        dto.setProduit(entity.getProduit());
        dto.setSensTrafic(entity.getSensTrafic());
        dto.setTarifUnifie(entity.getTarifUnifie());
        dto.setTcSuppl(entity.getTcSuppl());
        dto.setTonnageMinimum(entity.getTonnageMinimum());
        dto.setTonnageReel(entity.getTonnageReel());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setIsAffected(entity.getIsAffected());
        if (entity.getCommande() != null)
            dto.setCommandeId(entity.getCommande().getId());
        if (entity.getPrestation() != null)
            dto.setPrestationId(entity.getPrestation().getId());
        dto.setGenlbp(entity.getGenlbp());
        return dto;
    }

    @Override
    public LigneCommandeEntity convertToEntity(LigneCommandeDTO dto) {
        LigneCommandeEntity entity = new LigneCommandeEntity();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setDesignationPrestation(dto.getDesignationPrestation());
        entity.setHeure(dto.getHeure());
        entity.setNombre(dto.getNombre());
        entity.setProduit(dto.getProduit());
        entity.setSensTrafic(dto.getSensTrafic());
        entity.setTarifUnifie(dto.getTarifUnifie());
        entity.setTcConv(dto.getTcConv());
        entity.setTcSuppl(dto.getTcSuppl());
        entity.setTonnageMinimum(dto.getTonnageMinimum());
        entity.setTonnageReel(dto.getTonnageReel());
        entity.setIsDeleted(dto.getIsDeleted());
        entity.setIsAffected(dto.getIsAffected());
        entity.setGenlbp(dto.getGenlbp());
        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<LigneCommandeDTO> convertToDtoList(List<LigneCommandeEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<LigneCommandeEntity> convertToEntitiesList(List<LigneCommandeDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
