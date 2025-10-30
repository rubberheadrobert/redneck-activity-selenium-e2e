package com.activity.pages;

import com.activity.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingsSlidersPage extends BasePage {

    private final By roundLengthArea = By.xpath("//div[@id='round-length-area']");
    private final By wordsAmountArea = By.xpath("//div[@id='words-amount-area']");
    private final By roundLengthValue = By.xpath("//div[@id='round-length-area']/span");

    private final By wordsAmountValue = By.xpath("//div[@id='words-amount-area']/span");
    private final By roundLengthSlider = By.xpath("//div[@id='round-length-area']/div");
    private final By wordsAmountSlider = By.xpath("//div[@id='words-amount-area']/div");
    private final By roundLengthInfo = By.xpath("//div[@id='round-length-area']/p");
    private final By wordsAmountInfo = By.xpath("//div[@id='words-amount-area']/p");
    private final By nextButton = By.cssSelector("[aria-label='to-players']");
    public SettingsSlidersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded(){
        return find(wordsAmountArea).isDisplayed()
                && find(roundLengthArea).isDisplayed();
    }

    public void setSliderValue(By sliderLocator, int min, int max, int desiredValue) {
        final int targetValue = Math.max(min, Math.min(desiredValue, max));

        WebElement slider = find(sliderLocator);
        WebElement handle = slider.findElement(By.cssSelector(".rc-slider-handle"));

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(handle));

        int sliderWidth = slider.getSize().width;
        int currentValue = Integer.parseInt(handle.getAttribute("aria-valuenow"));

        double pixelsPerUnit = (double) sliderWidth / (max - min);
        int totalOffset = (int) Math.round((targetValue - currentValue) * pixelsPerUnit);

        System.out.printf(
                "Slider width=%d, pixels/unit=%.2f, moving by %d px (from %d → %d)%n",
                sliderWidth, pixelsPerUnit, totalOffset, currentValue, targetValue
        );

        Actions actions = new Actions(driver);
        actions.moveToElement(handle).clickAndHold();

        // Instead of 1px or 10px per move, move in larger chunks
        int absOffset = Math.abs(totalOffset);
        int step = (absOffset < 100) ? totalOffset : totalOffset / 5; // 5 smooth moves max
        int moved = 0;

        while (Math.abs(moved) < Math.abs(totalOffset)) {
            int nextMove = (Math.abs(totalOffset - moved) < Math.abs(step))
                    ? (totalOffset - moved)
                    : step;
            actions.moveByOffset(nextMove, 0).pause(Duration.ofMillis(40));
            moved += nextMove;
        }
        actions.release().perform();

        // Wait for React to confirm new value
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> {
            int newValue = Integer.parseInt(handle.getAttribute("aria-valuenow"));
            System.out.println("Polling... current value: " + newValue);
            return newValue == targetValue;
        });

        // Fallback JS fix if still off
        int finalValue = Integer.parseInt(handle.getAttribute("aria-valuenow"));
        if (finalValue != targetValue) {
            System.out.println("Value mismatch after drag; forcing JS update");
            ((JavascriptExecutor) driver).executeScript(
                    "const h = arguments[0], v = arguments[1];" +
                            "h.setAttribute('aria-valuenow', v);" +
                            "h.style.left = ((v - h.getAttribute('aria-valuemin')) / " +
                            "(h.getAttribute('aria-valuemax') - h.getAttribute('aria-valuemin'))) * 100 + '%';" +
                            "h.dispatchEvent(new Event('change', { bubbles: true }));",
                    handle, targetValue
            );
        }
    }

    // Convenience methods for your specific sliders
    public void setRoundLength(int value) {
        setSliderValue(roundLengthSlider, 10, 60, value); // min=10, max=60
    }

    public void setWordsAmount(int value) {
        setSliderValue(wordsAmountSlider, 2, 10, value);
    }

    public String getRoundLengthValue(){
        return find(roundLengthValue).getText();
    }

    public String getRoundLengthInfo(){
        return find(roundLengthInfo).getText();
    }

    public String getWordsAmountInfo(){
        return find(wordsAmountInfo).getText();
    }

    public String getWordsAmountValue(){
        return find(wordsAmountValue).getText();
    }

    public AddPlayersPage gotoAddPlayers() {
        click(nextButton);
        return new AddPlayersPage(driver);
    }
}
