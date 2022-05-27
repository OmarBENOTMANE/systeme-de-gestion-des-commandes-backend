package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.gcmd.enums.TypePaiementEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulltinPrestationDTO {

    private Long id;

    private String description;

    private LocalDate date;

    private LocalTime heure;

    private Integer numeroDossierPrestation;

    private TypePaiementEnum typePaiement;

    private Integer codeClient;

    private String nomClient;

    private Integer numeroEscale;

    private Boolean moyenOdepClient;

    private Integer codeNature;

    private Boolean preValidation;

    private LocalDate dateDepot;

    private LocalDate dateProbableExecution;

    private String text;

    private Integer numeroCmd;

    private Boolean isDeleted = false;

    private Boolean validated = false;

    private Boolean isfactured = false;

}
