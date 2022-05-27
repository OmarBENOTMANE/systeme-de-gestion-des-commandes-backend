package org.backend.gcmd.service;

import org.backend.gcmd.dto.TypeClientDTO;
import org.backend.gcmd.entity.TypeClientEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.TypeClientMapper;
import org.backend.gcmd.repository.TypeClientRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TypeClientService {

    @Autowired
    private TypeClientRepository typeClientRepository;

    @Autowired
    private TypeClientMapper typeClientMapper;

    public TypeClientDTO findById(Long id) {
        Validate.notNull(id, "id must be not null");
        Optional<TypeClientEntity> entity = typeClientRepository.findById(id);
        if (entity.isPresent()) {
            return typeClientMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("TypeClientDTO not found");
        }
    }

    public TypeClientDTO save(TypeClientDTO dto) {
        Validate.notNull(dto, "TypeClientDTO must be not null");
        TypeClientEntity entity = typeClientMapper.convertToEntity(dto);
        TypeClientEntity saved = typeClientRepository.save(entity);
        return typeClientMapper.convertToDto(saved);
    }

    public TypeClientDTO update(TypeClientDTO dto) {
        Validate.notNull(dto, "TypeClientDTO must be not null");
        Validate.notNull(dto.getId(), "TypeClientDTO id must be not null");
        findById(dto.getId());
        TypeClientEntity entity = typeClientMapper.convertToEntity(dto);
        TypeClientEntity saved = typeClientRepository.save(entity);
        return typeClientMapper.convertToDto(saved);
    }

    public Page<TypeClientDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<TypeClientEntity> page = typeClientRepository.findAllByIsDeletedFalse(pageable);
        return typeClientMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        TypeClientDTO dto = findById(id);
        dto.setIsDeleted(true);
        TypeClientEntity entity = typeClientMapper.convertToEntity(dto);
        typeClientRepository.save(entity);
    }

}
