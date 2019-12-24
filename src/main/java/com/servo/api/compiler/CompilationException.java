package com.servo.api.compiler;

import lombok.Getter;
import lombok.Setter;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import java.util.List;

@Getter
@Setter
public class CompilationException extends Exception {

    private static final long serialVersionUID = -6162005186151034001L;

    private List<Diagnostic<? extends JavaFileObject>> list;

    public CompilationException(List<Diagnostic<? extends JavaFileObject>> list) {
        this.list = list;
    }

}
