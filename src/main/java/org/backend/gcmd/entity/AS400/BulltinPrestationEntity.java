package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@Table(name = "DBULPRD")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "BulltinPrestationEntityAS400")
public class BulltinPrestationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BUNUBP")
    private String numeroBulletinPrestation;

    @Column(name = "BUCPA")
    private String codeCpa;

    @Column(name = "BUCODE")
    private String codeOperation;

    @Column(name = "BUTYPC")
    private String codeClient;

    @Column(name = "BUNOMC")
    private String nomClient;


    @Column(name = "BUNUES")
    private Double numeroEscale;


    @Column(name = "BUMOYE")
    private String codeOperation1;

    @Column(name = "BUPRVA")
    private String codeOperation2;


    @Column(name = "BUVALI")
    private String codeOperation3;


    @Column(name = "BUCDNA")
    private String codeNaturePrestation;

    @Column(name = "BUANUL")
    private String bulltinAnnule;


    @Column(name = "BUCSAN")
    private String codeCauseAnnulation;

    @Column(name = "BUNUFA")
    private String numeroFacture;

    @Column(name = "BUDTLI")
    private LocalDate dateLimiteFacture;

    private Boolean isDeleted;
}
