package com.softserve.edu.teachua.pages.top;

import com.softserve.edu.teachua.data.Challengies;
import com.softserve.edu.teachua.data.Cities;
import com.softserve.edu.teachua.pages.challenge.ChallengeTeachPage;
import com.softserve.edu.teachua.pages.menu.HomePage;
import com.softserve.edu.teachua.pages.top.DropdownComponent;
import com.softserve.edu.teachua.pages.user.LoggedDropdown;
import com.softserve.edu.teachua.pages.club.ClubPage;
import com.softserve.edu.teachua.pages.menu.AboutUsPage;
import com.softserve.edu.teachua.pages.menu.NewsPage;
import com.softserve.edu.teachua.pages.menu.UkrainianServicePage;
import com.softserve.edu.teachua.pages.user.GuestDropdown;
import com.softserve.edu.teachua.pages.user.LoginModal;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.time.Duration;
import java.util.List;

public abstract class TopPart {
    public static final String POPUP_MESSAGE_SUCCESSFULLY = "Ви успішно залогувалися!";
    //
    public static final String TAG_ATTRIBUTE_VALUE = "value";
    public static final String TAG_ATTRIBUTE_SRC = "src";
    public static final String TAG_ATTRIBUTE_CLASS = "class";
    public static final String TAG_ATTRIBUTE_HREF = "href";
    //
    public static final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
    protected final String OPTION_NULL_MESSAGE = "Dropdown is null";
    //protected final String PAGE_DO_NOT_EXIST = "Page do not exist.";
    //
    public static final String POPUP_MESSAGE_CSSSELECTOR = "div.ant-message-notice-wrapper span:last-child";
    protected final String LIST_CHALLENGE_CSSSELECTOR = "a[href*='/challenges']";
    protected final String LIST_CITY_CSSSELECTOR = "ul.ant-dropdown-menu span";

    protected WebDriver driver;
    //
    private WebElement homeLink;
    private WebElement clubLink;
    private WebElement challengeLink;
    private WebElement newsLink;
    private WebElement aboutUsLink;
    private WebElement ukrainianServiceLink;
    private WebElement cityDropdownLink;
    private WebElement caretDropdownLink;
    private WebElement qubStudioLabel;
    private WebElement userProfilePic;
    //
    private DropdownComponent dropdownComponent;
    private GuestDropdown dropdownGuest;
    private LoggedDropdown dropdownLogged;

    public TopPart(WebDriver driver) {
        this.driver = driver;
        initElements();
        //checkElements();
    }

    private void initElements() {
        // init elements
        homeLink = driver.findElement(By.cssSelector("div.left-side-menu > a"));
        clubLink = driver.findElement(By.cssSelector("span.ant-menu-title-content > a[href*='/clubs']"));
        challengeLink = driver.findElement(By.cssSelector("span.challenge-text"));
        newsLink = driver.findElement(By.cssSelector("span.ant-menu-title-content > a[href*='/news']"));
        aboutUsLink = driver.findElement(By.cssSelector("span.ant-menu-title-content > a[href*='/about']"));
        ukrainianServiceLink = driver.findElement(By.cssSelector("span.ant-menu-title-content > a[href*='/service']"));
        cityDropdownLink = driver.findElement(By.cssSelector("div.city span.anticon-caret-down"));
        caretDropdownLink = driver.findElement(By.cssSelector("div.user-profile span.anticon.anticon-caret-down"));
        qubStudioLabel = driver.findElement(By.cssSelector("div.qubstudio"));
        userProfilePic = driver.findElement(By.cssSelector("div.user-profile span.ant-avatar"));
    }

    // Page Object

    // homeLink
    public WebElement getHomeLink() {
        //return driver.findElement(By.cssSelector("div.left-side-menu > a"));
        return homeLink;
    }

    public String getHomeLinkText() {
        return getHomeLink().getText();
    }

    public void clickHomeLink() {
        getHomeLink().click();
    }

    // clubLink
    public WebElement getClubLink() {
        return clubLink;
    }

    public String getClubLinkText() {
        return getClubLink().getText();
    }

    public void clickClubLink() {
        getClubLink().click();
    }

    // challengeLink
    public WebElement getChallengeLink() {
        return challengeLink;
    }

    public String getChallengeLinkText() {
        return getChallengeLink().getText();
    }

