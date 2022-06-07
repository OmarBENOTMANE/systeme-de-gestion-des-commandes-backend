package org.backend.gcmd.controller.AS400;

import org.backend.gcmd.dto.AS400.CodeMouvementDTO;
import org.backend.gcmd.service.AS400.CodeMouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("codeMouvementControllerAS400")
@RequestMapping("api/AS400/v1/codeMouvements")
public class CodeMouvementController {

    @Autowired
    private CodeMouvementService codeMouvementService;

    @GetMapping("{id}")
    public ResponseEntity<CodeMouvementDTO> findById(@PathVariable Long id) {
        CodeMouvementDTO mouvementDTO = codeMouvementService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mouvementDTO);
    }

    @PostMapping
    public ResponseEntity<CodeMouvementDTO> save(@RequestBody CodeMouvementDTO mouvementDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(codeMouvementService.save(mouvementDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<CodeMouvementDTO> update(@PathVariable Long id,
                                                   @RequestBody CodeMouvementDTO mouvementDTO) {
        mouvementDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(codeMouvementService.update(mouvementDTO));
    }

    @GetMapping
    public ResponseEntity<Page<CodeMouvementDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(codeMouvementService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        codeMouvementService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}