package com.altarix.crud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class WebServiceLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceLogger.class);

    @Pointcut("@annotation(com.altarix.crud.annotation.Loggable)")
    public void loggableMethods() { }

    @After("loggableMethods()")
    public void logMethod(JoinPoint joinPoint) {
        LOGGER.info("Executing method: " + joinPoint.getSignature().getName() + " with parameters: " + joinPoint.getArgs());
    }
}