    public void clickChallengeLink() {
        getChallengeLink().click();
    }

    // newsLink
    public WebElement getNewsLink() {
        return newsLink;
    }

    public String getNewsLinkText() {
        return getNewsLink().getText();
    }

    public void clickNewsLink() {
        getNewsLink().click();
    }

    // aboutUsLink
    public WebElement getAboutUsLink() {
        return aboutUsLink;
    }

    public String getAboutUsLinkText() {
        return getAboutUsLink().getText();
    }

    public void clickAboutUsLink() {
        getAboutUsLink().click();
    }

    // ukrainianServiceLink
    public WebElement getUkrainianServiceLink() {
        return ukrainianServiceLink;
    }

    public String getUkrainianServiceLinkText() {
        return getUkrainianServiceLink().getText();
    }

    public void clickUkrainianServiceLink() {
        getUkrainianServiceLink().click();
    }

    // qubStudioLabel
    public WebElement getQubStudioLabel() {
        return qubStudioLabel;
    }

    public String getQubStudioLabelText() {
        return getQubStudioLabel().getText();
    }

    public void clickQubStudioLabel() {
        getQubStudioLabel().click();
    }

    // cityDropdownLink
    public WebElement getCityDropdownLink() {
        return cityDropdownLink;
    }

    public void clickCityDropdownLink() {
        getCityDropdownLink().click();
    }

    // caretDropdownLink
    public WebElement getCaretDropdownLink() {
        return caretDropdownLink;
    }

    public void clickCaretDropdownLink() {
        getCaretDropdownLink().click();
    }

    // userProfilePic
    public WebElement getUserProfilePic() {
        return userProfilePic;
    }

    // dropdownComponent
    protected DropdownComponent getDropdownComponent() {
        if (dropdownComponent == null) {
            // TODO Develop Custom Exception
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return dropdownComponent;
    }

    private DropdownComponent createDropdownComponent(By searchLocator) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dropdownComponent = new DropdownComponent(driver, searchLocator);
        //dropdownComponent = new DropdownComponent(searchLocator);
        return getDropdownComponent();
    }

    private void clickDropdownComponentByPartialName(String optionName) {
        if (!getDropdownComponent().isExistDropdownOptionByPartialName(optionName)) {
            // TODO Develop Custom Exception
            throw new RuntimeException(String.format(OPTION_NOT_FOUND_MESSAGE, optionName,
                    getDropdownComponent().getListOptionsText().toString()));
        }
        getDropdownComponent().clickDropdownOptionByPartialName(optionName);
        dropdownComponent = null;
        //closeDropdownComponent();
    }

    private void closeDropdownComponent() {
        clickQubStudioLabel();
        dropdownComponent = null;
    }

