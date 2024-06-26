package com.softserve.edu.teachua.tests;

import com.softserve.edu.teachua.pages.club.ClubComponent;
import com.softserve.edu.teachua.pages.club.ClubDetailsPage;
import com.softserve.edu.teachua.pages.club.ClubNotFoundPage;
import com.softserve.edu.teachua.pages.club.ClubPage;
import com.softserve.edu.teachua.pages.menu.HomePage;
import com.softserve.edu.teachua.pages.top.TopPart;
import com.softserve.edu.teachua.pages.user.LoginModal;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TeachTest extends TestRunner {

    @Test
    public void checkSmoke() {
        // Steps
        HomePage homePage = loadApplication()
                .gotoClubPage()
                .gotoNewsPage()
                .gotoAboutUsPage()
                .gotoUkrainianServicePage()
                .gotoHomePage();
        presentationSleep();
        //
        // Check
        Assertions.assertTrue(homePage.getTeachLabelText().contains(HomePage.BEGIN_TEACH_LABEL_MESSAGE));
    }

    @ParameterizedTest(name = "{index} => email={0}, password={1}")
    @CsvSource({"yagifij495@eqvox.com, Qwerty_1"})
    public void checkSuccessfulLogin(String email, String password) {
        // Steps
        HomePage homePage = loadApplication()
                .openLoginModal()
                .successfulLogin(email, password);
        String popupMessage = homePage.getPopupMessageLabelText();
        presentationSleep();
        //
        // TODO Check pop-up message
        Assertions.assertEquals(TopPart.POPUP_MESSAGE_SUCCESSFULLY, popupMessage);
        //
        // Check
        Assertions.assertTrue(homePage.getTeachLabelText().contains(HomePage.BEGIN_TEACH_LABEL_MESSAGE));
        presentationSleep();
        //
        // TODO
        homePage = homePage.signoutUser();
        Assertions.assertFalse(homePage.isUserLogged());
        presentationSleep();
    }

    @ParameterizedTest(name = "{index} => email={0}, password={1}")
    @CsvSource({"hahaha@gmail.com, Qwerty_1"})
    public void checkUnsuccessfulLogin(String email, String password) {
        // Steps
        LoginModal loginModal = loadApplication()
                .openLoginModal()
                .unsuccessfulLoginPage(email, password);
        String popupMessage = loginModal.getPopupMessageLabelText();
        presentationSleep();
        //
        // TODO Check pop-up message
        Assertions.assertEquals(LoginModal.POPUP_MESSAGE_UNSUCCESSFULLY, popupMessage);
        presentationSleep();
    }

    @ParameterizedTest(name = "{index} => searchText={0}, clubTitle={1}")
    @CsvSource({"Dream Team, Школа танців Dream Team"})
    public void checkClubExist(String searchText, String clubTitle) {
        // updated
        ClubComponent clubComponent = loadApplication()
                .successfulSearchClub(searchText)
                .getClubContainer()
                .getClubComponentByPartialTitle(searchText);
        //
        // Check club title
        Assertions.assertEquals(clubTitle, clubComponent.getTitleLinkText());
        //
        /*
        ClubPage clubPage = loadApplication()
                .successfulSearchClub(searchText);
        //
        // Check club title
        Assertions.assertEquals(clubTitle, clubPage.getClubTitleLinkText());
        */
    }

    @ParameterizedTest(name = "{index} => searchText={0}")
    @CsvSource({"proba"})
    public void checkClubNotExist(String searchText) {
        ClubNotFoundPage clubNotFoundPage = loadApplication()
                .unsuccessfulSearchClub(searchText);
        //
        // Check club title
        Assertions.assertEquals(ClubNotFoundPage.NOT_FOUND_MASSAGE, clubNotFoundPage.getNotFoundLabelText());
    }

    @ParameterizedTest(name = "{index} => email={0}, password={1}, searchText={2}, commentText={3}")
    @CsvSource({"yagifij495@eqvox.com, Qwerty_1, IT освіта: курси, Проба Коментар44"})
    public void checkAddComment(String email, String password, String searchText, String commentText) {
        ClubDetailsPage clubDetailsPage = loadApplication()
                .openLoginModal()
                .successfulLogin(email, password)
                .successfulSearchClub(searchText)
                // Updated
                .getClubContainer()
                .getClubComponentByPartialTitle(searchText)
                .openClubDetailsPage()
                //
                //.gotoClubDetailsPage()
                //
                .openClubCommentModal()
                .submitComment(commentText);
        //
        // TODO Check comment text
        //System.out.println("\tclubDetailsPage.getCommentContentLabelText() = " + clubDetailsPage.getCommentContentLabelText());
        //Assertions.assertEquals(commentText, clubDetailsPage.getCommentContentLabelText());
        System.out.println("\tclubDetailsPage.getCommentContentLabelText() = "
                + clubDetailsPage.getCommentsContainer().getFirstCommentComponent().getCommentLabelText());
        Assertions.assertEquals(commentText, clubDetailsPage
                .getCommentsContainer().getFirstCommentComponent().getCommentLabelText());
        //
        HomePage homePage = clubDetailsPage.signoutUser();
        Assertions.assertFalse(homePage.isUserLogged());
        presentationSleep();
    }

    @ParameterizedTest(name = "{index} => searchText={0}, commentText={1}")
    @CsvSource({"IT освіта: курси, Проба Коментар44"})
    public void checkExistComment(String searchText, String commentText) {
        ClubDetailsPage clubDetailsPage = loadApplication()
                .successfulSearchClub(searchText)
                //Updated
                .getClubContainer()
                .getClubComponentByPartialTitle(searchText)
                .openClubInfoModal()
                //
                //.openClubInfoModal()
                .gotoClubDetailsPage();
        //
        // TODO Check comment text
        //System.out.println("\tclubDetailsPage.getCommentContentLabelText() = " + clubDetailsPage.getCommentContentLabelText());
        //Assertions.assertEquals(commentText, clubDetailsPage.getCommentContentLabelText());
        System.out.println("\tclubDetailsPage.getCommentContentLabelText() = "
                + clubDetailsPage.getCommentsContainer().getFirstCommentComponent().getCommentLabelText());
        Assertions.assertEquals(commentText, clubDetailsPage
                .getCommentsContainer().getFirstCommentComponent().getCommentLabelText());
        //
        HomePage homePage = clubDetailsPage.gotoHomePage();
        Assertions.assertFalse(homePage.isUserLogged());
        presentationSleep();
    }
}
