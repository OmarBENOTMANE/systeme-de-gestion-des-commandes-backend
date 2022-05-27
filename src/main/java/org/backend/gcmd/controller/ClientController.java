package org.backend.gcmd.controller;

import org.backend.gcmd.dto.ClientDTO;
import org.backend.gcmd.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("{id}")
    public ResponseEntity<ClientDTO> findByIdByIsDeletedFalse(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id,
                                            @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientService.update(clientDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
