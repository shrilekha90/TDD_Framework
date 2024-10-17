package com.zoho.show.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.zoho.show.base.GlobalLibrary;
import com.zoho.show.util.ConfigReader;
import com.zoho.show.util.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	static String relativePath = TestUtils.getRelativePath();
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
        /**
         * This method is used to initialize the ThreadLocal driver based on the given browser
         */
        public static WebDriver init_driver(String browser) {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", relativePath + "\\BrowserSpecificDrivers\\chromedriver.exe");
                tlDriver.set(new ChromeDriver());
            } else {
                System.out.println("Please pass the correct browser value: " + browser);
            }
            return getDriver();
        }

        /**
         * This method is used to get the driver with ThreadLocal
         * @return it returns WebDriver
         */
        public static synchronized WebDriver getDriver() {
            return tlDriver.get();
        }

        /**
         * Method to quit the WebDriver for the current thread
         */
        public static void quitDriver() {
            WebDriver driver = getDriver();
            if (driver != null) {
                driver.quit();
                tlDriver.remove();
            } else {
                System.out.println("Driver is null, cannot quit.");
            }
        }
    


}
