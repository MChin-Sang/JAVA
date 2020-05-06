import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ******** NOTES *********
 * Thread.sleep() is used only for me to view what the test is doing
 *
 * Synchronization
 * implicit and explicit wait:
 *
 * Implicit waits:
 *      driver.manage().timeouts().implicitlyWait(TimeOut, TimeUnit.SECONDS);
 *      - TimeOut is the time to wait as an integer
 *      - TimeUnit.SECONDS is the unit of measure
 *      - An explicit waits is code you define to wait for a certain condition to occur before proceeding further in the code.
 *
 * Explicit waits:
 *      WebDriverWait wait = new WebDriverWait(driver, 10); // declares wait object
 *      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("elementID")); // condition
 *      - 10 represents the max time to wait
 *      - An implicit wait is to tell WebDriver to poll the DOM for a certain amount of time when trying to find an
 *        element or elements if they are not immediately available.
 */
public class GmailTest2 {
    private WebDriver driver;
    private WebDriverWait wait;

    // not setup for other drivers but examples of different driver test setup
    public GmailTest2(String driver) {
        if (driver.equalsIgnoreCase("Chrome")) {
            // set the location of my local download of chrome driver
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\micha\\Desktop\\RBC\\selenium2\\chromedriver_win32\\chromedriver.exe");
            this.driver = new ChromeDriver();
        }
        else if (driver.equalsIgnoreCase("Firefox")) {
            this.driver = new ChromeDriver();
        }
        else {
            this.driver = new ChromeDriver();
        }
    }

    // can only be called after driver is set
    public void setExplicitWait(int time) {
        if (driver != null) {
            this.wait = new WebDriverWait(driver, time);
        }
    }

    public void goToGmail() {
        // open website
        driver.get("http://gmail.com/");
        // maximize window once open
        driver.manage().window().maximize();
    }

    public void enterUsername(String u) {
        // find the search bar element by id, inspected element to get id name
        // use of a WebElement for another option
        WebElement usrName = driver.findElement(By.id("identifierId"));
        usrName.sendKeys(u);

        // find next button by class name
        driver.findElement(By.className("CwaK9")).click();
    }

    public void enterPassword(String p) {
        // explicitly wait for element to be loaded
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        password.sendKeys(p);
    }

    // show password and click next button
    public void showPassword() throws InterruptedException {
        // span represented by multiple classes
        driver.findElement(By.cssSelector("span.A37UZe.sxyYjd.MQL3Ob")).click();

        Thread.sleep(1000);

        // click next button
        driver.findElement(By.cssSelector("span.CwaK9")).click();
    }

    // check if error message is displayed and if the message is displayed correctly
    public boolean checkPasswordErrorMsg(String expectedAlertText) {
        String passwordAlertText;

        // use xpath to get alert span on incorrect password
        // Assert.assertEquals used to compare the values inside alert
        WebElement passwordAlert = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .xpath("//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div[2]/div[2]/span")));

        passwordAlertText = passwordAlert.getText();
        return passwordAlertText.equals(expectedAlertText);
    }

    public void helpLink() {
        // click help link
        // ul is parent and identified by class="Bgzgmd" and we are going into the first li child - help link
        driver.findElement(By.cssSelector("ul.Bgzgmd li:nth-child(1)")).click();
    }

    public void helpSearchBar() throws InterruptedException {
        // store main handle
        String originalHandle = driver.getWindowHandle();
        // switch to and work on new help window
        for (String h : driver.getWindowHandles()) {
            // if we are not on the main window then start testing
            if (!h.equalsIgnoreCase(originalHandle)) {
                // work on new window - help
                driver.switchTo().window(h);
                WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoted-search__input")));
                searchBar.sendKeys("Password issue help...");
                Thread.sleep(1000);

                driver.close();
            }
        }
    }

    public void cleanUp() {
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        GmailTest2 test = new GmailTest2("Chrome");
        test.setExplicitWait(10);

        test.goToGmail();

        test.enterUsername("michaelcs8.mcs@gmail.com");
        Thread.sleep(1000);

        test.enterPassword("WRONG_PASSWORD");
        Thread.sleep(1000);

        test.showPassword();

        if (test.checkPasswordErrorMsg("Wrong password. Try again or click Forgot password to reset it.")) {
            System.out.println("password check [PASS]");
        } else {
            System.out.println("password check [FAIL]");
        }

        test.helpLink();

        test.helpSearchBar();

        test.cleanUp();
    }
}
