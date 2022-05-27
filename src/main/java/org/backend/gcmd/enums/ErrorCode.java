package org.backend.gcmd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    CODE_1("Code 1"),
    CODE_2("Code 2"),
    CODE_3("Code 3");

    private String code;

    // standard getters and setters

    public static Stream<ErrorCode> stream() {
        return Stream.of(ErrorCode.values());
    }

}
