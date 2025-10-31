package com.activity.pages;

import com.activity.base.BasePage;
import com.activity.helpers.UIHelper;
import org.openqa.selenium.By;
import com.activity.data.TestData;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddPlayersPage extends BasePage {
    private final By title = By.xpath("//h1[text()='Add Players']");
    private final By numOfPlayersInput = By.xpath("//input[@data-testid='num-of-players-input']");
    private final By numOfTeamsInput = By.xpath("//input[@data-testid='num-of-teams-input']");
    private final By playerInputs = By.xpath("//input[contains(@placeholder,'Player')]");
    private final By errorMessage = By.xpath("//p[@id='error-message']");
    private By prevButton = By.cssSelector("[aria-label='to-settings-sliders']");
    private By nextButton = By.cssSelector("[aria-label='to-words']");

    private UIHelper uiHelper;


    public AddPlayersPage(WebDriver driver) {
        super(driver);
        this.uiHelper = new UIHelper(driver);
    }

    public boolean isLoaded() {
        return find(title).isDisplayed();
    }

    public boolean isErrorMessageVisible() {
        return uiHelper.isReactElementVisible(errorMessage);
    }

    public void waitForErrorMessageToAppear() {
        uiHelper.waitForElementToAppear(errorMessage,10);
    }

    public void waitForErrorMessageToDisappear() {
        uiHelper.waitForElementToDisappear(errorMessage,10);
    }

    public void setNumOfPlayersInput(int num) {
        set(numOfPlayersInput, String.valueOf(num));
    }

    public void setNumOfTeamsInput(int num) {
        set(numOfTeamsInput, String.valueOf(num));
    }

    public void fillAllPlayerNames(List<String> names, int expectedCount) {
        List<WebElement> inputs = findAllPlayerInputs(expectedCount);

        for (int i = 0; i < names.size() && i < inputs.size(); i++) {
            inputs.get(i).clear();
            inputs.get(i).sendKeys(names.get(i));
        }
    }

    public List<WebElement> findAllPlayerInputs(int expectedCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d -> {
            List<WebElement> inputs = d.findElements(playerInputs);
            return inputs.size() == expectedCount ? inputs : null;
        });
    }

    public boolean isNextClickable(){
        return uiHelper.isReactButtonClickable(nextButton);
    }

    public void fillInPageDataCorrectly(int playerCount, int teamCount, List<String> names){
        setNumOfPlayersInput(playerCount);
        setNumOfTeamsInput(teamCount);
        fillAllPlayerNames(names, playerCount);
        waitForErrorMessageToDisappear();
    }
    public AddWordsPage gotoAddWords(int playerCount, int teamCount, List<String> names){
        fillInPageDataCorrectly(playerCount, teamCount, names);
        click(nextButton);
        return new AddWordsPage(driver);
    }
}
