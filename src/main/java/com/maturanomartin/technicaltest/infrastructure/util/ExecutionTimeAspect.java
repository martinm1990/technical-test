package com.maturanomartin.technicaltest.infrastructure.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

    @Around("@annotation(ExecutionTime)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder("Execution time: ");
        Instant start = Instant.now();

        Object result = joinPoint.proceed();

        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();

        stringBuilder.append(time);
        stringBuilder.append(" ms");

        log.info(stringBuilder.toString());

        return result;
    }
}
