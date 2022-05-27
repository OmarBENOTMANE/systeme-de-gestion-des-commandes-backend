package org.backend.gcmd.service;

import org.backend.gcmd.dto.TypeClientDTO;
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
@Sql(scripts = "classpath:/fixtures/typeClient.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TypeClientTest {

    Long idvar = 1L;

    @Autowired
    TypeClientService typeClientService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(typeClientService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typeClientService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        TypeClientDTO tdto = typeClientService.findById(idvar);
        Assertions.assertNotNull(tdto);
        Assertions.assertEquals(idvar, tdto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> typeClientService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typeClientService.save(null));
    }

    @Test
    void save_OK() {
        //given
        TypeClientDTO tcdto = TypeClientDTO.builder()
                .id(idvar).name("client").isDeleted(false)
                .build();
        //when
        TypeClientDTO result = typeClientService.save(tcdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typeClientService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typeClientService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> typeClientService.update(null));
        //given
        TypeClientDTO tdto = TypeClientDTO.builder()
                .id(idvar).name("client")
                .build();
        //when
        TypeClientDTO result = typeClientService.update(tdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> typeClientService.delete(null));
    }

    @Test
    void delete_ok() {
        TypeClientDTO tcdto = typeClientService.findById(idvar);
        assertNotNull(tcdto);
        assertEquals(idvar, tcdto.getId());
        typeClientService.delete(idvar);
        Assertions.assertTrue(typeClientService.findById(idvar).getIsDeleted());
        TypeClientDTO result = typeClientService.save(tcdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<TypeClientDTO> page = typeClientService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        TypeClientDTO tcDTO = page.getContent().get(0);
        assertEquals(idvar, tcDTO.getId());
        assertEquals(false, tcDTO.getIsDeleted());
    }

}
