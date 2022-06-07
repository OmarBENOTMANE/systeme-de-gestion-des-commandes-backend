package org.backend.gcmd.dto.AS400;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MouvementDTO {

    private Long id;

    private Double numeroEscale;

    private String codeMouvementNavire;

    private LocalDate date;

    private LocalTime heure;

    private String codePoste;

    private Double metriquePoste;

    private Boolean isDeleted = false;


}
