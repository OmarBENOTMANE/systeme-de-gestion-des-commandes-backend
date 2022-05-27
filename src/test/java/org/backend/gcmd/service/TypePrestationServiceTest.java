package org.backend.gcmd.service;

import org.backend.gcmd.dto.TypePrestationDTO;
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
@Sql(scripts = "classpath:/fixtures/typePrestation.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TypePrestationServiceTest {

    Long idvar = 1L;

    @Autowired
    TypePrestationService typePrestationService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(typePrestationService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typePrestationService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        TypePrestationDTO tdto = typePrestationService.findById(idvar);
        Assertions.assertNotNull(tdto);
        Assertions.assertEquals(idvar, tdto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> typePrestationService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typePrestationService.save(null));
    }

    @Test
    void save_OK() {
        //given
        TypePrestationDTO tpdto = TypePrestationDTO.builder()
                .id(idvar).name("prestation").isDeleted(false).uniteOrganisationelId(null)
                .build();
        //when
        TypePrestationDTO result = typePrestationService.save(tpdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typePrestationService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typePrestationService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typePrestationService.update(null));
        //given
        TypePrestationDTO tpdto = TypePrestationDTO.builder()
                .id(idvar).name("prestation modif").isDeleted(false).uniteOrganisationelId(null)
                .build();
        //when
        TypePrestationDTO result = typePrestationService.update(tpdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> typePrestationService.delete(null));
    }

    @Test
    void delete_ok() {
        TypePrestationDTO tpdto = typePrestationService.findById(idvar);
        assertNotNull(tpdto);
        assertEquals(idvar, tpdto.getId());
        typePrestationService.delete(idvar);
        Assertions.assertTrue(typePrestationService.findById(idvar).getIsDeleted());
        TypePrestationDTO result = typePrestationService.save(tpdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<TypePrestationDTO> page = typePrestationService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        TypePrestationDTO tpdto = page.getContent().get(0);
        assertEquals(idvar, tpdto.getId());
        assertEquals(false, tpdto.getIsDeleted());
    }
}
