package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@Table(name = "gcmd_mouvement")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MouvementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeMouvement;

    private LocalDate date;

    private LocalTime heure;

    private String codePoste;

    private Double metriquePoste;

    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "escale_id", nullable = true)
    private EscaleEntity escale;

}
