package org.backend.gcmd.dto.AS400;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibellePrestationDTO {

    private Long id;

    private String codePrestation;

    private String designationPrestation;

    private String codeTVA;

    private String codeActivite;

    private Boolean isDeleted = false;
}
