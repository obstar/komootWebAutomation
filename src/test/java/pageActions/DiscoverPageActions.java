package pageActions;

import configuration.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageObjects.DiscoverPage;

import static steps.ScenarioHooks.browserActions;

public class DiscoverPageActions {

    private static int slider30MinutesResolution = 16 ;

    public void AssertThatFirstTourHas(String difficultyLevel) {
        browserActions.assertEquals(difficultyLevel, browserActions.getText(DiscoverPage.divTourDifficultyTitle).toLowerCase());
    }

    public void AssertThatFirstTourDurationIsAtLeast(int minTourDuration) {
        browserActions.waitForPageLoad();
        browserActions.waitForAjaxToFinish();
        browserActions.waitElementVisible(DiscoverPage.spanToursDuration);
        String toursDurationNumbers = browserActions
                .getText(DiscoverPage.spanToursDuration)
                .replaceAll("\\D+","")
                .replaceAll("(\\d{2})(\\d+)", "$1.$2");

        Log.info("Assert true");
        Log.info("Condition: " + minTourDuration + " <= " + toursDurationNumbers);
        Assert.assertTrue(minTourDuration <= Double.valueOf(toursDurationNumbers));
    }

    public void AssertThatThereAreToursAround(String city) {
        browserActions.waitForPageLoad();
        browserActions.waitElementVisible(DiscoverPage.spanToursAround);
        String toursAroundText = browserActions.getText(DiscoverPage.spanToursAround);
        String tourAroundNumbers = toursAroundText.replaceAll("\\D+","");

        Log.info("Assert true");
        Log.info("Condition: " + tourAroundNumbers + " > " + 0);
        Assert.assertTrue(Integer.valueOf(tourAroundNumbers) > 0);
        Log.info("Assert true");
        Log.info("Condition: " + toursAroundText + " contains " + city);
        Assert.assertTrue(toursAroundText.contains(city));
    }

    public void AssertThatUserVisitThePage() {
        browserActions.waitForPageLoad();
        browserActions.waitElementExists(DiscoverPage.inputSearch);
        browserActions.assertEquals(DiscoverPage.Url, browserActions.getDriver().getCurrentUrl());
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

    public void moveTourDurationSliderBetween(int leftSliderHandleHours) {
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

    private int getSlideBy(int leftSliderHandleHours, double sliderPixelResolution) {
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