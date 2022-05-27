package org.backend.gcmd.controller;

import org.backend.gcmd.dto.LigneBpDTO;
import org.backend.gcmd.service.LigneBpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/ligneBulltinPrestations")
public class LigneBpController {

    @Autowired
    private LigneBpService ligneBpService;

    @GetMapping("{id}")
    public ResponseEntity<LigneBpDTO> findById(@PathVariable Long id) {
        LigneBpDTO ligneBpDTO = ligneBpService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ligneBpDTO);
    }

    @PostMapping
    public ResponseEntity<LigneBpDTO> save(@RequestBody LigneBpDTO ligneBpDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ligneBpService.save(ligneBpDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<LigneBpDTO> update(@PathVariable Long id,
                                             @RequestBody LigneBpDTO ligneBpDTO) {
        ligneBpDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ligneBpService.update(ligneBpDTO));
    }

    @GetMapping
    public ResponseEntity<Page<LigneBpDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(ligneBpService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ligneBpService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}