package org.backend.gcmd.controller;

import org.backend.gcmd.dto.LigneDevisDTO;
import org.backend.gcmd.service.LigneDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/lignesDevis")
public class LigneDevisController {
    @Autowired
    private LigneDevisService ligneDevisService;

    @GetMapping("{id}")
    public ResponseEntity<LigneDevisDTO> findById(@PathVariable Long id) {
        LigneDevisDTO ligneDevisDTO = ligneDevisService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ligneDevisDTO);
    }

    @PostMapping
    public ResponseEntity<LigneDevisDTO> save(@RequestBody LigneDevisDTO ligneDevisDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ligneDevisService.save(ligneDevisDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<LigneDevisDTO> update(@PathVariable Long id,
                                                @RequestBody LigneDevisDTO ligneDevisDTO) {
        ligneDevisDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ligneDevisService.update(ligneDevisDTO));
    }

    @GetMapping
    public ResponseEntity<Page<LigneDevisDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(ligneDevisService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ligneDevisService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);


    }
}