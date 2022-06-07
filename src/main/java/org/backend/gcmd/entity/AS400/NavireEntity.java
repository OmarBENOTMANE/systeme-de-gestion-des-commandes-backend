package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DNAVIR")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "NavireEntityAS400")
public class NavireEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NULLYD")
    private Double numeroLloyd;

    @Column(name = "NMNAV")
    private String nomNavire;

    @Column(name = "LNGNAV")
    private String longeurNavire;

    @Column(name = "TREMX")
    private String tirantEau;

    @Column(name = "TYPEN")
    private Double typeNavire;

    private Boolean isDeleted;
}
