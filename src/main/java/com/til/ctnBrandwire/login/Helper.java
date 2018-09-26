package com.til.ctnBrandwire.login;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.til.ctnBrandwire.test.TestSuperAdmin;

import junit.framework.Assert;

public class Helper {

	public static WebDriverWait wait = new WebDriverWait(TestSuperAdmin.driver, 25);

	static String imagePath = System.getProperty("user.dir") + File.separator + "inputdata" + File.separator
			+ "alibaba.jpg";
	public static final Logger logger2 = Logger.getLogger(Helper.class);

	public static boolean asserTrueMethod(String locator) throws InterruptedException {
		//Helper.waiting(locator);
		Thread.sleep(2000);
		boolean val = TestSuperAdmin.driver.findElement(value(locator)).isDisplayed();
		Thread.sleep(1500);
		if (val) {
			return true;
		} else {
			return false;
		}

	}
	public static boolean asserTrueMethodForContent(String locator) throws InterruptedException {
		//Helper.waiting(locator);
		Thread.sleep(5000);
		boolean val = TestSuperAdmin.driver.findElement(value(locator)).isDisplayed();
		Thread.sleep(5000);
		if (val) {
			return true;
		} else {
			return false;
		}

	}

	public static void click(String locator) {
		TestSuperAdmin.driver.findElement(value(locator)).click();
	}

	public static void sendData(String sendDataElement, String sendDataElementValue) {
		TestSuperAdmin.driver.findElement(value(sendDataElement)).sendKeys(sendDataElementValue);
	}

