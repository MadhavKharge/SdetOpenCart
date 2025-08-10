package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {

		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath ="//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement checkPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	
	public void setFirstname(String fname) {
		txtFirstname.sendKeys(fname);
	}

	public void setLastname(String lname) {
		txtLastname.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void setCofirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}

	public void setPrivacyPolicy() {
		checkPolicy.click();
	}

	public void clickContinue() {
		btnContinue.click();

		// sol-2 if click() button is not working
		// btnContinue.submit();

		// sol-3
		// Actions action=new Actions(driver);
		// action.moveToElement(btnContinue).click().perform();

		// sol-4
		//JavascriptExecutor jse = (JavascriptExecutor) driver;
		//jse.executeScript("Argument[0].click();", btnContinue);
		
		//sol-5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//sol-6
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(4));
		//wait.until(ExpectedConditions.elementToBeClickable(btnContinue));
	
	}
	
	public String getConfirmationMsg() {
		try {
			
		return	msgConfirmation.getText();
		} 
		catch (Exception e) {
			
			return e.getMessage();
		}
		
	}

}
