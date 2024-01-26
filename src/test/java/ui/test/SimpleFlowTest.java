package ui.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.xm.ui.appcontent.StocksPageConstants;
import com.xm.ui.appcontent.TradingPageConstants;
import com.xm.ui.pageobjects.HomePage;
import com.xm.ui.pageobjects.StockDetailsPage;
import com.xm.ui.pageobjects.StocksPage;
import com.xm.ui.pageobjects.TradingPage;
import com.xm.ui.pageobjects.helpers.CustomAssertions;
import com.xm.ui.setup.Setup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SimpleFlowTest {

    private final HomePage homePage = new HomePage(Setup.getDriver());
    private final TradingPage tradingPage = new TradingPage(Setup.getDriver());
    private final StocksPage stocksPage = new StocksPage(Setup.getDriver());
    private final StockDetailsPage stockDetailsPage = new StockDetailsPage(Setup.getDriver());

    @BeforeAll
    public static void globalSetup() {
        Setup.setUpDriver();
        Setup.openPage();
    }

    @AfterAll
    public static void tearDown() {
        Setup.tearDown();
    }

    @DisplayName("Resolution Test")
    @ParameterizedTest
    @ValueSource(strings = {"max", "1024x768", "800x600"})
    void testSimpleFlow(String resolution) {
        Setup.setBrowserResolution(resolution);

        homePage.open();

        homePage.clickTradingHeader();
        assertThat(homePage.getTradingMenuText(homePage.getCurrentElement()).trim().toLowerCase())
                .contains(TradingPageConstants.TRADING_MENU_TEXT.toLowerCase().trim());

        tradingPage.clickStocks();
        assertThat(stocksPage.getActiveClassText())
                .isEqualTo(StocksPageConstants.ACTIVE_CLASS_OPTIONS);

        stocksPage.clickStockCFDOption(StocksPageConstants.NORWAY_REGION);
        assertThat(stocksPage.getStockCFDOptionText().toLowerCase())
                .isEqualTo(StocksPageConstants.NORWAY_REGION.toLowerCase());

        var stockDetails = stocksPage.getDataFromTableWithPagination(StocksPageConstants.SEARCHED_STOCK);
        assertThat(stockDetails.getSymbolWithDescription()).isEqualTo(StocksPageConstants.SEARCHED_STOCK);

        var tradingConditions = stockDetailsPage.getTradingConditions();
        assertAll(() -> {
            assertThat(StocksPageConstants.SEARCHED_STOCK.toLowerCase())
                    .contains(tradingConditions.getSymbols().toLowerCase());
            assertThat(StocksPageConstants.SEARCHED_STOCK.toLowerCase())
                    .contains(tradingConditions.getDescription().toLowerCase());
        });

        new CustomAssertions().assertStockDetails(tradingConditions, stockDetails);
    }
}
