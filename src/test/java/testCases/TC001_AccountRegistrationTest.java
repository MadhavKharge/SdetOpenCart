package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	
	public void verify_account_registration() {

		logger.info("**** Starting TC001_AccountRegistrationTest ****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("clicked on MyAccount link....");

			hp.clickRegister();
			logger.info("clicked on Register link....");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

			logger.info("Providing customer details.... ");
			regpage.setFirstname(randomString().toUpperCase());
			regpage.setLastname(randomString().toUpperCase());
			regpage.setEmail(randomString() + "@gmail.com");
			regpage.setTelephone(randomNumber());

			String password = randomAlphaNumeric();

			regpage.setPassword(password);
			regpage.setCofirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("Validating expected message....");
			String confmsg = regpage.getConfirmationMsg();
			
			if(confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else {
				logger.error("Test failed....");
				logger.debug("debug log....");
				Assert.assertTrue(false);
			}
			
			//Assert.assertEquals(confmsg, "Your Account Has Been Created!");

		} catch (Exception e) {
			
			Assert.fail();
		}

		logger.info("**** Finished TC001_AccountRegistrationTest ****");

	}

}
