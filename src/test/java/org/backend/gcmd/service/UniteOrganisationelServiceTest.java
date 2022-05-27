package org.backend.gcmd.service;

import org.backend.gcmd.dto.UniteOrganisationelDTO;
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
@Sql(scripts = "classpath:/fixtures/uniteOrganisationel.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UniteOrganisationelServiceTest {

    @Autowired
    UniteOrganisationelService uniteOrganisationelService;

    Long idvar = 1L;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(uniteOrganisationelService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> uniteOrganisationelService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        UniteOrganisationelDTO udto = uniteOrganisationelService.findById(1L);
        Assertions.assertNotNull(udto);
        Assertions.assertEquals(idvar, udto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> uniteOrganisationelService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> uniteOrganisationelService.save(null));
    }

    @Test
    void save_OK() {
        //given
        UniteOrganisationelDTO udto = UniteOrganisationelDTO.builder()
                .id(null).label("label").type("type").description("desciption").uniteOrganisationelId(idvar)
                .build();
        //when
        UniteOrganisationelDTO result = uniteOrganisationelService.save(udto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> uniteOrganisationelService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> uniteOrganisationelService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> uniteOrganisationelService.update(null));
        //given
        UniteOrganisationelDTO udto = UniteOrganisationelDTO.builder()
                .id(idvar).label("label").type("type").description("desciption")
                .build();
        //when
        UniteOrganisationelDTO result = uniteOrganisationelService.update(udto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> uniteOrganisationelService.delete(null));
    }

    @Test
    void delete_ok() {
        UniteOrganisationelDTO udto = uniteOrganisationelService.findById(idvar);
        assertNotNull(udto);
        assertEquals(idvar, udto.getId());
        uniteOrganisationelService.delete(idvar);
        Assertions.assertTrue(uniteOrganisationelService.findById(idvar).getIsDeleted());
        UniteOrganisationelDTO result = uniteOrganisationelService.save(udto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<UniteOrganisationelDTO> page = uniteOrganisationelService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        UniteOrganisationelDTO udto = page.getContent().get(0);
        assertEquals(idvar, udto.getId());
        assertEquals(false, udto.getIsDeleted());
    }


}
