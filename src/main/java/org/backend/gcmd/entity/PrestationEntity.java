package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "gcmd_prestation")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PrestationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;

    private String typePrestation;

    private String typeTarif;

    @OneToMany(mappedBy = "prestation")
    private List<LigneDevisEntity> ligneDevisList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tarif_id", referencedColumnName = "id")
    private TarifEntity tarif;

    @ManyToOne()
    @JoinColumn(name = "soustypeprestation_id", nullable = true)
    private SousTypePrestationEntity soustypeprestation;

    private Boolean isDeleted = false;


}
