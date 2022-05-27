package org.backend.gcmd.service;


import org.backend.gcmd.dto.LigneBpDTO;
import org.backend.gcmd.enums.SenstraficEnum;
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
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(scripts = "classpath:/fixtures/clear.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:/fixtures/ligneBp.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class LigneBpServiceTest {

    @Autowired
    LigneBpService ligneBpService;

    @Autowired
    BulltinPrestationService bulltinPrestationService;

    Long idvar = 1L;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(ligneBpService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneBpService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        LigneBpDTO ddto = ligneBpService.findById(idvar);
        Assertions.assertNotNull(ddto);
        Assertions.assertEquals(idvar, ddto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> ligneBpService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneBpService.save(null));
    }

    @Test
    void save_OK() {
        //given
        LigneBpDTO ddto = LigneBpDTO.builder()
                .id(idvar).date(LocalDate.parse("2022-04-27")).heure(LocalTime.parse("09:00:00")).nombre(2).designationPrestation("prestation").produit("produit").sensTrafic(SenstraficEnum.valueOf("IMPORT")).tarifUnifie(true).tcConv(false).tcSuppl("tc sup").tonnageMinimum(33).tonnageReel(44).bulltinPrestationId(idvar).isDeleted(false)
                .build();
        //when
        LigneBpDTO result = ligneBpService.save(ddto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneBpService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneBpService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneBpService.update(null));
        //given
        LigneBpDTO ddto = LigneBpDTO.builder()
                .id(idvar).date(LocalDate.parse("2022-04-27")).heure(LocalTime.parse("09:00:00")).nombre(2).designationPrestation("prestation").produit("produit").sensTrafic(SenstraficEnum.valueOf("IMPORT")).tarifUnifie(true).tcConv(false).tcSuppl("tc sup").tonnageMinimum(33).tonnageReel(44).bulltinPrestationId(idvar)
                .build();
        //when
        LigneBpDTO result = ligneBpService.update(ddto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void update_Ok_bpId() {
        LigneBpDTO lbpdto = LigneBpDTO.builder().id(idvar).bulltinPrestationId(idvar).build();
        ligneBpService.update(lbpdto);
        LigneBpDTO result = ligneBpService.findById(idvar);
        assertNotNull(lbpdto);
        assertEquals(idvar, result.getId());
        assertEquals(idvar, result.getBulltinPrestationId());
    }

    @Test
    void delete_ok() {
        LigneBpDTO lbpdto = ligneBpService.findById(idvar);
        assertNotNull(lbpdto);
        assertEquals(idvar, lbpdto.getId());
        ligneBpService.delete(idvar);
        Assertions.assertTrue(ligneBpService.findById(idvar).getIsDeleted());
        LigneBpDTO result = ligneBpService.save(lbpdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<LigneBpDTO> page = ligneBpService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        LigneBpDTO lbpdto = page.getContent().get(0);
        assertEquals(idvar, lbpdto.getId());
        assertEquals(false, lbpdto.getIsDeleted());
    }
}
