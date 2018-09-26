package com.til.ctnBrandwire.login;

import org.apache.log4j.Logger;
import com.til.ctnBrandwire.test.TestSuperAdmin;

public class LoginPage extends Helper {
	public static final Logger logger1 = Logger.getLogger(LoginPage.class);

	public boolean verifyLoginMethod() {
		try {
			LoginPage.click(TestSuperAdmin.prop.getProperty("signInButton"));
			LoginPage.waiting(TestSuperAdmin.prop.getProperty("emailField"));
			LoginPage.sendData(TestSuperAdmin.prop.getProperty("emailField"), TestSuperAdmin.prop.getProperty("email"));
			LoginPage.waiting(TestSuperAdmin.prop.getProperty("logInButton"));
			LoginPage.click(TestSuperAdmin.prop.getProperty("logInButton"));
			LoginPage.sendData(TestSuperAdmin.prop.getProperty("passwordField"),
					TestSuperAdmin.prop.getProperty("password"));
			LoginPage.click(TestSuperAdmin.prop.getProperty("logInButton"));
			LoginPage.waiting(TestSuperAdmin.prop.getProperty("logOutButton"));
			return true;

		} catch (Exception e) {
			logger1.error("exception  " + e);
			return false;
		}
	}

	public boolean verifyAdminDashboardPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			boolean firstValue = Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("adminAccordion"),
					TestSuperAdmin.prop.getProperty("adminDashboard"),
					TestSuperAdmin.prop.getProperty("adminDashboardHeading"), expectedHeading);
			boolean secondValue = Helper.asserTrueMethod(TestSuperAdmin.prop.getProperty("adminDashboardBody"));
			Boolean isEqual = (firstValue == secondValue);
			if (isEqual) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return null != null;

	}

	public boolean verifyAdminCreateAccountPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			Helper.withoutAccordionHelperPage(TestSuperAdmin.prop.getProperty("adminCreateAccount"),
					TestSuperAdmin.prop.getProperty("adminCreateAccountHeading"), expectedHeading);

			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyAccountDashboardPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			boolean firstValue = Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("accountsAccordion"),
					TestSuperAdmin.prop.getProperty("accountDashboard"),
					TestSuperAdmin.prop.getProperty("accountDashboardHeading"), expectedHeading);
			boolean secondValue = Helper.asserTrueMethodForContent(TestSuperAdmin.prop.getProperty("DashboardBody"));
			Boolean isEqual = (firstValue == secondValue);
			if (isEqual) {
				return true;
			}

		} catch (Exception e) {
			return false;

		}
		return null != null;
	}

	public boolean verifyAccountsCreateAccountPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("accountsAccordion"),
					TestSuperAdmin.prop.getProperty("accountsCreateAccount"),
					TestSuperAdmin.prop.getProperty("accountsCreateAccountHeading"), expectedHeading);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	public boolean verifyAccountsAddBrandPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("accountsAccordion"),
					TestSuperAdmin.prop.getProperty("addBrand"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("addBrandHeading"), expectedHeading);

			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyAccountsManageBrandPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			Helper.withoutAccordionHelperPage(TestSuperAdmin.prop.getProperty("manageBrand"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("manageBrandHeading"), expectedHeading);

			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyAccountsAddManagerPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			Helper.withoutAccordionHelperPage(TestSuperAdmin.prop.getProperty("addManager"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("addManagerHeading"), expectedHeading);

			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyAccountsManageManagerPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("accountsAccordion"),
					TestSuperAdmin.prop.getProperty("manageManager"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("manageManagerHeading"), expectedHeading);

			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyAccountsManagePublicationPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("accountsAccordion"),
					TestSuperAdmin.prop.getProperty("managePublication"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("managePublicationHeading"), expectedHeading);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyFinanceDashboardPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			boolean firstValue = Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("financeAccordion"),
					TestSuperAdmin.prop.getProperty("financeDashboard"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("financeDashboardHeading"), expectedHeading);
			boolean secondValue = Helper.asserTrueMethodForContent(TestSuperAdmin.prop.getProperty("DashboardBody"));
			Boolean isEqual = (firstValue == secondValue);
			if (isEqual) {
				return true;
			}

		} catch (Exception e) {
			return false;

		}
		return null != null;
	}

	public boolean verifyFinanceCreateAccountPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("financeAccordion"),
					TestSuperAdmin.prop.getProperty("financeCreateAccount"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("financeCreateAccountHeading"), expectedHeading);
			// LoginPage.asserTrueMethod(TestSuperAdmin.prop.getProperty("accountDashboardHeading"));
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyFinanceRecordSalesPage(String expectedUrl) {
		try {
			Thread.sleep(2000);
			Helper.withAccordionFinanceHelperPage(TestSuperAdmin.prop.getProperty("financeAccordion"),
					TestSuperAdmin.prop.getProperty("financeRecordSales"),
					 expectedUrl);
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyFinanceManageOfferingsPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			// LoginPage.click(TestSuperAdmin.prop.getProperty("financeAccordion"));
			Helper.withoutAccordionHelperPage(TestSuperAdmin.prop.getProperty("financeManageOfferings"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("financeManageOfferingsHeading"), expectedHeading);

			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyFinanceInvoicesPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("financeAccordion"),
					TestSuperAdmin.prop.getProperty("financeInvoices"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("financeInvoicesHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyContentDashboardPage(String expectedHeading) {
		try {
			Thread.sleep(2000);
			boolean firstValue = Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("contentAccordion"),
					TestSuperAdmin.prop.getProperty("contentDashboard"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("contentDashboardHeading"), expectedHeading);
			boolean secondValue = Helper.asserTrueMethodForContent(TestSuperAdmin.prop.getProperty("contentDashboardBody"));
			Boolean isEqual = (firstValue == secondValue);
			if (isEqual) {
				return true;
			}

		} catch (Exception e) {
			return false;

		}
		return null != null;
	}

	public boolean verifyCreateStoryPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("contentAccordion"),
					TestSuperAdmin.prop.getProperty("createStory"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("createStoryHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}

	}

	public boolean verifyCreateResponsePage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("contentAccordion"),
					TestSuperAdmin.prop.getProperty("createResponse"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("createResponseHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}

	}

	public boolean verifyCreateUpdatePage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("contentAccordion"),
					TestSuperAdmin.prop.getProperty("createUpdate"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("createUpdateHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}

	}

	public boolean verifyCreateCommentPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("contentAccordion"),
					TestSuperAdmin.prop.getProperty("createComment"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("createCommentHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}

	}

	public boolean verifycontentExecDashboardPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("contentAccordion"),
					TestSuperAdmin.prop.getProperty("contentExecDashboard"),
					TestSuperAdmin.prop.getProperty("contentExecDashboardHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}

	}

	public boolean verifyPackageDashboardPage(String expectedUrl) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			boolean firstValue = Helper.withAccordionFinanceHelperPage(TestSuperAdmin.prop.getProperty("packageAccordion"),
					TestSuperAdmin.prop.getProperty("packageDashboard"),
					expectedUrl);
			boolean secondValue = Helper.asserTrueMethodForContent(TestSuperAdmin.prop.getProperty("DashboardBody"));
			Boolean isEqual = (firstValue == secondValue);
			if (isEqual) {
				return true;
			}

		} catch (Exception e) {
			return false;

		}
		return null != null;
	}

	public boolean verifyCreatePackagePage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("packageAccordion"),
					TestSuperAdmin.prop.getProperty("createPackage"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("createPackageHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyManagePricingPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("packageAccordion"),
					TestSuperAdmin.prop.getProperty("managePricing"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("managePricingHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyPackageExecDashboardPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			// LoginPage.click(TestSuperAdmin.prop.getProperty("financeAccordion"));
			Helper.withoutAccordionHelperPage(TestSuperAdmin.prop.getProperty("packageExecDashboard"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("packageExecDashboardHeading"), expectedHeading);

			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyLeadDashboardPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
			boolean firstValue = Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("salesAccordion"),
					TestSuperAdmin.prop.getProperty("leadDashboard"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("leadDashboardHeading"), expectedHeading);
			boolean secondValue = Helper.asserTrueMethod(TestSuperAdmin.prop.getProperty("DashboardBody"));
			Boolean isEqual = (firstValue == secondValue);
			if (isEqual) {
				return true;
			}
			// return true;

		} catch (Exception e) {
			return false;

		}
		return null != null;
	}

	public boolean verifyCreateLeadPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("salesAccordion"),
					TestSuperAdmin.prop.getProperty("createLead"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("createLeadHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyLeadCreateClientAccountPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
			Helper.withoutAccordionHelperPage(TestSuperAdmin.prop.getProperty("createClientAccount"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("createClientAccountHeading"), expectedHeading);

			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifySalesDashboardPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			boolean firstValue = Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("salesAccordion"),
					TestSuperAdmin.prop.getProperty("salesDashboard"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("salesDashboardHeading"), expectedHeading);
			boolean secondValue = Helper.asserTrueMethod(TestSuperAdmin.prop.getProperty("DashboardBody"));
			Boolean isEqual = (firstValue == secondValue);
			if (isEqual) {
				return true;
			}
			// return true;

		} catch (Exception e) {
			return false;

		}
		return null != null;
	}

	public boolean verifyLeadRecordSalesPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("salesAccordion"),
					TestSuperAdmin.prop.getProperty("salesRecordSales"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("salesRecordSalesHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyLeadManageOfferingsPage(String expectedHeading) {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(2000);
			Helper.withoutAccordionHelperPage(TestSuperAdmin.prop.getProperty("salesManageOfferings"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("salesManageOfferingsHeading"), expectedHeading);

			return true;

		} catch (Exception e) {
			return false;

		}
	}

	public boolean verifyLeadInvoicesPage(String expectedHeading) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			Helper.withAccordionHelperPage(TestSuperAdmin.prop.getProperty("salesAccordion"),
					TestSuperAdmin.prop.getProperty("salesInvoices"),
					// TestSuperAdmin.prop.getProperty("logOutButton"),
					TestSuperAdmin.prop.getProperty("salesInvoicesHeading"), expectedHeading);
			return true;

		} catch (Exception e) {
			return false;

		}
	}

}
