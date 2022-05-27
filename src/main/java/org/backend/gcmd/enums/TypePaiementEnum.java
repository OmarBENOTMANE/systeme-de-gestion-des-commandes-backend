package org.backend.gcmd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypePaiementEnum {

    COMPTANT(1), CREDIT(0);

    private int value;
}
