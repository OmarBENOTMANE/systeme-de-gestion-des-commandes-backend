package org.backend.gcmd.dto.AS400;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeNavireDTO {

    private Long id;

    private String typeNavire;

    private String libelle17;

    private Boolean isDeleted = false;
}
