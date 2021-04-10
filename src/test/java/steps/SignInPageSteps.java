package steps;

import io.cucumber.java.en.Given;
import pageActions.SignInPageActions;

public class SignInPageSteps {

    public SignInPageActions signInPageActions;

    public SignInPageSteps(SignInPageActions signInPageActions) {
        this.signInPageActions = signInPageActions;
    }

    @Given("existing user is logged in")
    public void existingUserIsLoggedIn(){
        signInPageActions.loggedInExistingUser();
    }
}