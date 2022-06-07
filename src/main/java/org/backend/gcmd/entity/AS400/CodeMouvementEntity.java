package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DMVNAV")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CodeMouvementEntityAS400")
public class CodeMouvementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODMVT")
    private String codeMouvementNavire;

    @Column(name = "LIBMVT")
    private String libelle;

    private Boolean isDeleted;
}
