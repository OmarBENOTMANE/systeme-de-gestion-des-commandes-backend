package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NavireDTO {

    private Long id;
    private String navireName;
    private Integer numeroEscale;
    private String consignataire;
    private String etat;

    private Boolean isDeleted = false;
}
