package com.xm.ui.pageobjects.locators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StocksPageLocators {
    SYMBOL_WITH_DESCRIPTION("td[data-xm-qa-name='symbolWithDescription']"),
    MT5_SYMBOL("td[data-xm-qa-name='symbol']"),
    SPREAD_LOW_AS("td[data-xm-qa-name='minSpread']"),
    MIN_MAX_TRADE_SIZE("td[data-xm-qa-name='minMaxTradeSize']"),
    MARGIN_PERCENTAGE("td[data-xm-qa-name='marginRequirement']"),
    LONG_SWAP_VALUE("td[data-xm-qa-name='swapLong']"),
    SHORT_SWAP_VALUE("td[data-xm-qa-name='swapShort']"),
    LIMIT_AND_STOP_LEVEL("td[data-xm-qa-name='limitStopLevel']"),
    READ_MORE_URL("td[data-xm-qa-name='url'] a");

    private final String locator;
}
