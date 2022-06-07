package org.backend.gcmd.service;

import org.backend.gcmd.dto.*;
import org.backend.gcmd.entity.CommandeEntity;
import org.backend.gcmd.entity.EscaleEntity;
import org.backend.gcmd.entity.LigneCommandeEntity;
import org.backend.gcmd.entity.MouvementEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.*;
import org.backend.gcmd.repository.CommandeRepository;
import org.backend.gcmd.repository.EscaleRepository;
import org.backend.gcmd.repository.LigneCommandeRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EscaleService {

    @Autowired
    private EscaleRepository escaleRepository;
    @Autowired
    private EscaleMapper escaleMapper;

    @Autowired
    private NavireService navireService;
    @Autowired
    private NavireMapper navireMapper;

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private CommandeMapper commandeMapper;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private LigneCommandeMapper ligneCommandeMapper;

    @Autowired
    private MouvementMapper mouvementMapper;

    public EscaleDTO findById(Long id) {
        Validate.notNull(id, "findby: id mus be not null");
        Optional<EscaleEntity> entity = escaleRepository.findById(id);
        if (entity.isPresent()) {
            return escaleMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("findby: EscaleDTO not found");
        }
    }

    public EscaleDTO save(EscaleDTO dto) {
        Validate.notNull(dto, "save: EscaleDTO must be not null");
        EscaleEntity entity = escaleMapper.convertToEntity(dto);
        if (dto.getNavireId() != null) {
            NavireDTO navireDTO = navireService.findById(dto.getNavireId());
            entity.setNavire(navireMapper.convertToEntity(navireDTO));
        }
        EscaleEntity saved = escaleRepository.save(entity);
        return escaleMapper.convertToDto(saved);
    }

    public EscaleDTO update(EscaleDTO dto) {
        Validate.notNull(dto, "update: EscaleDTO must be not null");
        Validate.notNull(dto.getId(), "update: EscaleDTO id must be not null");
        findById(dto.getId());
        EscaleEntity entity = escaleMapper.convertToEntity(dto);
        if (dto.getNavireId() != null) {
            NavireDTO navireDTO = navireService.findById(dto.getNavireId());
            entity.setNavire(navireMapper.convertToEntity(navireDTO));
        }
        EscaleEntity saved = escaleRepository.save(entity);
        return escaleMapper.convertToDto(saved);
    }


    public Page<EscaleDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<EscaleEntity> page = escaleRepository.findAllByIsDeletedFalse(pageable);
        return escaleMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "delete: Id must be not null");
        EscaleDTO edto = findById(id);
        edto.setIsDeleted(true);
        EscaleEntity entity = escaleMapper.convertToEntity(edto);
        escaleRepository.save(entity);
    }

    public CommandeDTO generateCmd(Long id) {
        Validate.notNull(id, "generateCmd: EscaleDTO must be not null");
        EscaleDTO eDto = findById(id);
        CommandeDTO cmdDto = createCmd(eDto);
        List<MouvementDTO> mvDtos = getMouvementDtoList(id);
        mvDtos.forEach(mv -> generateLigneCmd(mv, cmdDto));
        return cmdDto;
    }


    public CommandeDTO createCmd(EscaleDTO escaleDTO) {
        CommandeEntity entity = new CommandeEntity();
        // entity.setDateAmarage(escaleDTO.getLamanageDate());
        entity.setNumeroEscale(escaleDTO.getNumeroEscale());
        entity.setIsfactured(escaleDTO.getIsfactured());
        entity.setEscale(escaleMapper.convertToEntity(escaleDTO));
        CommandeEntity saved = commandeRepository.save(entity);
        return commandeMapper.convertToDto(saved);
    }

    public LigneCommandeDTO generateLigneCmd(MouvementDTO mouvementDTO, CommandeDTO commandeDTO) {
        LigneCommandeEntity lcEntity = new LigneCommandeEntity();
        // lcEntity.setDescription(mouvementDTO.getDescription());
        lcEntity.setCommande(commandeMapper.convertToEntity(commandeDTO));
        LigneCommandeEntity saved = ligneCommandeRepository.save(lcEntity);
        return ligneCommandeMapper.convertToDto(saved);
    }

    public List<MouvementDTO> getMouvementDtoList(Long id) {
        EscaleEntity escaleEntity = escaleRepository.findById(id).get();
        List<MouvementEntity> mouvementList = escaleEntity.getMouvementList();
        List<MouvementDTO> mouvementDTO = new ArrayList<>();
        mouvementList.forEach(mouvementEntity ->
                mouvementDTO.add(mouvementMapper.convertToDto(mouvementEntity)));
        return mouvementDTO;
    }


    public Page<EscaleDTO> findAllNavireAppareiller(Pageable pageable) {
        Page<EscaleEntity> page = escaleRepository.findAllNavireAppareiller(pageable);
        return escaleMapper.convertToPageDto(page);
    }
}
