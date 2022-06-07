package org.backend.gcmd.controller;

import org.backend.gcmd.dto.BulltinPrestationDTO;
import org.backend.gcmd.service.BulltinPrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/gcmd/v1/bulltinPrestations")
public class BulltinPrestationController {

    @Autowired
    private BulltinPrestationService bulltinPrestationService;

    @GetMapping("{id}")
    public ResponseEntity<BulltinPrestationDTO> findById(@PathVariable Long id) {
        BulltinPrestationDTO bulltinPrestationDTO = bulltinPrestationService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bulltinPrestationDTO);
    }

    @PostMapping
    public ResponseEntity<BulltinPrestationDTO> save(@RequestBody BulltinPrestationDTO bulltinPrestationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bulltinPrestationService.save(bulltinPrestationDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<BulltinPrestationDTO> update(@PathVariable Long id,
                                                       @RequestBody BulltinPrestationDTO bulltinPrestationDTO) {
        bulltinPrestationDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bulltinPrestationService.update(bulltinPrestationDTO));
    }

    @GetMapping
    public ResponseEntity<Page<BulltinPrestationDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(bulltinPrestationService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bulltinPrestationService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("findAllBpNotFacturedForEscale")
    public ResponseEntity<Page<BulltinPrestationDTO>> findAllBpNotFacturedForEscale(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(bulltinPrestationService.findAllBpNotFacturedForEscale(pageable));
    }
}
