package org.backend.gcmd.dto.AS400;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeMouvementDTO {

    private Long id;

    private String codeMouvementNavire;

    private String libelle;

    private Boolean isDeleted = false;
}
