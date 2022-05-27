package org.backend.gcmd.controller;

import org.backend.gcmd.dto.LigneCommandeDTO;
import org.backend.gcmd.service.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/ligneCommandes")
public class LigneCommandeController {

    @Autowired
    private LigneCommandeService ligneCommandeService;

    @GetMapping("{id}")
    public ResponseEntity<LigneCommandeDTO> findById(@PathVariable Long id) {
        LigneCommandeDTO ligneCommandeDTO = ligneCommandeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ligneCommandeDTO);
    }

    @PostMapping
    public ResponseEntity<LigneCommandeDTO> save(@RequestBody LigneCommandeDTO ligneCommandeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ligneCommandeService.save(ligneCommandeDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<LigneCommandeDTO> update(@PathVariable Long id,
                                                   @RequestBody LigneCommandeDTO ligneCommandeDTO) {
        ligneCommandeDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ligneCommandeService.update(ligneCommandeDTO));
    }

    @GetMapping
    public ResponseEntity<Page<LigneCommandeDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(ligneCommandeService.findAllByIsDeletedFalse(pageable));
    }

    @GetMapping("{id}/actions/changeStatus")
    public ResponseEntity<LigneCommandeDTO> changeStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(ligneCommandeService.affecter(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ligneCommandeService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}