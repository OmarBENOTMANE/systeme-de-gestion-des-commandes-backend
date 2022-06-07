package org.backend.gcmd.service;

import org.backend.gcmd.dto.DevisDTO;
import org.backend.gcmd.dto.LigneDevisDTO;
import org.backend.gcmd.dto.PrestationDTO;
import org.backend.gcmd.entity.LigneDevisEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.DevisMapper;
import org.backend.gcmd.mapper.LigneDevisMapper;
import org.backend.gcmd.mapper.PrestationMapper;
import org.backend.gcmd.repository.LigneDevisRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LigneDevisService {

    @Autowired
    private LigneDevisRepository ligneDevisRepository;
    @Autowired
    private LigneDevisMapper ligneDevisMapper;

    @Autowired
    private PrestationMapper prestationMapper;
    @Autowired
    private PrestationService prestationService;

    @Autowired
    private DevisMapper devisMapper;
    @Autowired
    private DevisService devisService;

    public LigneDevisDTO findById(Long id) {
        Validate.notNull(id, "id mus be not null");
        Optional<LigneDevisEntity> entity = ligneDevisRepository.findById(id);
        if (entity.isPresent()) {
            return ligneDevisMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("LigneDevisDTO not found");
        }
    }

    public LigneDevisDTO save(LigneDevisDTO dto) {
        Validate.notNull(dto, "LigneDevisDTO must be not null");
        LigneDevisEntity entity = ligneDevisMapper.convertToEntity(dto);
        if (dto.getPrestationId() != null) {
            PrestationDTO prestationDTO = prestationService.findById(dto.getPrestationId());
            entity.setPrestation(prestationMapper.convertToEntity(prestationDTO));
        }
        if (dto.getDevisId() != null) {
            DevisDTO devisDTO = devisService.findById(dto.getDevisId());
            entity.setDevis(devisMapper.convertToEntity(devisDTO));
        }
        LigneDevisEntity saved = ligneDevisRepository.save(entity);
        return ligneDevisMapper.convertToDto(saved);
    }

    public LigneDevisDTO update(LigneDevisDTO dto) {
        Validate.notNull(dto, "LigneDevisDTO must be not null");
        Validate.notNull(dto.getId(), "LigneDevisDTO id must be not null");
        findById(dto.getId());
        LigneDevisEntity entity = ligneDevisMapper.convertToEntity(dto);
        if (dto.getPrestationId() != null) {
            PrestationDTO prestationDTO = prestationService.findById(dto.getPrestationId());
            entity.setPrestation(prestationMapper.convertToEntity(prestationDTO));
        }
        if (dto.getDevisId() != null) {
            DevisDTO devisDTO = devisService.findById(dto.getDevisId());
            entity.setDevis(devisMapper.convertToEntity(devisDTO));
        }
        LigneDevisEntity saved = ligneDevisRepository.save(entity);
        return ligneDevisMapper.convertToDto(saved);
    }


    public Page<LigneDevisDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<LigneDevisEntity> page = ligneDevisRepository.findAllByIsDeletedFalse(pageable);
        return ligneDevisMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        LigneDevisDTO dto = findById(id);
        dto.setIsDeleted(true);
        LigneDevisEntity entity = ligneDevisMapper.convertToEntity(dto);
        ligneDevisRepository.save(entity);
    }
}
