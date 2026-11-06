package com.flmfoods.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.flmfoods.dto.ItemResponseDTO;

@Aspect
@Component
public class LoggingAspect {
	
	
	private static final Logger log= LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* com.flmfoods.controller.ItemController.*(..))")
	public void logBefore(JoinPoint joinPoint)
	{
		log.info("Entering Method : {}", joinPoint.getSignature().getName());
		
	}
	
	@After("execution(* com.flmfoods.controller.ItemController.*(..))")
	public void logAfter(JoinPoint joinPoint)
	{
		log.info("Returning from Method...{}", joinPoint.getSignature().getName());
		
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.flmfoods.service.impl.*.*(..))",
			throwing = "ex"
			)
	public void logAfterThrowing(Exception ex)
	{
		log.error("Encountered Exception : {}", ex.getMessage());
	}

	
	@Around("execution(* com.flmfoods.service.impl.ItemServiceImpl.getItemById(..))")
	public ItemResponseDTO logAround(ProceedingJoinPoint pjp) throws Throwable{
		log.info("Entering into method: {}", pjp.getSignature().getName());
		
		Object result = pjp.proceed();
		
		log.info("Returning From method: {}", pjp.getSignature().getName());
		
		return (ItemResponseDTO)result;
	}
	
	
}

