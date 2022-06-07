package org.backend.gcmd.controller;

import org.backend.gcmd.dto.ManifestDTO;
import org.backend.gcmd.service.ManifestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/manifests")
public class ManifestController {

    @Autowired
    private ManifestService manifestService;

    @GetMapping("{id}")
    public ResponseEntity<ManifestDTO> findById(@PathVariable Long id) {
        ManifestDTO manifestDTO = manifestService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(manifestDTO);
    }

    @PostMapping
    public ResponseEntity<ManifestDTO> save(@RequestBody ManifestDTO manifestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(manifestService.save(manifestDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<ManifestDTO> update(@PathVariable Long id,
                                              @RequestBody ManifestDTO manifestDTO) {
        manifestDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(manifestService.update(manifestDTO));
    }

    @GetMapping
    public ResponseEntity<Page<ManifestDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(manifestService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        manifestService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}