package org.backend.gcmd.service;


import org.backend.gcmd.dto.MarchandiseDTO;
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
@Sql(scripts = "classpath:/fixtures/marchandise.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MarchandiseServiceTest {

    @Autowired
    MarchandiseService marchandiseService;
    private final Long idMarchandise = 1L;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(marchandiseService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> marchandiseService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        MarchandiseDTO mdto = marchandiseService.findById(idMarchandise);
        Assertions.assertNotNull(mdto);
        Assertions.assertEquals(idMarchandise, mdto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> marchandiseService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> marchandiseService.save(null));
    }

    @Test
    void save_OK() {
        //given
        MarchandiseDTO mdto = MarchandiseDTO.builder()
                .designation("desi").escaleId(null).quantite(3.2).reference("ref").isDeleted(false)
                .build();
        //when
        MarchandiseDTO result = marchandiseService.save(mdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> marchandiseService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> marchandiseService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> marchandiseService.update(null));
        //given
        MarchandiseDTO mdto = MarchandiseDTO.builder()
                .id(idMarchandise).designation("desi modif").escaleId(1L)
                .build();
        //when
        marchandiseService.update(mdto);
        MarchandiseDTO result = marchandiseService.findById(1L);
        //then
        Assertions.assertNotNull(result.getId());
        assertEquals(1L, result.getEscaleId());
        assertEquals("desi modif", result.getDesignation());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> marchandiseService.delete(null));
    }

    @Test
    void delete_ok() {
        MarchandiseDTO mdto = marchandiseService.findById(idMarchandise);
        assertNotNull(mdto);
        assertEquals(idMarchandise, mdto.getId());
        marchandiseService.delete(idMarchandise);
        Assertions.assertTrue(marchandiseService.findById(idMarchandise).getIsDeleted());
        MarchandiseDTO result = marchandiseService.save(mdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<MarchandiseDTO> page = marchandiseService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        MarchandiseDTO cDTO = page.getContent().get(0);
        assertEquals(idMarchandise, cDTO.getId());
        assertEquals(false, cDTO.getIsDeleted());
    }
}

