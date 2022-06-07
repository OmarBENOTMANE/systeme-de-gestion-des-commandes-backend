package org.backend.gcmd.service.AS400;

import org.backend.gcmd.dto.AS400.ClientDTO;
import org.backend.gcmd.entity.AS400.ClientEntity;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.mapper.AS400.ClientMapper;
import org.backend.gcmd.repository.AS400.ClientRepository;
import org.backend.gcmd.validator.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("clientServiceAS400")
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

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
        ClientEntity saved = clientRepository.save(entity);
        return clientMapper.convertToDto(saved);
    }

    public ClientDTO update(ClientDTO dto) {
        Validate.notNull(dto, "ClientDTO must be not null");
        Validate.notNull(dto.getId(), "ClientDTO id must be not null");
        findById(dto.getId());
        ClientEntity entity = clientMapper.convertToEntity(dto);
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
