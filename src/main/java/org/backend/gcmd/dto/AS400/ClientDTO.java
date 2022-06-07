package org.backend.gcmd.dto.AS400;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;

    private String codeClient;

    private String nomClient;

    private Boolean isDeleted = false;

}
