package org.backend.gcmd.entity;

import lombok.*;
import org.backend.gcmd.enums.EnginsColisEnum;
import org.backend.gcmd.enums.ImportExportEnum;
import org.backend.gcmd.enums.MmMcEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "gcmd_devis")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DevisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomNavire;
    private LocalDate date;
    private Integer bl;
    private Integer nombreColis;
    private Double poids;
    private String designation;

    @Enumerated(EnumType.STRING)
    private ImportExportEnum importExport;

    @Enumerated(EnumType.STRING)
    private MmMcEnum mmMc;
    private Integer numeroMafi;

    @Enumerated(EnumType.STRING)
    private EnginsColisEnum enginsColis;

    private Integer numeroCommande;
    private String nomClient;
    private LocalDate dateFacturation;
    private LocalDate dateSortie;

    @ManyToOne
    @JoinColumn(name = "escale_id", nullable = true)
    private EscaleEntity escale;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private ClientEntity client;

    @OneToMany(mappedBy = "devis", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LigneDevisEntity> ligneDevisList;

    @OneToMany(mappedBy = "devis", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CommandeEntity> commandeList;

    private Boolean isDeleted = false;
}
