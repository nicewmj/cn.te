package cn.tedu.store.aspect;

import java.util.Date;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeEclapsedAspect {
	
	public TimeEclapsedAspect() {
		System.out.println("TimeEclapsedAspect()");
	}
	
	@Around("execution(* cn.tedu.store.service.*.*(..))")
	public void doAround(ProceedingJoinPoint pjp) throws Throwable {
		// Before
		long start = System.currentTimeMillis();
		
		pjp.proceed();
		
		// After
		long end = System.currentTimeMillis();
		System.out.println("eclapsed time = " + (end - start));
	}

	long start;
	
	@Before("execution(* cn.tedu.store.service.*.*(..))")
	public void doBefore(JoinPoint jp) {
		start = System.currentTimeMillis();
		System.out.println("doBefore:" + new Date());
	}
	
	@After("execution(* cn.tedu.store.service.*.*(..))")
	public void doAfter(JoinPoint jp) {
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println("doAfter:" + new Date());
	}
}



