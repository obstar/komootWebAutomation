package steps;

import io.cucumber.java.en.Given;
import pageActions.SignInPageActions;
import org.springframework.beans.factory.annotation.Autowired;

public class SignInPageSteps {

    @Autowired
    public SignInPageActions signInPageActions;

    @Given("existing user is logged in")
    public void existingUserIsLoggedIn() {
        signInPageActions.loggedInExistingUser();
    }
}
