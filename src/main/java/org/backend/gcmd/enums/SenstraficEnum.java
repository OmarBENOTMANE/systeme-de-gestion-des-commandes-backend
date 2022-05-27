package org.backend.gcmd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SenstraficEnum {

    IMPORT("import"), EXPORT("export"), TRANSIT("transit");

    private String value;


}
