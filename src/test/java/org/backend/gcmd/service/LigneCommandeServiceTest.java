package org.backend.gcmd.service;

import org.backend.gcmd.dto.LigneBpDTO;
import org.backend.gcmd.dto.LigneCommandeDTO;
import org.backend.gcmd.enums.SenstraficEnum;
import org.backend.gcmd.exceptions.technical.IllegalNullParamException;
import org.backend.gcmd.exceptions.technical.ObjectNotFoundException;
import org.backend.gcmd.validator.Validate;
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
@Sql(scripts = "classpath:/fixtures/ligneCommande.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class LigneCommandeServiceTest {

    Long idlignecmd = 1L;

    @Autowired
    LigneCommandeService ligneCommandeService;

    @Autowired
    LigneBpService ligneBpService;

    @Test
    void serviceInjection() {
        Assertions.assertNotNull(ligneCommandeService);
    }

    //findById
    @Test
    void findById_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneCommandeService.findById(null));
    }

    @Test
    void findById_OK_ID_Found() {
        LigneCommandeDTO lcdto = ligneCommandeService.findById(idlignecmd);
        Assertions.assertNotNull(lcdto);
        Assertions.assertEquals(idlignecmd, lcdto.getId());
    }

    @Test
    void findById_KO_ID_Not_Found() {
        Assertions.assertThrows(ObjectNotFoundException.class,
                () -> ligneCommandeService.findById(9999L));
    }

    //save
    @Test
    void save_KO_nullId() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneCommandeService.save(null));
    }

    @Test
    void save_OK() {
        //given
        LigneCommandeDTO lcdto = LigneCommandeDTO.builder()
                .id(idlignecmd).commandeId(idlignecmd).nombre(2).prestationId(idlignecmd).produit("produit").sensTrafic(SenstraficEnum.valueOf("EXPORT")).tarifUnifie(true).tcConv(false).tcSuppl("tc sup").tonnageMinimum(33).tonnageReel(44)
                .build();
        //when
        LigneCommandeDTO result = ligneCommandeService.save(lcdto);
        //then
        Assertions.assertNotNull(result.getId());
    }

    //update
    @Test
    void update_Ok_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneCommandeService.update(null));
    }

    @Test
    void update_KO_dtoNull() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneCommandeService.update(null));
    }

    @Test
    void update_Ok() {
        Assertions.assertThrows(IllegalNullParamException.class,
                () -> ligneCommandeService.update(null));
        //given
        Long idcommande = 1L;
        Long idprestation = 1L;
        LigneCommandeDTO lcdto = LigneCommandeDTO.builder()
                .id(idlignecmd).commandeId(idcommande).prestationId(idprestation).produit("produit modif")
                .build();
        //when
        ligneCommandeService.update(lcdto);
        LigneCommandeDTO result = ligneCommandeService.findById(1L);
        //then
        Assertions.assertNotNull(result.getId());
        assertEquals(1L, result.getCommandeId());
        assertEquals(1L, result.getPrestationId());
        assertEquals("produit modif", result.getProduit());
    }

    @Test
    void delete_KO_nullId() {
        assertThrows(IllegalNullParamException.class,
                () -> ligneCommandeService.delete(null));
    }

    @Test
    void delete_ok() {
        LigneCommandeDTO lcdto = ligneCommandeService.findById(idlignecmd);
        assertNotNull(lcdto);
        assertEquals(idlignecmd, lcdto.getId());
        ligneCommandeService.delete(idlignecmd);
        Assertions.assertTrue(ligneCommandeService.findById(idlignecmd).getIsDeleted());
        LigneCommandeDTO result = ligneCommandeService.save(lcdto);
        assertNotNull(result.getId());
    }

    @Test
    void findAllByIsDeletedFalse_OK() {
        Page<LigneCommandeDTO> page = ligneCommandeService.findAllByIsDeletedFalse(PageRequest.of(0, 10));
        assertNotNull(page);
        assertEquals(1, page.getContent().size());
        LigneCommandeDTO lcdto = page.getContent().get(0);
        assertEquals(idlignecmd, lcdto.getId());
        assertEquals(false, lcdto.getIsDeleted());
    }

    @Test
    void affecter_OK() {
        Validate.notNull(idlignecmd, "Id must be not null");
        LigneCommandeDTO lcdto = ligneCommandeService.findById(idlignecmd);
        if (lcdto.getIsAffected() != null && !lcdto.getIsAffected()) {
            lcdto = ligneCommandeService.affecter(1L);
            assertEquals(true, lcdto.getIsAffected());
        }
    }

    @Test
    void desaffecter() {
        LigneCommandeDTO lcdto = ligneCommandeService.affecter(1L);
        LigneCommandeDTO lc = ligneCommandeService.affecter(lcdto.getId());
        LigneBpDTO lbpdto = ligneBpService.findById(lcdto.getGenlbp());
        assertEquals(true, lbpdto.getIsDeleted());
        assertNull(lc.getGenlbp());
        assertEquals(false, lc.getIsAffected());

    }

    @Test
    void genererbp_OK() {
        LigneCommandeDTO ligneCommandeDTO = new LigneCommandeDTO();
        LigneBpDTO result = ligneCommandeService.genererbp(ligneCommandeDTO);
        assertSame(ligneCommandeDTO.getDesignationPrestation(), result.getDesignationPrestation());
        assertSame(ligneCommandeDTO.getDate(), result.getDate());
        assertSame(ligneCommandeDTO.getHeure(), result.getHeure());
        assertSame(ligneCommandeDTO.getSensTrafic(), result.getSensTrafic());
        assertSame(ligneCommandeDTO.getTcSuppl(), result.getTcSuppl());
        assertSame(ligneCommandeDTO.getTcConv(), result.getTcConv());
        assertSame(ligneCommandeDTO.getNombre(), result.getNombre());
        assertSame(ligneCommandeDTO.getTarifUnifie(), result.getTarifUnifie());
        assertSame(ligneCommandeDTO.getTonnageMinimum(), result.getTonnageMinimum());
        assertSame(ligneCommandeDTO.getTonnageReel(), result.getTonnageReel());
    }

}
