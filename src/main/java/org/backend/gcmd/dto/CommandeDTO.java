package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommandeDTO {

    private Long id;

    private Integer numeroBc;

    private String navire;

    private String consignataire;

    private LocalDate dateAmarage;

    private LocalDate dateDesamarage;

    private Integer lht;

    private Double jaugeBrute;

    private String poste;

    private String capitaine;

    private Integer numeroEscale;

    private Integer numeroCredit;

    private Integer numeroCommande;

    private Integer connaissement;

    private Integer bulletinReception;

    private Long escaleId;

    private Long devisId;

    private Long bulltinPrestationId;

    private Boolean isDeleted = false;

    private Boolean isfactured = false;

}
