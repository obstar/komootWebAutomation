package pageActions;

import configuration.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageObjects.DiscoverPage;

import static steps.ScenarioHooks.browserActions;

public class DiscoverPageActions {

    private static int slider30MinutesResolution = 16 ;


    public void AssertThatFirstTourTitleContains(String activityName) {
        browserActions.waitForPageLoad();
        browserActions.waitElementVisible(DiscoverPage.textTourTitle);
        String tourTitleText = browserActions.getText(DiscoverPage.textTourTitle);

        Log.info("Assert true");
        Log.info("Condition: " + tourTitleText + " contains " + activityName);
        Assert.assertTrue(tourTitleText.contains(activityName));
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

    public void AssertThatFirstTourHas(String difficultyLevel) {
        browserActions.assertEquals(difficultyLevel, browserActions.getText(DiscoverPage.divTourDifficultyTitle).toLowerCase());
    }

    public void AssertThatThereAreToursAround(String city) {
        browserActions.waitForPageLoad();
        browserActions.waitForAjaxToFinish();
        browserActions.waitElementVisible(DiscoverPage.spanToursAround);
        browserActions.waitElementClickable(DiscoverPage.spanToursAround);
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
        //sometimes Tours Around 'city' are not displaying, ergo page refresh
        browserActions.waitForAjaxToFinish();
        browserActions.getDriver().navigate().refresh();
        browserActions.waitElementVisible(DiscoverPage.linkRestFilter);
        browserActions.waitForAjaxToFinish();
    }

    public void clickRunningIcon() {
        browserActions.waitElementVisible(DiscoverPage.divRunningIcon);
        browserActions.waitElementClickable(DiscoverPage.divRunningIcon);
        browserActions.click(DiscoverPage.divRunningIcon);
    }

    public void moveTourDurationSliderBetween(int leftSliderHandleHours) {
        browserActions.slideHorizontallyByPixels(
                DiscoverPage.divSliderHandleLeft,
                getSlideBy(leftSliderHandleHours, getSliderPixelResolution(DiscoverPage.divSliderRange)));
    }

    public void searchFor(String cityToFind) {
        browserActions.waitElementVisible(DiscoverPage.inputSearch);
        browserActions.waitElementClickable(DiscoverPage.inputSearch);
        browserActions.click(DiscoverPage.inputSearch);
        browserActions.click(DiscoverPage.inputSearch);
        browserActions.clearInput(DiscoverPage.inputSearch);
        browserActions.sendKeys(DiscoverPage.inputSearch, cityToFind);
        browserActions.sendKeys(DiscoverPage.inputSearch, Keys.ENTER);
        browserActions.waitForPageLoad();
        browserActions.waitForAjaxToFinish();
    }

    public void selectActivityWithin(String distanceRadius) {
        browserActions.waitForAjaxToFinish();
        try {
            browserActions.click(DiscoverPage.dropdownRadiusDistance);
            browserActions.click(By.cssSelector("[data-value=\"" + distanceRadius + "\"]"));
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        browserActions.waitForAjaxToFinish();
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