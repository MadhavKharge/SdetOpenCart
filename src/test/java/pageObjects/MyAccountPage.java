package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	
	public MyAccountPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath = "(//a[text()='Logout'])[2]") //added in steps 6
	WebElement lnklogout; 
	
	
	public boolean isMyAccountpageExists() {
		try {
		return (msgHeading.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}

	
	public void clicklogout() {
		lnklogout.click();
	}
}
