package com.zoho.show.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.zoho.show.base.GlobalLibrary;
import com.zoho.show.factory.DriverFactory;

public class CreateSlidePage extends GlobalLibrary {

	public CreateSlidePage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindBy(xpath = "//div[@data-action='open']")
	WebElement open_presentation;

	@FindBy(xpath = "//li[@id='rp_slideshow']")
	WebElement slideshow_option;

	@FindBy(xpath = "//h3[text()='Custom Slideshow']")
	WebElement custom_slideshow;

	@FindBy(xpath = "//button[@id='createCustomSlideshow']")
	WebElement create_custom_slideshow;

	@FindBy(xpath = "//h2[@id='customshowHeading']")
	WebElement custom_slideshow_heading;

	@FindBy(xpath = "//div[@id='customNewInfoCon']")
	WebElement new_custom_slideshow_info;

	@FindBy(xpath = "(//div[@class='selectioncontainer'])[1]//div[2]/div")
	WebElement custom_slide_1;

	@FindBy(xpath = "(//div[@class='selectioncontainer'])[1]//div[2]/div")
	WebElement custom_slide_2;

	@FindBy(xpath = "//h4[@id='selectedSlides']")
	WebElement picked_slide_heading;

	@FindBy(xpath = "//div[@id='pickedSlide0']")
	WebElement picked_slide_1;

	@FindBy(xpath = "//div[@id='pickedSlide1']")
	WebElement picked_slide_2;

	@FindBy(xpath = "//input[@id='customshowname']")
	WebElement customshowname;

	@FindBy(xpath = "(//button[@id='createNewCustomshow'])[1]")
	WebElement create_new_customshow;

	@FindBy(xpath = "//div[@data-title='Slide 1']")
	WebElement slide_1;

	@FindBy(xpath = "//span[text()='Hide/Show Slide']")
	WebElement hide_show_slide_option;

	@FindBy(xpath = "(//li[@id='addcomment'])[2]")
	WebElement add_comment_option;

	@FindBy(xpath = "//li[@id='rp_comments']")
	WebElement comments_tab;

	@FindBy(xpath = "//div[@id='rp_newcomment']")
	WebElement comments_box;

	@FindBy(xpath = "//div[@id='file']")
	WebElement file_menu;

	@FindBy(xpath = "//span[text()='Make a Copy']")
	WebElement make_a_copy_option;

	@FindBy(xpath = "//h3[text()='Make a Copy']")
	WebElement make_a_copy_dialog;

	@FindBy(xpath = "//input[@id='duplicateDocName']")
	WebElement duplicate_doc_name;

	@FindBy(xpath = "//input[@id='zd_saveaslocation']")
	WebElement save_as_location;

	@FindBy(xpath = "//div[@id='openSaveAs']")
	WebElement open_saveas;

	@FindBy(xpath = "//span[text()='Include Comments']")
	WebElement include_comments;

	@FindBy(xpath = "//button[@id='cancelBtn']")
	WebElement cancel_btn;

	@FindBy(xpath = "//button[@id='makeCopyBtn']")
	WebElement make_a_copy_btn;

	@FindBy(xpath = "//*[text()='Copied Successfully!']")
	WebElement copied_successfully_dialog;

	@FindBy(xpath = "//*[@id='openDocument']")
	WebElement open_document_btn;

	@FindBy(xpath = "(//button[@id='laterBtn'])[1]")
	WebElement later_btn;
	
	@FindBy(xpath = "//li[@id='openfile']")
	WebElement open_file_option;
	
	@FindBy(xpath = "//a[@title='Recent Files']")
	WebElement recent_files;
	
	@FindBy(xpath = "//a[@title='My Folders']")
	WebElement my_folders;
	
	@FindBy(xpath = "//span[text()='Open a file']")
	WebElement open_a_file_dialog;
	
	@FindBy(xpath = "//a[@title='Favorites']")
	WebElement favorites;
	
	@FindBy(xpath = "//a[@title='Shared with Me']")
	WebElement shared_with_me;
	
	@FindBy(xpath = "(//span[text()='Cancel'])[2]")
	WebElement cancel;
	
	@FindBy(xpath = "(//span[text()='Open'])[2]")
	WebElement open_btn;
	
	@FindBy(xpath = "//div[@class='zwd-item']")
	WebElement select_item;
	
	@FindBy(xpath = "//div[@id='postCommentList']")
	WebElement post_comment_btn;
	
	@FindBy(xpath = "//*[@doaction='resolveCommentList']")
	WebElement resolve_comment_btn;
	
	@FindBy(xpath = "//a[@id='clearannmsg']")
	WebElement clear_maintenance_msg;
	
	

