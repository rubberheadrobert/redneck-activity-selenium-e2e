package com.activity.pages;

import com.activity.base.BasePage;
import com.activity.data.TestData;
import com.activity.helpers.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GamePage extends BasePage {
    private final By nextRoundModalHeader = By.xpath("//div[@class='modal']/h2");
    private final By nextRoundPlayer = By.xpath("//div[@class='modal']/table/tr[1]/td[3]");
    private final By nextRoundTeam = By.xpath("//div[@class='modal']/table/tr[2]/td[3]");
    private final By closeNextRoundButton = By.xpath("//div[@class='modal']/span[@class='close']");
    private final By roundWordsModalContainer = By.xpath("//div[@class='modal']");
    private final By roundType = By.xpath("//div[@data-testid='roundtype']/div");
    private final By currentWord = By.xpath("//span[@id='current-word']");
    private final By startButton = By.xpath("//button[text()='Start']");
    private final By skipButton = By.xpath("//button[text()='Skip']");
    private final By guessedButton = By.xpath("//button[text()='Guessed']");

    // --- Game Finished Modal ---
    private final By gameFinishedModal = By.xpath("//div[@data-testid='game-finished-modal']");
    private final By gameFinishedModalTitle = By.xpath("//div[@data-testid='game-finished-modal']/h1");
    private final By gameFinishedModalClose = By.xpath("//div[@data-testid='game-finished-modal']/span");

    private UIHelper uiHelper;


    public GamePage(WebDriver driver) {
        super(driver);
        this.uiHelper = new UIHelper(driver);
    }

    public boolean isLoaded(){
        return find(nextRoundModalHeader).isDisplayed();
    }

    public String getNextPlayerName(){
        return find(nextRoundPlayer).getText();
    }

    public String getNextTeamName(){
        return find(nextRoundTeam).getText();
    }

    public void closeNextRoundModal(){
        click(closeNextRoundButton);
    }

    public String getRoundType(){
        return find(roundType).getText();
    }

    public String getCurrentWord(){
        return find(currentWord).getText();
    }

    public void goThroughRound(){
        int totalAmountOfWords = TestData.WORDS_AMOUNT_DEFAULT * TestData.DEFAULT_PLAYER_COUNT;
        click(startButton);
        String roundType = getRoundType().trim();
        for (int i = 0; i < totalAmountOfWords; i++){
            System.out.println((i + 1) + " word: " + getCurrentWord());
            click(guessedButton);
        }
        uiHelper.waitForElementToAppear(roundWordsModalContainer, 10);
        click(closeNextRoundButton);
        System.out.println("Round Type: " + roundType);
        System.out.println("Data Round Type: " + TestData.ROUND_TYPES.get(2));
        if(roundType.equals(TestData.ROUND_TYPES.get(2))){
            uiHelper.waitForElementToAppear(gameFinishedModal, 10);
            System.out.println("In Game Finished modal");
        } else {
            click(closeNextRoundButton);
            uiHelper.waitForElementToDisappear(roundWordsModalContainer, 10);
        }

    }


}