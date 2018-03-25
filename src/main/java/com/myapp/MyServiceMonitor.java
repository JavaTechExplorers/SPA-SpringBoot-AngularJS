package com.myapp;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.entity.SysTxn;
import com.myapp.repository.SessionTxnRepository;

/**
 * 
 * AOP implementation
 * 
 * Ref :
 * https://docs.spring.io/spring/docs/4.3.x/spring-framework-reference/html/aop.html
 * 
 * @author Vignesh
 */
@Aspect
@Component
public class MyServiceMonitor {

	@Autowired
	private SessionTxnRepository sessionTxnRepository;

	@Autowired
	private MySecurityUtil mySecurityUtil;

	@Around(value = "@annotation(myServiceCode)")
	public Object aroundMethodExecution(ProceedingJoinPoint joinPoint, MyServiceCode myServiceCode) throws Throwable {

		System.out.println("************ Start : Service code = " + myServiceCode.serviceCode() + "; Method Name = "
				+ joinPoint.getSignature().getName() + " : " + new Date());

		SysTxn txn = storeMethodStart(joinPoint, myServiceCode);
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {

			//sessionTxnRepository.updateEndTimeAndErrorDetails(new Date(), e.getMessage(), txn.getTxnId());
			throw e;
		}

		sessionTxnRepository.updateEndTimeAndErrorDetails(new Date(), "", txn.getTxnId());

		System.out.println("************ End : " + joinPoint.getSignature().getName() + ": " + new Date());
		return obj;
	}

	@Before(value = "@annotation(com.myapp.MyServiceCode)")
	public void beforeMethodExecution(JoinPoint joinPoint) {

		// Object[] arr = joinPoint.getArgs();
		// System.out.println(joinPoint.getArgs());
	}

	@After(value = "@annotation(com.myapp.MyServiceCode)")
	public void afterMethodExecution(JoinPoint joinPoint) {

	}

	@AfterReturning(value = "@annotation(com.myapp.MyServiceCode)")
	public void afterMethodReturn(JoinPoint joinPoint) {

	}

	@AfterThrowing(value = "@annotation(com.myapp.MyServiceCode)")
	public void afterMethodException(JoinPoint joinPoint) {

	}

	private SysTxn storeMethodStart(ProceedingJoinPoint joinPoint, MyServiceCode myServiceCode) {

		SysTxn sysTxn = new SysTxn();
		sysTxn.setUserName(mySecurityUtil.getLoggedinUserName());
		sysTxn.setServiceCode(myServiceCode.serviceCode());
		sysTxn.setServiceMethod(joinPoint.getSignature().getName());
		sysTxn.setStartTime(new Date());
		sysTxn.setCreatedBy(mySecurityUtil.getLoggedinUserName());
		sysTxn.setCreatedDate(new Date());

		return sessionTxnRepository.save(sysTxn);
	}
}