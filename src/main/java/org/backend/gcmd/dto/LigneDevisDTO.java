package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LigneDevisDTO {


    private Long id;

    private String designation;
    private Integer quantite;
    private Double nombreUnite;
    private Double total;

    private Long prestationId;
    private Long devisId;

    private Boolean isDeleted = false;

}
