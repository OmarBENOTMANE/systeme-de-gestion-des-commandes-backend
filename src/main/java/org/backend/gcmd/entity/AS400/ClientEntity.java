package org.backend.gcmd.entity.AS400;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Table(name = "CLIENT")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ClientEntityAS400")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "CLCODC")
    private String codeClient;


    @Column(name = "CLNOM")
    private String nomClient;

    private Boolean isDeleted;

}
