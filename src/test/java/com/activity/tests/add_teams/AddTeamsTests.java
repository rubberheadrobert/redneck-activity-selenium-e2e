package com.activity.tests.add_teams;

import com.activity.base.BaseTest;
import com.activity.data.TestData;
import com.activity.pages.AddTeamsPage;
import com.activity.pages.GamePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddTeamsTests  extends BaseTest {

    @Test
    public void validatePlayers(){
        AddTeamsPage page = homePage.goToAddTeamsWithDefaultData();
        List<String> playersVisible = page.getVisibleTeamPlayers();
        List<String> expectedPlayers = TestData.PLAYER_NAMES.subList(0, TestData.DEFAULT_PLAYER_COUNT);
        Collections.sort(expectedPlayers);
        Collections.sort(playersVisible);
        Assert.assertEquals(expectedPlayers, playersVisible,
                "The players visible don't match the expected players\n" +
                        "Players visible: " + playersVisible + "\n" +
                        "Players expected: " + expectedPlayers
        );
        System.out.println("Players Visible: " + playersVisible);
    }

    @Test
    public void validateOnlyOneTeamVisible(){
        AddTeamsPage page = homePage.goToAddTeamsWithDefaultData();
        Assert.assertEquals(page.getNumOfVisibleTeams(), 1,
                "Only one team should be visible at a time.");

    }

    @Test
    public void testShuffle(){
        AddTeamsPage page = homePage.goToAddTeamsWithDefaultData();
        List<String> beforeShufflePlayers = page.getVisibleTeamPlayers();
        page.clickShuffleButton();
        List<String> afterShufflePlayers = page.getVisibleTeamPlayers();
        Assert.assertTrue(beforeShufflePlayers.containsAll(afterShufflePlayers),
                "The same players aren't displayed after shuffle.");
        Assert.assertFalse(beforeShufflePlayers.containsAll(afterShufflePlayers)
                        && afterShufflePlayers.containsAll(beforeShufflePlayers),
                "The players weren't shuffled.");
    }

    @Test
    public void testEditTeamName(){
        AddTeamsPage page = homePage.goToAddTeamsWithDefaultData();
        page.editTeamName(TestData.TEAM_NAME_EDIT);
        Assert.assertTrue(page.validateEditedTeamName(TestData.TEAM_NAME_EDIT),
                "Edited team name not visible.");
    }

    @Test
    public void navigateToGame(){
        GamePage page = homePage.goToGameWithDefaultData();
        page.isLoaded();
    }
}
