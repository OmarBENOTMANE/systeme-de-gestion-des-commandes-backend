package org.backend.gcmd.controller;

import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.dto.EscaleDTO;
import org.backend.gcmd.service.EscaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/escales")
public class EscaleController {

    @Autowired
    private EscaleService escaleService;

    @GetMapping("{id}")
    public ResponseEntity<EscaleDTO> findById(@PathVariable Long id) {
        EscaleDTO escaleDTO = escaleService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(escaleDTO);
    }

    @PostMapping
    public ResponseEntity<EscaleDTO> save(@RequestBody EscaleDTO escaleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(escaleService.save(escaleDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<EscaleDTO> update(@PathVariable Long id,
                                            @RequestBody EscaleDTO escaleDTO) {
        escaleDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(escaleService.update(escaleDTO));
    }

    @GetMapping
    public ResponseEntity<Page<EscaleDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(escaleService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        escaleService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("{escaleId}/genererCommandeForEscale")
    public ResponseEntity<CommandeDTO> genererCommandeForEscale(@PathVariable Long escaleId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(escaleService.generateCmd(escaleId));
    }

    @GetMapping("findAllNavireAppareiller")
    public ResponseEntity<Page<EscaleDTO>> findAllNavireAppareiller(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(escaleService.findAllNavireAppareiller(pageable));
    }
}