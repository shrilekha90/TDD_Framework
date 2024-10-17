package com.zoho.show.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zoho.show.base.GlobalLibrary;
import com.zoho.show.factory.DriverFactory;

public class LoginPage extends GlobalLibrary {

	public LoginPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//a[@id='cta_header_zs_home']")
	WebElement access_show;

	
	/**
	 * Method to navigate to Zoho Show Page
	 * 
	 **/
	public void navigateToZohoShowPage() {
		try {
		isElementPresent_WithMessage(access_show, "Access show button is not present");
		clickOnElement(access_show);
		navigateToWindow("Zoho Show Window");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}



}
