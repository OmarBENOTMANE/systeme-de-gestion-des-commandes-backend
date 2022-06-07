package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DESCAL")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EscaleEntityAS400")
public class EscaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUESC")
    private Double numeroEscale;

    @Column(name = "NMNAVE")
    private String nomNavire;

    @Column(name = "NULLYE")
    private String numeroLloyd;

    @Column(name = "TYNAVE")
    private String typeNavire;

    @Column(name = "ETA1")
    private Double dateArrivéeEstimative;

    private Boolean isDeleted;
}
