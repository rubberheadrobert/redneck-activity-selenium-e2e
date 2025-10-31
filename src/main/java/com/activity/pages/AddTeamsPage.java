package com.activity.pages;

import com.activity.base.BasePage;
import com.activity.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AddTeamsPage extends BasePage {

    private final By title = By.xpath("//h1[text()='Add Teams']");
    private final By teamContainer = By.xpath("//div[@id='team-container']");
    private final By visibleTeamContainer = By.xpath("//div[@id='team-container' and contains(@style, 'display: block')]");
    private final By visibleTeamName = By.xpath("//div[@id='team-container' and contains(@style, 'display: block')]/input");
    private final By visibleTeamPlayers = By.xpath("//div[@id='team-container' and contains(@style, 'display: block')]/p");
    private final By nextTeamButton = By.xpath("//button[@buttonname='next']");
    private final By prevTeamButton = By.xpath("//button[@buttonname='prev']");
    private final By shuffleButton = By.xpath("//button[text()='Shuffle']");
    private final By nextButton = By.xpath("//button[@aria-label='to-game']");
    public AddTeamsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded(){
        return find(title).isDisplayed();

    }

    public void addToVisiblePlayers(List<String> players){
        int numOfPlayersPerTeam = TestData.DEFAULT_PLAYER_COUNT / TestData.DEFAULT_TEAM_COUNT;
        List<WebElement> playerNames = findAllTeamParagraphs(numOfPlayersPerTeam);
        for (int i = 0; i < numOfPlayersPerTeam; i++){
            String player = playerNames.get(i).getText();
            System.out.println(i + " - found: " + player);
            players.add(player);
        }
    }

    public List<WebElement> findAllTeamParagraphs(int expectedCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d -> {
            List<WebElement> inputs = d.findElements(visibleTeamPlayers);
            int found = inputs.size();
            System.out.printf("🔍 Found %d / expected %d player texts%n", found, expectedCount);
            if (found >= expectedCount && inputs.stream().allMatch(WebElement::isDisplayed)) {
                return inputs;
            }
            return null;
        });
    }

    public int getNumOfVisibleTeams() {
        List<WebElement> teams = driver.findElements(visibleTeamContainer);
        long visibleCount = teams.stream().filter(WebElement::isDisplayed).count();


        return (int) visibleCount;
    }

    public void nextTeam(){
        click(nextTeamButton);
    }

    public List<String> getVisibleTeamPlayers() {
        List<String> playersVisible = new ArrayList<>();
        for (int i = 0; i < TestData.DEFAULT_TEAM_COUNT; i++){
            addToVisiblePlayers(playersVisible);
            nextTeam();
        }
        return playersVisible;
    }

    public void clickShuffleButton(){
        click(shuffleButton);
        click(shuffleButton);
        waitForElementToAppear(visibleTeamContainer, 10);
    }

    public void editTeamName(String name){
        set(visibleTeamName, name);
    }

    public Boolean validateEditedTeamName(String name){
        click(nextTeamButton);
        click(prevTeamButton);
        return find(visibleTeamName).getAttribute("value").equals(name);
    }

    public GamePage gotoGame(){
        click(nextButton);
        return new GamePage(driver);
    }
}
