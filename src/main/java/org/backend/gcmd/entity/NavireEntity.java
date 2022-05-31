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

    private String navireName;
    private Integer numeroEscale;
    private String consignataire;
    private String etat;

    @OneToMany(mappedBy = "navire")
    private List<EscaleEntity> escaleList;

    private Boolean isDeleted = false;

}
