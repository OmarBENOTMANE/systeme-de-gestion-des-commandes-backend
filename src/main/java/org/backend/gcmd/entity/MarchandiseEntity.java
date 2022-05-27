package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "gcmd_marchandise")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MarchandiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private String reference;
    private Double quantite;

    @ManyToOne
    @JoinColumn(name = "escale_id", nullable = true)
    private EscaleEntity escale;

    private Boolean isDeleted = false;


}
