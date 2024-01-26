package com.xm.ui.pageobjects.helpers;

import com.xm.ui.pageobjects.tables.StocksTable;
import com.xm.ui.pageobjects.tables.TradingConditionsTable;
import org.assertj.core.api.SoftAssertions;

@SuppressWarnings("LineLength")
public class CustomAssertions {
    public void assertStockDetails(TradingConditionsTable tradingConditionsTable, StocksTable stocksTable) {
        SoftAssertions.assertSoftly(softAssert -> {
            softAssert.assertThat(tradingConditionsTable.getSymbols()).isEqualTo(stocksTable.getMt5Symbol());
            softAssert.assertThat(tradingConditionsTable.getSpreadAsLow()).isEqualTo(stocksTable.getSpreadAsLowAs());
            softAssert.assertThat(tradingConditionsTable.getMinMaxTradeSize()).isEqualTo(stocksTable.getMinMaxTradeSize());
            softAssert.assertThat(tradingConditionsTable.getMarginRequirement()).isEqualTo(stocksTable.getMarginPercentage());
            softAssert.assertThat(tradingConditionsTable.getSwapValueInMarginCurrencyLong()).isEqualTo(stocksTable.getLongSwapValue());
        });
    }
}
