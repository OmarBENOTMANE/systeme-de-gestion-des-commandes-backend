package org.backend.gcmd.dto.AS400;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDTO {

    private Long id;

    private String codeCpa;


    private String numeroDossier;

    private String numeroBonCommande;


    private LocalDate dateCreationCommande;

    private LocalDate dateExecutionCommande;

    private Boolean isDeleted;

}
