package com.servo.to;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompileError {

    private long lineNumber;

    private long columnNumber;

    private long endPosition;

    private String message;

    private long position;

    private long startPosition;

}
