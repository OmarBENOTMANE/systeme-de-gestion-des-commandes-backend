package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@Table(name = "DEXECPR")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PrestationEntityAS400")
public class PrestationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "EXCPA")
    private Double codeCpa;


    @Column(name = "EXNUBP")
    private Double numeroDossier;


    @Column(name = "EXNUCD")
    private Double numeroBonCommande;


    @Column(name = "EXNUOR")
    private Double numeroOrdrePrestation;


    @Column(name = "EXCDPR")
    private Double codePrestation;


    @Column(name = "EXDTDE")
    private LocalDate dateDebut;


    @Column(name = "EXHEDE")
    private LocalTime heureDebut;


    @Column(name = "EXDTFN")
    private LocalDate dateFin;


    @Column(name = "EXHEFN")
    private LocalTime heureFin;


    @Column(name = "EXTONN")
    private Double poids;


    @Column(name = "EXNBRS")
    private Double quantite1;


    @Column(name = "EXNBRT")
    private Double quantite2;

    @Column(name = "EXTYIE")
    private String importExport;

    @Column(name = "EXNUDE")
    private String numeroDeclaration;

    private Boolean isDeleted;
}
