package com.activity.tests.home;

import com.activity.base.BaseTest;
import com.activity.pages.FindGamePage;
import com.activity.pages.SettingsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTests extends BaseTest {

    @Test
    public void verifyHomeElements(){
        Assert.assertTrue(homePage.isLoaded(), "Home page is not loaded.");
    }

    @Test
    public void navigateToFindGame(){
        FindGamePage page = homePage.gotoFindGame();
        Assert.assertTrue(page.isLoaded(), "Find game page is not loaded");
    }

    @Test
    public void navigateToSettings(){
        SettingsPage page = homePage.gotoSettings();
        Assert.assertTrue(page.isLoaded(), "Settings page is not loaded");
    }
}
