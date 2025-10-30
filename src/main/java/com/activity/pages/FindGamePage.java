package com.activity.pages;

import com.activity.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FindGamePage extends BasePage {

    private By title = By.xpath("//h1[text()='Find Game']");
    public FindGamePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded(){
        return find(title).isDisplayed();
    }
}