	public static void waiting(String waitElement) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(value(waitElement)));
	}

	static void hidden(String waitElement) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(value(waitElement)));
	}

	static void elementClickable(String waitElement) {
		wait.until(ExpectedConditions.elementToBeClickable(value(waitElement)));
	}

	static void presenceLocated(String waitElement) {
		wait.until(ExpectedConditions.presenceOfElementLocated(value(waitElement)));
	}

	static void sendEnterKey(String sendDataElement, char enter) {
		TestSuperAdmin.driver.findElement(value(sendDataElement)).sendKeys(Keys.getKeyFromUnicode(enter));
	}
	static void sendEscapeKey(String sendDataElement,char escape){
		TestSuperAdmin.driver.findElement(value(sendDataElement)).sendKeys(Keys.getKeyFromUnicode(escape));
	}

	static String getText(String locator) {
		String s = TestSuperAdmin.driver.findElement(value(locator)).getText();
		return s;
	}

	static String getUrlOfPage() {
		String url = TestSuperAdmin.driver.getCurrentUrl();
		return url;
	}
	public static boolean isAlive(){
		try{
			TestSuperAdmin.driver.getCurrentUrl();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	/*static boolean verifyPageWithAccordion(String accordionElement, String element, String currentHeading,
			String expectedHeading) throws InterruptedException {
		try{
		Thread.sleep(800);
		Helper.click(accordionElement);
		Helper.waiting(element);
		Helper.click(element);
		// Helper.waiting(currentHeading);
		wait.until(ExpectedConditions.visibilityOfElementLocated(value(currentHeading)));
		Thread.sleep(1000);
		String currentText = TestSuperAdmin.driver.findElement(value(currentHeading)).getText();
		//System.out.println("Value of current heading is " + currentText);
		//System.out.println("Value of expected heading is " + expectedHeading);
		if (currentText.equals(expectedHeading)) {
			return true;
		} else {
			return false;
		}
		}
		catch(TimeoutException e){
			System.out.println("Webpage is taking more than 25 secs to load......Exiting the app");
			System.exit(1);
		}
		return null != null;
	}*/
	static boolean withAccordionHelperPage(String accordionElement, String element, String currentHeading,
			String expectedHeading) throws InterruptedException{
		try{
			Thread.sleep(1500);
			Helper.click(accordionElement);
			Helper.waiting(element);
			Helper.click(element);
			wait.until(ExpectedConditions.visibilityOfElementLocated(value(currentHeading)));
			Thread.sleep(3000);
			String currentText = TestSuperAdmin.driver.findElement(value(currentHeading)).getText();
			//System.out.println("Value of current heading is " + currentText);
			//System.out.println("Value of expected heading is " + expectedHeading);
			if (currentText.equals(expectedHeading)) {
				return true;
			} else {
				return false;
			}
		}
		catch(TimeoutException e){
			Thread.sleep(1000);
			TestSuperAdmin.driver.navigate().refresh();
			//Helper.sendEscapeKey(TestSuperAdmin.prop.getProperty("logOutButton"), Keys.ESCAPE.charAt(0));
			wait.until(ExpectedConditions.visibilityOfElementLocated(value(currentHeading)));
			Thread.sleep(3000);
			String currentText = TestSuperAdmin.driver.findElement(value(currentHeading)).getText();
			//System.out.println("Value of current heading is " + currentText);
			//System.out.println("Value of expected heading is " + expectedHeading);
			if (currentText.equals(expectedHeading)) {
				return true;
			} else {
				return false;
			}
		}
		
	}
	static boolean withAccordionFinanceHelperPage(String accordionElement, String element,String expectedUrl) throws InterruptedException{
		try{
			Thread.sleep(1000);
			Helper.click(accordionElement);
			Helper.waiting(element);
			Helper.click(element);
			Thread.sleep(5000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(value(currentHeading)));
			Thread.sleep(1000);
			String currentUrl = TestSuperAdmin.driver.getCurrentUrl();
			//System.out.println("Value of current heading is " + currentText);
			//System.out.println("Value of expected heading is " + expectedHeading);
			if (currentUrl.equals(expectedUrl)) {
				return true;
			} else {
				return false;
			}
		}
		catch(TimeoutException e){
			Thread.sleep(1000);
			//TestSuperAdmin.driver.navigate().refresh();
			//Helper.sendEscapeKey(TestSuperAdmin.prop.getProperty("logOutButton"), Keys.ESCAPE.charAt(0));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(value(currentHeading)));
			Thread.sleep(1000);
			System.out.println("some error occured");
			//String currentText = TestSuperAdmin.driver.findElement(value(currentHeading)).getText();
			//System.out.println("Value of current heading is " + currentText);
			//System.out.println("Value of expected heading is " + expectedHeading);
			/*if (currentText.equals(expectedUrl)) {
				return true;
			} else {
				return false;
			}*/
			return false;
		}
		
	}

	/*static void verifyPageWithoutAccordion(String element, String currentHeading, String expectedHeading)
			throws InterruptedException {
		// Helper.waiting(element);
		Thread.sleep(2000);
		Helper.click(element);
		wait.until(ExpectedConditions.presenceOfElementLocated(value(currentHeading)));
		Helper.waiting(currentHeading);
		String currentText = TestSuperAdmin.driver.findElement(value(currentHeading)).getText();
		//System.out.println("Value of current heading is " + currentText);
		//System.out.println("Value of expected heading is " + expectedHeading);
		Assert.assertEquals(expectedHeading, currentText);
	}*/
	static boolean withoutAccordionHelperPage(String element, String currentHeading, String expectedHeading) throws InterruptedException{
		try{
			Thread.sleep(2000);
			Helper.click(element);
			wait.until(ExpectedConditions.presenceOfElementLocated(value(currentHeading)));
			Helper.waiting(currentHeading);
			Thread.sleep(1000);
			String currentText = TestSuperAdmin.driver.findElement(value(currentHeading)).getText();
			//System.out.println("Value of current heading is " + currentText);
			//System.out.println("Value of expected heading is " + expectedHeading);
			Thread.sleep(500);
			Assert.assertEquals(expectedHeading, currentText);
			return true;
		}
		catch(TimeoutException e){
			Thread.sleep(1000);
			Helper.sendEscapeKey(TestSuperAdmin.prop.getProperty("logOutButton"), Keys.ESCAPE.charAt(0));
			Thread.sleep(1000);
			String currentText = TestSuperAdmin.driver.findElement(value(currentHeading)).getText();
			//System.out.println("Value of current heading is " + currentText);
			//System.out.println("Value of expected heading is " + expectedHeading);
			if (currentText.equals(expectedHeading)) {
				return true;
			} else {
				return false;
			}			
			
		}
	}

	public static By value(String locator) {
		// TODO Auto-generated method stub
		// System.out.println("++++++++++++++++++++++++++++++++++++++++ locator
		// " + locator);
		String breakLocatorType = locator.substring(0, 3);
		String breakLocatorValue = locator.substring(3, locator.length());
		if (breakLocatorType.equals("xp_")) { // Locator type = xpath
			return By.xpath(breakLocatorValue);
		} else if (breakLocatorType.equals("id_")) { // Locator type = id
			return By.id(breakLocatorValue);
		} else if (breakLocatorType.equals("cl_")) { // Locator type = class
			return By.className(breakLocatorValue);
		}
		return null;
	}

}
