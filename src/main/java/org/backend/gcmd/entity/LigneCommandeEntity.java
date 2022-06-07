package org.backend.gcmd.entity;

import lombok.*;
import org.backend.gcmd.enums.SenstraficEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "gcmd_ligne_commande")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LigneCommandeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designationPrestation;

    private LocalDate date;

    private LocalTime heure;

    @Enumerated(EnumType.STRING)
    private SenstraficEnum sensTrafic;

    private String produit;

    private String tcSuppl;

    private Boolean tcConv;

    private Integer nombre;

    private Boolean tarifUnifie;

    private Integer tonnageReel;

    private Integer tonnageMinimum;

    private String description;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = true)
    private CommandeEntity commande;

    @OneToMany(mappedBy = "commande", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LigneCommandeEntity> ligneCommandeList;

    @ManyToOne
    @JoinColumn(name = "prestation_id", nullable = true)
    private PrestationEntity prestation;

    private Boolean isDeleted = false;

    private Boolean isAffected = false;

    private Long genlbp;

    private Boolean isSelected = true;

}

