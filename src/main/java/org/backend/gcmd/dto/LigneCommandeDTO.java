package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.gcmd.enums.SenstraficEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LigneCommandeDTO {

    private Long id;

    private String designationPrestation;

    private LocalDate date;

    private LocalTime heure;

    private SenstraficEnum sensTrafic;

    private String produit;

    private String tcSuppl;

    private Boolean tcConv;

    private Integer nombre;

    private Boolean tarifUnifie;

    private Integer tonnageReel;

    private Integer tonnageMinimum;

    private String description;

    private Long commandeId;

    private Long prestationId;

    private Boolean isDeleted = false;

    private Boolean isAffected = false;

    private Long genlbp;

}
