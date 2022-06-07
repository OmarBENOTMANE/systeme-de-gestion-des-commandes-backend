package org.backend.gcmd.service.AS400;

import org.backend.gcmd.dto.AS400.EscaleDTO;
import org.backend.gcmd.entity.AS400.EscaleEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.AS400.EscaleMapper;
import org.backend.gcmd.repository.AS400.EscaleRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("EscaleServiceAS400")
@Transactional
public class EscaleService {

    @Autowired
    private EscaleRepository escaleRepository;
    @Autowired
    private EscaleMapper escaleMapper;


    public EscaleDTO findById(Long id) {
        Validate.notNull(id, "findby: id mus be not null");
        Optional<EscaleEntity> entity = escaleRepository.findById(id);
        if (entity.isPresent()) {
            return escaleMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("findby: EscaleDTO not found");
        }
    }

    public EscaleDTO save(EscaleDTO dto) {
        Validate.notNull(dto, "save: EscaleDTO must be not null");
        EscaleEntity entity = escaleMapper.convertToEntity(dto);
        EscaleEntity saved = escaleRepository.save(entity);
        return escaleMapper.convertToDto(saved);
    }

    public EscaleDTO update(EscaleDTO dto) {
        Validate.notNull(dto, "update: EscaleDTO must be not null");
        Validate.notNull(dto.getId(), "update: EscaleDTO id must be not null");
        findById(dto.getId());
        EscaleEntity entity = escaleMapper.convertToEntity(dto);
        EscaleEntity saved = escaleRepository.save(entity);
        return escaleMapper.convertToDto(saved);
    }

    public Page<EscaleDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<EscaleEntity> page = escaleRepository.findAllByIsDeletedFalse(pageable);
        return escaleMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "delete: Id must be not null");
        EscaleDTO edto = findById(id);
        edto.setIsDeleted(true);
        EscaleEntity entity = escaleMapper.convertToEntity(edto);
        escaleRepository.save(entity);
    }

}
