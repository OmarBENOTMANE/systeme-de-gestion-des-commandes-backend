package org.backend.gcmd.service.AS400;

import org.backend.gcmd.dto.AS400.PrestationDTO;
import org.backend.gcmd.entity.AS400.PrestationEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.AS400.PrestationMapper;
import org.backend.gcmd.repository.AS400.PrestationRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("prestationServiceAS400")
@Transactional
public class PrestationService {

    @Autowired
    private PrestationRepository prestationRepository;
    @Autowired
    private PrestationMapper prestationMapper;


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

        PrestationEntity saved = prestationRepository.save(entity);
        return prestationMapper.convertToDto(saved);
    }

    public PrestationDTO update(PrestationDTO dto) {
        Validate.notNull(dto, "PrestationDTO must be not null");
        Validate.notNull(dto.getId(), "PrestationDTO id must be not null");
        findById(dto.getId());
        PrestationEntity entity = prestationMapper.convertToEntity(dto);

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

}
