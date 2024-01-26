package com.xm.ui.pageobjects.tables;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradingConditionsTable {
    private String marginRequirement;
    private String symbols;
    private String description;
    private String minPriceFluctuation;
    private String valueOfMinPriceFluctuation;
    private String valueOfOneLot;
    private String spreadAsLow;
    private String minMaxTradeSize;
    private String swapValueInMarginCurrencyLong;
    private String swapValueInMarginCurrencyShort;
    private String limitAndStopLevels;
    private String totalVolumeLimit;
}
