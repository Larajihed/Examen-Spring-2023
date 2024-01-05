package com.example.larayedhjihed.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @After("execution(* com.example.larayedhjihed.services.*.addVehiculeReservationAndAffectToWashingservice(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        log.info("Waiting for a Worker");
    }

}
