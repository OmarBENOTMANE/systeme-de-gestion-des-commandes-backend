package org.backend.gcmd.service;

import org.backend.gcmd.dto.DevisDTO;
import org.backend.gcmd.entity.DevisEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.DevisMapper;
import org.backend.gcmd.repository.DevisRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private DevisMapper devisMapper;

    public DevisDTO findById(Long id) {
        Validate.notNull(id, "id d mus be not null");
        Optional<DevisEntity> entity = devisRepository.findById(id);
        if (entity.isPresent()) {
            return devisMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("DevisDTO not found");
        }
    }

    public DevisDTO save(DevisDTO dto) {
        Validate.notNull(dto, "DevisDTO must be not null");
        DevisEntity entity = devisMapper.convertToEntity(dto);
        DevisEntity saved = devisRepository.save(entity);
        return devisMapper.convertToDto(saved);
    }

    public DevisDTO update(DevisDTO dto) {
        Validate.notNull(dto, "DevisDTO must be not null");
        Validate.notNull(dto.getId(), "DevisDTO id must be not null");
        findById(dto.getId());
        DevisEntity entity = devisMapper.convertToEntity(dto);
        DevisEntity saved = devisRepository.save(entity);
        return devisMapper.convertToDto(saved);
    }

    public Page<DevisDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<DevisEntity> page = devisRepository.findAllByIsDeletedFalse(pageable);
        return devisMapper.convertToPageDto(page);

    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        DevisDTO dto = findById(id);
        dto.setIsDeleted(true);
        DevisEntity entity = devisMapper.convertToEntity(dto);
        devisRepository.save(entity);
    }
}
