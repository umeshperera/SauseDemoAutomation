package base;

import config.ConfigReader;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {
    protected WebDriver driver;
    protected Faker faker;

    @BeforeEach
    public void setup(){
        if(ConfigReader.get("browser").equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();

            //Open in incognito to avoid other elements
            options.addArguments("--incognito");

            //Headless for CI
            if(ConfigReader.get("headless").equalsIgnoreCase("true")){
                //Improvements for CI
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--headless=new");
            }

            driver = new ChromeDriver(options);
        } else if (ConfigReader.get("browser").equalsIgnoreCase("firefox")){
            FirefoxOptions options = new FirefoxOptions();

            //Open in private mode to avoid other elements
            options.addArguments("-private");

            //Headless for CI
            if(ConfigReader.get("headless").equalsIgnoreCase("true")){
                options.addArguments("-headless");
            }

            driver = new FirefoxDriver(options);
        }

        //Maximize browser window
        driver.manage().window().maximize();
        //Navigate to baseURL
        driver.get(ConfigReader.get("baseURL"));

        //Setting up faker
        faker = new Faker();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
