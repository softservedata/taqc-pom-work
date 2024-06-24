package com.softserve.edu.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.User;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.EditAccountPage;
import com.softserve.edu.opencart.pages.HomePage;
import com.softserve.edu.opencart.pages.UnsuccessfulLoginPage;

public class LoginTest extends TestRunner {

    @DataProvider(parallel = true)
    public Object[][] dataSuccessful() {
        //
        // TODO
        //
        return new Object[][] {
//            { new UserRepository().getExistUser() },
            { UserRepository.get().customer() },
            { UserRepository.get().customerHahaha() },
        };
    }

    @Test(dataProvider = "dataSuccessful")
    public void checkSuccessful(IUser user) {
        //
        // Steps
        EditAccountPage editAccountPage = loadApplication()
                .gotoLoginPage()
                //.successfulLogin(user.getEmail(), user.getPassword())
                .successfulLogin(user)
                .gotoEditAccountRight();
        presentationSleep();
        //
        // Check
        Assert.assertEquals(editAccountPage.getFirstNameFieldText(), user.getFirstname());
        //
        // Return to Previous State
        HomePage homePage = editAccountPage
                .gotoContinue() // optional
                .gotoLogoutRight()
                .gotoContinue();
        //
        // Check
        Assert.assertTrue(homePage
                .getSlideshow0FirstImageAttributeSrcText()
                .contains(HomePage.EXPECTED_IPHONE6));
        presentationSleep();
    }
    
    @DataProvider
    public Object[][] dataUnsuccessful() {
        return new Object[][] {
            //
            // TODO
            //
            //{ "invalid@awdrt.com", "pass", "not" },
            { UserRepository.get().notExist() },
        };
    }
    
    //@Test(dataProvider = "dataUnsuccessful")
    //
    // TODO
    //
    //public void checkUnsuccessful(String email, String password, String firstName) {
    public void checkUnsuccessful(User invalidUser) {
        //
        // Steps
        UnsuccessfulLoginPage unsuccessfulLoginPage = loadApplication()
                .gotoLoginPage()
                //.unsuccessfulLoginPage(email, password)
                .unsuccessfulLoginPage(invalidUser)
                .unsuccessfulLoginPage(invalidUser)
                .unsuccessfulLoginPage(invalidUser)
                .unsuccessfulLoginPage(invalidUser)
                .unsuccessfulLoginPage(invalidUser);
        presentationSleep();
        //
        // Check
        Assert.assertTrue(unsuccessfulLoginPage.getAlertWarningText()
                .contains(UnsuccessfulLoginPage.EXPECTED_LOGIN_MESSAGE));
        presentationSleep();
        //
        // Steps
        unsuccessfulLoginPage = unsuccessfulLoginPage
                //.unsuccessfulLoginPage(email, password)
                .unsuccessfulLoginPage(invalidUser)
                .unsuccessfulLoginPage(invalidUser);
        //
        // Check
        Assert.assertTrue(unsuccessfulLoginPage.getAlertWarningText()
                .contains(UnsuccessfulLoginPage.EXPECTED_LOCK_MESSAGE));
        presentationSleep();
        //
        // Return to Previous State
        HomePage homePage = unsuccessfulLoginPage
                .gotoHomePage();
        //
        // Check (optional)
        Assert.assertTrue(homePage
                .getSlideshow0FirstImageAttributeSrcText()
                .contains(HomePage.EXPECTED_IPHONE6));
        presentationSleep();
    }

}
