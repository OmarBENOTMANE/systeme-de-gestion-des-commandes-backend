package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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

    private String description;

    private LocalDate dateMouvement;

    @ManyToOne
    @JoinColumn(name = "navire_id", nullable = true)
    private NavireEntity navire;

    private Boolean isDeleted = false;


}
