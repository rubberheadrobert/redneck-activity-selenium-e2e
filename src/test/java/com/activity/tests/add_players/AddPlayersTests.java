package com.activity.tests.add_players;

import com.activity.base.BaseTest;
import com.activity.data.TestData;
import com.activity.pages.AddPlayersPage;
import com.activity.pages.AddWordsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddPlayersTests extends BaseTest {

    @Test
    public void correctNumOfPlayerInputsAppear(){
        AddPlayersPage page = homePage.gotoSettings().gotoSettingsSliders().gotoAddPlayers();
        page.setNumOfPlayersInput(TestData.DEFAULT_PLAYER_COUNT);
        int numOfPlayerInputs = page.findAllPlayerInputs(TestData.DEFAULT_PLAYER_COUNT).size();
        Assert.assertEquals(numOfPlayerInputs, TestData.DEFAULT_PLAYER_COUNT);
    }

    @Test
    public void testErrorMessage(){
        AddPlayersPage page = homePage.gotoSettings().gotoSettingsSliders().gotoAddPlayers();
        Assert.assertTrue(page.isErrorMessageVisible(), "Error message is not visible.");
        page.fillInPageDataCorrectly( TestData.DEFAULT_PLAYER_COUNT,
                TestData.DEFAULT_TEAM_COUNT,
                TestData.PLAYER_NAMES);
        Assert.assertFalse(page.isErrorMessageVisible(), "Error message is visible.");
    }

    @Test
    public void testNextPageButton(){
        AddPlayersPage page = homePage.gotoSettings().gotoSettingsSliders().gotoAddPlayers();
        Assert.assertFalse(page.isNextClickable(), "Next Page Button is clickable.");
        page.fillInPageDataCorrectly( TestData.DEFAULT_PLAYER_COUNT,
                                        TestData.DEFAULT_TEAM_COUNT,
                                        TestData.PLAYER_NAMES);
        Assert.assertTrue(page.isNextClickable(), "Next Page Button is not clickable");
    }

    @Test
    public void navigateToAddWords(){
        AddWordsPage page = homePage.goToAddWordsWithDefaultData();
        page.isLoaded();
    }
}
