package org.backend.gcmd.service;

import org.backend.gcmd.dto.PrestationDTO;
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
@Sql(scripts = "classpath:/fixtures/prestation.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PrestationServiceTest {

    Long idvar = 1L;

    @Autowired
    PrestationService prestationService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(prestationService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> prestationService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        PrestationDTO pdto = prestationService.findById(idvar);
        Assertions.assertNotNull(pdto);
        Assertions.assertEquals(idvar, pdto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> prestationService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> prestationService.save(null));
    }

    @Test
    void save_OK() {
        //given
        PrestationDTO pdto = PrestationDTO.builder()
                .id(idvar).designation("des").soustypeprestationId(idvar).typePrestation("type").typeTarif("tarif").isDeleted(false)
                .build();
        //when
        PrestationDTO result = prestationService.save(pdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> prestationService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> prestationService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> prestationService.update(null));
        //given
        PrestationDTO pdto = PrestationDTO.builder()
                .id(idvar).designation("des").soustypeprestationId(idvar).typePrestation("type modif").typeTarif("tarif")
                .build();
        //when
        PrestationDTO result = prestationService.update(pdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> prestationService.delete(null));
    }

    @Test
    void delete_ok() {
        PrestationDTO pdto = prestationService.findById(idvar);
        assertNotNull(pdto);
        assertEquals(idvar, pdto.getId());
        prestationService.delete(idvar);
        Assertions.assertTrue(prestationService.findById(idvar).getIsDeleted());
        PrestationDTO result = prestationService.save(pdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<PrestationDTO> page = prestationService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        PrestationDTO pdto = page.getContent().get(0);
        assertEquals(idvar, pdto.getId());
        assertEquals(false, pdto.getIsDeleted());
    }
}
