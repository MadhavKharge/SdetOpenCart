package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	//constructor 
	public HomePage(WebDriver driver) {

		super(driver);
	}

	//locators
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement linkmyAccount;

	@FindBy(xpath = "//a[text()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//following-sibling::li[2]//a")
	WebElement linkLogin; // login link in step 5
	
	//Action method
	
	public void clickMyAccount() {
		linkmyAccount.click();
	}
	
	public void clickRegister() {
		linkRegister.click();
	}
	
	public void clickLogin() {
		linkLogin.click();
	}
}
