   
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;


public class JavaTodo {
    String username = "gmuttu90";
    String accesskey = "r4VHysUP7KD39xWoBJjWYYwn09h3faCuhMkLXNSpDjumCPVnUB";
    static RemoteWebDriver driver = null;
    String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    public static void main(String[] args) {
        new JavaTodo().test();
    }

public void test() {
        // To Setup driver
        setUp();
        try {
            // 1. Visit your own ecommerce site
            driver.get("https://www.amazon.com");

            // 2. Search for iPhone 13
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys("iphone13");
            searchBox.submit();

            // 3. Get the price
            WebElement priceElement = driver.findElement(By.cssSelector(".a-price-whole"));
            String price = priceElement.getText();

            System.out.println("iPhone 13 price on your ecommerce site: " + price);

        } finally {
        
            tearDown();
        }
    }

 private void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one.
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void tearDown() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit(); //really important statement for preventing your test execution from a timeout.
        }
    }
}





