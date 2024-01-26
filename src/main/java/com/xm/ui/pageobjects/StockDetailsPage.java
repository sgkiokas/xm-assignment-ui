package com.xm.ui.pageobjects;

import com.xm.ui.pageobjects.tables.TradingConditionsTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StockDetailsPage extends BasePage {

    @FindBy(css = "td[data-xm-qa-name='margin_requirement__value']")
    private WebElement marginRequirement;

    @FindBy(css = "td[data-xm-qa-name='symbols__value']")
    private WebElement symbols;

    @FindBy(css = "td[data-xm-qa-name='description__value']")
    private WebElement description;

    @FindBy(css = "td[data-xm-qa-name='fc_min_price_fluctuation__value']")
    private WebElement minPriceFluctuation;

    @FindBy(css = "td[data-xm-qa-name='value_of_minimum_price_fluctuation__value']")
    private WebElement valueOfMinPriceFluctuation;

    @FindBy(css = "td[data-xm-qa-name='value_one_lot__value']")
    private WebElement valueOneLot;

    @FindBy(css = "td[data-xm-qa-name='spreads_as_low_as__value']")
    private WebElement spreadAsLow;

    @FindBy(css = "td[data-xm-qa-name='min_max_trade_size__value']")
    private WebElement minMaxTradeSize;

    @FindBy(css = "td[data-xm-qa-name='limit_and_stop_levels__title'].ltr")
    private WebElement limitAndStopLevels;

    @FindBy(css = "td[data-xm-qa-name='cryptocurrency_total_volume_limit_new__title'].ltr")
    private WebElement totalVolumeLimit;

    @FindBy(css = ".table.pull-right td[data-xm-qa-name='spreads_as_low_as__value']")
    private WebElement spreadAsLowAs;

    @FindBy(css = "td[data-xm-qa-name='swap_value_in_margin_currency_long__value']")
    private WebElement longSwapValue;

    @FindBy(css = "td[data-xm-qa-name='swap_value_in_margin_currency_short__value']")
    private WebElement shortSwapValue;

    @FindBy(css = "h1.ltr.text-left")
    private WebElement selectedStockName;

    public StockDetailsPage(WebDriver driver) {
        super(driver);
    }

    public TradingConditionsTable getTradingConditions() {
        return TradingConditionsTable.builder()
                .marginRequirement(marginRequirement.getText())
                .symbols(symbols.getText())
                .description(description.getText())
                .minPriceFluctuation(minPriceFluctuation.getText())
                .valueOfMinPriceFluctuation(valueOfMinPriceFluctuation.getText())
                .valueOfOneLot(valueOneLot.getText())
                .spreadAsLow(spreadAsLowAs.getText())
                .minMaxTradeSize(minMaxTradeSize.getText())
                .swapValueInMarginCurrencyLong(longSwapValue.getText())
                .swapValueInMarginCurrencyShort(shortSwapValue.getText())
                .limitAndStopLevels(limitAndStopLevels.getText())
                .totalVolumeLimit(totalVolumeLimit.getText())
                .build();
    }
}
