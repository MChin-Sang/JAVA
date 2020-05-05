import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ******** NOTES *********
 * Synchronization
 * implicit and explicit wait:
 *
 * Implicit waits:
 *      driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
 *      - TimeOut is the time to wait as an integer
 *      - TimeUnit.SECONDS is the unit of measure
 *      - This will wait for the exact time specified so it could end up waiting too long
 *
 * Explicit waits:
 *      WebDriverWait wait = new WebDriverWait(driver, 10); // declares wait object
 *      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("elementID")); // condition
 *      - 10 represents the max time to wait
 *      - This method will wait up to the max or until the condition specified is met
 */

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        // set the location of my local download of chrome driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\micha\\Desktop\\RBC\\selenium2\\chromedriver_win32\\chromedriver.exe");

        // WebDriver is interface and we are creating ChromeDriver from it
        WebDriver driver = new ChromeDriver();
        // open website
        driver.get("http://gmail.com/");
        // maximize window once open
        driver.manage().window().maximize();

        // find the search bar element by id, inspected element to get id name
        // use of a WebElement for another option
        WebElement usrName = driver.findElement(By.id("identifierId"));
        usrName.sendKeys("michaelcs8.mcs@gmail.com");
        Thread.sleep(1000);

        // find next button by class name
        driver.findElement(By.className("CwaK9")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // explicitly wait for element to be loaded
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        password.sendKeys("WRONG_PASSWORD");
        Thread.sleep(1000);

        // originally had below to wait for elements to appear
        //
        // Thread.sleep(3000);
        // Enter the password
        // WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        // password.sendKeys("WRONG_PASSWORD");
        // Thread.sleep(1000);
        //
        //////////////////////////

        // show password
        // span represented by multiple classes
        WebElement showPassword = driver.findElement(By.cssSelector("span.A37UZe.sxyYjd.MQL3Ob"));
        showPassword.click();
        Thread.sleep(2000);

        // click next button
        driver.findElement(By.cssSelector("span.CwaK9")).click();
        Thread.sleep((1000));

        // click help link
        // ul is parent and identified by class="Bgzgmd" and we are going into the first li child - help link
        WebElement helpLink = driver.findElement(By.cssSelector("ul.Bgzgmd li:nth-child(1)"));
        helpLink.click();

        // store main handle before switch to help page
        String originalHandle = driver.getWindowHandle();
        // switch to and work on new help window
        for (String h : driver.getWindowHandles()) {
            // if we are not on the main window then start testing
            if (!h.equalsIgnoreCase(originalHandle)) {
                // work on new window - help
                driver.switchTo().window(h);
                WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoted-search__input")));
                // **PREVIOUS** WebElement searchBar = driver.findElement(By.cssSelector("input.promoted-search__input"));
                searchBar.sendKeys("Password issue help...");
                Thread.sleep(2000);
                driver.close();
            }
        }

        // switch back to originalHandle - gmail
        driver.switchTo().window(originalHandle);
        Thread.sleep(1000);

        // compare the current handle to expected title of "gmail"
        // pass if they are equal
        String currentTitle = driver.getTitle();
        String expectedTitle = "gmail";
        Thread.sleep(1000);

        driver.quit();

        if (currentTitle.equalsIgnoreCase(expectedTitle)) {
            System.out.println("Test Pass: " + "[" + "expectedTitle: " + expectedTitle + ", currentTitle: " + currentTitle + "]");
        } else {
            System.out.println("Test Fail: " + "[" + "expectedTitle: " + expectedTitle + ", currentTitle: " + currentTitle + "]");
        }

    }
}
