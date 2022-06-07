package org.backend.gcmd.service;

import org.backend.gcmd.dto.*;
import org.backend.gcmd.entity.LigneCommandeEntity;
import org.backend.gcmd.entity.PrestationEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.*;
import org.backend.gcmd.repository.LigneCommandeRepository;
import org.backend.gcmd.repository.PrestationRepository;
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
public class PrestationService {

    @Autowired
    private PrestationRepository prestationRepository;
    @Autowired
    private PrestationMapper prestationMapper;

    @Autowired
    private TarifService tarifService;
    @Autowired
    private TarifMapper tarifMapper;

    @Autowired
    private SousTypePrestationService sousTypePrestationService;
    @Autowired
    private SousTypePrestationMapper sousTypePrestationMapper;

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private CommandeMapper commandeMapper;

    @Autowired
    private LigneCommandeMapper ligneCommandeMapper;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    public PrestationDTO findById(Long id) {
        Validate.notNull(id, "id mus be not null");
        Optional<PrestationEntity> entity = prestationRepository.findById(id);
        if (entity.isPresent()) {
            return prestationMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("PrestationDTO not found");
        }
    }

    public PrestationDTO save(PrestationDTO dto) {
        Validate.notNull(dto, "PrestationDTO must be not null");
        PrestationEntity entity = prestationMapper.convertToEntity(dto);
        if (dto.getTarifId() != null) {
            TarifDTO tarifDTO = tarifService.findById(dto.getTarifId());
            entity.setTarif(tarifMapper.convertToEntity(tarifDTO));
        }
        if (dto.getSoustypeprestationId() != null) {
            SousTypePrestationDTO sousTypePrestationDTO = sousTypePrestationService.findById(dto.getSoustypeprestationId());
            entity.setSoustypeprestation(sousTypePrestationMapper.convertToEntity(sousTypePrestationDTO));
        }
        PrestationEntity saved = prestationRepository.save(entity);
        return prestationMapper.convertToDto(saved);
    }

    public PrestationDTO update(PrestationDTO dto) {
        Validate.notNull(dto, "PrestationDTO must be not null");
        Validate.notNull(dto.getId(), "PrestationDTO id must be not null");
        findById(dto.getId());
        PrestationEntity entity = prestationMapper.convertToEntity(dto);
        if (dto.getTarifId() != null) {
            TarifDTO tarifDTO = tarifService.findById(dto.getTarifId());
            entity.setTarif(tarifMapper.convertToEntity(tarifDTO));
        }
        if (dto.getSoustypeprestationId() != null) {
            SousTypePrestationDTO sousTypePrestationDTO = sousTypePrestationService.findById(dto.getSoustypeprestationId());
            entity.setSoustypeprestation(sousTypePrestationMapper.convertToEntity(sousTypePrestationDTO));
        }
        PrestationEntity saved = prestationRepository.save(entity);
        return prestationMapper.convertToDto(saved);
    }

    public Page<PrestationDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<PrestationEntity> page = prestationRepository.findAllByIsDeletedFalse(pageable);
        return prestationMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        PrestationDTO dto = findById(id);
        dto.setIsDeleted(true);
        PrestationEntity entity = prestationMapper.convertToEntity(dto);
        prestationRepository.save(entity);
    }

    public LigneCommandeDTO generateLcmdFromPrestationforCommande(Long idPrestation, Long idCmd) {
        PrestationDTO pdto = findById(idPrestation);
        CommandeDTO cmdDto = commandeService.findById(idCmd);
        LigneCommandeEntity lcmdEntity = new LigneCommandeEntity();
        lcmdEntity.setDesignationPrestation(pdto.getDesignation());
        if (cmdDto != null) {
            lcmdEntity.setCommande(commandeMapper.convertToEntity(cmdDto));
        }
        LigneCommandeEntity saved = ligneCommandeRepository.save(lcmdEntity);
        return ligneCommandeMapper.convertToDto(saved);
    }

    public List<LigneCommandeDTO> traitementListPrestation(List<Long> listPrestationsId, Long idCmd) {
        List<LigneCommandeDTO> lcmdDtos = new ArrayList<>();
        listPrestationsId.forEach(idPrestation -> {
            LigneCommandeDTO ligneCommandeDTO = generateLcmdFromPrestationforCommande(idPrestation, idCmd);
            lcmdDtos.add(ligneCommandeDTO);
        });
        return lcmdDtos;
    }
}
