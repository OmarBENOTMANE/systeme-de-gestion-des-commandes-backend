package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "gcmd_navire")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NavireEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String numeroLlyod;

    private Double longeur;

    private Double tiranteau;

    private String typeNavire;

    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "navire")
    private List<EscaleEntity> escaleList;

}
