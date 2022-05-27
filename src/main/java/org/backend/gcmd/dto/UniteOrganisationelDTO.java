package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UniteOrganisationelDTO {

    private Long id;

    private String label;

    private String type;

    private String description;

    private Long uniteOrganisationelId;

    private Boolean isDeleted = false;
}
