package bring_it_on.test;


import bring_it_on.page.PastebinHP;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PastebinHPTest {
    private WebDriver driver;
    private PastebinHP resultPage;
    private String code = "git config --global user.name  \"New Sheriff in Town\""
            +"\n"+"git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")"
            +"\n"+"git push origin master --force";
    private String name = "how to gain dominance among developers";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        resultPage = new PastebinHP(driver);
        resultPage.createNewPasteWithConditions(code, name);
    }

    @Test(description = "Check page title")
    public void checkTitle() {
        String resultTitle = resultPage.getPageTitle();
        Assert.assertEquals(resultTitle, name);
    }

    @Test(description = "Syntax highlighted for bash")
    public void checkHighlightingForBash() {
        Assert.assertTrue(resultPage.IsSyntaxABash());
    }

    @Test(description = "Check code")
    public void checkCode() {
        String expectCode = code;
        String resultCode = driver.findElement(By.xpath(".//textarea[@class='textarea']")).getText();
        Assert.assertEquals(expectCode, resultCode);
    }

    @AfterMethod(alwaysRun = true)
    public void browserDown() {
        driver.quit();
        driver = null;
    }

}
