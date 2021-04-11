package pageActions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageObjects.DiscoverPage;

import static steps.ScenarioHooks.browserActions;

public class DiscoverPageActions {

    private static int slider30MinutesResolution = 16 ;

    public void AssertThatFirstTourHas(String difficultyLevel) {
        Assert.assertEquals(difficultyLevel, browserActions.getText(DiscoverPage.divTourDifficultyTitle).toLowerCase());
    }

    public void AssertThatThereAreToursAround(String city) {
        browserActions.waitForPageLoad();
        browserActions.waitElementVisible(DiscoverPage.spanToursAround);
        String toursAroundText = browserActions.getText(DiscoverPage.spanToursAround);

        Assert.assertTrue(Integer.valueOf(toursAroundText.replaceAll("\\D+","")) > 0);
        Assert.assertTrue(toursAroundText.contains(city));
    }

    public void AssertThatUserVisitThePage() {
        browserActions.waitForPageLoad();
        browserActions.waitElementExists(DiscoverPage.inputSearch);

        Assert.assertEquals(DiscoverPage.Url, browserActions.getDriver().getCurrentUrl());
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

    public void moveTourDurationSliderBetween(String leftSliderHandleHours, String rightSliderHandleHours) {
        browserActions.slideHorizontallyByPixels(
                DiscoverPage.divSliderHandleLeft,
                getSlideBy(leftSliderHandleHours, getSliderPixelResolution(DiscoverPage.divSliderRange)));
    }

    public void searchFor(String cityToFind) {
        browserActions.click(DiscoverPage.inputSearch);
        browserActions.click(DiscoverPage.inputSearch);
        browserActions.sendKeys(DiscoverPage.inputSearch, cityToFind);
        browserActions.sendKeys(DiscoverPage.inputSearch, Keys.ENTER);
    }

    private int getSlideBy(String leftSliderHandleHours, double sliderPixelResolution) {
        double slideBy = sliderPixelResolution * (Double.valueOf(leftSliderHandleHours) * 2 );
        double n = slideBy - Math.floor(slideBy);
        if(n < 0.7) {
            slideBy = Math.floor(slideBy);
        }
        else {
            slideBy = Math.ceil(slideBy);
        }
        return Integer.valueOf((int) slideBy);
    }

    private double getSliderPixelResolution(By target) {
        browserActions.waitElementVisible(target);
        return Double.valueOf(browserActions.findElement(target).getAttribute("offsetLeft"))
                / slider30MinutesResolution;
    }
}