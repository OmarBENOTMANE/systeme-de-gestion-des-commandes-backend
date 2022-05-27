package org.backend.gcmd.controller;

import org.backend.gcmd.dto.SousTypePrestationDTO;
import org.backend.gcmd.service.SousTypePrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/sousTypePrestations")
public class SousTypePrestationController {
    @Autowired
    private SousTypePrestationService sousTypePrestationService;

    @GetMapping("{id}")
    public ResponseEntity<SousTypePrestationDTO> findById(@PathVariable Long id) {
        SousTypePrestationDTO sousTypePrestationDTO = sousTypePrestationService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(sousTypePrestationDTO);
    }

    @PostMapping
    public ResponseEntity<SousTypePrestationDTO> save(@RequestBody SousTypePrestationDTO sousTypePrestationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sousTypePrestationService.save(sousTypePrestationDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<SousTypePrestationDTO> update(@PathVariable Long id,
                                                        @RequestBody SousTypePrestationDTO sousTypePrestationDTO) {
        sousTypePrestationDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(sousTypePrestationService.update(sousTypePrestationDTO));
    }

    @GetMapping
    public ResponseEntity<Page<SousTypePrestationDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(sousTypePrestationService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sousTypePrestationService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}