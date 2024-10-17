package com.zoho.show.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.zoho.show.base.GlobalLibrary;
import com.zoho.show.factory.DriverFactory;
import com.zoho.show.listeners.CustomListener;
import com.zoho.show.pages.CreateSlidePage;
import com.zoho.show.pages.ListPage;
import com.zoho.show.pages.LoginPage;
import com.zoho.show.util.ConfigReader;

@Listeners(CustomListener.class)
public class ZohoShowTestcases extends GlobalLibrary {
	LoginPage loginPage;
	ListPage listPage;
	CreateSlidePage createSlidePage;

	@BeforeClass
	public void launchZohoShowApplication() {
		launchBrowser();
		
		loginPage = new LoginPage();
        listPage = new ListPage();
        createSlidePage = new CreateSlidePage();
		
		setCookies();
		
	}

	@DataProvider(name = "fileformat")
	public Object[][] getFileName() {
		return new Object[][] { { ConfigReader.getValue("pptx_format") }, { ConfigReader.getValue("pdf_format") } };
	}

	@Test(dataProvider = "fileformat")
	public void validateDownloadDialog(String filename) {
		launchZohoShow();
		loginPage.navigateToZohoShowPage();
		listPage.navigate_To_My_Presentations_ListPage();
		listPage.validate_download_presentation(filename);
	}

	@Test
	public void validateCustomSlideShow() {
		launchZohoShow();
		loginPage.navigateToZohoShowPage();
		listPage.navigate_To_My_Presentations_ListPage();
		createSlidePage.validate_custom_slideshow();
	}

	@Test
	public void validateHideShowSlideRightClick() {
		launchZohoShow();
		loginPage.navigateToZohoShowPage();
		listPage.navigate_To_My_Presentations_ListPage();
		createSlidePage.validate_hide_show_slide_in_right_click();

	}

	@Test(groups = { "Comment Actions" })
	public void validateAddCommentOption() {
		launchZohoShow();
		loginPage.navigateToZohoShowPage();
		listPage.navigate_To_My_Presentations_ListPage();
		createSlidePage.validate_add_comments_in_right_click();
	}

	@Test
	public void validateMakeACopyInFile() {
		launchZohoShow();
		loginPage.navigateToZohoShowPage();
		listPage.navigate_To_My_Presentations_ListPage();
		createSlidePage.validate_make_a_copy_in_file();
	}

	@Test
	public void validateOpenFile() {
		launchZohoShow();
		loginPage.navigateToZohoShowPage();
		listPage.navigate_To_My_Presentations_ListPage();
		createSlidePage.validate_open_file();
	}

	@Test(groups = { "Comment Actions" })
	public void resolveComment() {
		launchZohoShow();
		loginPage.navigateToZohoShowPage();
		listPage.navigate_To_My_Presentations_ListPage();
		createSlidePage.validate_resolve_comments();
	}

	@AfterClass
	public void teardown() {
		DriverFactory.quitDriver();
	}

}
