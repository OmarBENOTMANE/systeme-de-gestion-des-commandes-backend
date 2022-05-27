package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Table(name = "gcmd_commande")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommandeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroBc;

    private String navire;

    private String consignataire;

    private LocalDate dateAmarage;

    private LocalDate dateDesamarage;

    private Integer lht;

    private Double jaugeBrute;

    private String poste;

    private String capitaine;

    private Integer numeroEscale;

    private Integer numeroCredit;

    private Integer numeroCommande;

    private Integer connaissement;

    private Integer bulletinReception;

    private Boolean isDeleted = false;

    private Boolean isfactured = false;

    @ManyToOne
    @JoinColumn(name = "escale_id", nullable = true)
    private EscaleEntity escale;

    @ManyToOne
    @JoinColumn(name = "devis_id", nullable = true)
    private DevisEntity devis;

    @ManyToOne
    @JoinColumn(name = "bulltin_prestation_id", nullable = true)
    private BulltinPrestationEntity bulltinPrestation;

    @OneToMany(mappedBy = "commande")
    private List<LigneCommandeEntity> ligneCommandeEntityList;


}
