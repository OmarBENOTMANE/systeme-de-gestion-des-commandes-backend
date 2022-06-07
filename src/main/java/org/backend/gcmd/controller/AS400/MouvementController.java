package org.backend.gcmd.controller.AS400;

import org.backend.gcmd.dto.AS400.MouvementDTO;
import org.backend.gcmd.service.AS400.MouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("mouvementControllerAS400")
@RequestMapping("api/AS400/v1/mouvements")
public class MouvementController {
    @Autowired
    private MouvementService mouvementService;

    @GetMapping("{id}")
    public ResponseEntity<MouvementDTO> findById(@PathVariable Long id) {
        MouvementDTO mouvementDTO = mouvementService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mouvementDTO);
    }

    @PostMapping
    public ResponseEntity<MouvementDTO> save(@RequestBody MouvementDTO mouvementDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mouvementService.save(mouvementDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<MouvementDTO> update(@PathVariable Long id,
                                               @RequestBody MouvementDTO mouvementDTO) {
        mouvementDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mouvementService.update(mouvementDTO));
    }

    @GetMapping
    public ResponseEntity<Page<MouvementDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(mouvementService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mouvementService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}