package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "gcmd_soustypeprestation")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SousTypePrestationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "soustypeprestation", fetch = FetchType.LAZY)
    private List<PrestationEntity> prestationList;

    @ManyToOne
    @JoinColumn(name = "typeprestation_id", nullable = true)
    private TypePrestationEntity typeprestation;

    private Boolean isDeleted = false;

}
