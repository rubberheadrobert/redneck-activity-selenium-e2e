package com.activity.tests.add_words;

import com.activity.base.BasePage;
import com.activity.base.BaseTest;
import com.activity.data.TestData;
import com.activity.pages.AddTeamsPage;
import com.activity.pages.AddWordsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AddWordsTests extends BaseTest {

    @Test
    public void correctNumOfWordsPerPlayer() {
        AddWordsPage page = homePage.goToAddWordsWithDefaultData();
        int wordIndex = 0;

        for (int i = 0; i < TestData.DEFAULT_PLAYER_COUNT; i++){
            List<String> currentWords = new ArrayList<>();
            for (int j = 0; j < TestData.WORDS_AMOUNT_DEFAULT; j++){
                page.addManualWord(TestData.WORDS.get(wordIndex));
                currentWords.add(TestData.WORDS.get(wordIndex));
                wordIndex++;

            }
            page.waitForConfirmationModalToAppear();
            Assert.assertTrue(page.validateAllWordModalInputs(currentWords, TestData.WORDS_AMOUNT_DEFAULT),
                    "Not all added words are visible in the Words Modal");
            page.clickNextPlayer();
            System.out.println("Player " + (i+1) + " words validated: " + currentWords);
        }

        System.out.println("All Players added words successfully.");
    }

    @Test
    public void testRandomWord(){
        AddWordsPage page = homePage.goToAddWordsWithDefaultData();
        List<String> currentRandomWords = new ArrayList<>();

        for (int i = 0; i < TestData.WORDS_AMOUNT_DEFAULT; i++){
            String randomWord = page.addRandomWord();
            currentRandomWords.add(randomWord);
        }
        page.waitForConfirmationModalToAppear();
        page.validateAllWordModalInputs(currentRandomWords, TestData.WORDS_AMOUNT_DEFAULT);
    }

    @Test
    public void navigateToAddTeams(){
        AddTeamsPage page = homePage.goToAddTeamsWithDefaultData();
        page.isLoaded();
    }

}
