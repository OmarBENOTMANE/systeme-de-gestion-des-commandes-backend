package org.backend.gcmd.mapper;

import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.entity.CommandeEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandeMapper implements Mapper<CommandeDTO, CommandeEntity> {

    @Override
    public Page<CommandeDTO> convertToPageDto(Page<CommandeEntity> page) {
        return page.map(this::convertToDto);
    }

    @Override
    public CommandeDTO convertToDto(CommandeEntity entity) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(entity.getId());
        dto.setNumeroCommande(entity.getNumeroCommande());
        dto.setNumeroCredit(entity.getNumeroCredit());
        dto.setNavire(entity.getNavire());
        dto.setNumeroBc(entity.getNumeroBc());
        dto.setNumeroEscale(entity.getNumeroEscale());
        dto.setConnaissement(entity.getConnaissement());
        dto.setConsignataire(entity.getConsignataire());
        dto.setBulletinReception(entity.getBulletinReception());
        dto.setCapitaine(entity.getCapitaine());
        dto.setDateAmarage(entity.getDateAmarage());
        dto.setDateDesamarage(entity.getDateDesamarage());
        dto.setJaugeBrute(entity.getJaugeBrute());
        dto.setLht(entity.getLht());
        dto.setPoste(entity.getPoste());
        if (entity.getBulltinPrestation() != null)
            dto.setBulltinPrestationId(entity.getBulltinPrestation().getId());
        if (entity.getEscale() != null)
            dto.setEscaleId(entity.getEscale().getId());
        if (entity.getDevis() != null)
            dto.setDevisId(entity.getDevis().getId());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setIsfactured(entity.getIsfactured());
        return dto;
    }

    @Override
    public CommandeEntity convertToEntity(CommandeDTO dto) {
        CommandeEntity entity = new CommandeEntity();
        entity.setId(dto.getId());
        entity.setBulletinReception(dto.getBulletinReception());
        entity.setCapitaine(dto.getCapitaine());
        entity.setConnaissement(dto.getConnaissement());
        entity.setConsignataire(dto.getConsignataire());
        entity.setDateAmarage(dto.getDateAmarage());
        entity.setDateDesamarage(dto.getDateDesamarage());
        entity.setJaugeBrute(dto.getJaugeBrute());
        entity.setLht(dto.getLht());
        entity.setNumeroCommande(dto.getNumeroCommande());
        entity.setNumeroCredit(dto.getNumeroCredit());
        entity.setNavire(dto.getNavire());
        entity.setNumeroBc(dto.getNumeroBc());
        entity.setNumeroEscale(dto.getNumeroEscale());
        entity.setPoste(dto.getPoste());
        entity.setIsDeleted(dto.getIsDeleted());
        entity.setIsfactured(dto.getIsfactured());
        return entity;
    }

    @Override
    public List<CommandeDTO> convertToDtoList(List<CommandeEntity> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<CommandeEntity> convertToEntitiesList(List<CommandeDTO> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toCollection(ArrayList::new));
    }
}
