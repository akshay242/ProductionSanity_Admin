package com.til.ctnBrandwire.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;
import com.til.ctnBrandwire.login.Helper;
import com.til.ctnBrandwire.login.LoginPage;

public class TestSuperAdmin {
	public static final Logger logger = Logger.getLogger(TestSuperAdmin.class);
	public static LoginPage loginPage;
	public static WebDriver driver;
	public static String folderPath = System.getProperty("user.dir");
	public static Properties prop = new Properties();
	static FileInputStream fis;
	public static ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	public static ExtentTest test;

	@BeforeClass
	public void driverInitialize() throws TimeoutException {
		try {

			htmlReporter = new ExtentHtmlReporter(folderPath + File.separator + "AutomationTestReport.html");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			logger.info("driverInitialize method is called");
			fis = new FileInputStream(folderPath + File.separator + "inputdata" + File.separator + "data.properties");
			prop.load(fis);
			PropertyConfigurator.configure(folderPath + File.separator + "log4j.properties");

			// System.setProperty("webdriver.gecko.driver",folderPath
			// +File.separator + "libs" + File.separator + "geckodriver.exe");
			// driver = new FirefoxDriver();

			System.setProperty("webdriver.chrome.driver",
					folderPath + File.separator + "libs" + File.separator + "chromedriver.exe");
			/*
			 * ChromeOptions chromeOptions = new ChromeOptions();
			 * chromeOptions.addArguments("--headless");
			 * chromeOptions.setHeadless(true);
			 * chromeOptions.addArguments("window-size=1366x768");
			 */
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("url"));
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			logger.info("driverInitialize method successfully runs");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("Something went wrong ", e);
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			logger.error("Something went wrong ", e1);
		} catch (IOException e2) {
			logger.error("Something went wrong ", e2);
		}

	}

	@Test(priority = 0)
	public void verifyLogin() throws IOException {
		logger.info("verifyLogin method is called");
		loginPage = new LoginPage();
		if (loginPage.verifyLoginMethod()) {
			logger.info("Login on Brandwire.in is working fine");
			test = extent.createTest("Login on Brandwire.in is working fine");
			test.info("Login Passed");
			test.pass("Test got passed");

		} else {
			logger.info("Sign In is not working on Brandwire.in");
			test = extent.createTest("Login on Brandwire.in failed");
			test.info("fail");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyLogin" })
	public void verifyAdminDashboard() throws InterruptedException {
		boolean connAlive = Helper.isAlive();
		if(connAlive){
		String expectedHeading = "Admin Dashboard";
		if (loginPage.verifyAdminDashboardPage(expectedHeading)) {
			logger.info("Admin Dashboard page is working fine");
			test = extent.createTest("Admin Dashboard page is loading fine");
			test.info("Admin dashboard Passed");
			test.pass("Test got passed");

		} else {
			logger.info("Admin Dashboard page is not working fine");
			test = extent.createTest("Admin Dashboard page failed");
			test.info("Admin dashboard failed");
			test.fail("Test got failed");

		}
		}else{
			logger.info("Browser not available.....ending the tests execution.");
			test = extent.createTest("There are some issues with your WebBrowser.");
			System.exit(1);
			
		}
	}

	@Test(dependsOnMethods = { "verifyAdminDashboard" })
	public void verifyAdminDashboardFilter() {
		boolean connAlive = Helper.isAlive();
		if(connAlive){
		Helper.click(TestSuperAdmin.prop.getProperty("adminRemoveDateFilter"));
		try {
			boolean val = Helper.asserTrueMethod(TestSuperAdmin.prop.getProperty("adminDashboardBody"));
			if (val) {
				logger.info("dashboard filters working fine");
				test.info("Admin Dashboard filters are working fine");
				test.pass("Admin dashboard filters passed");
			} else {
				logger.info("dashboard filters not working fine");
				test.fail("Admin dashboard filters failed");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
			logger.info("Browser not available.....ending the tests execution.");
			test = extent.createTest("There are some issues with your WebBrowser.");
			System.exit(1);
		}
	}

	@Test(dependsOnMethods = { "verifyAdminDashboardFilter" }, alwaysRun = true)
	public void verifyAdminCreateAccount() {
		String expectedHeading = "Create Account";
		if (loginPage.verifyAdminCreateAccountPage(expectedHeading)) {
			logger.info("Admin Create account page is working fine");
			test = extent.createTest("Admin Create account page is loading fine");
			test.info("Admin create account page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Admin Create account page is not working fine");
			test = extent.createTest("Admin Create account page failed");
			test.info("Admin create account page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyAdminCreateAccount" }, alwaysRun = true)
	public void verifyAccountDashboard() {
		String expectedHeading = "Account Dashboard";
		if (loginPage.verifyAccountDashboardPage(expectedHeading)) {
			logger.info("Account Dashboard page is working fine");
			test = extent.createTest("Account Dashboard page is loading fine");
			test.info("Account Dashboard page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Account Dashboard page is not working fine");
			test = extent.createTest("Account Dashboard page failed");
			test.info("Account Dashboard page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyAccountDashboard" })
	public void verifyAccountDashboardFilter() {
		Helper.click(TestSuperAdmin.prop.getProperty("adminRemoveDateFilter"));
		try {
			boolean val = Helper.asserTrueMethodForContent(TestSuperAdmin.prop.getProperty("DashboardBody"));
			if (val) {
				logger.info("dashboard filters working fine");
				test.info("Account Dashboard filters are working fine");
				test.pass("Account dashboard filters passed");
			} else {
				logger.info("dashboard filters not working fine");
				test.fail("Account dashboard filters failed");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(dependsOnMethods = { "verifyAccountDashboardFilter" }, alwaysRun = true)
	public void verifyAccountsCreateAccount() {
		String expectedHeading = "Create Account";
		if (loginPage.verifyAccountsCreateAccountPage(expectedHeading)) {
			logger.info("Accounts create account page is working fine");
			test = extent.createTest("Accounts create account page is loading fine");
			test.info("Accounts create account page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Accounts create account page is not working fine");
			test = extent.createTest("Accounts create account page failed");
			test.info("Accounts create account page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyAccountsCreateAccount" }, alwaysRun = true)
	public void verifyAccountsAddBrand() {
		String expectedHeading = "Add Brand";
		if (loginPage.verifyAccountsAddBrandPage(expectedHeading)) {
			logger.info("Add brand page is working fine");
			test = extent.createTest("Add brand page is loading fine");
			test.info("Add brand page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Add brand page is not working fine");
			test = extent.createTest("Add brand page failed");
			test.info("Add brand page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyAccountsAddBrand" }, alwaysRun = true)
	public void verifyAccountsManageBrand() {
		String expectedHeading = "Manage Brands";
		if (loginPage.verifyAccountsManageBrandPage(expectedHeading)) {
			logger.info("Manage brand page is working fine");
			test = extent.createTest("Manage brand page is loading fine");
			test.info("Manage brand page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Manage brand page is not working fine");
			test = extent.createTest("Manage brand page failed");
			test.info("Manage brand page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyAccountsManageBrand" }, alwaysRun = true)
	public void verifyAccountsAddManager() {
		String expectedHeading = "Add Manager";
		if (loginPage.verifyAccountsAddManagerPage(expectedHeading)) {
			logger.info("Add manager page is working fine");
			test = extent.createTest("Add manager page is loading fine");
			test.info("Add manager page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Add manager page is not working fine");
			test = extent.createTest("Add manager page failed");
			test.info("Add manager page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyAccountsAddManager" }, alwaysRun = true)
	public void verifyAccountsManageManager() {
		String expectedHeading = "Manage Manager";
		if (loginPage.verifyAccountsManageManagerPage(expectedHeading)) {
			logger.info("Manage manager page is working fine");
			test = extent.createTest("Manage manager page is loading fine");
			test.info("Manage manager page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Manage manager page is not working fine");
			test = extent.createTest("Manage manager page failed");
			test.info("Manage manager page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyAccountsManageManager" }, alwaysRun = true)
	public void verifyAccountsManagePublication() {
		String expectedHeading = "Manage Publication";
		if (loginPage.verifyAccountsManagePublicationPage(expectedHeading)) {
			logger.info("Manage publication page is working fine");
			test = extent.createTest("Manage publication page is loading fine");
			test.info("Manage publication page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Manage publication page is not working fine");
			test = extent.createTest("Manage publication page failed");
			test.info("Manage publication page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyAccountsManagePublication" }, alwaysRun = true)
	public void verifyFinanceDashboard() {
		String expectedHeading = "Finance Dashboard";
		if (loginPage.verifyFinanceDashboardPage(expectedHeading)) {
			logger.info("Finance dashboard page is working fine");
			test = extent.createTest("Finance dashboard page is loading fine");
			test.info("Finance dashboard page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Finance dashboard page is not working fine");
			test = extent.createTest("Finance dashboard page failed");
			test.info("Finance dashboard page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyFinanceDashboard" })
	public void verifyFinanceDashboardFilter() {
		Helper.click(TestSuperAdmin.prop.getProperty("adminRemoveDateFilter"));
		try {
			boolean val = Helper.asserTrueMethodForContent(TestSuperAdmin.prop.getProperty("DashboardBody"));
			if (val) {
				logger.info("dashboard filters working fine");
				test.info("Finance Dashboard filters are working fine");
				test.pass("Finance dashboard filters passed");
			} else {
				logger.info("Finance filters not working fine");
				test.fail("Finance dashboard filters failed");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(dependsOnMethods = { "verifyFinanceDashboardFilter" }, alwaysRun = true)
	public void verifyFinanceCreateAccount() {
		String expectedHeading = "Create Account";
		if (loginPage.verifyFinanceCreateAccountPage(expectedHeading)) {
			logger.info("Finance Create Account page is working fine");
			test = extent.createTest("Finance Create Account page is loading fine");
			test.info("Finance Create Account page Passed");
			test.pass("test got passed");
		} else {
			logger.info("Finance Create Account page is not working fine");
			test = extent.createTest("Finance Create Account page failed");
			test.info("Finance Create Account page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyFinanceCreateAccount" }, alwaysRun = true)
	public void verifyFinanceRecordSales() {
		String expectedUrl = "https://www.brandwire.in/admin/finance/make-payment";
		if (loginPage.verifyFinanceRecordSalesPage(expectedUrl)) {
			logger.info(" Finance Record Sales page is working fine");
			test = extent.createTest("verify Finance RecordSales page is loading fine");
			test.info("verify Finance Record Sales page Passed");
			test.pass("test got passed");
		} else {
			logger.info("verify Finance Record Sales page is not working fine");
			test = extent.createTest("verify FinanceRecord Sales page failed");
			test.info("verify FinanceRecord Sales page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyFinanceRecordSales" }, alwaysRun = true)
	public void verifyFinanceManageOfferings() {
		String expectedHeading = "Manage Offerings";
		if (loginPage.verifyFinanceManageOfferingsPage(expectedHeading)) {
			logger.info("verifyFinanceManageOfferingsPage is working fine");
			test = extent.createTest("verifyFinanceManageOfferingsPage is loading fine");
			test.info("verifyFinanceManageOfferingsPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyFinanceManageOfferingsPage is not working fine");
			test = extent.createTest("verifyFinanceManageOfferingsPage failed");
			test.info("verify Finance Manage Offerings Page Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyFinanceManageOfferings" }, alwaysRun = true)
	public void verifyFinanceInvoices() {
		String expectedHeading = "Invoices";
		if (loginPage.verifyFinanceInvoicesPage(expectedHeading)) {
			logger.info("verifyFinanceInvoicesPage is working fine");
			test = extent.createTest("verifyFinanceInvoicesPage is loading fine");
			test.info("verifyFinanceInvoicesPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyFinanceInvoicesPage is not working fine");
			test = extent.createTest("verifyFinanceInvoicesPage failed");
			test.info("verifyFinanceInvoicesPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyFinanceInvoices" }, alwaysRun = true)
	public void verifyContentDashboard() {
		String expectedHeading = "Content Dashboard";
		if (loginPage.verifyContentDashboardPage(expectedHeading)) {
			logger.info("verifyContentDashboardPage is working fine");
			test = extent.createTest("verifyContentDashboardPage is loading fine");
			test.info("verifyContentDashboardPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyContentDashboardPage is not working fine");
			test = extent.createTest("verifyContentDashboardPage failed");
			test.info("verifyContentDashboardPage Failed");
			test.fail("Test got failed");
		}

	}

	/*@Test(dependsOnMethods = { "verifyContentDashboard" })
	public void verifyContentDashboardFilter() {
		Helper.click(TestSuperAdmin.prop.getProperty("contentRemoveDateFilter"));
		try {
			boolean val = Helper.asserTrueMethodForContent(TestSuperAdmin.prop.getProperty("contentDashboardBody"));
			if (val) {
				logger.info("dashboard filters working fine");
				test.info("Content Dashboard filters are working fine");
				test.pass("Content dashboard filters passed");
			} else {
				logger.info("Content filters not working fine");
				test.fail("Content dashboard filters failed");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	@Test(dependsOnMethods = { "verifyContentDashboard" }, alwaysRun = true)
	public void verifyCreateStory() {
		String expectedHeading = "Create Brand Story";
		if (loginPage.verifyCreateStoryPage(expectedHeading)) {
			logger.info("verifyCreateStoryPage is working fine");
			test = extent.createTest("verifyCreateStoryPage is loading fine");
			test.info("verifyCreateStoryPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyCreateStoryPage is not working fine");
			test = extent.createTest("verifyCreateStoryPage failed");
			test.info("verifyCreateStoryPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyCreateStory" }, alwaysRun = true)
	public void verifyCreateResponse() {
		String expectedHeading = "Create Brand Response";
		if (loginPage.verifyCreateResponsePage(expectedHeading)) {
			logger.info("verifyCreateResponse is working fine");
			test = extent.createTest("verifyCreateResponse is loading fine");
			test.info("verifyCreateResponse Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyCreateResponse is not working fine");
			test = extent.createTest("verifyCreateResponse failed");
			test.info("verifyCreateResponse Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyCreateResponse" }, alwaysRun = true)
	public void verifyCreateUpdate() {
		String expectedHeading = "Create Brand Update";
		if (loginPage.verifyCreateUpdatePage(expectedHeading)) {
			logger.info("verifyCreateUpdate is working fine");
			test = extent.createTest("verifyCreateUpdate is loading fine");
			test.info("verifyCreateUpdate Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyCreateUpdatePage is not working fine");
			test = extent.createTest("verifyCreateUpdatePage failed");
			test.info("verifyCreateUpdatePage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyCreateUpdate" }, alwaysRun = true)
	public void verifyCreateComment() {
		String expectedHeading = "Create Brand Comment";
		if (loginPage.verifyCreateCommentPage(expectedHeading)) {
			logger.info("verifyCreateCommentPage is working fine");
			test = extent.createTest("verifyCreateCommentPage is loading fine");
			test.info("verifyCreateCommentPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyCreateCommentPage is not working fine");
			test = extent.createTest("verifyCreateCommentPage failed");
			test.info("verifyCreateCommentPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyCreateComment" }, alwaysRun = true)
	public void verifycontentExecDashboard() throws InterruptedException {
		String expectedHeading = "Executive Dashboard";
		if (loginPage.verifycontentExecDashboardPage(expectedHeading)) {
			logger.info("verifycontentExecDashboardPage is working fine");
			test = extent.createTest("verifycontentExecDashboardPage is loading fine");
			test.info("verifycontentExecDashboardPage Passed");
			test.pass("test got passed");
			Thread.sleep(2000);
		} else {
			logger.info("verifycontentExecDashboardPage is not working fine");
			test = extent.createTest("verifycontentExecDashboardPage failed");
			test.info("verifycontentExecDashboardPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifycontentExecDashboard" }, alwaysRun = true)
	public void verifyPackageDashboard() {
		String expectedUrl = "https://www.brandwire.in/admin/package/package-dashboard";
		if (loginPage.verifyPackageDashboardPage(expectedUrl)) {
			logger.info("verifyPackageDashboardPage is working fine");
			test = extent.createTest("verifyPackageDashboardPage is loading fine");
			test.info("verifyPackageDashboardPage Passed");
			test.pass("Test got passed");
		} else {
			logger.info("verifyPackageDashboardPage is not working fine");
			test = extent.createTest("verifyPackageDashboardPage failed");
			test.info("verifyPackageDashboardPage Failed");
			test.fail("Test got failed");

		}

	}

	@Test(dependsOnMethods = { "verifyPackageDashboard" }, alwaysRun = true)
	public void verifyCreatePackage() {
		String expectedHeading = "Create Package";
		if (loginPage.verifyCreatePackagePage(expectedHeading)) {
			logger.info("verifyCreatePackagePage is working fine");
			test = extent.createTest("verifyCreatePackagePage is loading fine");
			test.info("verifyCreatePackagePage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyCreatePackagePage is not working fine");
			test = extent.createTest("verifyCreatePackagePage failed");
			test.info("verifyCreatePackagePage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyCreatePackage" }, alwaysRun = true)
	public void verifyManagePricing() {
		String expectedHeading = "Manage Pricing";
		if (loginPage.verifyManagePricingPage(expectedHeading)) {
			logger.info("verifyManagePricingPage is working fine");
			test = extent.createTest("verifyManagePricingPage is loading fine");
			test.info("verifyManagePricingPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyManagePricingPage is not working fine");
			test = extent.createTest("verifyManagePricingPage failed");
			test.info("verifyManagePricingPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyManagePricing" }, alwaysRun = true)
	public void verifyPackageExecDashboard() {
		String expectedHeading = "Executive Dashboard";
		if (loginPage.verifyPackageExecDashboardPage(expectedHeading)) {
			logger.info("verifyPackageExecDashboardPage is working fine");
			test = extent.createTest("verifyPackageExecDashboardPage is loading fine");
			test.info("verifyPackageExecDashboardPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyPackageExecDashboardPage is not working fine");
			test = extent.createTest("verifyPackageExecDashboardPage failed");
			test.info("verifyPackageExecDashboardPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyPackageExecDashboard" }, alwaysRun = true)
	public void verifyLeadDashboard() {
		String expectedHeading = "Lead Dashboard";
		if (loginPage.verifyLeadDashboardPage(expectedHeading)) {
			logger.info("verifyLeadDashboardPage is working fine");
			test = extent.createTest("verifyLeadDashboardPage is loading fine");
			test.info("verifyLeadDashboardPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyLeadDashboardPage is not working fine");
			test = extent.createTest("verifyLeadDashboardPage failed");
			test.info("verifyLeadDashboardPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyLeadDashboard" })
	public void verifyLeadDashboardFilter() {
		Helper.waiting(TestSuperAdmin.prop.getProperty("contentRemoveDateFilter"));
		Helper.click(TestSuperAdmin.prop.getProperty("contentRemoveDateFilter"));
		try {
			boolean val = Helper.asserTrueMethod(TestSuperAdmin.prop.getProperty("DashboardBody"));
			if (val) {
				logger.info("dashboard filters working fine");
				test.info("Lead Dashboard filters are working fine");
				test.pass("Lead dashboard filters passed");
			} else {
				logger.info("Lead filters not working fine");
				test.fail("Lead dashboard filters failed");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(dependsOnMethods = { "verifyLeadDashboardFilter" }, alwaysRun = true)
	public void verifyCreateLead() {
		String expectedHeading = "Create Lead";
		if (loginPage.verifyCreateLeadPage(expectedHeading)) {
			logger.info("verifyCreateLeadPage is working fine");
			test = extent.createTest("verifyCreateLeadPage is loading fine");
			test.info("verifyCreateLeadPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyCreateLeadPage is not working fine");
			test = extent.createTest("verifyCreateLeadPage failed");
			test.info("verifyCreateLeadPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyCreateLead" }, alwaysRun = true)
	public void verifyLeadCreateClientAccount() {
		String expectedHeading = "Create Account";
		if (loginPage.verifyLeadCreateClientAccountPage(expectedHeading)) {
			logger.info("verifyCreateClientAccountPage is working fine");
			test = extent.createTest("verifyCreateClientAccountPage is loading fine");
			test.info("verifyCreateClientAccountPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyCreateClientAccountPage is not working fine");
			test = extent.createTest("verifyCreateClientAccountPage failed");
			test.info("verifyCreateClientAccountPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyLeadCreateClientAccount" }, alwaysRun = true)
	public void verifySalesDashboard() {
		String expectedHeading = "Sales Dashboard";
		if (loginPage.verifySalesDashboardPage(expectedHeading)) {
			logger.info("verifySalesDashboardPage is working fine");
			test = extent.createTest("verifySalesDashboardPage is loading fine");
			test.info("verifySalesDashboardPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifySalesDashboardPage is not working fine");
			test = extent.createTest("verifySalesDashboardPage failed");
			test.info("verifySalesDashboardPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifySalesDashboard" }, alwaysRun = true)
	public void verifyLeadRecordSales() {
		String expectedHeading = "Record Sales";
		if (loginPage.verifyLeadRecordSalesPage(expectedHeading)) {
			logger.info("verifyLeadRecordSalesPage is working fine");
			test = extent.createTest("verifyLeadRecordSalesPage is loading fine");
			test.info("verifyLeadRecordSalesPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyLeadRecordSalesPage is not working fine");
			test = extent.createTest("verifyLeadRecordSalesPage failed");
			test.info("verifyLeadRecordSalesPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyLeadRecordSales" }, alwaysRun = true)
	public void verifyLeadManageOfferings() {
		String expectedHeading = "Manage Offerings";
		if (loginPage.verifyLeadManageOfferingsPage(expectedHeading)) {
			logger.info("verifyLeadManageOfferingsPage is working fine");
			test = extent.createTest("verifyLeadManageOfferingsPage is loading fine");
			test.info("verifyLeadManageOfferingsPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyLeadManageOfferingsPage is not working fine");
			test = extent.createTest("verifyLeadManageOfferingsPage failed");
			test.info("verifyLeadManageOfferingsPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyLeadManageOfferings" }, alwaysRun = true)
	public void verifyLeadInvoices() {
		String expectedHeading = "Invoices";
		if (loginPage.verifyLeadInvoicesPage(expectedHeading)) {
			logger.info("verifyLeadInvoicesPage is working fine");
			test = extent.createTest("verifyLeadInvoicesPage is loading fine");
			test.info("verifyLeadInvoicesPage Passed");
			test.pass("test got passed");
		} else {
			logger.info("verifyLeadInvoicesPage is not working fine");
			test = extent.createTest("verifyLeadInvoicesPage failed");
			test.info("verifyLeadInvoicesPage Failed");
			test.fail("Test got failed");
		}

	}

	@Test(dependsOnMethods = { "verifyLeadInvoices" }, alwaysRun = true)
	public void logOutApp() {
		Helper.click(TestSuperAdmin.prop.getProperty("logOutButton"));
		Helper.click(TestSuperAdmin.prop.getProperty("clickOnLogout"));
		Helper.waiting(TestSuperAdmin.prop.getProperty("signInButton"));
		try {
			if (Helper.asserTrueMethod(TestSuperAdmin.prop.getProperty("signInButton"))) {
				test = extent.createTest("Application logged out");
				test.pass("logOutApp got passed");
			} else {
				test = extent.createTest("Application didnt log out");
				test.fail("logOutApp got failed");

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = { "logOutApp" }, alwaysRun = true)
	public void browserClose() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
		test = extent.createTest("Closing the browser......Exiting the tests");
		test.pass("Browser closed");
		extent.flush();
	}

}
