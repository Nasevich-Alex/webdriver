package i_can_win.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement newPasteTextArea;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pastExpirationDropDownList;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement tenMinutesExpiration;

    @FindBy(id = "postform-name")
    private WebElement pasteNameArea;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement createNewPasteButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("postform-text")));
        return this;
    }

    public PastebinHomePage printCodeInNewPasteForm(String text) throws InterruptedException {
        WebElement newPasteTextArea = waitForElementLocatedBy(driver, By.id("postform-text"));
        newPasteTextArea.sendKeys(text);
        return this;
    }

    public PastebinHomePage chooseExpirationForTenMinutes() throws InterruptedException {
        pastExpirationDropDownList.click();
        tenMinutesExpiration.click();
        return this;
    }

    public PastebinHomePage pasteName(String name) throws InterruptedException {
        WebElement pasteNameArea = waitForElementLocatedBy(driver, By.id("postform-name"));
        pasteNameArea.sendKeys(name);
        return this;
    }

    public PastebinHomePage createNewPaste() throws InterruptedException {
        WebElement pasteNameArea = waitForElementLocatedBy(driver, By.xpath("//button[text()='Create New Paste']"));
        createNewPasteButton.click();
        return this;
    }


    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        WebElement element = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

}
