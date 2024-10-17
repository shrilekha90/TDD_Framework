package com.zoho.show.base;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.zoho.show.factory.DriverFactory;
import com.zoho.show.util.ConfigReader;
import com.zoho.show.util.TestUtils;

public class GlobalLibrary {

	JavascriptExecutor js;
	private Robot robot;
	
	Properties prop;

	public void launchBrowser() {
		prop = ConfigReader.init_prop();
		String browserName = prop.getProperty("browser");
		DriverFactory.init_driver(browserName);
		waitForPageLoad();
		maximizeBrowser();
		DriverFactory.getDriver().get(ConfigReader.getValue("zoho_account_url"));
		sleep(100000);
	}

	/***
	 * Method to maximize browser window
	 * 
	 */
	public void maximizeBrowser() {
		try {
			DriverFactory.getDriver().manage().window().maximize();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to set cookies
	 * 
	 **/
	public void setCookies() {
		try {
			Cookie cookie1 = new Cookie.Builder("_iamadt",
					"c1b9495c6d90a035c55bfc2530ecf3953bb89c072144991a26f717d0c1577025fb6350a125547b7411f7ff51e0210c1baa8beb62276e8812db52d084b936ea99")
					.build();
			DriverFactory.getDriver().manage().addCookie(cookie1);

			Cookie cookie2 = new Cookie.Builder("_iambdt",
					"9934505b452bb323e7edab8cc8ca74258ac4f5dc9ab996eb079816d29a8a9caf218d5324f4b1b4e9d7de5b37cea6d0863347606a545534c3a1e7cfc6b8dcf193")
					.build();
			DriverFactory.getDriver().manage().addCookie(cookie2);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to launch Zoho Show URL in web browser
	 * 
	 **/
	public void launchZohoShow() {
		try {
			OpenNewTab();
			DriverFactory.getDriver().get(ConfigReader.getValue("zoho_show_url"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to wait for the page to load - within given seconds
	 * 
	 **/
	public void waitForPageLoad() {
		try {
			DriverFactory.getDriver().manage().timeouts()
					.pageLoadTimeout(Duration.ofSeconds(TestUtils.PAGE_LOAD_TIMEOUT));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to wait until element visible
	 * 
	 **/
	public void waitForElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(TestUtils.WAIT));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to wait until element is Clickable
	 * 
	 **/
	public void waitForElementClickable(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(TestUtils.WAIT));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to validate if the element is present with message
	 * 
	 **/
	public boolean isElementPresent_WithMessage(WebElement element, String message) {
		try {
			waitForElementVisible(element);
			Assert.assertTrue(element.isDisplayed(), message);
			return true;
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			return false;
		}

	}

	/**
	 * Method to validate if the element is present
	 * 
	 **/
	public boolean isElementPresent(WebElement element) {
		try {
			Assert.assertTrue(element.isDisplayed());
			return true;
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			return false;
		}

	}

	/**
	 * Method to click on the element
	 * 
	 **/
	public void clickOnElement(WebElement element) {
		try {
			waitForElementClickable(element);
			String elementText = getElementText(element);
			element.click();
			System.out.println(elementText + " - Element is clicked successfully");
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to get text
	 * 
	 **/
	public String getElementText(WebElement element) {
		try {
			waitForElementVisible(element);
			String elementText = element.getText();
			return elementText;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * Method to wait for windowHandling
	 * 
	 **/
	public void waitForCountOfWindows(int windowsCount) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), (Duration.ofSeconds(TestUtils.WAIT)));
			wait.until(ExpectedConditions.numberOfWindowsToBe(windowsCount));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to switch to child window
	 * 
	 **/
	public void navigateToWindow(String windowName) {
		try {
			String parentWindow = DriverFactory.getDriver().getWindowHandle();
			Set<String> windowHandles = DriverFactory.getDriver().getWindowHandles();
			int numberOfWindows = windowHandles.size();
			waitForCountOfWindows(numberOfWindows);
			Iterator<String> iterator = windowHandles.iterator();
			while (iterator.hasNext()) {
				String childWindow = iterator.next();
				if (!childWindow.equals(parentWindow)) {
					DriverFactory.getDriver().switchTo().window(childWindow);
					System.out.println("Successfully navigated to " + windowName);
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/***
	 * Method to get Title
	 * 
	 */
	public String getTitle() {
		try {
			String title = DriverFactory.getDriver().getTitle();
			return title;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/***
	 * Method to validate downloaded file
	 * 
	 */
	public void validate_downloaded_file(String filename) {
		try {
			File file = new File(TestUtils.getDownloadsPath(), filename);

			FluentWait<File> wait = new FluentWait<File>(file)
					.withTimeout(Duration.ofMinutes(TestUtils.FLUENT_WAIT_IN_MINUTES))
					.pollingEvery(Duration.ofMillis(TestUtils.WAIT)).ignoring(Exception.class)
					.withMessage("file is not downloaded");

			Boolean isDownloaded = wait.until(f -> f.exists() && f.canRead());
			Assert.assertTrue(isDownloaded, "File is not downloaded");
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to send the value
	 * 
	 **/
	public void sendKeys(WebElement element, String value) {
		try {
			waitForElementVisible(element);
			element.sendKeys(value);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to open new Tab
	 * 
	 **/
	public void OpenNewTab() {
		try {
			js = (JavascriptExecutor) DriverFactory.getDriver();
			js.executeScript("window.open()");
			for (String handle : DriverFactory.getDriver().getWindowHandles()) {
				DriverFactory.getDriver().switchTo().window(handle);
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to mouse over and click on the element
	 * 
	 **/
	public void moveToElement_and_click(WebElement element) {
		try {
			Actions action = new Actions(DriverFactory.getDriver());
			action.moveToElement(element).click().perform();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to clear the text present
	 * 
	 **/
	public void clearText(WebElement element) {
		try {
			if (isElementPresent(element)) {
				element.click();
				element.clear();
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to right click on the element
	 * 
	 **/
	public void rightClickOnElement(WebElement element) {
		try {
			waitForElementClickable(element);
			Actions action = new Actions(DriverFactory.getDriver());
			action.contextClick(element).build().perform();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Method to take screenshot
	 * 
	 **/
	public String takeScreenshot(String testMethodName) {
		try {

			String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

			File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);

			String targetFilePath = TestUtils.getRelativePath() + "\\Screenshots\\" + testMethodName + "_" + timestamp
					+ ".jpg";

			FileUtils.copyFile(srcFile, new File(targetFilePath));

			return targetFilePath;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Method to sleep
	 * 
	 **/
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	}
	
	/**
	 * Method to click using javascript
	 * 
	 **/
	public void jsClickOnElement(WebElement element) {
		try {
			js = (JavascriptExecutor) DriverFactory.getDriver();
			js.executeScript("arguments[0].click();", element);
			} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * Method to send text through js
	 * 
	 **/
	public void jsSendKeys(String text, WebElement element) {
		try {
			js = (JavascriptExecutor) DriverFactory.getDriver();
			js.executeScript(text);
			js.executeScript("arguments[0].setAttribute('value',text)",element);
			} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * Method to send text through robot
	 * 
	 **/
	public void sendKeysThroughRobot() {
		try {
			robot = new Robot();
			sleep(2000);
			robot.keyPress(KeyEvent.VK_O);
			robot.keyPress(KeyEvent.VK_K);
			robot.keyRelease(KeyEvent.VK_O);
			robot.keyRelease(KeyEvent.VK_K);
			} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	

}
