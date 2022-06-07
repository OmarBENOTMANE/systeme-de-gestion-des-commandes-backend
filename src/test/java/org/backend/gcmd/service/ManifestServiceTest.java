package org.backend.gcmd.service;


import org.backend.gcmd.dto.ManifestDTO;
import org.backend.gcmd.exceptions.technical.IllegalNullParamException;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "classpath:/fixtures/clear.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:/fixtures/manifest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ManifestServiceTest {

    private final Long idManifest = 1L;
    @Autowired
    ManifestService manifestService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(manifestService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> manifestService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        ManifestDTO mdto = manifestService.findById(idManifest);
        Assertions.assertNotNull(mdto);
        Assertions.assertEquals(idManifest, mdto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> manifestService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> manifestService.save(null));
    }

    @Test
    void save_OK() {
        //given
        ManifestDTO mdto = ManifestDTO.builder()
                .designation("desi").escaleId(null).quantite(3.2).reference("ref").isDeleted(false)
                .build();
        //when
        ManifestDTO result = manifestService.save(mdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> manifestService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> manifestService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> manifestService.update(null));
        //given
        ManifestDTO mdto = ManifestDTO.builder()
                .id(idManifest).designation("desi modif").escaleId(1L)
                .build();
        //when
        manifestService.update(mdto);
        ManifestDTO result = manifestService.findById(1L);
        //then
        Assertions.assertNotNull(result.getId());
        assertEquals(1L, result.getEscaleId());
        assertEquals("desi modif", result.getDesignation());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> manifestService.delete(null));
    }

    @Test
    void delete_ok() {
        ManifestDTO mdto = manifestService.findById(idManifest);
        assertNotNull(mdto);
        assertEquals(idManifest, mdto.getId());
        manifestService.delete(idManifest);
        Assertions.assertTrue(manifestService.findById(idManifest).getIsDeleted());
        ManifestDTO result = manifestService.save(mdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<ManifestDTO> page = manifestService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        ManifestDTO cDTO = page.getContent().get(0);
        assertEquals(idManifest, cDTO.getId());
        assertEquals(false, cDTO.getIsDeleted());
    }
}

