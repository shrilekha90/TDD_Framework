package com.zoho.show.factory;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface LaunchBrowser {
	
	String browser() default "chrome";

}
