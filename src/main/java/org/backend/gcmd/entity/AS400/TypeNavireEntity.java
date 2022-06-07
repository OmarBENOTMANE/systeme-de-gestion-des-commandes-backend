package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "DTYNAV")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TypeNavireEntityAS400")
public class TypeNavireEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TYPNV")
    private String typeNavire;

    @Column(name = "LIBTYPNV")
    private String libelle17;

    private Boolean isDeleted;
}
