package com.zoho.show.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.zoho.show.base.GlobalLibrary;
import com.zoho.show.factory.DriverFactory;

public class ListPage extends GlobalLibrary {

	public ListPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//p[text()='My Presentations']")
	WebElement my_presentations;

	@FindBy(xpath = "//h2[text()='My Presentations']")
	WebElement my_presentations_title;

	@FindBy(xpath = "(//ul[@id='list' and @class='zs-list-panel ui-overflow'])[1]")
	WebElement list_view;

	@FindBy(xpath = "(//div[@id='document-details'])[1]")
	WebElement presentation_name;

	@FindBy(xpath = "(//button[@id='zs_download'])[1]")
	WebElement download_button;

	@FindBy(xpath = "(//span[text()='MS PowerPoint Presentation'])[2]")
	WebElement ms_powerpoint_presentation;

	@FindBy(xpath = "//h5[text()='Exporting']")
	WebElement exporting;

	@FindBy(xpath = "(//span[text()='PDF'])[2]")
	WebElement pdf;

	@FindBy(xpath = "//div[@class='ui-show-dialog ui-import-dialog ui-processing-dialog']")
	WebElement download_dialog;

	@FindBy(xpath = "//h3[text()='Download']")
	WebElement download_title;

	@FindBy(xpath = "(//div[@id='excludeSlideType'])[2]")
	WebElement pdf_excludeSlideType;

	@FindBy(xpath = "(//label[@id='excludeHiddenSlidesLabel'])[2]")
	WebElement excludeHiddenSlide_checkbox;

	@FindBy(xpath = "//button[@id='hiddenSlideExportCancel']")
	WebElement pdf_cancel_button;

	@FindBy(xpath = "//button[@id='downloadHiddenSlideDocument']")
	WebElement pdf_download_button;

	@FindBy(xpath = "//div[@id='ExportPdfInfo']")
	WebElement zoho_show_chrome_extension;

	@FindBy(xpath = "//button[@id='ExportPdfContinue']")
	WebElement continue_without_installing_button;

	@FindBy(xpath = "//div[@id='ExportingPdfLoading']")
	WebElement pdf_download_dialog;

	public void navigate_To_My_Presentations_ListPage() {
		try {
			isElementPresent_WithMessage(my_presentations, "My Presentations option is not present");
			clickOnElement(my_presentations);
			isElementPresent_WithMessage(my_presentations_title, "My Presentations title is not present");
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	public void validate_download_presentation(String filename) {
		try {
//		isElementPresent_WithMessage(download_button, "Download button is not present");
			sleep(2000);
			moveToElement_and_click(presentation_name);
			sleep(2000);
			moveToElement_and_click(download_button);
			if (filename.contains(".pptx")) {
				clickOnElement(ms_powerpoint_presentation);
				isElementPresent_WithMessage(exporting, "Exporting dialog is not displayed");
				validate_downloaded_file(filename);
			} else if (filename.contains(".pdf")) {
				clickOnElement(pdf);
				isElementPresent_WithMessage(download_dialog, "Download dialog is not displayed");
				isElementPresent_WithMessage(download_title, "Download title is not displayed");
				isElementPresent_WithMessage(pdf_excludeSlideType, "PDF is not displayed");
				isElementPresent_WithMessage(excludeHiddenSlide_checkbox,
						"Exclude Hidden Slide Checkbox is not displayed");
				isElementPresent_WithMessage(pdf_cancel_button, "Cancel button is not displayed");
				isElementPresent_WithMessage(pdf_download_button, "Download button is not displayed");
				clickOnElement(pdf_download_button);
				navigateToWindow("Presentation Window");
//				if (isElementPresent(zoho_show_chrome_extension)) {
//					clickOnElement(continue_without_installing_button);
//				}
				isElementPresent_WithMessage(pdf_download_dialog, "Download dialog is not displayed");
				validate_downloaded_file(filename);
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
