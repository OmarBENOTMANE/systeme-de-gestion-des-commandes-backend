package org.backend.gcmd.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {

    private String code;
    private String message;
    private int status;
    private LocalDateTime dateTime;

    private Boolean isDeleted = false;

}
