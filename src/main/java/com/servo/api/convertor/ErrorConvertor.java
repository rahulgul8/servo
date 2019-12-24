package com.servo.api.convertor;

import com.servo.to.CompileError;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import java.util.Locale;

public class ErrorConvertor {

    private ErrorConvertor() {
    }

    public static CompileError convert(Diagnostic<? extends JavaFileObject> d) {
        CompileError e = new CompileError();
        e.setColumnNumber(d.getColumnNumber());
        e.setEndPosition(d.getEndPosition());
        e.setLineNumber(d.getLineNumber());
        e.setMessage(d.getMessage(Locale.getDefault()));
        e.setPosition(d.getPosition());
        e.setStartPosition(d.getStartPosition());
        return e;
    }
}
