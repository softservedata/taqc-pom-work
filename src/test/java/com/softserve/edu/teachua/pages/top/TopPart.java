package com.softserve.edu.teachua.pages.top;

import com.softserve.edu.teachua.data.Challengies;
import com.softserve.edu.teachua.pages.challenge.ChallengeTeachPage;
import com.softserve.edu.teachua.pages.menu.HomePage;
import com.softserve.edu.teachua.pages.user.LoggedDropdown;
import com.softserve.edu.teachua.pages.club.ClubPage;
import com.softserve.edu.teachua.pages.menu.AboutUsPage;
import com.softserve.edu.teachua.pages.menu.NewsPage;
import com.softserve.edu.teachua.pages.menu.UkrainianServicePage;
import com.softserve.edu.teachua.pages.user.GuestDropdown;
import com.softserve.edu.teachua.pages.user.LoginModal;
import org.openqa.selenium.*;

import java.lang.reflect.Constructor;

public abstract class TopPart {
    public static final String TAG_ATTRIBUTE_VALUE = "value";
    //public static final String TAG_ATTRIBUTE_SRC = "src";
    //
    public static final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
    protected final String OPTION_NULL_MESSAGE = "Dropdown is null";
    //protected final String PAGE_DO_NOT_EXIST = "Page do not exist.";
    //
    protected final String LIST_CHALLENGE_CSSSELECTOR = "a[href*='/challenges']";

    protected WebDriver driver;
    //
    private WebElement homeLink;
    private WebElement clubLink;
    private WebElement challengeLink;
    private WebElement newsLink;
    private WebElement aboutUsLink;
    private WebElement ukrainianServiceLink;
    private WebElement caretDropdownLink;
    private WebElement qubStudioLabel;
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
        caretDropdownLink = driver.findElement(By.cssSelector("div.user-profile span.anticon.anticon-caret-down"));
        qubStudioLabel = driver.findElement(By.cssSelector("div.qubstudio"));
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

    // caretDropdownLink
    public WebElement getCaretDropdownLink() {
        return caretDropdownLink;
    }

    public void clickCaretDropdownLink() {
        getCaretDropdownLink().click();
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

    // challengeLink
    private void openDropdownComponent() {
        clickQubStudioLabel();
        clickChallengeLink();
        createDropdownComponent(By.cssSelector(LIST_CHALLENGE_CSSSELECTOR));
    }

    private <T> T chooseChallenge(String challengeName, Class<T> clazz) {
        openDropdownComponent();
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
        openDropdownComponent();
        clickDropdownComponentByPartialName("Навчайся");
        return new ChallengeTeachPage(driver);
    }

    public <T> T gotoChallengePage(String challengeName, Class<T> clazz) {
        return chooseChallenge(challengeName, clazz);
    }

    public <T> T gotoChallengePage(Challengies challengeName, Class<T> clazz) {
        return chooseChallenge(challengeName.getName(), clazz);
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
