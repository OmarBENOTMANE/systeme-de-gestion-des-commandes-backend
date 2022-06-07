package org.backend.gcmd.controller.AS400;

import org.backend.gcmd.dto.AS400.BulltinPrestationDTO;
import org.backend.gcmd.service.AS400.BulltinPrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("bulltinPrestationControllerAS400")
@RequestMapping("api/as400/v1/bulltinPrestations")
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

}
