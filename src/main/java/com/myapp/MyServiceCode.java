package com.myapp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Service Code annotation to track and log the Service invocation details
 * 
 * @author Vignesh
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyServiceCode {

	int serviceCode() default 0;
}