package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "gcmd_unite_organisationel")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UniteOrganisationelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private String type;

    private String description;

    @OneToMany(mappedBy = "uniteOrganisationel", fetch = FetchType.LAZY)
    private List<TypePrestationEntity> typePrestationList;

    private Boolean isDeleted = false;


}
