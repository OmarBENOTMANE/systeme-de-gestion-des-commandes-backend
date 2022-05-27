package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.gcmd.enums.EnginsColisEnum;
import org.backend.gcmd.enums.ImportExportEnum;
import org.backend.gcmd.enums.MmMcEnum;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DevisDTO {

    private Long id;
    private String nomNavire;
    private LocalDate date;
    private Integer bl;
    private Integer nombreColis;
    private Double poids;
    private String designation;
    private ImportExportEnum importExport;
    private MmMcEnum mmMc;
    private Integer numeroMafi;
    private EnginsColisEnum enginsColis;
    private Integer numeroCommande;
    private String nomClient;
    private LocalDate dateFacturation;
    private LocalDate dateSortie;

    private Long escaleId;
    private Long clientId;

    private Boolean isDeleted = false;
}
