package org.backend.gcmd.service;


import org.backend.gcmd.dto.ClientDTO;
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
@Sql(scripts = "classpath:/fixtures/client.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ClientServiceTest {

    Long idvar = 1L;

    @Autowired
    ClientService clientService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(clientService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> clientService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        ClientDTO cdto = clientService.findById(idvar);
        Assertions.assertNotNull(cdto);
        Assertions.assertEquals(idvar, cdto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> clientService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> clientService.save(null));
    }

    @Test
    void save_OK() {
        //given
        ClientDTO cdto = ClientDTO.builder()
                .id(idvar).email("client2@mail.com").name("client2").phone("0626262626").typeClientId(idvar).isDeleted(false)
                .build();
        //when
        ClientDTO result = clientService.save(cdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> clientService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> clientService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> clientService.update(null));
        //given
        ClientDTO cdto = ClientDTO.builder()
                .id(idvar).email("client1@modif.com").name("modif1").phone("0606060620").typeClientId(idvar)
                .build();
        //when
        ClientDTO result = clientService.update(cdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void update_Ok_TypeClientId() {
        ClientDTO cdto = ClientDTO.builder().id(idvar).typeClientId(idvar).build();
        clientService.update(cdto);
        ClientDTO result = clientService.findById(idvar);
        assertNotNull(cdto);
        assertEquals(idvar, result.getId());
        assertEquals(idvar, result.getTypeClientId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> clientService.delete(null));
    }

    @Test
    void delete_ok() {
        ClientDTO cdto = clientService.findById(idvar);
        assertNotNull(cdto);
        assertEquals(idvar, cdto.getId());
        clientService.delete(idvar);
        Assertions.assertTrue(clientService.findById(idvar).getIsDeleted());
        ClientDTO result = clientService.save(cdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<ClientDTO> page = clientService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        ClientDTO cDTO = page.getContent().get(0);
        assertEquals(idvar, cDTO.getId());
        assertEquals(false, cDTO.getIsDeleted());
    }
}
