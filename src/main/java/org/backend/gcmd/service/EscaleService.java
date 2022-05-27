package org.backend.gcmd.service;

import org.backend.gcmd.dto.*;
import org.backend.gcmd.entity.CommandeEntity;
import org.backend.gcmd.entity.EscaleEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.CommandeMapper;
import org.backend.gcmd.mapper.EscaleMapper;
import org.backend.gcmd.mapper.NavireMapper;
import org.backend.gcmd.repository.CommandeRepository;
import org.backend.gcmd.repository.EscaleRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EscaleService {

    @Autowired
    private EscaleRepository escaleRepository;
    @Autowired
    private EscaleMapper escaleMapper;

    @Autowired
    private NavireService navireService;
    @Autowired
    private NavireMapper navireMapper;

    @Autowired
    private CommandeRepository commandeRepository;


    public EscaleDTO findById(Long id) {
        Validate.notNull(id, "id e mus be not null");
        Optional<EscaleEntity> entity = escaleRepository.findById(id);
        if (entity.isPresent()) {
            return escaleMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("EscaleDTO not found");
        }
    }

    public EscaleDTO save(EscaleDTO dto) {
        Validate.notNull(dto, "EscaleDTO must be not null");
        EscaleEntity entity = escaleMapper.convertToEntity(dto);
        EscaleEntity saved = escaleRepository.save(entity);
        return escaleMapper.convertToDto(saved);
    }

    public EscaleDTO update(EscaleDTO dto) {
        Validate.notNull(dto, "EscaleDTO must be not null");
        Validate.notNull(dto.getId(), "EscaleDTO id must be not null");
        findById(dto.getId());
        EscaleEntity entity = escaleMapper.convertToEntity(dto);
        if (dto.getNavireId() != null) {
            NavireDTO navireDTO = navireService.findById(dto.getNavireId());
            entity.setNavire(navireMapper.convertToEntity(navireDTO));
        }
        EscaleEntity saved = escaleRepository.save(entity);
        return escaleMapper.convertToDto(saved);
    }


    public Page<EscaleDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<EscaleEntity> page = escaleRepository.findAllByIsDeletedFalse(pageable);
        return escaleMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        EscaleDTO edto = findById(id);
        edto.setIsDeleted(true);
        EscaleEntity entity = escaleMapper.convertToEntity(edto);
        escaleRepository.save(entity);
    }

    public EscaleDTO generateCmd(Long id) {
        Validate.notNull(id, "EscaleDTO must be not null");
        EscaleDTO edto = findById(id);
        createCmd(edto);
        update(edto);
        return edto;
    }

    public void  createCmd(EscaleDTO escaleDTO) {
        CommandeEntity entity = new CommandeEntity();
        entity.setDateAmarage(escaleDTO.getLamanageDate());
        entity.setNumeroEscale(escaleDTO.getNumeroEscale());
        entity.setIsfactured(escaleDTO.getIsfactured());
        entity.setEscale(escaleMapper.convertToEntity(escaleDTO));
        commandeRepository.save(entity);
    }

    }
