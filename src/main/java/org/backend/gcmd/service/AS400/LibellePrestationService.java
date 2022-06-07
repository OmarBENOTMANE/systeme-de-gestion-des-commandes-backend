package org.backend.gcmd.service.AS400;

import org.backend.gcmd.dto.AS400.LibellePrestationDTO;
import org.backend.gcmd.entity.AS400.LibellePrestationEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.AS400.LibellePrestationMapper;
import org.backend.gcmd.repository.AS400.LibellePrestationRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("libellePrestationServiceAS400")
@Transactional
public class LibellePrestationService {

    @Autowired
    private LibellePrestationRepository libellePrestationRepository;

    @Autowired
    private LibellePrestationMapper libellePrestationMapper;


    public LibellePrestationDTO findById(Long id) {
        Validate.notNull(id, "id mus be not null");
        Optional<LibellePrestationEntity> entity = libellePrestationRepository.findById(id);
        if (entity.isPresent()) {
            return libellePrestationMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("LibellePrestationDTO not found");
        }
    }

    public LibellePrestationDTO save(LibellePrestationDTO dto) {
        Validate.notNull(dto, "LibellePrestationDTO must be not null");
        LibellePrestationEntity entity = libellePrestationMapper.convertToEntity(dto);
        LibellePrestationEntity saved = libellePrestationRepository.save(entity);
        return libellePrestationMapper.convertToDto(saved);
    }

    public LibellePrestationDTO update(LibellePrestationDTO dto) {
        Validate.notNull(dto, "LibellePrestationDTO must be not null");
        Validate.notNull(dto.getId(), "LibellePrestationDTO id must be not null");
        findById(dto.getId());
        LibellePrestationEntity entity = libellePrestationMapper.convertToEntity(dto);
        LibellePrestationEntity saved = libellePrestationRepository.save(entity);
        return libellePrestationMapper.convertToDto(saved);
    }

    public Page<LibellePrestationDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<LibellePrestationEntity> page = libellePrestationRepository.findAllByIsDeletedFalse(pageable);
        return libellePrestationMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        LibellePrestationDTO dto = findById(id);
        dto.setIsDeleted(true);
        LibellePrestationEntity entity = libellePrestationMapper.convertToEntity(dto);
        libellePrestationRepository.save(entity);
    }

}
