package org.backend.gcmd.service.AS400;

import org.backend.gcmd.dto.AS400.CodeMouvementDTO;
import org.backend.gcmd.entity.AS400.CodeMouvementEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.AS400.CodeMouvementMapper;
import org.backend.gcmd.repository.AS400.CodeMouvementRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("codeMouvementServiceAS400")
@Transactional
public class CodeMouvementService {

    @Autowired
    private CodeMouvementRepository codeMouvementRepository;

    @Autowired
    private CodeMouvementMapper codeMouvementMapper;


    public CodeMouvementDTO findById(Long id) {
        Validate.notNull(id, "id mus be not null");
        Optional<CodeMouvementEntity> entity = codeMouvementRepository.findById(id);
        if (entity.isPresent()) {
            return codeMouvementMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("CodeMouvementDTO not found");
        }
    }

    public CodeMouvementDTO save(CodeMouvementDTO dto) {
        Validate.notNull(dto, "CodeMouvementDTO must be not null");
        CodeMouvementEntity entity = codeMouvementMapper.convertToEntity(dto);
        CodeMouvementEntity saved = codeMouvementRepository.save(entity);
        return codeMouvementMapper.convertToDto(saved);
    }

    public CodeMouvementDTO update(CodeMouvementDTO dto) {
        Validate.notNull(dto, "CodeMouvementDTO must be not null");
        Validate.notNull(dto.getId(), "CodeMouvementDTO id must be not null");
        findById(dto.getId());
        CodeMouvementEntity entity = codeMouvementMapper.convertToEntity(dto);
        CodeMouvementEntity saved = codeMouvementRepository.save(entity);
        return codeMouvementMapper.convertToDto(saved);
    }

    public Page<CodeMouvementDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<CodeMouvementEntity> page = codeMouvementRepository.findAllByIsDeletedFalse(pageable);
        return codeMouvementMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        CodeMouvementDTO dto = findById(id);
        dto.setIsDeleted(true);
        CodeMouvementEntity entity = codeMouvementMapper.convertToEntity(dto);
        codeMouvementRepository.save(entity);
    }

}
