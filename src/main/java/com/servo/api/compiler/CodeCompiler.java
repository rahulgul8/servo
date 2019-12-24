package com.servo.api.compiler;

import com.servo.to.CodeBean;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeCompiler {

    private CodeCompiler() {
    }

    public static List<Diagnostic<? extends JavaFileObject>> compile(CodeBean codeBean) {
        try {
            compileAndGetClassLoader(codeBean.getCode(), codeBean.getClassName());
            return Collections.emptyList();
        } catch (CompilationException e) {
            return e.getList();
        }
    }

    public static CompiledClassLoader compileAndGetClassLoader(CodeBean codeBean) throws CompilationException {
        return compileAndGetClassLoader(codeBean.getCode(), codeBean.getClassName());
    }

    public static CompiledClassLoader compileAndGetClassLoader(String code, String className)
            throws CompilationException {

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        JavaFileObject compilationUnit = new SourceFromString(className, code);

        SimpleJavaFileManager fileManager = new SimpleJavaFileManager(
                compiler.getStandardFileManager(null, null, null));

        JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, fileManager, diagnostics, null, null,
                Arrays.asList(compilationUnit));

        if (!compilationTask.call()) {
            throw new CompilationException(diagnostics.getDiagnostics());
        }

        return new CompiledClassLoader(fileManager.getGeneratedOutputFiles());
    }
}
