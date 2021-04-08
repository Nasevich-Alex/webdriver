package bring_it_on.page;

import i_can_win.page.PastebinHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHP {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement newPasteTextArea;

    @FindBy(id = "select2-postform-format-container")
    private WebElement syntaxHighlightingList;

    @FindBy(xpath = "//li[text()='Bash']")
    private WebElement bashLanguage;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pastExpirationDropDownList;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement tenMinutesExpiration;

    @FindBy(id = "postform-name")
    private WebElement pasteNameArea;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement createNewPasteButton;

    public PastebinHP(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHP openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("postform-text")));
        return this;
    }


}
