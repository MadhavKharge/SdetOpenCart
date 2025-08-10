package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
       super(driver);
    
	}
	
	@FindBy(id = "input-email")
	WebElement txtEmailAddress;
	
	@FindBy( id = "input-password")
	WebElement txtLoginPassword;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement btnLogin;
	
	
	public void setLoginEmail(String email) {
		txtEmailAddress.sendKeys(email);
	}
	
	public void setLoginPassword(String pass) {
		txtLoginPassword.sendKeys(pass);
	}
	
	public void clickSignLogin() {
		btnLogin.click();
	}
	
	
	
	
	
}
