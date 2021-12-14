package web.ui.automation.task1.testscripts;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import web.ui.automation.data.utilities.DataUtilities;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class WebUIAutomationTest {
	public static FirefoxDriver driver = null;
	
  @Test
  public void verifyUITAP() {
	  DataUtilities dUtil = new DataUtilities();
	  	  try {
		  
		  driver.findElement(By.xpath(dUtil.getPageObjectByPageField("BttnClick"))).click();
		  WebDriverWait waits = new WebDriverWait(driver, 20);
		  String getValue = waits.until(
	        		ExpectedConditions.presenceOfElementLocated(
	        				By.cssSelector(
	        						dUtil.getPageObjectByPageField("BttnClckSuccessMessage")
	        						)
	        				)
	        		).getText();
	      Assert.assertEquals(getValue, dUtil.getDataByPageField("BttnClckSuccessMessage"), "Verify the text==>"+ "Data calculated on the client side" +"==>on the textbox is Failed ");
	} catch (Exception e) {
		  e.printStackTrace(); 
		}
	 
	  
  }
  @BeforeClass
  public void beforeClass() {
	  
	  WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		driver.get("http://www.uitestingplayground.com/clientdelay");
		System.out.println("Application is Launched......");
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
	  System.out.println("Application is Closed.....");
	  
  }

}
