package com.xm.ui.pageobjects;

import com.xm.ui.pageobjects.locators.StocksPageLocators;
import com.xm.ui.pageobjects.tables.StocksTable;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@SuppressWarnings({"AbbreviationAsWordInName", "LineLength"})
public class StocksPage extends BasePage {

    static final String CSS_SYMBOL_WITH_DESCRIPTION = "td[data-xm-qa-name='symbolWithDescription']";

    @FindBy(css = "h1.ltr.text-left")
    private WebElement stocksMainHeader;

    @FindBy(css = "a.paginate_button.next")
    private WebElement nextPageButton;

    @FindBy(css = "table#DataTables_Table_0 tbody tr")
    private List<WebElement> stockTableRows;

    @FindBy(css = CSS_SYMBOL_WITH_DESCRIPTION)
    private WebElement symbolWithDescription;

    @FindBy(css = "a.btn.btn-green")
    private List<WebElement> readMoreButton;

    public StocksPage(WebDriver driver) {
        super(driver);
    }

    public String getActiveClassText() {
        waitForVisibility(stocksMainHeader);

        return getElementText(stocksMainHeader);
    }

    public void clickStockCFDOption(String value) {
        WebElement stockOption = driver.findElement(By.cssSelector(String.format(".btn[data-value=%s]", value)));

        // this is used due to some overlapping bars in the UI
        this.scrollIntoView(stockOption);
        clickElementWithJS(stockOption);

        setCurrentElement(stockOption);
    }

    public String getStockCFDOptionText() {
        return getCurrentElementText();
    }

    public StocksTable getDataFromTableWithPagination(String identifier) {
        if (isTextPresentInTable(identifier)) {
            return getStocksTableRowData(identifier);
        }

        if (nextPageButton.isEnabled()) {
            clickElementWithJS(nextPageButton);
            return getDataFromTableWithPagination(identifier);
        }

        throw new ElementNotFoundException(String.format("Element with identifier '%s' not found in the table.", identifier));
    }

    @SuppressWarnings("VariableDeclarationUsageDistance")
    private StocksTable getStocksTableRowData(String identifier) {
        List<WebElement> tableRows = this.stockTableRows;

        for (WebElement row : tableRows) {
            WebElement symbolWithDescriptionCell = row.findElement(By.cssSelector(StocksPageLocators.SYMBOL_WITH_DESCRIPTION.getLocator()));
            setCurrentElement(symbolWithDescriptionCell);

            String symbolWithDescription = symbolWithDescriptionCell.getText();

            if (symbolWithDescription.equals(identifier)) {
                StocksTable stocksTable = StocksTable.builder()
                        .symbolWithDescription(row.findElement(By.cssSelector(StocksPageLocators.SYMBOL_WITH_DESCRIPTION.getLocator())).getText())
                        .mt5Symbol(row.findElement(By.cssSelector(StocksPageLocators.MT5_SYMBOL.getLocator())).getText())
                        .spreadAsLowAs(row.findElement(By.cssSelector(StocksPageLocators.SPREAD_LOW_AS.getLocator())).getText())
                        .minMaxTradeSize(row.findElement(By.cssSelector(StocksPageLocators.MIN_MAX_TRADE_SIZE.getLocator())).getText())
                        .marginPercentage(row.findElement(By.cssSelector(StocksPageLocators.MARGIN_PERCENTAGE.getLocator())).getText())
                        .longSwapValue(row.findElement(By.cssSelector(StocksPageLocators.LONG_SWAP_VALUE.getLocator())).getText())
                        .shortSwapValue(row.findElement(By.cssSelector(StocksPageLocators.SHORT_SWAP_VALUE.getLocator())).getText())
                        .limitAndStopLevels(row.findElement(By.cssSelector(StocksPageLocators.LIMIT_AND_STOP_LEVEL.getLocator())).getText())
                        .build();

                WebElement readMore = row.findElement(By.cssSelector(StocksPageLocators.READ_MORE_URL.getLocator()));

                if (!readMore.isDisplayed()) {
                    WebElement symbol = row.findElement(By.cssSelector(StocksPageLocators.SYMBOL_WITH_DESCRIPTION.getLocator()));
                    scrollIntoView(symbol);
                    waitForElementToBeClickable(symbol);

                    symbol.click();
                    clickElement(readMoreButton.stream()
                            .filter(WebElement::isDisplayed)
                            .findAny()
                            .orElseThrow(() -> new RuntimeException("Read More button not displayed!")));
                } else {
                    scrollIntoView(readMore);
                    waitForElementToBeClickable(readMore);

                    readMore.click();
                }

                return stocksTable;
            }
        }

        throw new ElementNotFoundException(String.format("Element with identifier '%s' not found in the table.", identifier));
    }

    private boolean isTextPresentInTable(String identifier) {
        return driver.findElements(By.tagName("td"))
                .stream()
                .map(WebElement::getText)
                .anyMatch(text -> text.equals(identifier));
    }
}
