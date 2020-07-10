import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RBCRewardsTest {
    private WebDriver driver;
    private WebDriverWait wait;

    public RBCRewardsTest(String driver) {
        if (driver.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\micha\\Desktop\\RBC\\selenium2\\chromedriver_win32\\chromedriver.exe");
            this.driver = new ChromeDriver();
            this.wait = new WebDriverWait(this.driver, 10);
        }
        // for demonstration puposes
        else if (driver.equalsIgnoreCase("Firefox")) {
            this.driver = new ChromeDriver();
        }
        else {
            this.driver = new ChromeDriver();
        }
    }

    public void goToRBCRewards() {
        driver.get("http://rbcrewards.com");
        driver.manage().window().maximize();
    }

    public void signIn() {
        driver.findElement(By.id("sign-in-button-uncookied")).click();

    }

    public void selectOLB() {
        WebElement olb = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uncookied-personal-olb")));
        olb.click();
    }
    public void enterClientCard(String number) {
        WebElement clientCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("K1")));
        clientCard.sendKeys(number);
    }

    public void enterPassword(String password) {
        WebElement passwordBox = driver.findElement(By.id("Q1"));
        passwordBox.sendKeys(password);
    }

    public void selectSignIn() {
        WebElement signInButton = driver.findElement(By.className("yellowBtnLarge"));
        signInButton.click();
    }
    public void cleanUp() {
        driver.quit();
    }

    // ####################
    // ### MAIN ###########
    // ####################
    public static void main(String[] args) throws InterruptedException {
        RBCRewardsTest test = new RBCRewardsTest("Chrome");
        test.goToRBCRewards();
        test.signIn();
        test.selectOLB();
        test.enterClientCard("123456789012345");
        test.enterPassword("Wrong Password");
        test.selectSignIn();
        Thread.sleep(3000);

        test.cleanUp();
    }
}
