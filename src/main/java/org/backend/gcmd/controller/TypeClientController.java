package org.backend.gcmd.controller;

import org.backend.gcmd.dto.TypeClientDTO;
import org.backend.gcmd.service.TypeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/typeClients")
public class TypeClientController {
    @Autowired
    private TypeClientService typeClientService;

    @GetMapping("{id}")
    public ResponseEntity<TypeClientDTO> findById(@PathVariable Long id) {
        TypeClientDTO typeClientDTO = typeClientService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(typeClientDTO);
    }

    @PostMapping
    public ResponseEntity<TypeClientDTO> save(@RequestBody TypeClientDTO typeClientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(typeClientService.save(typeClientDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<TypeClientDTO> update(@PathVariable Long id,
                                                @RequestBody TypeClientDTO typeClientDTO) {
        typeClientDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(typeClientService.update(typeClientDTO));
    }

    @GetMapping
    public ResponseEntity<Page<TypeClientDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(typeClientService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeClientService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}