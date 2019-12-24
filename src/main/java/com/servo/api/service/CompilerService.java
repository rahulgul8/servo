package com.servo.api.service;

import com.servo.api.compiler.CodeCompiler;
import com.servo.api.convertor.ErrorConvertor;
import com.servo.to.CodeBean;
import com.servo.to.CompileError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompilerService {

    @PostMapping("compile")
    public List<CompileError> compile(@RequestBody CodeBean codeBean) {
        List<CompileError> response = CodeCompiler.compile(codeBean).stream().map(ErrorConvertor::convert)
                .peek(e -> System.out.println(e.getMessage())).collect(Collectors.toList());

        return response;
    }
}
