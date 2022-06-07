package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MouvementDTO {

    private Long id;

    private String codeMouvement;

    private LocalDate date;

    private LocalTime heure;

    private String codePoste;

    private Double metriquePoste;

    private Boolean isDeleted = false;

    private Long escaleId;

}
