package com.xm.ui.pageobjects.tables;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StocksTable {
    private String symbolWithDescription;
    private String mt5Symbol;
    private String spreadAsLowAs;
    private String minMaxTradeSize;
    private String marginPercentage;
    private String longSwapValue;
    private String shortSwapValue;
    private String limitAndStopLevels;
}
