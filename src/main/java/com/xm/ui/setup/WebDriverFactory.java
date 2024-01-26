package com.xm.ui.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    public static WebDriver initiateRequestedWebBrowser() {
        return new ChromeDriver();
    }
}
