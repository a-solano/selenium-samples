package com.andres;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoBlazeAuthTest {

    private WebDriver driver;

	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
        

    }

    @After
    public void tearDown() {
        //driver.quit();
    }
    
    @Test
    public void signUpLoginTest() throws InterruptedException {
        Thread.sleep(2000);

        String userName;
        String password;
        userName = java.util.UUID.randomUUID().toString();
        password = java.util.UUID.randomUUID().toString();
        WebElement singUpNavButton = driver.findElement(By.id("signin2"));
        singUpNavButton.click();

        Thread.sleep(2000);

        WebElement signInModal = driver.findElement(By.id("signInModal"));
        assertTrue(signInModal.isDisplayed());

        WebElement signUserName = driver.findElement(By.id("sign-username"));
        WebElement signPassword = driver.findElement(By.id("sign-password"));
        WebElement signButton = driver.findElement(By.xpath("//*[@id='signInModal']/div/div/div[3]/button[2]"));

        signUserName.sendKeys(userName);
        signPassword.sendKeys(password);
        signButton.click();

        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        assertEquals("Sign up successful.", alert.getText());
        alert.accept();

        Thread.sleep(2000);

        WebElement loginNavButton = driver.findElement(By.id("login2"));
        loginNavButton.click();
        
        Thread.sleep(2000);

        WebElement loginModal = driver.findElement(By.id("logInModal"));
        assertTrue(loginModal.isDisplayed());

        WebElement loginUserName = driver.findElement(By.id("loginusername"));
        WebElement loginPassword = driver.findElement(By.id("loginpassword"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]"));

        loginUserName.sendKeys(userName);
        loginPassword.sendKeys(password);

        loginButton.click();

        Thread.sleep(2000);

        WebElement welcomeMessage = driver.findElement(By.id("nameofuser"));
        assertEquals("Welcome " + userName, welcomeMessage.getText());

    }
    
}