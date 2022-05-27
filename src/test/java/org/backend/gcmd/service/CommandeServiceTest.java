package org.backend.gcmd.service;

import org.backend.gcmd.dto.CommandeDTO;
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
@Sql(scripts = "classpath:/fixtures/commande.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CommandeServiceTest {

    Long idcommande = 1L;

    @Autowired
    CommandeService commandeService;

    @Autowired
    BulltinPrestationService bulltinPrestationService;

    @Test
    void serviceInjection() {
        assertNotNull(commandeService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> commandeService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        CommandeDTO cdto = commandeService.findById(idcommande);
        assertNotNull(cdto);
        assertEquals(idcommande, cdto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> commandeService.findById(9999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> commandeService.save(null));
    }

    @Test
    void save_OK() {
        //given
        CommandeDTO cdto = CommandeDTO.builder()
                .bulletinReception(1).bulltinPrestationId(idcommande).capitaine("capi").connaissement(2).consignataire("consi").dateAmarage(LocalDate.now()).dateDesamarage(LocalDate.now()).devisId(null).escaleId(null).jaugeBrute(2.0).lht(3).numeroCommande(4).numeroCredit(5).navire("navi2").numeroBc(6).numeroEscale(7).poste("poste").isDeleted(false).isfactured(false)
                .build();
        //when
        CommandeDTO result = commandeService.save(cdto);
        //then
        assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> commandeService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> commandeService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> commandeService.update(null));
        //given
        CommandeDTO cdto = CommandeDTO.builder()
                .id(idcommande).capitaine("capi cmd modif")
                .build();
        //when
        CommandeDTO result = commandeService.update(cdto);
        //then
        assertNotNull(result.getId());
        assertEquals("capi cmd modif", result.getCapitaine());
    }

    @Test
    void update_Ok_bpId() {
        Long idbp = 1L;
        CommandeDTO cdto = CommandeDTO.builder().id(idcommande).bulltinPrestationId(idbp).build();
        commandeService.update(cdto);
        CommandeDTO result = commandeService.findById(idcommande);
        assertNotNull(cdto);
        assertEquals(idcommande, result.getId());
        assertEquals(idcommande, result.getBulltinPrestationId());
    }

    @Test
    void update_Ok_devisId() {
        Long iddevis = 1L;
        CommandeDTO cdto = CommandeDTO.builder().id(idcommande).devisId(iddevis).build();
        commandeService.update(cdto);
        CommandeDTO result = commandeService.findById(idcommande);
        assertNotNull(cdto);
        assertEquals(idcommande, result.getId());
        assertEquals(idcommande, result.getDevisId());
    }

    @Test
    void update_Ok_escaleId() {
        Long idescale = 1L;
        CommandeDTO cdto = CommandeDTO.builder().id(idcommande).escaleId(idescale).build();
        commandeService.update(cdto);
        CommandeDTO result = commandeService.findById(idcommande);
        assertNotNull(cdto);
        assertEquals(idcommande, result.getId());
        assertEquals(idcommande, result.getEscaleId());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> commandeService.delete(null));
    }

    @Test
    void delete_ok() {
        CommandeDTO cdto = commandeService.findById(idcommande);
        assertNotNull(cdto);
        assertEquals(idcommande, cdto.getId());
        commandeService.delete(idcommande);
        Assertions.assertTrue(commandeService.findById(idcommande).getIsDeleted());
        CommandeDTO result = commandeService.save(cdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<CommandeDTO> page = commandeService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        CommandeDTO cDTO = page.getContent().get(0);
        assertEquals(idcommande, cDTO.getId());
        assertEquals(false, cDTO.getIsDeleted());
    }

    @Test
    void findAllByIsfacturedFalse_OK() {
        Page<CommandeDTO> page = commandeService.findAllByIsfacturedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        CommandeDTO cDTO = page.getContent().get(0);
        assertEquals(idcommande, cDTO.getId());
        assertEquals(false, cDTO.getIsfactured());
    }
}
