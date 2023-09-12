package Luma.LumaWeb.Modules;

import Luma.LumaWeb.Component.BaseTest;
import Luma.LumaWeb.Pages.AccountPage;
import Luma.LumaWeb.Pages.LoginPage;
import Luma.LumaWeb.Pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SignUpLoginTCS extends BaseTest {

    @Test(testName = "validate user can't SignUp with invalid Email ", dataProvider = "getSignUpData")
    public void InvalidSignUpTC01001(HashMap<String, String> input) {
        SignUpPage signUpPage = homePage.goToSignUpPage();
        signUpPage.createNewAccount(
                input.get("firstName"), input.get("lastName"), "ahmed.com", input.get("password"), input.get("confirmPass"));
        Assert.assertEquals(signUpPage.getEmailErrorMsgSignUp(), "Please enter a valid email address (Ex: johndoe@domain.com).");
    }

    @Test(testName = "validate user can't SignUp with empty 'First Name' field ", dataProvider = "getSignUpData")
    public void InvalidSignUpTC01002(HashMap<String, String> input) {
        SignUpPage signUpPage = homePage.goToSignUpPage();
        signUpPage.createNewAccount(
                " ", input.get("lastName"), input.get("email"), input.get("password"), input.get("confirmPass"));
        Assert.assertEquals(signUpPage.getFirstNameErrorMsg(), "This is a required field.");
    }

    @Test(testName = "validate user can't sign up with empty 'Last Name' field", dataProvider = "getSignUpData")
    public void InvalidSignUpTC01003(HashMap<String, String> input) {
        SignUpPage signUpPage = homePage.goToSignUpPage();
        signUpPage.createNewAccount(
                input.get("firstName"), " ", input.get("email"), input.get("password"), input.get("confirmPass"));
        Assert.assertEquals(signUpPage.getLastNameErrorMsg(), "This is a required field.");
    }

    @Test(testName = "validate user can't SignUp with Valid Email & Invalid Password 'short < 8' ", dataProvider = "getSignUpData")
    public void InvalidSignUpTC01004(HashMap<String, String> input) {
        SignUpPage signUpPage = homePage.goToSignUpPage();
        signUpPage.createNewAccount(
                input.get("firstName"), input.get("lastName"), input.get("email"), "ahmed12", "ahmed12");
        Assert.assertEquals(signUpPage.getPasswordErrorMsgSignUp(),
                "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.");
    }

    @Test(testName = "validate user can't SignUp with Valid Email & Invalid Password 'no numbers or capital letters' ", dataProvider = "getSignUpData")
    public void InvalidSignUpTC01005(HashMap<String, String> input) {
        SignUpPage signUpPage = homePage.goToSignUpPage();
        signUpPage.createNewAccount(
                input.get("firstName"), input.get("lastName"), input.get("email"), "ahmedlabib", "ahmedlabib");
        Assert.assertEquals(signUpPage.getPasswordErrorMsgSignUp(),
                "Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters.");
    }

    @Test(testName = "validate user can't SignUp with Valid Email & Invalid Password 'not matching'", dataProvider = "getSignUpData")
    public void InvalidSignUpTC01006(HashMap<String, String> input) {
        SignUpPage signUpPage = homePage.goToSignUpPage();
        signUpPage.createNewAccount(
                input.get("firstName"), input.get("lastName"), input.get("email"), input.get("password"), "ahmedlabib");
        Assert.assertEquals(signUpPage.getConfirmPassErrorMsg(), "Please enter the same value again.");
    }

    @Test(testName = "validate user can SignUp with Valid data", dataProvider = "getSignUpData")
    public void ValidSignUpTC01007(HashMap<String, String> input)   {
        SignUpPage signUpPage = homePage.goToSignUpPage();
        AccountPage accountPage=signUpPage.createNewAccount(
                input.get("firstName"), input.get("lastName"), "ahmed"+signUpPage.getRandomNumber()+"@gmail.com", input.get("password"), input.get("confirmPass"));
        Assert.assertEquals(accountPage.getAlertConfirmationMsg(),"Thank you for registering with Main Website Store.");

    }

    @Test(testName = "Validate user can't login with invalid email",dataProvider = "getSignUpData")
    public void InvalidLoginTC01008(HashMap<String,String>input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("ahmed.ocm", input.get("password"));
        Assert.assertEquals(loginPage.getErrorEmailMsgLogin(), "Please enter a valid email address (Ex: johndoe@domain.com).");
    }

    @Test(testName = "Validate user can't login with invalid password",dataProvider = "getSignUpData")
    public void InvalidLoginTC01009(HashMap<String,String>input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), "Ahmed#123");
        Assert.assertEquals(loginPage.getErrorLoginMsg(), "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");
    }

    @Test(testName = "Validate user can't login with empty password",dataProvider = "getSignUpData")
    public void InvalidLoginTC01010(HashMap<String,String>input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), "");
        Assert.assertEquals(loginPage.getErrorPasswordMsgLogin(), "This is a required field.");
    }

    @Test(testName = "Validate user can login with valid email &  password",dataProvider = "getSignUpData")
    public void ValidLoginTC01011(HashMap<String,String>input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), input.get("password"));
        Assert.assertEquals(homePage.getGreetingMsg(), "Welcome, ahmed labib!");
    }

    @Test(testName = "Validate user can go to signUp page from login Page")
    public void ValidLoginTC01012() {
        LoginPage loginPage = homePage.goToLoginPage();
        SignUpPage signUpPage=loginPage.goToCreateAccountPage();
        Assert.assertEquals(signUpPage.getPageTitleLbl(), "Create New Customer Account");
    }
    @Test(testName = "Validate user can log out from his account",dataProvider = "getSignUpData")
    public void ValidLoginTC01013(HashMap<String,String>input)   {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), input.get("password"));
        homePage.signOut();
    }

}
