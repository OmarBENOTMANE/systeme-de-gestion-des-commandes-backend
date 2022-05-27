package org.backend.gcmd.service;

import org.backend.gcmd.dto.UniteOrganisationelDTO;
import org.backend.gcmd.entity.UniteOrganisationelEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.UniteOrganisationelMapper;
import org.backend.gcmd.repository.UniteOrganisationelRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UniteOrganisationelService {

    @Autowired
    private UniteOrganisationelRepository uniteOrganisationelRepository;

    @Autowired
    private UniteOrganisationelMapper uniteOrganisationelMapper;

    public UniteOrganisationelDTO findById(Long id) {
        Validate.notNull(id, "id mus be not null");
        Optional<UniteOrganisationelEntity> entity = uniteOrganisationelRepository.findById(id);
        if (entity.isPresent()) {
            return uniteOrganisationelMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("UniteOrganisationelDTO not found");
        }
    }

    public UniteOrganisationelDTO save(UniteOrganisationelDTO dto) {
        Validate.notNull(dto, "UniteOrganisationelDTO must be not null");
        UniteOrganisationelEntity entity = uniteOrganisationelMapper.convertToEntity(dto);
        UniteOrganisationelEntity saved = uniteOrganisationelRepository.save(entity);
        return uniteOrganisationelMapper.convertToDto(saved);
    }

    public UniteOrganisationelDTO update(UniteOrganisationelDTO dto) {
        Validate.notNull(dto, "UniteOrganisationelDTO must be not null");
        Validate.notNull(dto.getId(), "UniteOrganisationelDTO id must be not null");
        findById(dto.getId());
        UniteOrganisationelEntity entity = uniteOrganisationelMapper.convertToEntity(dto);
        UniteOrganisationelEntity saved = uniteOrganisationelRepository.save(entity);
        return uniteOrganisationelMapper.convertToDto(saved);
    }

    public Page<UniteOrganisationelDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<UniteOrganisationelEntity> page = uniteOrganisationelRepository.findAllByIsDeletedFalse(pageable);
        return uniteOrganisationelMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        UniteOrganisationelDTO dto = findById(id);
        dto.setIsDeleted(true);
        UniteOrganisationelEntity entity = uniteOrganisationelMapper.convertToEntity(dto);
        uniteOrganisationelRepository.save(entity);
    }

}
