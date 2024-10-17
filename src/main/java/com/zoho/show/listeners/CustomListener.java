package com.zoho.show.listeners;

//import java.awt.Desktop;
//import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.zoho.show.base.GlobalLibrary;
import com.zoho.show.util.TestUtils;

public class CustomListener extends GlobalLibrary implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String reportName;
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+ " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String imgPath = takeScreenshot(result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(imgPath);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}

	@Override
	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report_" + timestamp + ".html";
		sparkReporter = new ExtentSparkReporter(TestUtils.getRelativePath()+"\\Reports\\"+reportName);
		
		sparkReporter.config().setDocumentTitle("Zoho Show Automation");
		sparkReporter.config().setReportName("Zoho Show Automation Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Zoho Show");
		
		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
//		String pathOfExtentReport = TestUtils.getRelativePath()+"\\Reports\\"+reportName;
//		File extentReport = new File(pathOfExtentReport);
//		
//		try {
//			Desktop.getDesktop().browse(extentReport.toURI());
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
	}

}
