package com.activity.pages;

import com.activity.base.BasePage;
import com.activity.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private By title = By.xpath("//div[text()='Redneck Activity']");
    private By findGameButton = By.cssSelector("[data-testid='find-game-btn']");
    private By createGameButton = By.cssSelector("[data-testid='create-game-btn']");
    private By optionsButton = By.cssSelector("[data-testid='options-btn']");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        try {
            return find(title).isDisplayed()
                    && find(findGameButton).isDisplayed()
                    && find(createGameButton).isDisplayed()
                    && find(optionsButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public OptionsPage gotoOptions(){
        click(optionsButton);
        return new OptionsPage(driver);
    }

    public SettingsPage gotoSettings(){
        click(createGameButton);
        return new SettingsPage(driver);
    }

    public FindGamePage gotoFindGame(){
        click(findGameButton);
        return new FindGamePage(driver);
    }

    public AddWordsPage goToAddWordsWithDefaultData() {
        return this.gotoSettings()
                .gotoSettingsSliders()
                .gotoAddPlayers()
                .gotoAddWords(
                        TestData.DEFAULT_PLAYER_COUNT,
                        TestData.DEFAULT_TEAM_COUNT,
                        TestData.PLAYER_NAMES
                );
    }

    public AddTeamsPage goToAddTeamsWithDefaultData() {
        return this.gotoSettings()
                .gotoSettingsSliders()
                .gotoAddPlayers()
                .gotoAddWords(
                        TestData.DEFAULT_PLAYER_COUNT,
                        TestData.DEFAULT_TEAM_COUNT,
                        TestData.PLAYER_NAMES
                ).gotoAddTeams();
    }

    public GamePage goToGameWithDefaultData() {
        return this.gotoSettings()
                .gotoSettingsSliders()
                .gotoAddPlayers()
                .gotoAddWords(
                        TestData.DEFAULT_PLAYER_COUNT,
                        TestData.DEFAULT_TEAM_COUNT,
                        TestData.PLAYER_NAMES
                ).gotoAddTeams()
                .gotoGame();
    }
}
