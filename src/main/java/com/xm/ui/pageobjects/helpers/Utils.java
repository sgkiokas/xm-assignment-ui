package com.xm.ui.pageobjects.helpers;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class Utils {
    public Dimension getCurrentResolution(WebDriver driver) {
        return driver.manage().window().getSize();
    }
}
