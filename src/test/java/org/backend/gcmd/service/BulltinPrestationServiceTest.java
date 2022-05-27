package org.backend.gcmd.service;

import org.backend.gcmd.dto.BulltinPrestationDTO;
import org.backend.gcmd.enums.TypePaiementEnum;
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
@Sql(scripts = "classpath:/fixtures/bulltinPrestation.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class BulltinPrestationServiceTest {

    @Autowired
    BulltinPrestationService bulltinPrestationService;

    Long idvar = 1L;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(bulltinPrestationService);
    }

    @Test
    void findById_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> bulltinPrestationService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        BulltinPrestationDTO bpdto = bulltinPrestationService.findById(idvar);
        Assertions.assertNotNull(bpdto);
        Assertions.assertEquals(idvar, bpdto.getId());
        Assertions.assertEquals("blabla", bpdto.getDescription());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        assertThrows(ObjectNotFoundException.class,
                () -> bulltinPrestationService.findById(9999L));
    }

    @Test
    void save_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> bulltinPrestationService.save(null));
    }

    @Test
    void save_OK() {
        //given
        BulltinPrestationDTO bpdto = BulltinPrestationDTO.builder()
                .codeClient(4).codeNature(44).date(LocalDate.now()).dateDepot(LocalDate.now()).dateProbableExecution(LocalDate.now()).description("new4").moyenOdepClient(true).nomClient("clinet4").numeroCmd(444).numeroDossierPrestation(4444).numeroEscale(404).preValidation(false).text("blabla").typePaiement(TypePaiementEnum.COMPTANT)
                .build();
        //when
        BulltinPrestationDTO result = bulltinPrestationService.save(bpdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void update_Ok_dtoNull() {
        assertThrows(IllegalNullParamException.class,
                () -> bulltinPrestationService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        assertThrows(IllegalNullParamException.class,
                () -> bulltinPrestationService.update(null));
    }

    @Test
    void update_Ok() {
        assertThrows(IllegalNullParamException.class,
                () -> bulltinPrestationService.update(null));
        //given
        BulltinPrestationDTO bpdto = BulltinPrestationDTO.builder()
                .id(idvar).description("bla").moyenOdepClient(false).preValidation(false)
                .build();
        //when
        BulltinPrestationDTO result = bulltinPrestationService.update(bpdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> bulltinPrestationService.delete(null));
    }

    @Test
    void delete_ok() {
        findById_OK_ID_Found();
        bulltinPrestationService.delete(idvar);
        Assertions.assertTrue(bulltinPrestationService.findById(idvar).getIsDeleted());
        save_OK();
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<BulltinPrestationDTO> page = bulltinPrestationService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        BulltinPrestationDTO bpDTO = page.getContent().get(0);
        assertEquals(idvar, bpDTO.getId());
        assertEquals(false, bpDTO.getIsDeleted());
    }
}
