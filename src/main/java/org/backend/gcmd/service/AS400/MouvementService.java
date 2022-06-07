package org.backend.gcmd.service.AS400;

import org.backend.gcmd.dto.AS400.MouvementDTO;
import org.backend.gcmd.entity.AS400.MouvementEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.AS400.MouvementMapper;
import org.backend.gcmd.repository.AS400.MouvementRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("mouvementServiceAS400")
@Transactional
public class MouvementService {

    @Autowired
    private MouvementRepository mouvementRepository;
    @Autowired
    private MouvementMapper mouvementMapper;

    public MouvementDTO findById(Long id) {
        Validate.notNull(id, "id mus be not null");
        Optional<MouvementEntity> entity = mouvementRepository.findById(id);
        if (entity.isPresent()) {
            return mouvementMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("MouvementDTO not found");
        }
    }

    public MouvementDTO save(MouvementDTO dto) {
        Validate.notNull(dto, "MouvementDTO must be not null");
        MouvementEntity entity = mouvementMapper.convertToEntity(dto);
        MouvementEntity saved = mouvementRepository.save(entity);
        return mouvementMapper.convertToDto(saved);
    }

    public MouvementDTO update(MouvementDTO dto) {
        Validate.notNull(dto, "MouvementDTO must be not null");
        Validate.notNull(dto.getId(), "MouvementDTO id must be not null");
        findById(dto.getId());
        MouvementEntity entity = mouvementMapper.convertToEntity(dto);
        MouvementEntity saved = mouvementRepository.save(entity);
        return mouvementMapper.convertToDto(saved);
    }

    public Page<MouvementDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<MouvementEntity> page = mouvementRepository.findAllByIsDeletedFalse(pageable);
        return mouvementMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        MouvementDTO dto = findById(id);
        dto.setIsDeleted(true);
        MouvementEntity entity = mouvementMapper.convertToEntity(dto);
        mouvementRepository.save(entity);
    }

}
