package org.backend.gcmd.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Table(name = "gcmd_client")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DevisEntity> devisList;

    @ManyToOne
    @JoinColumn(name = "type_client_id", nullable = true)
    private TypeClientEntity typeClient;

    private Boolean isDeleted = false;

}
