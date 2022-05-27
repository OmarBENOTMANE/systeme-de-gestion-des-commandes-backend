package org.backend.gcmd.entity;

import lombok.*;
import org.backend.gcmd.enums.TypePaiementEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Table(name = "gcmd_bulltin_prestation")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class BulltinPrestationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDate date;

    private LocalTime heure;

    private Integer numeroDossierPrestation;

    @Enumerated(EnumType.ORDINAL)
    private TypePaiementEnum typePaiement;

    private Integer codeClient;

    private String nomClient;

    private Integer numeroEscale;

    private Boolean moyenOdepClient;

    private Integer codeNature;

    private Boolean preValidation;

    private LocalDate dateDepot;

    private LocalDate dateProbableExecution;

    private String text;

    private Integer numeroCmd;

    @OneToMany(mappedBy = "bulltinPrestation", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CommandeEntity> commandeList;

    @OneToMany(mappedBy = "bulltinPrestation", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LigneBpEntity> ligneBpEntityList;

    private Boolean isDeleted = false;

    private Boolean validated = false;

    private Boolean isfactured = false;

}
