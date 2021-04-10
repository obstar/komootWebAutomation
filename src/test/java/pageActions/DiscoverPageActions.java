package pageActions;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import pageObjects.DiscoverPage;

import static steps.ScenarioHooks.browserActions;

public class DiscoverPageActions {

    public void searchFor(String cityToFind) {
        browserActions.click(DiscoverPage.inputSearch);
        browserActions.click(DiscoverPage.inputSearch);
        browserActions.sendKeys(DiscoverPage.inputSearch, cityToFind);
        browserActions.sendKeys(DiscoverPage.inputSearch, Keys.ENTER);
    }

    public void clickChosenDifficulty(String difficultyLevel) {
        switch(difficultyLevel) {
            case "easy":
                browserActions.click(DiscoverPage.divEasyLevel);
                break;
            case "intermediate":
                browserActions.click(DiscoverPage.divModerateLevel);
                break;
            case "expert":
                browserActions.click(DiscoverPage.divDifficultLevel);
                break;
        }
        browserActions.waitElementVisible(DiscoverPage.linkRestFilter);
    }

    public void AssertThatUserVisitThePage() {
        browserActions.waitForPageLoad();
        browserActions.waitElementExists(DiscoverPage.inputSearch);

        Assert.assertEquals(DiscoverPage.Url, browserActions.getDriver().getCurrentUrl());
    }

    public void AssertThatThereAreToursAround(String city) {
        browserActions.waitForPageLoad();
        browserActions.waitElementVisible(DiscoverPage.spanToursAround);
        String toursAroundText = browserActions.getText(DiscoverPage.spanToursAround);

        Assert.assertTrue(Integer.valueOf(toursAroundText.replaceAll("\\D+","")) > 0);
        Assert.assertTrue(toursAroundText.contains(city));
    }

    public void AssertThatFirstTourHas(String difficultyLevel) {
        Assert.assertEquals(difficultyLevel, browserActions.getText(DiscoverPage.divTourDifficultyTitle).toLowerCase());
    }
}