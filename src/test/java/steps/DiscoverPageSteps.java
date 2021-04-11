package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageActions.DiscoverPageActions;

public class DiscoverPageSteps {

    public DiscoverPageActions discoverPageActions;

    public DiscoverPageSteps(DiscoverPageActions discoverPageActions) {
        this.discoverPageActions = discoverPageActions;
    }

    @Given("I search for {string} on Discover page")
    public void iSearchForPortoOnDiscoverPage(String cityToFind) {
        discoverPageActions.searchFor(cityToFind);
    }

    @When("I chose activity by {string} difficulty")
    public void iChoseActivityByEasyDifficulty(String difficultyLevel) {
        discoverPageActions.clickChosenDifficulty(difficultyLevel);
    }

    @When("I chose at least {int} hours tour duration")
    public void iChoseTourDurationAtLeastHours(int leftSliderHandleHours) {
        discoverPageActions.moveTourDurationSliderBetween(leftSliderHandleHours);
    }

    @When("I chose running activity")
    public void iChoseRunningActivity() {
        discoverPageActions.clickRunningIcon();
    }

    @When("I chose within {string} km radius")
    public void iChoseWithinKmRadius(String distanceRadius) {
        discoverPageActions.selectActivityWithin(distanceRadius);
    }

    @Then("user visit Discover page")
    public void userVisitDiscoverPage() {
        discoverPageActions.AssertThatUserVisitThePage();
    }

    @Then("I see tours around {string}")
    public void iSeeToursAroundPorto(String city) {
        discoverPageActions.AssertThatThereAreToursAround(city);
    }

    @Then("first tour has difficulty {string}")
    public void firstTourHasDifficulty(String difficultyLevel) {
        discoverPageActions.AssertThatFirstTourHas(difficultyLevel);
    }

    @Then("first tour has in title {string}")
    public void firstTourHasInTitleRunning(String activityName) {
        discoverPageActions.AssertThatFirstTourTitleContains(activityName);
    }

    @Then("first tour has tour duration at least {int} hours")
    public void firstTourHasTourDurationAtLeastHours(int minTourDuration) {
        discoverPageActions.AssertThatFirstTourDurationIsAtLeast(minTourDuration);
    }
}