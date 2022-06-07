package org.backend.gcmd.service;

import org.backend.gcmd.dto.EscaleDTO;
import org.backend.gcmd.dto.MarchandiseDTO;
import org.backend.gcmd.entity.MarchandiseEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.EscaleMapper;
import org.backend.gcmd.mapper.MarchandiseMapper;
import org.backend.gcmd.repository.MarchandiseRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MarchandiseService {

    @Autowired
    private MarchandiseRepository marchandiseRepository;
    @Autowired
    private MarchandiseMapper marchandiseMapper;

    @Autowired
    private EscaleService escaleService;
    @Autowired
    private EscaleMapper escaleMapper;

    public MarchandiseDTO findById(Long id) {
        Validate.notNull(id, "id mus be not null");
        Optional<MarchandiseEntity> entity = marchandiseRepository.findById(id);
        if (entity.isPresent()) {
            return marchandiseMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("MarchandiseDTO not found");
        }
    }

    public MarchandiseDTO save(MarchandiseDTO dto) {
        Validate.notNull(dto, "MarchandiseDTO must be not null");
        return getMarchandiseDTO(dto);
    }

    private MarchandiseDTO getMarchandiseDTO(MarchandiseDTO dto) {
        MarchandiseEntity entity = marchandiseMapper.convertToEntity(dto);
        if (dto.getEscaleId() != null) {
            EscaleDTO escaleDTO = escaleService.findById(dto.getEscaleId());
            entity.setEscale(escaleMapper.convertToEntity(escaleDTO));
        }
        MarchandiseEntity saved = marchandiseRepository.save(entity);
        return marchandiseMapper.convertToDto(saved);
    }

    public MarchandiseDTO update(MarchandiseDTO dto) {
        Validate.notNull(dto, "MarchandiseDTO must be not null");
        Validate.notNull(dto.getId(), "MarchandiseDTO id must be not null");
        findById(dto.getId());
        return getMarchandiseDTO(dto);
    }

    public Page<MarchandiseDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<MarchandiseEntity> page = marchandiseRepository.findAllByIsDeletedFalse(pageable);
        return marchandiseMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        MarchandiseDTO dto = findById(id);
        dto.setIsDeleted(true);
        MarchandiseEntity entity = marchandiseMapper.convertToEntity(dto);
        marchandiseRepository.save(entity);
    }

}
