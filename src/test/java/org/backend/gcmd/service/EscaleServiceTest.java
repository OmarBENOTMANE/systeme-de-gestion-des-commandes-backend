package org.backend.gcmd.service;

import org.backend.gcmd.controller.EscaleController;
import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.dto.EscaleDTO;
import org.backend.gcmd.dto.MouvementDTO;
import org.backend.gcmd.entity.CommandeEntity;
import org.backend.gcmd.entity.EscaleEntity;
import org.backend.gcmd.entity.MouvementEntity;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "classpath:/fixtures/clear.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:/fixtures/escale.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EscaleServiceTest {

    Long idescale = 1L;

    @Autowired
    EscaleService escaleService;

    @Autowired
    CommandeService commandeService;

    @Autowired
    EscaleController escaleController;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(escaleService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> escaleService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        EscaleDTO edto = escaleService.findById(idescale);
        Assertions.assertNotNull(edto);
        Assertions.assertEquals(idescale, edto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> escaleService.findById(99999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> escaleService.save(null));
    }

    @Test
    void save_OK() {
        //given
        EscaleDTO edto = EscaleDTO.builder()
                .id(idescale).numeroEscale(2).isDeleted(false).lamanageDate(LocalDate.ofEpochDay(2020-01-01))
                .build();
        //when
        EscaleDTO result = escaleService.save(edto);
        //then
        Assertions.assertNotNull(result.getId());
        assertEquals(2020-01-01, result.getLamanageDate().toEpochDay());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> escaleService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> escaleService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> escaleService.update(null));
        //given
        EscaleDTO edto = EscaleDTO.builder()
                .id(idescale).numeroEscale(3).isDeleted(false)
                .build();
        //when
        EscaleDTO result = escaleService.update(edto);
        //then
        Assertions.assertNotNull(result.getId());
        assertEquals(3, result.getNumeroEscale());

    }

    @Test
    void update_Ok_NavireId() {
        EscaleDTO edto = EscaleDTO.builder().id(idescale).navireId(1L).build();
        escaleService.update(edto);
        EscaleDTO result = escaleService.findById(idescale);
        assertNotNull(edto);
        assertEquals(idescale, result.getId());
        assertEquals(1L, result.getNavireId());
        assertEquals("navname", result.getNavireName());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> escaleService.delete(null));
    }

    @Test
    void delete_ok() {
        EscaleDTO edto = escaleService.findById(idescale);
        assertNotNull(edto);
        assertEquals(idescale, edto.getId());
        escaleService.delete(idescale);
        Assertions.assertTrue(escaleService.findById(idescale).getIsDeleted());
        EscaleDTO result = escaleService.save(edto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<EscaleDTO> page = escaleService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        EscaleDTO eDTO = page.getContent().get(0);
        assertEquals(idescale, eDTO.getId());
        assertEquals(false, eDTO.getIsDeleted());
    }

    @Test
    void findAllByIsDeletedFalse_KO() {
        Page<EscaleDTO> page = escaleService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        EscaleDTO eDTO = page.getContent().get(0);
        assertEquals(idescale, eDTO.getId());
        assertNotEquals(true, eDTO.getIsDeleted());
    }

    @Test
    void generateCmd   () {



    }
//    EscaleDTO edto = EscaleDTO.builder().id(idescale).navireId(1L).build();
//        escaleService.update(edto);
//    EscaleDTO result = escaleService.findById(idescale);
//    assertNotNull(edto);
//    assertEquals(idescale, result.getId());
//    assertEquals(1L, result.getNavireId());
//    assertEquals("navname", result.getNavireName());
}
