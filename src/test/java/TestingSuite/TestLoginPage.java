package TestingSuite;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageObjects.Login_Page;

public class TestLoginPage {
	
	WebDriver driver;
	
	@BeforeClass
	void SetUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://captv-stg1.pegacloud.io/prweb/app/PIC/UuLDf6PK9-l7FZm2O0pKstkg4aAVpnvm*/!STANDARD?pzPostData=1697395401");
	}
	
	@Test(priority = 3)
	void TestValidCredentials() throws InterruptedException {
		Login_Page tc = new Login_Page(driver);
		tc.sendUsername("samrat_qa");
		tc.sendPassword("rules");
		tc.clickLogin();
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(tc.checkLogoPresence());
		tc.clickLogo();
		Thread.sleep(Duration.ofSeconds(3));
		tc.clickLogOff();
		Thread.sleep(Duration.ofSeconds(2));
		driver.navigate().refresh();
		Thread.sleep(Duration.ofSeconds(2));
		
	}
	
	@Test(priority = 2)
	void TestINValidCredentials() throws InterruptedException {
		Login_Page tc1 = new Login_Page(driver);
		tc1.sendUsername("samrat");
		tc1.sendPassword("rule");
		tc1.clickLogin();
		Thread.sleep(Duration.ofSeconds(2));
		
		Assert.assertTrue(tc1.checkError());
		
	}
	
	@Test(priority = 1)
	void TestInvalidUsername() throws InterruptedException {
		Login_Page tc1 = new Login_Page(driver);
		tc1.sendUsername("samrat");
		tc1.sendPassword("rules");
		tc1.clickLogin();
		Assert.assertTrue(tc1.checkError());
		Thread.sleep(Duration.ofSeconds(2));
	}
	
	@Test(priority = 4)
	void TestInvalidPassword() throws InterruptedException{
		try {
			if(driver.findElement(By.xpath("//p[text()=\"Authentication Failed.\"]")).isDisplayed()) {
				driver.navigate().refresh();
			}
		}catch(Exception e) {
			
		}finally{
			Login_Page tc1 = new Login_Page(driver);
			tc1.sendUsername("samrat_qa");
			tc1.sendPassword("roles");
			tc1.clickLogin();
			Thread.sleep(Duration.ofSeconds(3));
			Assert.assertTrue(tc1.checkError());
			Thread.sleep(Duration.ofSeconds(2));
		}
		
		
		
	}
	@Test(priority = 5)
	void emptyLogin() {
		try {
			if(driver.findElement(By.xpath("//p[text()=\"Authentication Failed.\"]")).isDisplayed()) {
				driver.navigate().refresh();
			}
		}catch(Exception e) {
			
		}finally {
			Login_Page tc1 = new Login_Page(driver);
			tc1.clickLogin();
			Assert.assertTrue(tc1.checkError());
		}
		
		
	}

}
