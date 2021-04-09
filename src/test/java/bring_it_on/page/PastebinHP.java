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

    @FindBy(xpath = "/html/head/meta[5]")
    private WebElement pageTitle;

    @FindBy(xpath = ".//a[text()='Bash']")
    private WebElement syntaxIsBash;

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

    public PastebinHP printCodeInNewPasteForm(String text) {
        WebElement newPasteTextArea = waitForElementLocatedBy(driver, By.id("postform-text"));
        newPasteTextArea.sendKeys(text);
        return this;
    }

    public PastebinHP chooseSyntaxHighlightingForBash() {
        syntaxHighlightingList.click();
        bashLanguage.click();
        return this;
    }

    public PastebinHP chooseExpirationForTenMinutes() {
        pastExpirationDropDownList.click();
        tenMinutesExpiration.click();
        return this;
    }

    public PastebinHP pasteName(String name) {
        WebElement pasteNameArea = waitForElementLocatedBy(driver, By.id("postform-name"));
        pasteNameArea.sendKeys(name);
        return this;
    }

    public PastebinHP clickNewPasteBtn() {
        WebElement pasteNameArea = waitForElementLocatedBy(driver, By.xpath("//button[text()='Create New Paste']"));
        createNewPasteButton.click();
        return this;
    }

    public void createNewPasteWithConditions(String code, String name) {
        openPage()
                .printCodeInNewPasteForm(code)
                .chooseSyntaxHighlightingForBash()
                .chooseExpirationForTenMinutes()
                .pasteName(name)
                .clickNewPasteBtn();
    }

    public String getPageTitle() {
        return driver.findElement(By.xpath("/html/head/meta[5]")).getText();
    }

    public boolean IsSyntaxABash() {
        return (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(syntaxIsBash)).isDisplayed();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        WebElement element = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }
}
