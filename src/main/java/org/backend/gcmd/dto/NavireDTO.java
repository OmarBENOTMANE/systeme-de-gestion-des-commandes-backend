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

    private String name;

    private String numeroLlyod;

    private Double longeur;

    private Double tiranteau;

    private String typeNavire;

    private Boolean isDeleted = false;
}
