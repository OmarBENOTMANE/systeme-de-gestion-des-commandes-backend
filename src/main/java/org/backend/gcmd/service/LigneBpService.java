package org.backend.gcmd.service;

import org.backend.gcmd.dto.BulltinPrestationDTO;
import org.backend.gcmd.dto.LigneBpDTO;
import org.backend.gcmd.entity.LigneBpEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.BulltinPrestationMapper;
import org.backend.gcmd.mapper.LigneBpMapper;
import org.backend.gcmd.repository.LigneBpRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LigneBpService {

    @Autowired
    BulltinPrestationService bulltinPrestationService;
    @Autowired
    BulltinPrestationMapper bulltinPrestationMapper;

    @Autowired
    private LigneBpRepository ligneBpRepository;
    @Autowired
    private LigneBpMapper ligneBpMapper;

    public LigneBpDTO findById(Long id) {
        Validate.notNull(id, "id mus be not null");
        Optional<LigneBpEntity> entity = ligneBpRepository.findById(id);
        if (entity.isPresent()) {
            return ligneBpMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("LigneBpDTO not found");
        }
    }

    public LigneBpDTO save(LigneBpDTO dto) {
        Validate.notNull(dto, "LigneBpDTO must be not null");
        LigneBpEntity entity = ligneBpMapper.convertToEntity(dto);
        if (dto.getBulltinPrestationId() != null) {
            BulltinPrestationDTO bulltinPrestationDTO = bulltinPrestationService.findById(dto.getBulltinPrestationId());
            entity.setBulltinPrestation(bulltinPrestationMapper.convertToEntity(bulltinPrestationDTO));
        }
        LigneBpEntity saved = ligneBpRepository.save(entity);
        return ligneBpMapper.convertToDto(saved);
    }

    public LigneBpDTO update(LigneBpDTO dto) {
        Validate.notNull(dto, "LigneBpDTO must be not null");
        Validate.notNull(dto.getId(), "LigneBpDTOO id must be not null");
        findById(dto.getId());
        LigneBpEntity entity = ligneBpMapper.convertToEntity(dto);
        if (dto.getBulltinPrestationId() != null) {
            BulltinPrestationDTO bulltinPrestationDTO = bulltinPrestationService.findById(dto.getBulltinPrestationId());
            entity.setBulltinPrestation(bulltinPrestationMapper.convertToEntity(bulltinPrestationDTO));
        }
        LigneBpEntity saved = ligneBpRepository.save(entity);
        return ligneBpMapper.convertToDto(saved);
    }

    public Page<LigneBpDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<LigneBpEntity> page = ligneBpRepository.findAllByIsDeletedFalse(pageable);
        return ligneBpMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        LigneBpDTO dto = findById(id);
        dto.setIsDeleted(true);
        LigneBpEntity entity = ligneBpMapper.convertToEntity(dto);
        ligneBpRepository.save(entity);
    }

}
