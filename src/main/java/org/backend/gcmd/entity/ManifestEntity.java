package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "gcmd_manifest")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ManifestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String designation;
    private String reference;
    private Double quantite;

    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "escale_id", nullable = true)
    private EscaleEntity escale;


}
