package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gcmd_tarif")
public class TarifEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double tarifHt;

    private Double tarifTtc;

    @OneToOne(mappedBy = "tarif")
    private PrestationEntity prestation;

    private Boolean isDeleted = false;

}
