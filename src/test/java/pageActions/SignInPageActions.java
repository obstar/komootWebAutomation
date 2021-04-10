package pageActions;

import pageObjects.SignInPage;

import static steps.ScenarioHooks.browserActions;

public class SignInPageActions {

    public void GoToSignInPage()  {
        browserActions.navigateTo(SignInPage.Url);
    }

    private void ClickContinueWithEmail()  {
        browserActions.click(SignInPage.buttonContinueWithEmail);
    }

    private void ClickLogIn() {
        browserActions.click(SignInPage.buttonLogIn);
    }

    private void TypeInEmail(String email){
        browserActions.sendKeys(SignInPage.inputEmail, email);
    }

    private void TypeInPassword(String password){
        browserActions.sendKeys(SignInPage.inputPassword, password);
    }

    public void loggedInExistingUser() {
        GoToSignInPage();
        TypeInEmail("o.b.star@wp.pl");
        ClickContinueWithEmail();
        TypeInPassword("P4ssw0rd");
        ClickLogIn();
    }
}