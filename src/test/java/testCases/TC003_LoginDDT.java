package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class,groups="DataDriven") // getting data provider from different
																				// class
	void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {

		logger.info("***** Starting TC003_LoginDDT *****");

		try {
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// LoginPAge
			LoginPage lp = new LoginPage(driver);
			lp.setLoginEmail(email);
			lp.setLoginPassword(pwd);
			lp.clickSignLogin();

			// MyAccountPage
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountpageExists();
			/*
			 * //Data is valid 1) login success - test pass - logout 2) login failed - test
			 * fail
			 * 
			 * 
			 * //Data is invalid 1) login success - test fail logout 2) login failed - test
			 * pass-
			 * 
			 */

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {

					macc.clicklogout();
					Assert.assertTrue(true);

				} else {

					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {

				if (targetPage == true) {

					macc.clicklogout();
					Assert.assertTrue(false);

				} else {

					Assert.assertTrue(true);

				}
			}
		} catch (Exception e) {

			Assert.fail();
		}
		Thread.sleep(5000);
		logger.info("***** finished TC003_LoginDDT *****");

	}

}
