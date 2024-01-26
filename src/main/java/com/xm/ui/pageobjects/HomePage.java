package com.xm.ui.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(className = "main_nav_trading")
    private WebElement tradingHeader;

    @FindBy(className = "toggleLeftNav")
    private WebElement toggleMenuButton;

    @FindBy(className = "navbar-nav__toggleArrow")
    private WebElement toggleTradingButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getTradingMenuText(WebElement element) {
        waitForVisibility(element);

        return getElementText(element);
    }

    public void clickTradingHeader() {
        if (!tradingHeader.isDisplayed()) {
            clickElement(toggleMenuButton);
            waitForVisibility(toggleTradingButton);
            clickElement(toggleTradingButton);

            setCurrentElement(toggleTradingButton);
        } else {
            clickElement(tradingHeader);

            setCurrentElement(tradingHeader);
        }
    }
}
