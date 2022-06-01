package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "gcmd_type_prestation")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TypePrestationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "typePrestation")
    private List<SousTypePrestationEntity> soustypeprestationList;

    @ManyToOne
    @JoinColumn(name = "unite_organisationel_id", nullable = true)
    private UniteOrganisationelEntity uniteOrganisationel;

    private Boolean isDeleted = false;

}
