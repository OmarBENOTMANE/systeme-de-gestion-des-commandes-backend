package org.backend.gcmd.dto.AS400;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestationDTO {

    private Long id;

    private Double codeCpa;

    private Double numeroDossier;

    private Double numeroBonCommande;

    private Double numeroOrdrePrestation;

    private Double codePrestation;

    private LocalDate dateDebut;

    private LocalTime heureDebut;

    private LocalDate dateFin;

    private LocalTime heureFin;

    private Double poids;

    private Double quantite1;

    private Double quantite2;

    private String importExport;

    private String numeroDeclaration;

    private Boolean isDeleted = false;

}
