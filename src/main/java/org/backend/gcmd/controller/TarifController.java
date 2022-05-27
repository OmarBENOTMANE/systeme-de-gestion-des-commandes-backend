package org.backend.gcmd.controller;

import org.backend.gcmd.dto.TarifDTO;
import org.backend.gcmd.service.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/tarifs")
public class TarifController {
    @Autowired
    private TarifService tarifService;

    @GetMapping("{id}")
    public ResponseEntity<TarifDTO> findById(@PathVariable Long id) {
        TarifDTO tarifDTO = tarifService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(tarifDTO);
    }

    @PostMapping
    public ResponseEntity<TarifDTO> save(@RequestBody TarifDTO tarifDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarifService.save(tarifDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<TarifDTO> update(@PathVariable Long id,
                                           @RequestBody TarifDTO tarifDTO) {
        tarifDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(tarifService.update(tarifDTO));
    }

    @GetMapping
    public ResponseEntity<Page<TarifDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(tarifService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarifService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}