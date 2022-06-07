package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "PRESTATI")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "LibellePrestationEntityAS400")
public class LibellePrestationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "PRCODE")
    private String codePrestation;


    @Column(name = "PRDESI")
    private String designationPrestation;


    @Column(name = "PRCOTV")
    private String codeTVA;


    @Column(name = "PRCOAC")
    private String codeActivite;

    private Boolean isDeleted;

}
