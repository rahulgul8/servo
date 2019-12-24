package com.servo.to;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExecutorBean extends CodeBean {

    private String methodName;

    private Class<?>[] paramTypes;

    private Object[] params;

    private Object expectedResult;

    private boolean isExceptionExpected;

    private String exceptionType;

    private String exceptionMessage;
}
