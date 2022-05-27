package org.backend.gcmd.service;

import org.backend.gcmd.dto.ClientDTO;
import org.backend.gcmd.dto.TypeClientDTO;
import org.backend.gcmd.entity.ClientEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.ClientMapper;
import org.backend.gcmd.mapper.TypeClientMapper;
import org.backend.gcmd.repository.ClientRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private TypeClientService typeClientService;
    @Autowired
    private TypeClientMapper typeClientMapper;

    public ClientDTO findById(Long id) {
        Validate.notNull(id, "id must be not null");
        Optional<ClientEntity> entity = clientRepository.findById(id);
        if (entity.isPresent()) {
            return clientMapper.convertToDto(entity.get());
        } else {
            throw new ObjectNotFoundException("ClientDTO not found");
        }
    }

    public ClientDTO save(ClientDTO dto) {
        Validate.notNull(dto, "ClientDTO must be not null");
        ClientEntity entity = clientMapper.convertToEntity(dto);
        if (dto.getTypeClientId() != null) {
            TypeClientDTO typeClientDTO = typeClientService.findById(dto.getTypeClientId());
            entity.setTypeClient(typeClientMapper.convertToEntity(typeClientDTO));
        }
        ClientEntity saved = clientRepository.save(entity);
        return clientMapper.convertToDto(saved);
    }

    public ClientDTO update(ClientDTO dto) {
        Validate.notNull(dto, "ClientDTO must be not null");
        Validate.notNull(dto.getId(), "ClientDTO id must be not null");
        findById(dto.getId());
        ClientEntity entity = clientMapper.convertToEntity(dto);
        if (dto.getTypeClientId() != null) {
            TypeClientDTO typeClientDTO = typeClientService.findById(dto.getTypeClientId());
            entity.setTypeClient(typeClientMapper.convertToEntity(typeClientDTO));
        }
        ClientEntity saved = clientRepository.save(entity);
        return clientMapper.convertToDto(saved);
    }

    public Page<ClientDTO> findAllByIsDeletedFalse(Pageable pageable) {
        Page<ClientEntity> page = clientRepository.findAllByIsDeletedFalse(pageable);
        return clientMapper.convertToPageDto(page);
    }

    public void delete(Long id) {
        Validate.notNull(id, "Id must be not null");
        ClientDTO dto = findById(id);
        dto.setIsDeleted(true);
        ClientEntity entity = clientMapper.convertToEntity(dto);
        clientRepository.save(entity);
    }

}
