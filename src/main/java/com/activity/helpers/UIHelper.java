package com.activity.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UIHelper {
    private WebDriver driver;

    public UIHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isReactElementVisible(By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            if (elements.isEmpty()) return false;
            WebElement el = elements.get(0);
            return el.isDisplayed();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isReactButtonClickable(By locator) {
        WebElement button = driver.findElement(locator);
        return button.isDisplayed()
                && Boolean.parseBoolean(button.getAttribute("data-clickable"));
    }

    public void waitForElementToAppear(By locator, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForChildElementsToAppear(By locator, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementToBeActive(By locator, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToDisappear(By locator, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
