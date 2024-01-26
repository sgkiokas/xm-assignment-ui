package com.xm.ui.pageobjects;

import com.xm.ui.setup.Setup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@SuppressWarnings("AbbreviationAsWordInName")
public class BasePage {

    private ThreadLocal<WebElement> currentElement = new ThreadLocal<>();

    @FindBy(css = "button.gtm-acceptDefaultCookieFirstVisit")
    private WebElement acceptCookies;

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void clickElement(WebElement element) {
        element.click();
    }

    protected String getElementText(WebElement element) {
        return element.getText();
    }

    public WebElement getCurrentElement() {
        return currentElement.get();
    }

    public String getCurrentElementText() {
        return currentElement.get().getText();
    }

    protected void setCurrentElement(WebElement element) {
        currentElement.remove();
        currentElement.set(element);
    }

    protected void waitForVisibility(WebElement element) {
        Setup.getWebDriverWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        Setup.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void acceptCookies() {
        clickElement(acceptCookies);
    }

    protected void clickElementWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @SuppressWarnings({"MethodParamPad", "LineLength"})
    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'})", element);
    }

    public void open() {
        if (acceptCookies.isDisplayed()) {
            this.acceptCookies();

            setCurrentElement(acceptCookies);
        }
    }

    public static class ElementNotFoundException extends RuntimeException {
        public ElementNotFoundException(String message) {
            super(message);
        }
    }
}
