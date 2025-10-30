package com.activity.tests.settings;

import com.activity.base.BaseTest;
import com.activity.pages.SettingsPage;
import com.activity.pages.SettingsSlidersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SettingsTests extends BaseTest {

    @Test
    public void testMultiplePhoneWarningAppears(){
        SettingsPage settingsPage = homePage.gotoSettings();
        settingsPage.clickMultiplePhonesButton();
        Assert.assertTrue(settingsPage.isMultiplePhoneInfoDisplayed(),
                "Expected 'feature not implemented yet' text to be visible");
        settingsPage.gotoSettingsSliders();
        Assert.assertTrue(settingsPage.isLoaded(),
                "Should remain on settings page since Multiple phones feature not implemented yet");
    }

    @Test
    public void testTeamRandomizationToggle(){
        SettingsPage settingsPage = homePage.gotoSettings();
        settingsPage.clickNonRandomButton();
        Assert.assertTrue(settingsPage.isNonRandomInfoDisplayed(),
                "Expected 'Teams will be selected by players.' not displayed");
        settingsPage.clickRandomButton();
        Assert.assertTrue(settingsPage.isRandomInfoDisplayed(),
                "Expected 'Teams will be selected randomly.' not displayed");
    }

    @Test
    public void navigateToSettingsSliders(){
        SettingsSlidersPage page = homePage.gotoSettings().gotoSettingsSliders();
        page.isLoaded();

    }
}
