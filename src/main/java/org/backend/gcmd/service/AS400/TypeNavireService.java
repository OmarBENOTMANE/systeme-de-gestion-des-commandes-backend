package org.backend.gcmd.service.AS400;


import org.backend.gcmd.dto.AS400.TypeNavireDTO;
import org.backend.gcmd.entity.AS400.TypeNavireEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.AS400.TypeNavireMapper;
import org.backend.gcmd.repository.AS400.TypeNavireRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("typeNavireServiceAS400")
@Transactional
public class TypeNavireService {

    @Autowired
    private TypeNavireRepository typeNavireRepository;

    @Autowired
    private TypeNavireMapper typeNavireMapper;

    public TypeNavireDTO findById(Long id) {
        Validate.notNull(id, "id must be not null");
        Optional<TypeNavireEntity> entity = typeNavireRepository.findById(id);
        if (entity.isPresent()) {
            return typeNavireMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("TypeNavireDTO not found");
        }
    }

    public TypeNavireDTO save(TypeNavireDTO dto) {
        Validate.notNull(dto, "TypeNavireDTO must be not null");
        TypeNavireEntity entity = typeNavireMapper.convertToEntity(dto);
        TypeNavireEntity saved = typeNavireRepository.save(entity);
        return typeNavireMapper.convertToDto(saved);
    }

    public TypeNavireDTO update(TypeNavireDTO dto) {
        Validate.notNull(dto, "TypeNavireDTO must be not null");
        Validate.notNull(dto.getId(), "TypeNavireDTO id must be not null");
        findById(dto.getId());
        TypeNavireEntity entity = typeNavireMapper.convertToEntity(dto);
        TypeNavireEntity saved = typeNavireRepository.save(entity);
        return typeNavireMapper.convertToDto(saved);
    }

    public Page<TypeNavireDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<TypeNavireEntity> page = typeNavireRepository.findAllByIsDeletedFalse(pageable);
        return typeNavireMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        TypeNavireDTO dto = findById(id);
        dto.setIsDeleted(true);
        TypeNavireEntity entity = typeNavireMapper.convertToEntity(dto);
        typeNavireRepository.save(entity);
    }

}
