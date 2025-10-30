package com.activity.pages;

import com.activity.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingsPage extends BasePage {

    private By title = By.xpath("//h1[text()='Settings']");
    private By randomButton = By.cssSelector("[data-testid='teams-random-btn']");
    private By nonRandomButton = By.cssSelector("[data-testid='teams-nonrandom-btn']");
    private By singlePhoneButton = By.cssSelector("[data-testid='single-phone-btn']");
    private By multiplePhonesButton = By.cssSelector("[data-testid='multiple-phones-btn']");
    private By prevButton = By.cssSelector("[aria-label='to-home']");
    private By nextButton = By.cssSelector("[aria-label='to-settings-sliders']");
    private final By teamInfoLabel = By.xpath("//div[@id='teams-random-container']//p");
    private final By phoneInfoLabel = By.xpath("//div[@id='game-type-container']//p");

    private final String randomText = "Teams will be selected randomly.";
    private final String nonRandomText = "Teams will be selected by players.";
    private final String singlePhoneText = "Game will be played with a single phone";
    private final String multiplePhoneText = "This feature has not been implemented yet";
    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded(){
        return find(title).isDisplayed();
    }

    public void clickRandomButton(){
        click(randomButton);
    }

    public void clickNonRandomButton(){
        click(nonRandomButton);
    }

    public void clickSinglePhoneButton(){
        click(singlePhoneButton);
    }

    public void clickMultiplePhonesButton(){
        click(multiplePhonesButton);
    }

    public SettingsSlidersPage gotoSettingsSliders(){
        click(nextButton);
        return new SettingsSlidersPage(driver);
    }

    public HomePage gotoHomePage(){
        click(prevButton);
        return new HomePage(driver);
    }

    public String getTeamInfoText() {
        return find(teamInfoLabel).getText();
    }

    public String getPhoneInfoText() {
        return find(phoneInfoLabel).getText();
    }

    public boolean isRandomInfoDisplayed() {
        return getTeamInfoText().contains(randomText);
    }

    public boolean isNonRandomInfoDisplayed() {
        return getTeamInfoText().contains(nonRandomText);
    }

    public boolean isSinglePhoneInfoDisplayed() {
        return getPhoneInfoText().contains(singlePhoneText);
    }

    public boolean isMultiplePhoneInfoDisplayed() {
        return getPhoneInfoText().contains(multiplePhoneText);
    }
}
