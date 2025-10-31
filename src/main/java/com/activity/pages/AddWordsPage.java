package com.activity.pages;

import com.activity.base.BasePage;
import com.activity.data.TestData;
import com.activity.helpers.UIHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AddWordsPage extends BasePage {
    private final By title = By.xpath("//h1[text()='Add Words']");
    private final By wordInput = By.xpath("//div[@id='word-input-container']/input");
    private final By addWordButton = By.xpath("//button[text()='Add Word']");
    private final By randomWordButton = By.xpath("//button[text()='Random Word']");
    private final By nextPlayerButton = By.xpath("//button[text()='Next Player']");
    private final By wordModalInputs = By.xpath("//div[@id='add-words-modal']/input");

    private UIHelper uiHelper;

    public AddWordsPage(WebDriver driver) {
        super(driver);
        this.uiHelper = new UIHelper(driver);
    }

    public boolean isLoaded(){
        return find(title).isDisplayed();
    }

    public void addManualWord(String word){
        set(wordInput, word);
        click(addWordButton);
    }

    public String addRandomWord() {
        click(randomWordButton);
        uiHelper.waitForElementToBeActive(wordInput, 10);
        String randomWord = find(wordInput).getAttribute("value").trim();
        System.out.println("random word: " + randomWord);
        click(addWordButton);
        return randomWord;
    }

    public void waitForConfirmationModalToAppear(){
        uiHelper.waitForElementToAppear(nextPlayerButton, 10);
    }

    public List<WebElement> findAllWordModalInputs(int expectedCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d -> {
            List<WebElement> inputs = d.findElements(wordModalInputs);
            int found = inputs.size();
            System.out.printf("🔍 Found %d / expected %d word inputs%n", found, expectedCount);
            if (found >= expectedCount && inputs.stream().allMatch(WebElement::isDisplayed)) {
                return inputs;
            }
            return null;
        });
    }

    public boolean validateAllWordModalInputs(List<String> words, int expectedCount){
        System.out.println("📩 Waiting for modal inputs...");
        List<WebElement> inputs = findAllWordModalInputs(expectedCount);
        System.out.println("✅ Inputs found: " + inputs.size());
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> inputs.stream().allMatch(input -> !input.getAttribute("value").trim().isEmpty()));

        for (int i = 0; i < words.size(); i++){
            String expected = words.get(i);
            String actual = inputs.get(i).getAttribute("value");
            System.out.printf("Comparing word %d: expected='%s', actual='%s'%n", i + 1, expected, actual);

            if (!expected.equals(actual)) {
                System.out.println("❌ Mismatch detected at index " + i);
                return false;
            }
        }

        System.out.println("✅ All modal inputs validated successfully.");
        return true;
    }

    public void clickNextPlayer(){
        click(nextPlayerButton);
    }

    public AddTeamsPage gotoAddTeams(){
        int wordIndex = 0;

        for (int i = 0; i < TestData.DEFAULT_PLAYER_COUNT; i++){
            List<String> currentWords = new ArrayList<>();
            for (int j = 0; j < TestData.WORDS_AMOUNT_DEFAULT; j++){
                addManualWord(TestData.WORDS.get(wordIndex));
                currentWords.add(TestData.WORDS.get(wordIndex));
                wordIndex++;

            }
            waitForConfirmationModalToAppear();
            clickNextPlayer();
        }

        return new AddTeamsPage(driver);
    }
}
