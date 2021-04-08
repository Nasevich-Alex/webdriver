import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.tut.by/");
       // new WebDriverWait(driver, 10).until(pageobject_model.page.CustomCondition.jQueryAJAXsCompleted());

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("search_from_str")));

       // WebElement searchInput = driver.findElement(By.id("search_from_str"));
        WebElement searchInput = waitForElementLocatedBy(driver, By.id("search_from_str"));
        searchInput.sendKeys("selenium java");

        List<WebElement> searchButton = driver.findElements(By.xpath("//*[@id='search']/div/div[1]/input[2]"));
        searchButton.get(0).click();

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class,'col-i') and contains(.,'selenium') and contains(.,'java')]"));
        System.out.println("Search result number for request term: " + searchResults.size());

        driver.quit();
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        WebElement searchInput = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
        return searchInput;
    }
}
