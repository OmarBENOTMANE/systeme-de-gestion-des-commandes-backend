package org.backend.gcmd.mapper.AS400;

import org.backend.gcmd.dto.AS400.PrestationDTO;
import org.backend.gcmd.entity.AS400.PrestationEntity;
import org.backend.gcmd.mapper.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("prestationMapperAS400")
public class PrestationMapper implements Mapper<PrestationDTO, PrestationEntity> {

    @Override
    public Page<PrestationDTO> convertToPageDto(Page<PrestationEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public PrestationDTO convertToDto(PrestationEntity entity) {
        PrestationDTO dto = new PrestationDTO();
        dto.setId(entity.getId());
        dto.setCodeCpa(entity.getCodeCpa());
        dto.setNumeroDossier(entity.getNumeroDossier());
        dto.setNumeroBonCommande(entity.getNumeroBonCommande());
        dto.setNumeroOrdrePrestation(entity.getNumeroOrdrePrestation());
        dto.setCodePrestation(entity.getCodePrestation());
        dto.setDateDebut(entity.getDateDebut());
        dto.setHeureDebut(entity.getHeureDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setHeureFin(entity.getHeureFin());
        dto.setPoids(entity.getPoids());
        dto.setQuantite1(entity.getQuantite1());
        dto.setQuantite2(entity.getQuantite2());
        dto.setImportExport(entity.getImportExport());
        dto.setNumeroDeclaration(entity.getNumeroDeclaration());
        return dto;
    }

    @Override
    public PrestationEntity convertToEntity(PrestationDTO dto) {
        PrestationEntity entity = new PrestationEntity();
        entity.setId(dto.getId());
entity.setCodeCpa(dto.getCodeCpa());
entity.setNumeroDossier(dto.getNumeroDossier());
entity.setNumeroBonCommande(dto.getNumeroBonCommande());
entity.setNumeroOrdrePrestation(dto.getNumeroOrdrePrestation());
entity.setCodePrestation(dto.getCodePrestation());
entity.setDateDebut(dto.getDateDebut());
entity.setHeureDebut(dto.getHeureDebut());
entity.setDateFin(dto.getDateFin());
entity.setHeureFin(dto.getHeureFin());
entity.setPoids(dto.getPoids());
entity.setQuantite1(dto.getQuantite1());
entity.setQuantite2(dto.getQuantite2());
entity.setImportExport(dto.getImportExport());
entity.setNumeroDeclaration(dto.getNumeroDeclaration());

        entity.setIsDeleted(dto.getIsDeleted());
        return entity;
    }

    @Override
    public List<PrestationDTO> convertToDtoList(List<PrestationEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<PrestationEntity> convertToEntitiesList(List<PrestationDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
