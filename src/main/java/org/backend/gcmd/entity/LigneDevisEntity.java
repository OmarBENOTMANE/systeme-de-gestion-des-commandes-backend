package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "gcmd_ligne_devis")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LigneDevisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;
    private Integer quantite;
    private Double nombreUnite;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "devis_id", nullable = true)
    private DevisEntity devis;

    @ManyToOne
    @JoinColumn(name = "prestation_id", nullable = true)
    private PrestationEntity prestation;

    private Boolean isDeleted = false;
}
