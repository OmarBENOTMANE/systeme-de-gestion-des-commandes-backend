package org.backend.gcmd.controller;

import org.backend.gcmd.dto.MarchandiseDTO;
import org.backend.gcmd.service.MarchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/marchandises")
public class MarchandiseController {
    @Autowired
    private MarchandiseService marchandiseService;

    @GetMapping("{id}")
    public ResponseEntity<MarchandiseDTO> findById(@PathVariable Long id) {
        MarchandiseDTO marchandiseDTO = marchandiseService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(marchandiseDTO);
    }

    @PostMapping
    public ResponseEntity<MarchandiseDTO> save(@RequestBody MarchandiseDTO marchandiseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(marchandiseService.save(marchandiseDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<MarchandiseDTO> update(@PathVariable Long id,
                                                 @RequestBody MarchandiseDTO marchandiseDTO) {
        marchandiseDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(marchandiseService.update(marchandiseDTO));
    }

    @GetMapping
    public ResponseEntity<Page<MarchandiseDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(marchandiseService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        marchandiseService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}