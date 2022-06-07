package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "gcmd_escale")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EscaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroEscale;

    private String numeroLlyod;

    private LocalDate dateArrivee;

    private Boolean isDeleted = false;

    private Boolean isfactured = false;

    @ManyToOne
    @JoinColumn(name = "navire_id", nullable = true)
    private NavireEntity navire;

    @OneToMany(mappedBy = "escale", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ManifestEntity> manifestList;

    @OneToMany(mappedBy = "escale", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DevisEntity> devisList;

    @OneToMany(mappedBy = "escale", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CommandeEntity> commandeList;

    @OneToMany(mappedBy = "escale")
    private List<MouvementEntity> mouvementList;

}
