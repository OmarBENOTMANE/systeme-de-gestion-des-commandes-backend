package org.backend.gcmd.service;


import org.backend.gcmd.dto.NavireDTO;
import org.backend.gcmd.exceptions.technical.IllegalNullParamException;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "classpath:/fixtures/clear.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:/fixtures/navire.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class NavireServiceTest {


    @Autowired
    NavireService navireService;

    Long idnavire = 1L;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(navireService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> navireService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        NavireDTO ndto = navireService.findById(idnavire);
        Assertions.assertNotNull(ndto);
        Assertions.assertEquals(idnavire, ndto.getId());

    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> navireService.findById(9999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> navireService.save(null));
    }

    @Test
    void save_OK() {
        //given
        NavireDTO ndto = NavireDTO.builder()
                .id(idnavire).etat("non facturé")
                .build();
        //when
        NavireDTO result = navireService.save(ndto);
        //then
        Assertions.assertNotNull(result.getId());
        assertEquals("non facturé", result.getEtat());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> navireService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> navireService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> navireService.update(null));
        //given
        NavireDTO ndto = NavireDTO.builder()
                .id(idnavire).etat("facturé").numeroEscale(2)
                .build();
        //when
        NavireDTO result = navireService.update(ndto);
        //then
        Assertions.assertNotNull(result.getId());
        assertEquals("facturé", result.getEtat());
        assertEquals(2, result.getNumeroEscale());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> navireService.delete(null));
    }

    @Test
    void delete_ok() {
        NavireDTO ndto = navireService.findById(idnavire);
        assertNotNull(ndto);
        assertEquals(idnavire, ndto.getId());
        navireService.delete(idnavire);
        Assertions.assertTrue(navireService.findById(idnavire).getIsDeleted());
        NavireDTO result = navireService.save(ndto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<NavireDTO> page = navireService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        NavireDTO nDTO = page.getContent().get(0);
        assertEquals(idnavire, nDTO.getId());
        assertEquals(false, nDTO.getIsDeleted());
    }
}
