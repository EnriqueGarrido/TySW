package edu.uclm.esi.tysweb.selenium;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class FPLGame {
  private WebDriver driver1;
  private WebDriver driver2;
  private WebDriver driverchosen;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private ArrayList<Integer> positions= new ArrayList<Integer>();

@Before
  public void setUp() throws Exception {
	//System.setProperty("webdriver.chrome.driver", "D:\\Github\\TySWeb_Practica\\TySW\\Navegador\\chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", "..\\TySWeb_Practica\\src\\edu\\uclm\\esi\\tysweb\\selenium\\chromedriver.exe");
	driver1 = new ChromeDriver();
	
	System.setProperty("webdriver.gecko.driver", "..\\TySWeb_Practica\\src\\edu\\uclm\\esi\\tysweb\\selenium\\geckodriver.exe");
	driver2 = new FirefoxDriver();
    driver1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver2.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
  }

  @Test
  public void testRPSGame() throws Exception {
    driver1.get("http://localhost:8080/TySWeb_Practica/index.html");
    driver2.get("http://localhost:8080/TySWeb_Practica/index.html");
    driver1.findElement(By.id("email")).click();
    driver1.findElement(By.id("email")).clear();
    driver1.findElement(By.id("email")).sendKeys("henry@cedor.com");
    driver1.findElement(By.id("pass")).click();
    driver1.findElement(By.id("pass")).clear();
    driver1.findElement(By.id("pass")).sendKeys("hola");
    driver1.findElement(By.xpath("//button[@onclick='login()']")).click();
    Thread.sleep(1000);
    driver2.findElement(By.id("email")).click();
    driver2.findElement(By.id("email")).clear();
    driver2.findElement(By.id("email")).sendKeys("ana@nita.com");
    driver2.findElement(By.id("pass")).click();
    driver2.findElement(By.id("pass")).clear();
    driver2.findElement(By.id("pass")).sendKeys("ana123");
    driver2.findElement(By.xpath("//button[@onclick='login()']")).click();
    driver1.findElement(By.xpath("//button[@onclick='FindLetters()']")).click();
    Thread.sleep(1000);
    driver2.findElement(By.xpath("//button[@onclick='FindLetters()']")).click();
    Thread.sleep(3000);
    driverchosen=driver1;
    //driverchosen.findElement(By.id("1")).click();
    for(int i=0; i<99; i++) {
    	if (!positions.contains(i)) {
    		for (int j=i+1; j<100; j++) {
    			if(!positions.contains(j)) {
    				if((driverchosen.findElement(By.id("option_letter"+j)).getAttribute("innerHTML")).equals(driverchosen.findElement(By.id("option_letter"+i)).getAttribute("innerHTML")))
                	{
            			driverchosen.findElement(By.id(""+i)).click();
            			driverchosen.findElement(By.id(""+j)).click();
            			positions.add(i);
            			positions.add(j);
            			if (driverchosen==driver1) {
            				driverchosen=driver2;
            			}else {
            				driverchosen=driver1;
            			}
            			break;
                	}
    			}
        	}
    	}
    }
    Thread.sleep(5000);
  }

  @After
  public void tearDown() throws Exception {
    driver1.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver1.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver1.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver1.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

