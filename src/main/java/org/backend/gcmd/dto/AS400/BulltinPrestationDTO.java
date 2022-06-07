package org.backend.gcmd.dto.AS400;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BulltinPrestationDTO {

    private Long id;

    private String numeroBulletinPrestation;

    private String codeCpa;

    private String codeOperation;

    private String codeClient;

    private String nomClient;

    private Double numeroEscale;

    private String codeOperation1;

    private String codeOperation2;

    private String codeOperation3;

    private String codeNaturePrestation;

    private String bulltinAnnule;

    private String codeCauseAnnulation;

    private String numeroFacture;

    private LocalDate dateLimiteFacture;

    private Boolean isDeleted = false;
}
