package org.backend.gcmd.service;


import org.backend.gcmd.dto.TarifDTO;
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
@Sql(scripts = "classpath:/fixtures/tarif.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TarifServiceTest {

    Long idvar = 1L;

    @Autowired
    TarifService tarifService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(tarifService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> {
                    tarifService.findById(null);
                });
    }

    @Test
    void findById_OK_ID_Found() {
        TarifDTO udto = tarifService.findById(idvar);
        Assertions.assertNotNull(udto);
        Assertions.assertEquals(idvar, udto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> {
                    tarifService.findById(999999L);
                });
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> {
                    tarifService.save(null);
                });
    }

    @Test
    void save_OK() {
        //given
        TarifDTO tdto = TarifDTO.builder()
                .id(idvar).tarifHt(100.4).tarifTtc(120.4)
                .build();
        //when
        TarifDTO result = tarifService.save(tdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> {
                    tarifService.update(null);
                });
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> {
                    tarifService.update(null);
                });
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> {
                    tarifService.update(null);
                });
        //given
        TarifDTO tdto = TarifDTO.builder()
                .id(idvar).tarifHt(100.4).tarifTtc(120.4)
                .build();
        //when
        TarifDTO result = tarifService.update(tdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> {
                    tarifService.delete(null);
                });
    }

    @Test
    void delete_ok() {
        TarifDTO tdto = tarifService.findById(idvar);
        assertNotNull(tdto);
        assertEquals(idvar, tdto.getId());
        tarifService.delete(idvar);
        Assertions.assertTrue(tarifService.findById(idvar).getIsDeleted());
        TarifDTO result = tarifService.save(tdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<TarifDTO> page = tarifService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        TarifDTO tDTO = page.getContent().get(0);
        assertEquals(idvar, tDTO.getId());
        assertEquals(false, tDTO.getIsDeleted());
    }
}
