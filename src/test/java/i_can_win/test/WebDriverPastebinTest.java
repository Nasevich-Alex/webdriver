package i_can_win.test;

import i_can_win.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverPastebinTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "First task - I Can Win")
    public void createNewPaste() throws InterruptedException {
        new PastebinHomePage(driver)
                .openPage()
                .printCodeInNewPasteForm("Hello from WebDriver")
                .chooseExpirationForTenMinutes()
                .pasteName("helloweb")
                .createNewPaste();
    }

    @AfterMethod(alwaysRun = true)
    public void browserDown() {
        driver.quit();
        driver = null;
    }
}
