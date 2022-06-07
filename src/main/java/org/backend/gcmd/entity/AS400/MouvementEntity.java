package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@Table(name = "DESTDM")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MouvementEntityAS400")
public class MouvementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUESCM")
    private Double numeroEscale;

    @Column(name = "DTDEBM")
    private String codeMouvementNavire;

    @Column(name = "HRDEBM")
    private LocalDate Date;

    @Column(name = "CPSTM")
    private LocalTime Heure;

    @Column(name = "CODMVM")
    private String codePoste;

    @Column(name = "METR1M")
    private Double metriquePoste;

    private Boolean isDeleted;
}
