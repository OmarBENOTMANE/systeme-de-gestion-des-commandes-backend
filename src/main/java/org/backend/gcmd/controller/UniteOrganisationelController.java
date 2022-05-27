package org.backend.gcmd.controller;

import org.backend.gcmd.dto.UniteOrganisationelDTO;
import org.backend.gcmd.service.UniteOrganisationelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gcmd/v1/uniteOrganisationels")
public class UniteOrganisationelController {
    @Autowired
    private UniteOrganisationelService uniteOrganisationelService;


    @GetMapping("{id}")
    public ResponseEntity<UniteOrganisationelDTO> findById(@PathVariable Long id) {
        UniteOrganisationelDTO uniteOrganisationelDTO = uniteOrganisationelService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(uniteOrganisationelDTO);
    }

    @PostMapping
    public ResponseEntity<UniteOrganisationelDTO> save(@RequestBody UniteOrganisationelDTO uniteOrganisationelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(uniteOrganisationelService.save(uniteOrganisationelDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<UniteOrganisationelDTO> update(@PathVariable Long id,
                                                         @RequestBody UniteOrganisationelDTO uniteOrganisationelDTO) {
        uniteOrganisationelDTO.setId(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(uniteOrganisationelService.update(uniteOrganisationelDTO));
    }

    @GetMapping
    public ResponseEntity<Page<UniteOrganisationelDTO>> findAllByIsDeletedFalse(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(uniteOrganisationelService.findAllByIsDeletedFalse(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        uniteOrganisationelService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}