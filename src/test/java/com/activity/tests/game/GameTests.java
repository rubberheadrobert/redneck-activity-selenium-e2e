package com.activity.tests.game;

import com.activity.base.BaseTest;
import com.activity.data.TestData;
import com.activity.pages.GamePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GameTests extends BaseTest {

    @Test
    public void validateNextRoundModal(){
        GamePage page = homePage.goToGameWithDefaultData();
        Assert.assertEquals(page.getNextPlayerName(), TestData.PLAYER_NAMES.get(0),
                "Next player is not as expected.");
        Assert.assertEquals(page.getNextTeamName(), TestData.DEFAULT_TEAM_NAMES.get(0));
    }

    @Test
    public void validateRoundTypeRotation(){
        GamePage page = homePage.goToGameWithDefaultData();
        page.closeNextRoundModal();
        for (int i = 0; i < 3; i++){
            Assert.assertEquals(page.getRoundType(), TestData.ROUND_TYPES.get(i));
            page.goThroughRound();
        }
    }
}
