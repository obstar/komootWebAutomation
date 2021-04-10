package pageObjects;

import org.openqa.selenium.By;

public class SignInPage {

    public static final String Url = "https://account.komoot.com/signin";
    public static By buttonContinueWithEmail = By.cssSelector("[data-test-id=\"email_next\"]");
    public static By buttonLogIn= By.cssSelector("[data-test-id=\"password_next\"]");
    public static By inputEmail = By.id("email");
    public static By inputPassword = By.id("password");
}