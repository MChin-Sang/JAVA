import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
        Thread.sleep(3000);

        // Enter the password
        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("WRONG_PASSWORD");
        Thread.sleep(1000);

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
        Thread.sleep(2000);

        // store main handle before switch to help page
        String originalHandle = driver.getWindowHandle();
        // switch to and work on new help window
        for (String h : driver.getWindowHandles()) {
            // if we are not on the main window then start testing
            if (!h.equalsIgnoreCase(originalHandle)) {
                // work on new window - help
                driver.switchTo().window(h);
                WebElement searchBar = driver.findElement(By.cssSelector("input.promoted-search__input"));
                searchBar.sendKeys("Password issue...");
                Thread.sleep(2000);
            }
        }

        // switch back to originalHandle - gmail
        driver.switchTo().window(originalHandle);
        Thread.sleep(1000);

        String actualTitle = driver.getTitle();
        String compareTitle = "gmail";

        Thread.sleep(1000);
        driver.quit();

        if (actualTitle.equalsIgnoreCase(compareTitle)) {
            System.out.println("Test Pass: " + "[" + "compareTitle: " + compareTitle + ", actualTitle: " + actualTitle + "]");
        } else {
            System.out.println("Test Fail: " + "[" + "compareTitle: " + compareTitle + ", actualTitle: " + actualTitle + "]");
        }

    }
}
