package org.backend.gcmd.service;

import org.backend.gcmd.dto.LigneDevisDTO;
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
@Sql(scripts = "classpath:/fixtures/ligneDevis.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class LigneDevisServiceTest {

    private final Long idlignedevis = 1L;
    @Autowired
    LigneDevisService ligneDevisService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(ligneDevisService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneDevisService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        LigneDevisDTO lddto = ligneDevisService.findById(idlignedevis);
        Assertions.assertNotNull(lddto);
        Assertions.assertEquals(1L, lddto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> ligneDevisService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneDevisService.save(null));
    }

    @Test
    void save_OK() {
        //given
        LigneDevisDTO lddto = LigneDevisDTO.builder()
                .designation("desi").devisId(null).nombreUnite(5.4).prestationId(null).quantite(2).total(3.4)
                .build();
        //when
        LigneDevisDTO result = ligneDevisService.save(lddto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneDevisService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneDevisService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneDevisService.update(null));
        //given

        LigneDevisDTO lddto = LigneDevisDTO.builder()
                .id(idlignedevis).devisId(1L).prestationId(1L)
                .build();
        //when
        ligneDevisService.update(lddto);
        //then
        LigneDevisDTO result = ligneDevisService.findById(1L);
        Assertions.assertNotNull(result.getId());
        assertEquals(1L, result.getDevisId());
        assertEquals(1L, result.getPrestationId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> ligneDevisService.delete(null));
    }

    @Test
    void delete_ok() {
        LigneDevisDTO lddto = ligneDevisService.findById(idlignedevis);
        assertNotNull(lddto);
        assertEquals(idlignedevis, lddto.getId());
        ligneDevisService.delete(idlignedevis);
        Assertions.assertTrue(ligneDevisService.findById(idlignedevis).getIsDeleted());
        LigneDevisDTO result = ligneDevisService.save(lddto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<LigneDevisDTO> page = ligneDevisService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        LigneDevisDTO lddto = page.getContent().get(0);
        assertEquals(idlignedevis, lddto.getId());
        assertEquals(false, lddto.getIsDeleted());
    }
}
