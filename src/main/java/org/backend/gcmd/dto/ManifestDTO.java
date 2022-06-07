package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ManifestDTO {


    private Long id;
    private String designation;
    private String reference;
    private Double quantite;

    private Long escaleId;

    private Boolean isDeleted = false;
}
