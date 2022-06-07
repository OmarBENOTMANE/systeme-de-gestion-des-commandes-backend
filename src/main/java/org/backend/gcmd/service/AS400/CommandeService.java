package org.backend.gcmd.service.AS400;

import org.backend.gcmd.dto.AS400.CommandeDTO;
import org.backend.gcmd.entity.AS400.CommandeEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.AS400.CommandeMapper;
import org.backend.gcmd.repository.AS400.CommandeRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("commandeServiceAS400")
@Transactional
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private CommandeMapper commandeMapper;

    public CommandeDTO findById(Long id) {
        Validate.notNull(id, "id must be not null");
        Optional<CommandeEntity> entity = commandeRepository.findById(id);
        if (entity.isPresent()) {
            return commandeMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("CommandeDTO not found");
        }
    }

    public CommandeDTO save(CommandeDTO dto) {
        Validate.notNull(dto, "CommandeDTO must be not null");
        CommandeEntity entity = commandeMapper.convertToEntity(dto);
        CommandeEntity saved = commandeRepository.save(entity);
        return commandeMapper.convertToDto(saved);
    }

    public CommandeDTO update(CommandeDTO dto) {
        Validate.notNull(dto, "CommandeDTO must be not null");
        Validate.notNull(dto.getId(), "CommandeDTO id must be not null");
        findById(dto.getId());
        CommandeEntity entity = commandeMapper.convertToEntity(dto);
        CommandeEntity saved = commandeRepository.save(entity);
        return commandeMapper.convertToDto(saved);
    }

    public Page<CommandeDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<CommandeEntity> page = commandeRepository.findAllByIsDeletedFalse(pageable);
        return commandeMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        CommandeDTO dto = findById(id);
        dto.setIsDeleted(true);
        CommandeEntity entity = commandeMapper.convertToEntity(dto);
        commandeRepository.save(entity);
    }

}
