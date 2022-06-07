package org.backend.gcmd.controller.AS400;

import org.backend.gcmd.dto.AS400.TypeNavireDTO;
import org.backend.gcmd.service.AS400.TypeNavireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("typeNavireControllerAS400")
@RequestMapping("api/AS400/v1/typeNavires")
public class TypeNavireController {

    @Autowired
    private TypeNavireService typeNavireService;

    @GetMapping("{id}")
    public ResponseEntity<TypeNavireDTO> findById(@PathVariable Long id) {
        TypeNavireDTO typeNavireDTO = typeNavireService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(typeNavireDTO);
    }

    @PostMapping
    public ResponseEntity<TypeNavireDTO> save(@RequestBody TypeNavireDTO typeNavireDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(typeNavireService.save(typeNavireDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<TypeNavireDTO> update(@PathVariable Long id,
                                                @RequestBody TypeNavireDTO typeNavireDTO) {
        typeNavireDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(typeNavireService.update(typeNavireDTO));
    }

    @GetMapping
    public ResponseEntity<Page<TypeNavireDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(typeNavireService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeNavireService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}