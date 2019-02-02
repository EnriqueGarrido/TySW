package edu.uclm.esi.tysweb.selenium;

import java.util.regex.Pattern;
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

public class Chat {
  private WebDriver driver1;
  private WebDriver driver2;
  //private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

@Before
  public void setUp() throws Exception {
	//System.setProperty("webdriver.chrome.driver", "D:\\Github\\TySWeb_Practica\\TySW\\Navegador\\chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", "..\\TySWeb_Practica\\src\\edu\\uclm\\esi\\tysweb\\selenium\\chromedriver.exe");
	driver1 = new ChromeDriver();
	
	//System.setProperty("webdriver.gecko.driver", "D:\\Github\\TySWeb_Practica\\TySW\\Navegador\\geckodriver.exe");
	System.setProperty("webdriver.gecko.driver", "..\\TySWeb_Practica\\src\\edu\\uclm\\esi\\tysweb\\selenium\\geckodriver.exe");
	driver2 = new FirefoxDriver();
	//driver = new FirefoxDriver();
    //baseUrl = "https://www.katalon.com/";
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
    Thread.sleep(1000);
    driver1.findElement(By.xpath("//button[@onclick='LoadChat()']")).click();
    Thread.sleep(1000);
    driver2.findElement(By.xpath("//button[@onclick='LoadChat()']")).click();
    Thread.sleep(1000);
    driver1.findElement(By.id("usermsg")).sendKeys("Hola, ¿hay alguien?");
    driver1.findElement(By.xpath("//button[@onclick='Send()']")).click();
    Thread.sleep(1000);
    driver2.findElement(By.id("usermsg")).sendKeys("Si, soy Ana");
    driver2.findElement(By.xpath("//button[@onclick='Send()']")).click();
    Thread.sleep(1000);
    driver1.findElement(By.id("usermsg")).sendKeys("Encantado, ¿como estas?");
    driver1.findElement(By.xpath("//button[@onclick='Send()']")).click();
    Thread.sleep(1000);
    driver2.findElement(By.id("usermsg")).sendKeys("Bien, aquí haciendo una práctica");
    driver2.findElement(By.xpath("//button[@onclick='Send()']")).click();
    Thread.sleep(1000);
    driver1.findElement(By.id("usermsg")).sendKeys("Yo también");
    driver1.findElement(By.xpath("//button[@onclick='Send()']")).click();
    Thread.sleep(1000);
    driver2.findElement(By.id("usermsg")).sendKeys("Seguro que sacamos un 10");
    driver2.findElement(By.xpath("//button[@onclick='Send()']")).click();
    Thread.sleep(1000);
    driver1.findElement(By.id("usermsg")).sendKeys("Ójala");
    driver1.findElement(By.xpath("//button[@onclick='Send()']")).click();
    Thread.sleep(1000);
    Thread.sleep(1000);
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

