package com.activity.base;

import com.activity.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.activity.base.BasePage.delay;

public class BaseTest {
    protected WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;

    private String REDNECKACTIVITY_URL = "http://localhost:3000/";

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void loadApplication(){
        driver.get(REDNECKACTIVITY_URL);
        basePage = new BasePage(driver);
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown(){
        delay(3000);
        driver.quit();
    }
}
