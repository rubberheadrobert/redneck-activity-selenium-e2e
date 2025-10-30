package com.activity.tests.settings_sliders;

import com.activity.base.BaseTest;
import com.activity.pages.AddPlayersPage;
import com.activity.pages.SettingsSlidersPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SettingsSlidersTests extends BaseTest {

    @Test
    public void testRoundLengthSlider(){
        SettingsSlidersPage page = homePage.gotoSettings().gotoSettingsSliders();
        page.setRoundLength(11);
        Assert.assertEquals(page.getRoundLengthValue(), "11");
        Assert.assertTrue(page.getRoundLengthInfo().contains("11 seconds"),
                "Round Length text does not contain '11 seconds'");
    }

    @Test
    public void testWordsAmountSlider(){
        SettingsSlidersPage page = homePage.gotoSettings().gotoSettingsSliders();
        page.setWordsAmount(2);
        Assert.assertEquals(page.getWordsAmountValue(), "2");
        Assert.assertTrue(page.getWordsAmountInfo().contains("2 words"),
                "Words amount text does not contain '2 words");
    }

    @Test
    public void navigateToAddPlayers(){
        AddPlayersPage page = homePage.gotoSettings().gotoSettingsSliders().gotoAddPlayers();
        page.isLoaded();
    }
}
