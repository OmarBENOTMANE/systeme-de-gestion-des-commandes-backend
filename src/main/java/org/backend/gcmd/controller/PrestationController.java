package org.backend.gcmd.controller;

import org.backend.gcmd.dto.PrestationDTO;
import org.backend.gcmd.service.PrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/prestations")
public class PrestationController {
    @Autowired
    private PrestationService prestationService;

    @GetMapping("{id}")
    public ResponseEntity<PrestationDTO> findById(@PathVariable Long id) {
        PrestationDTO prestationDTO = prestationService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(prestationDTO);
    }

    @PostMapping
    public ResponseEntity<PrestationDTO> save(@RequestBody PrestationDTO prestationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prestationService.save(prestationDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<PrestationDTO> update(@PathVariable Long id,
                                                @RequestBody PrestationDTO prestationDTO) {
        prestationDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(prestationService.update(prestationDTO));
    }

    @GetMapping
    public ResponseEntity<Page<PrestationDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(prestationService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prestationService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}