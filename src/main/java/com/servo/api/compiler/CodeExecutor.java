package com.servo.api.compiler;

import com.servo.to.ExecutorBean;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

public class CodeExecutor {

    private Class<?> getClass(ExecutorBean bean) throws ClassNotFoundException, CompilationException {
        CompiledClassLoader loader = CodeCompiler.compileAndGetClassLoader(bean);
        return loader.findClass(bean.getClassName());
    }

    public boolean test(ExecutorBean bean) {
        Class<?> clazz;
        try {
            clazz = getClass(bean);
            Method method = clazz.getMethod(bean.getMethodName(), bean.getParamTypes());
            Object result = method.invoke(clazz.newInstance(), bean.getParams());
            return bean.getExpectedResult().equals(result);
        } catch (Exception e) {
            return checkException(bean, e);
        }
    }

    private boolean checkException(ExecutorBean bean, Exception e) {
        if (bean.isExceptionExpected()) {
            if (StringUtils.isBlank(bean.getExceptionType())) {
                return whenExceptionTypeIsBlank(bean, e);
            } else {
                return whenExceptionTypeIsPresent(bean, e);
            }
        } else {
            return false;
        }
    }

    private boolean whenExceptionTypeIsBlank(ExecutorBean bean, Exception e) {
        if (StringUtils.isBlank(bean.getExceptionMessage())) {
            return true;
        } else {
            return bean.getExceptionMessage().equals(e.getMessage());
        }
    }

    private boolean whenExceptionTypeIsPresent(ExecutorBean bean, Exception e) {
        if (bean.getExceptionType().equals(e.getClass().getSimpleName())) {
            if (StringUtils.isBlank(bean.getExceptionMessage())) {
                return true;
            } else {
                return bean.getExceptionMessage().equals(e.getMessage());
            }
        } else {
            return false;
        }
    }
}
