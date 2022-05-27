package org.backend.gcmd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Long typeClientId;

    private Boolean isDeleted = false;

}
