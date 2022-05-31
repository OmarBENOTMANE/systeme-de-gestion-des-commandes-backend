package org.backend.gcmd.service;


import org.backend.gcmd.dto.CommandeDTO;
import org.backend.gcmd.dto.MouvementDTO;
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
@Sql(scripts = "classpath:/fixtures/mouvement.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MouvementServiceTest {

    Long idMouvement = 1L;

    @Autowired
    MouvementService mouvementService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(mouvementService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> mouvementService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        MouvementDTO mdto = mouvementService.findById(1L);
        Assertions.assertNotNull(mdto);
        Assertions.assertEquals(1L, mdto.getId());

    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> mouvementService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> mouvementService.save(null));
    }

    @Test
    void save_OK() {
        //given
        MouvementDTO bpdto = MouvementDTO.builder()
                .id(1L).dateMouvement(LocalDate.now()).description("des").escaleId(1L)
                .build();
        //when
        MouvementDTO result = mouvementService.save(bpdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> mouvementService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> mouvementService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> mouvementService.update(null));
        //given
        MouvementDTO mdto = MouvementDTO.builder()
                .id(1L).dateMouvement(LocalDate.now()).description("acostage").escaleId(1L)
                .build();
        //when
        MouvementDTO result = mouvementService.update(mdto);
        //then
        Assertions.assertNotNull(result.getId());
    }
    @Test
    void update_Ok_escaleId() {
        Long idescale = 1L;
        MouvementDTO mdto = MouvementDTO.builder().id(idMouvement).escaleId(idescale).build();
        mouvementService.update(mdto);
        MouvementDTO result = mouvementService.findById(idMouvement);
        assertNotNull(mdto);
        assertEquals(idMouvement, result.getId());
        assertEquals(1L, result.getEscaleId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> mouvementService.delete(null));
    }

    @Test
    void delete_ok() {
        MouvementDTO mdto = mouvementService.findById(idMouvement);
        assertNotNull(mdto);
        assertEquals(idMouvement, mdto.getId());
        mouvementService.delete(idMouvement);
        Assertions.assertTrue(mouvementService.findById(idMouvement).getIsDeleted());
        MouvementDTO result = mouvementService.save(mdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<MouvementDTO> page = mouvementService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        MouvementDTO mdto = page.getContent().get(0);
        assertEquals(idMouvement, mdto.getId());
        assertEquals(false, mdto.getIsDeleted());
    }

}