    // dropdownGuest
    protected GuestDropdown getDropdownGuest() {
        if (dropdownGuest == null) {
            // TODO Develop Custom Exception
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return dropdownGuest;
    }

    private GuestDropdown createDropdownGuest() {
        dropdownGuest = new GuestDropdown(driver);
        return getDropdownGuest();
    }

    private void clickDropdownGuestRegister() {
        getDropdownGuest().clickRegister();
        dropdownGuest = null;
    }

    private void clickDropdownGuestLogin() {
        getDropdownGuest().clickLogin();
        dropdownGuest = null;
    }

    private void closeDropdownGuest() {
        clickQubStudioLabel();
        dropdownGuest = null;
    }

    // dropdownLogged
    protected LoggedDropdown getDropdownLogged() {
        if (dropdownLogged == null) {
            // TODO Develop Custom Exception
            throw new RuntimeException(OPTION_NULL_MESSAGE);
        }
        return dropdownLogged;
    }

    private LoggedDropdown createDropdownLogged() {
        dropdownLogged = new LoggedDropdown(driver);
        return getDropdownLogged();
    }

    private void clickDropdownLoggedAddClub() {
        getDropdownLogged().clickAddClubLink();
        dropdownLogged = null;
    }

    private void clickDropdownLoggedAddCenter() {
        getDropdownLogged().clickAddCenterLink();
        dropdownLogged = null;
    }

    private void clickDropdownLoggedSearchCertificates() {
        getDropdownLogged().clickSearchCertificatesLink();
        dropdownLogged = null;
    }

    private void clickDropdownLoggedPersonalProfile() {
        getDropdownLogged().clickPersonalProfileLink();
        dropdownLogged = null;
    }

    private void clickDropdownLoggedSignout() {
        getDropdownLogged().clickSignOutLink();
        dropdownLogged = null;
    }

    private void closeDropdownLogged() {
        clickQubStudioLabel();
        dropdownLogged = null;
    }

    // Functional

    // caretDropdown
    protected void openCaretDropdown() {
        clickQubStudioLabel();
        clickCaretDropdownLink();
    }

    // cityDropdownLink
    private void openCityDropdownComponent() {
        clickQubStudioLabel();
        clickCityDropdownLink();
        createDropdownComponent(By.cssSelector(LIST_CITY_CSSSELECTOR));
    }

    // challengeLink
    private void openChallengeDropdownComponent() {
        clickQubStudioLabel();
        clickChallengeLink();
        createDropdownComponent(By.cssSelector(LIST_CHALLENGE_CSSSELECTOR));
    }

    private <T> T chooseChallenge(String challengeName, Class<T> clazz) {
        openChallengeDropdownComponent();
        clickDropdownComponentByPartialName(challengeName);
        //
        Constructor<T> ctor = null;
        Object object = null;
        try {
            ctor = clazz.getConstructor(WebDriver.class);
            object = ctor.newInstance(new Object[]{driver});
        } catch (Exception e) {
            // TODO Develop Custom Exception
            throw new RuntimeException(e);
        }
        return (T) object;
        //
    }

    // popupMessageLabel
    public String getPopupMessageLabelText() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        WebElement popup = driver.findElement(By.cssSelector(POPUP_MESSAGE_CSSSELECTOR));
                        System.out.println("\tpopup.getText() = " + popup.getText());
                        return !popup.getText().isEmpty();
                    }
                }
        );
        //
        List<WebElement> popupMessageLabel = driver.findElements(By.cssSelector(POPUP_MESSAGE_CSSSELECTOR));
        System.out.println("\tpopupMessageLabel.size() = " + popupMessageLabel.size());
        System.out.println("\tpopupMessageLabel.get(0).getText() = " + popupMessageLabel.get(0).getText());
        if (popupMessageLabel.size() == 0) {
            return "";
        }
        return popupMessageLabel.get(0).getText();
    }

    // userProfilePic
    public boolean isUserLogged() {
        String userProfilePicSrc = getUserProfilePic().getAttribute(TAG_ATTRIBUTE_CLASS);
        return userProfilePicSrc.contains("avatarIfLogin");
    }

    protected void scrollToElement(WebElement webElement) {
        // Actions action = new Actions(driver);
        // action.moveToElement(webElement).perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    // Business Logic

    public HomePage gotoHomePage() {
        clickHomeLink();
        return new HomePage(driver);
    }

    public ClubPage gotoClubPage() {
        clickClubLink();
        return new ClubPage(driver);
    }

    public ChallengeTeachPage gotoTeachChallengePage() {
        openChallengeDropdownComponent();
        clickDropdownComponentByPartialName("Навчайся");
        return new ChallengeTeachPage(driver);
    }

    public <T> T gotoChallengePage(String challengeName, Class<T> clazz) {
        return chooseChallenge(challengeName, clazz);
    }

    public <T> T gotoChallengePage(Challengies challengeName, Class<T> clazz) {
        return chooseChallenge(challengeName.getName(), clazz);
    }

    public ClubPage chooseCity(Cities city) {
        // TODO
        openCityDropdownComponent();
        clickDropdownComponentByPartialName(city.getCity());
        return new ClubPage(driver);
    }

    public NewsPage gotoNewsPage() {
        clickNewsLink();
        return new NewsPage(driver);
    }

    public AboutUsPage gotoAboutUsPage() {
        clickAboutUsLink();
        return new AboutUsPage(driver);
    }

    public UkrainianServicePage gotoUkrainianServicePage() {
        clickUkrainianServiceLink();
        return new UkrainianServicePage(driver);
    }

    // dropdownGuest
    public LoginModal openLoginModal() {
        openCaretDropdown();
        createDropdownGuest();
        clickDropdownGuestLogin();
        return new LoginModal(driver);
    }

    // dropdownLogged
    public HomePage signoutUser() {
        openCaretDropdown();
        createDropdownLogged();
        clickDropdownLoggedSignout();
        return new HomePage(driver);
    }

}
