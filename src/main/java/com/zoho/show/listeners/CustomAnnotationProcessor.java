package com.zoho.show.listeners;

import java.lang.reflect.Method;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.zoho.show.factory.LaunchBrowser;

public class CustomAnnotationProcessor implements IInvokedMethodListener {

	

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		Method testMethod = method.getTestMethod().getConstructorOrMethod().getMethod();
		if (testMethod.isAnnotationPresent(LaunchBrowser.class)) {
			LaunchBrowser launchBrowser = testMethod.getAnnotation(LaunchBrowser.class);
			String browserName = launchBrowser.browser();

				
		}
	}

}
