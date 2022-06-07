package org.backend.gcmd.dto.AS400;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NavireDTO {

    private Long id;

    private Double numeroLloyd;

    private String nomNavire;

    private String longeurNavire;

    private String tirantEau;

    private Double typeNavire;

    private Boolean isDeleted = false;
}
