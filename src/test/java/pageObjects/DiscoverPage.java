package pageObjects;

import org.openqa.selenium.By;

public class DiscoverPage {
    public static final String Url = "https://www.komoot.com/discover";
    public static By buttonSearch = By.cssSelector("[data-test-id=\"search_button\"]");
    public static By divDifficultLevel = By.cssSelector("[data-test-id=\"difficulty_difficult\"]");
    public static By divEasyLevel = By.cssSelector("[data-test-id=\"difficulty_easy\"]");
    public static By divModerateLevel = By.cssSelector("[data-test-id=\"difficulty_moderate\"]");
    public static By divTourDifficultyTitle = By.cssSelector("[data-test-id=\"tour_difficulty\"]");
    public static By linkRestFilter = By.xpath("//*[contains(text(), 'Reset Filter')]");
    public static By inputSearch = By.cssSelector("[data-test-id=\"location_search_input\"]");
    public static By spanToursAround = By.cssSelector("[data-test-id=\"tours_around\"]");
}