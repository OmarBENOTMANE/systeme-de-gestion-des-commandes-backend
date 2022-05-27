package org.backend.gcmd.service;

import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.dto.LigneBpDTO;
import org.backend.gcmd.dto.LigneCommandeDTO;
import org.backend.gcmd.dto.PrestationDTO;
import org.backend.gcmd.entity.LigneBpEntity;
import org.backend.gcmd.entity.LigneCommandeEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.CommandeMapper;
import org.backend.gcmd.mapper.LigneBpMapper;
import org.backend.gcmd.mapper.LigneCommandeMapper;
import org.backend.gcmd.mapper.PrestationMapper;
import org.backend.gcmd.repository.LigneBpRepository;
import org.backend.gcmd.repository.LigneCommandeRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LigneCommandeService {

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private LigneCommandeMapper ligneCommandeMapper;

    @Autowired
    private LigneBpService ligneBpService;
    @Autowired
    private LigneBpRepository ligneBpRepository;
    @Autowired
    private LigneBpMapper ligneBpMapper;

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private CommandeMapper commandeMapper;

    @Autowired
    private PrestationService prestationService;
    @Autowired
    private PrestationMapper prestationMapper;

    public LigneCommandeDTO findById(Long id) {
        Validate.notNull(id, "id must be not null");
        Optional<LigneCommandeEntity> entity = ligneCommandeRepository.findById(id);
        if (entity.isPresent()) {
            return ligneCommandeMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("LigneCommandeDTO not found");
        }
    }

    public LigneCommandeDTO save(LigneCommandeDTO dto) {
        Validate.notNull(dto, "LigneCommandeDTO must be not null");
        LigneCommandeEntity entity = ligneCommandeMapper.convertToEntity(dto);
        LigneCommandeEntity saved = ligneCommandeRepository.save(entity);
        return ligneCommandeMapper.convertToDto(saved);
    }

    public LigneCommandeDTO update(LigneCommandeDTO dto) {
        Validate.notNull(dto, "LigneCommandeDTO must be not null");
        Validate.notNull(dto.getId(), "LigneCommandeDTO id must be not null");
        findById(dto.getId());
        LigneCommandeEntity entity = ligneCommandeMapper.convertToEntity(dto);
        if (dto.getCommandeId() != null) {
            CommandeDTO commandeDTO = commandeService.findById(dto.getCommandeId());
            entity.setCommande(commandeMapper.convertToEntity(commandeDTO));
        }
        if (dto.getPrestationId() != null) {
            PrestationDTO prestationDTO = prestationService.findById(dto.getPrestationId());
            entity.setPrestation(prestationMapper.convertToEntity(prestationDTO));
        }
        LigneCommandeEntity saved = ligneCommandeRepository.save(entity);
        return ligneCommandeMapper.convertToDto(saved);
    }

    public Page<LigneCommandeDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<LigneCommandeEntity> page = ligneCommandeRepository.findAllByIsDeletedFalse(pageable);
        return ligneCommandeMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        LigneCommandeDTO dto = findById(id);
        dto.setIsDeleted(true);
        LigneCommandeEntity entity = ligneCommandeMapper.convertToEntity(dto);
        ligneCommandeRepository.save(entity);
    }

    public LigneCommandeDTO affecter(Long id) {
        Validate.notNull(id, "LigneCommandeDTO must be not null");
        LigneCommandeDTO lcdto = findById(id);
        if (lcdto.getIsAffected() != null && !lcdto.getIsAffected()) {
            LigneBpDTO lbpdto = genererbp(lcdto);
            lcdto.setGenlbp(lbpdto.getId());
            lcdto.setIsAffected(true);
            update(lcdto);
        } else {
            LigneBpDTO lbpdto = ligneBpService.findById(lcdto.getGenlbp());
            lbpdto.setIsDeleted(true);
            ligneBpService.update(lbpdto);
            lcdto.setGenlbp(null);
            lcdto.setIsAffected(false);
            update(lcdto);
        }
        return lcdto;
    }

    public LigneBpDTO genererbp(LigneCommandeDTO ligneCommandeDTO) {
        LigneBpDTO ligneBpDTO = new LigneBpDTO();
        ligneBpDTO.setDesignationPrestation(ligneCommandeDTO.getDesignationPrestation());
        ligneBpDTO.setDate(ligneCommandeDTO.getDate());
        ligneBpDTO.setHeure(ligneCommandeDTO.getHeure());
        ligneBpDTO.setSensTrafic(ligneCommandeDTO.getSensTrafic());
        ligneBpDTO.setTcSuppl(ligneCommandeDTO.getTcSuppl());
        ligneBpDTO.setTcConv(ligneCommandeDTO.getTcConv());
        ligneBpDTO.setNombre(ligneCommandeDTO.getNombre());
        ligneBpDTO.setTarifUnifie(ligneCommandeDTO.getTarifUnifie());
        ligneBpDTO.setTonnageReel(ligneCommandeDTO.getTonnageReel());
        ligneBpDTO.setTonnageMinimum(ligneCommandeDTO.getTonnageMinimum());
        LigneBpEntity entity = ligneBpMapper.convertToEntity(ligneBpDTO);
        ligneBpRepository.save(entity);
        return ligneBpMapper.convertToDto(entity);
    }

}