	public void validate_custom_slideshow() {
		try {
			clickOnElement(open_presentation);
			navigateToWindow("Presentation Window");
			sleep(2000);
//			if(isElementPresent(clear_maintenance_msg)) {
//				jsClickOnElement(clear_maintenance_msg);
//			}
			jsClickOnElement(slideshow_option);
			sleep(2000);
			isElementPresent_WithMessage(custom_slideshow, "Custom Slideshow Text is not present");
			isElementPresent_WithMessage(create_custom_slideshow, "Create Custom Slideshow button is not present");
			jsClickOnElement(create_custom_slideshow);
			isElementPresent_WithMessage(custom_slideshow_heading, "Create Custom Slideshow Heading is not present");
			isElementPresent_WithMessage(new_custom_slideshow_info, "New Custom Slideshow Information is not present");
			moveToElement_and_click(custom_slide_1);
			moveToElement_and_click(custom_slide_2);
			isElementPresent_WithMessage(picked_slide_heading, "Picked Slide Heading is not present");
			isElementPresent_WithMessage(picked_slide_1, "Picked Slide 1 is not present");
			isElementPresent_WithMessage(picked_slide_2, "Picked Slide 2 is not present");
			clearText(customshowname);
			sendKeys(customshowname, "Custom Slideshow Test");
			clickOnElement(create_new_customshow);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	public void validate_hide_show_slide_in_right_click() {
		try {
			clickOnElement(open_presentation);
			navigateToWindow("Presentation Window");
			rightClickOnElement(slide_1);
			clickOnElement(hide_show_slide_option);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	public void validate_add_comments_in_right_click() {
		try {
			clickOnElement(open_presentation);
			navigateToWindow("Presentation Window");
			rightClickOnElement(slide_1);
			clickOnElement(add_comment_option);
			isElementPresent_WithMessage(comments_tab, "Comments tab is not present in the right panel");
			isElementPresent_WithMessage(comments_box, "Comments Box is not present in the right panel");
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	public void validate_make_a_copy_in_file() {
		try {
			clickOnElement(open_presentation);
			navigateToWindow("Presentation Window");
			sleep(2000);
//			if(isElementPresent(clear_maintenance_msg)) {
//				jsClickOnElement(clear_maintenance_msg);
//			}
			clickOnElement(file_menu);
			clickOnElement(make_a_copy_option);
			isElementPresent_WithMessage(make_a_copy_dialog, "Make a copy dialog box is not present");
			isElementPresent_WithMessage(duplicate_doc_name, "Duplicate doc name is not present");
			isElementPresent_WithMessage(save_as_location, "save as location is not present");
			isElementPresent_WithMessage(open_saveas, "Open Save as is not present");
			isElementPresent_WithMessage(include_comments, "Include comments checkbox is not present");
			isElementPresent_WithMessage(cancel_btn, "cancel button is not present");
			isElementPresent_WithMessage(make_a_copy_btn, "make a copy button is not present");
			clickOnElement(make_a_copy_btn);
			isElementPresent_WithMessage(copied_successfully_dialog, "Copied successfully dialog is not present");
			isElementPresent_WithMessage(open_document_btn, "open document button is not present");
			isElementPresent_WithMessage(later_btn, "later button is not present");
			} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}
	
	public void validate_open_file() {
		try {
			clickOnElement(open_presentation);
			navigateToWindow("Presentation Window");
			sleep(2000);
//			if(isElementPresent(clear_maintenance_msg)) {
//				jsClickOnElement(clear_maintenance_msg);
//			}
			clickOnElement(file_menu);
			clickOnElement(open_file_option);
			isElementPresent_WithMessage(open_a_file_dialog, "Open a file dialog box is not present");
			isElementPresent_WithMessage(recent_files, "Recent Files is not present");
			isElementPresent_WithMessage(my_folders, "My Folders is not present");
			isElementPresent_WithMessage(favorites, "Favorites is not present");
			isElementPresent_WithMessage(shared_with_me, "Shared with me is not present");
			isElementPresent_WithMessage(cancel, "cancel button is not present");
			isElementPresent_WithMessage(open_btn, "Open button is not present");
			clickOnElement(select_item);
			clickOnElement(open_btn);
			navigateToWindow("Presentation Window");			
			} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}
	
	public void validate_resolve_comments() {
		try {
			validate_add_comments_in_right_click();
			jsClickOnElement(comments_box);
			sendKeysThroughRobot();
			isElementPresent_WithMessage(post_comment_btn, "Post Comment button is not present");
			clickOnElement(post_comment_btn);
			moveToElement_and_click(resolve_comment_btn);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

}
