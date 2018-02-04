package com.restrain.annotation;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RelevanceClass {

	String value() default "";

}
