package com.xm.ui.setup;

import static com.xm.ui.setup.WebDriverFactory.initiateRequestedWebBrowser;

import com.xm.ui.dto.Config;
import com.xm.ui.utils.ConfigRetrieval;
import java.time.Duration;
import java.util.Objects;
import lombok.Getter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Setup {
    private static final int WAIT_TIME = 20000;

    private static Setup setUpClass;
    @Getter
    private static WebDriver driver;
    @Getter
    private static WebDriverWait webDriverWait;

    public Setup() {
        driver = initiateRequestedWebBrowser();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(WAIT_TIME));
    }

    private static Config retrieveConfig() {
        return Objects.requireNonNull(ConfigRetrieval.retrieve());
    }

    public static void openPage() {
        driver.get(retrieveConfig().getBaseUrl());
    }

    public static void setUpDriver() {
        if (setUpClass == null) {
            setUpClass = new Setup();
        }
    }

    public static void tearDown() {
        driver.close();
        driver.quit();
        setUpClass = null;
    }

    public static void setBrowserResolution(String resolution) {
        switch (resolution.toLowerCase()) {
            case "1024x768":
                setResolution(1024, 768);
                break;
            case "800x600":
                setResolution(800, 600);
                break;
            default:
                break;
        }
    }

    private static void setResolution(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }
}
