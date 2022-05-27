package org.backend.gcmd.service;

import org.backend.gcmd.dto.DevisDTO;
import org.backend.gcmd.enums.ImportExportEnum;
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

import static org.backend.gcmd.enums.EnginsColisEnum.COLIS;
import static org.backend.gcmd.enums.MmMcEnum.MM;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "classpath:/fixtures/clear.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:/fixtures/devis.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class DevisServiceTest {


    Long idvar = 1L;

    @Autowired
    DevisService devisService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(devisService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> devisService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        DevisDTO ddto = devisService.findById(1L);
        Assertions.assertNotNull(ddto);
        Assertions.assertEquals(1L, ddto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> devisService.findById(999999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> devisService.save(null));
    }

    @Test
    void save_OK() {
        //given
        DevisDTO ddto = DevisDTO.builder()
                .id(null).mmMc(MM).bl(22).clientId(1L).date(LocalDate.now()).dateFacturation(LocalDate.now()).dateSortie(LocalDate.now()).designation("design").enginsColis(COLIS).escaleId(1L).importExport(ImportExportEnum.IMPORT).nomClient("client").nomNavire("navire").nombreColis(2).numeroCommande(3).numeroMafi(4).poids(5.6)
                .build();
        //when
        DevisDTO result = devisService.save(ddto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> devisService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> devisService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> devisService.update(null));
        //given
        DevisDTO ddto = DevisDTO.builder()
                .id(idvar).mmMc(MM).bl(22).clientId(1L).date(LocalDate.now()).dateFacturation(LocalDate.now()).dateSortie(LocalDate.now()).designation("desi modof").enginsColis(COLIS).escaleId(1L).importExport(ImportExportEnum.IMPORT).nomClient("modif nc").nomNavire("navire").nombreColis(2).numeroCommande(3).numeroMafi(4).poids(5.6)

                .build();
        //when
        DevisDTO result = devisService.update(ddto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> devisService.delete(null));
    }

    @Test
    void delete_ok() {
        DevisDTO ddto = devisService.findById(idvar);
        assertNotNull(ddto);
        assertEquals(idvar, ddto.getId());
        devisService.delete(idvar);
        Assertions.assertTrue(devisService.findById(idvar).getIsDeleted());
        DevisDTO result = devisService.save(ddto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<DevisDTO> page = devisService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        DevisDTO bpDTO = page.getContent().get(0);
        assertEquals(idvar, bpDTO.getId());
        assertEquals(false, bpDTO.getIsDeleted());
    }
}
