package org.backend.gcmd.service;

import org.backend.gcmd.dto.EscaleDTO;
import org.backend.gcmd.dto.ManifestDTO;
import org.backend.gcmd.entity.ManifestEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.EscaleMapper;
import org.backend.gcmd.mapper.ManifestMapper;
import org.backend.gcmd.repository.ManifestRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ManifestService {

    @Autowired
    private ManifestRepository manifestRepository;
    @Autowired
    private ManifestMapper manifestMapper;

    @Autowired
    private EscaleService escaleService;
    @Autowired
    private EscaleMapper escaleMapper;

    public ManifestDTO findById(Long id) {
        Validate.notNull(id, "id mus be not null");
        Optional<ManifestEntity> entity = manifestRepository.findById(id);
        if (entity.isPresent()) {
            return manifestMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("ManifestDTO not found");
        }
    }

    public ManifestDTO save(ManifestDTO dto) {
        Validate.notNull(dto, "ManifestDTO must be not null");
        ManifestEntity entity = manifestMapper.convertToEntity(dto);
        if (dto.getEscaleId() != null) {
            EscaleDTO escaleDTO = escaleService.findById(dto.getEscaleId());
            entity.setEscale(escaleMapper.convertToEntity(escaleDTO));
        }
        ManifestEntity saved = manifestRepository.save(entity);
        return manifestMapper.convertToDto(saved);
    }

    public ManifestDTO update(ManifestDTO dto) {
        Validate.notNull(dto, "ManifestDTO must be not null");
        Validate.notNull(dto.getId(), "ManifestDTO id must be not null");
        findById(dto.getId());
        ManifestEntity entity = manifestMapper.convertToEntity(dto);
        if (dto.getEscaleId() != null) {
            EscaleDTO escaleDTO = escaleService.findById(dto.getEscaleId());
            entity.setEscale(escaleMapper.convertToEntity(escaleDTO));
        }
        ManifestEntity saved = manifestRepository.save(entity);
        return manifestMapper.convertToDto(saved);
    }


    public Page<ManifestDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<ManifestEntity> page = manifestRepository.findAllByIsDeletedFalse(pageable);
        return manifestMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        ManifestDTO dto = findById(id);
        dto.setIsDeleted(true);
        ManifestEntity entity = manifestMapper.convertToEntity(dto);
        manifestRepository.save(entity);
    }

}
