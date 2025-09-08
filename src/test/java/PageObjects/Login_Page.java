package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page {
	
	private WebDriver driver;
	
	
	public Login_Page( WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	//locators
	@FindBy(xpath="//input[@id=\"txtUserID\"]") WebElement username;
	@FindBy(xpath="//input[@id=\"txtPassword\"]") WebElement password;
	@FindBy(xpath="//button[@id=\"sub\"]") WebElement login;
	
	//Element from the dev studio to validation
	
	@FindBy(xpath="//button[@class=\"icons avatar name-s \"]") public WebElement logo;
	@FindBy(xpath="//span[text()=\"Log off\"]") public WebElement logoff;
	@FindBy(xpath="//div[text()=\"The information you entered was not recognized. \"]") public WebElement error;
	
	//Action methods
	
	public void sendUsername(String name) {
		username.sendKeys(name);
	}
	
	public void sendPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickLogin() {
		login.click();
	}
	
	
	public boolean checkLogoPresence() {
		try {
			return logo.isDisplayed();
		}catch(Exception e) {
			System.out.println("logo not found");
			return false;
		}
		
	}
	
	public void clickLogo() {
		logo.click();
	}
	
	public void clickLogOff() {
		logoff.click();
	}
	
	public boolean checkError() {
		return error.getText().equals("The information you entered was not recognized.");
	}

}
