package org.backend.gcmd.service;

import org.backend.gcmd.dto.SousTypePrestationDTO;
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
@Sql(scripts = "classpath:/fixtures/sousTypePrestation.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class SousTypePrestationTest {

    Long idvar = 1L;

    @Autowired
    SousTypePrestationService sousTypePrestationService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(sousTypePrestationService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> sousTypePrestationService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        SousTypePrestationDTO tpdto = sousTypePrestationService.findById(idvar);
        Assertions.assertNotNull(tpdto);
        Assertions.assertEquals(idvar, tpdto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> sousTypePrestationService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> sousTypePrestationService.save(null));
    }

    @Test
    void save_OK() {
        //given
        SousTypePrestationDTO udto = SousTypePrestationDTO.builder()
                .id(idvar).name("soustype").typePrestationId(idvar)
                .build();
        //when
        SousTypePrestationDTO result = sousTypePrestationService.save(udto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> sousTypePrestationService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> sousTypePrestationService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> sousTypePrestationService.update(null));
        //given
        SousTypePrestationDTO stdto = SousTypePrestationDTO.builder()
                .id(idvar).name("soustype").typePrestationId(idvar)
                .build();
        //when
        SousTypePrestationDTO result = sousTypePrestationService.update(stdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> sousTypePrestationService.delete(null));
    }

    @Test
    void delete_ok() {
        SousTypePrestationDTO stdto = sousTypePrestationService.findById(idvar);
        assertNotNull(stdto);
        assertEquals(idvar, stdto.getId());
        sousTypePrestationService.delete(idvar);
        Assertions.assertTrue(sousTypePrestationService.findById(idvar).getIsDeleted());
        SousTypePrestationDTO result = sousTypePrestationService.save(stdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<SousTypePrestationDTO> page = sousTypePrestationService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        SousTypePrestationDTO stdto = page.getContent().get(0);
        assertEquals(idvar, stdto.getId());
        assertEquals(false, stdto.getIsDeleted());
    }

}
