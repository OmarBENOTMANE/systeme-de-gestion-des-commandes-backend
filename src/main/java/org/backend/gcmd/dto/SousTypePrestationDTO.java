package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SousTypePrestationDTO {

    private Long id;

    private String name;

    private Long typeprestationId;

    private Boolean isDeleted = false;
}
