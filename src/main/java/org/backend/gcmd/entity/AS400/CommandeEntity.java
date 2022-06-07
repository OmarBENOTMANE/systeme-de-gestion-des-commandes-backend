package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@Table(name = "DCOMPRD")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CommandeEntityAS400")
public class CommandeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CPAPR")
    private String codeCpa;


    @Column(name = "NUMBPR")
    private String numeroDossier;


    @Column(name = "NUMBCD")
    private String numeroBonCommande;


    @Column(name = "DATECD")
    private LocalDate dateCreationCommande;


    @Column(name = "DATEEX")
    private LocalDate dateExecutionCommande;

    private Boolean isDeleted;
}
