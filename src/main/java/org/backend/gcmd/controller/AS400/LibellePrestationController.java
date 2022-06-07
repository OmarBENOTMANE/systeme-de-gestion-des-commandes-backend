package org.backend.gcmd.controller.AS400;

import org.backend.gcmd.dto.AS400.LibellePrestationDTO;
import org.backend.gcmd.service.AS400.LibellePrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("libellePrestationControllerAS400")
@RequestMapping("api/AS400/v1/libellePrestations")
public class LibellePrestationController {

    @Autowired
    private LibellePrestationService libellePrestationService;

    @GetMapping("{id}")
    public ResponseEntity<LibellePrestationDTO> findById(@PathVariable Long id) {
        LibellePrestationDTO libellePrestationDTO = libellePrestationService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(libellePrestationDTO);
    }

    @PostMapping
    public ResponseEntity<LibellePrestationDTO> save(@RequestBody LibellePrestationDTO libellePrestationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libellePrestationService.save(libellePrestationDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<LibellePrestationDTO> update(@PathVariable Long id,
                                                @RequestBody LibellePrestationDTO libellePrestationDTO) {
        libellePrestationDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(libellePrestationService.update(libellePrestationDTO));
    }

    @GetMapping
    public ResponseEntity<Page<LibellePrestationDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(libellePrestationService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        libellePrestationService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}