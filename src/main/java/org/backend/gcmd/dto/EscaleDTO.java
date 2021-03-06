package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EscaleDTO {

    private Long id;

    private Integer numeroEscale;

    private String numeroLlyod;

    private LocalDate dateArrivee;

    private Boolean isDeleted = false;

    private Boolean isfactured = false;

    private Long navireId;

}
