package com.xm.ui.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TradingPage extends BasePage {

    @FindBy(css = "li.menu-stocks a[href='https://www.xm.com/stocks']:not(:empty)")
    private WebElement stocksMenu;

    @FindBy(css = ".menu-stocks > a")
    private WebElement stocksButton;

    @FindBy(xpath = "//div[@id='tradingMenu']//span[normalize-space()='Stocks']")
    private WebElement stockToggleMenuButton;

    public TradingPage(WebDriver driver) {
        super(driver);
    }

    public void clickStocks() {
        if (!stocksMenu.isDisplayed()) {
            waitForElementToBeClickable(stockToggleMenuButton);
            clickElement(stockToggleMenuButton);
        } else {
            clickElement(stocksButton);
        }
    }
}
