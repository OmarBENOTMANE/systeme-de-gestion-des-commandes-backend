package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MouvementDTO {

    private Long id;

    private String description;

    private LocalDate dateMouvement;

    private Long escaleId;

    private Boolean isDeleted = false;
}
