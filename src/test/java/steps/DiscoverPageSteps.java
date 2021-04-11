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

    @When("I chose tour duration between {string} and {string} hours")
    public void iChoseTourDurationBetweenAndHours(String leftSliderHandleHours, String rightSliderHandleHours) {
        discoverPageActions.moveTourDurationSliderBetween(leftSliderHandleHours, rightSliderHandleHours);
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
}