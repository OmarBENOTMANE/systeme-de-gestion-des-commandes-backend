package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrestationDTO {

    private Long id;

    private String designation;

    private String typePrestation;

    private String typeTarif;

    private Long soustypeprestationId;

    private Boolean isDeleted = false;

}
