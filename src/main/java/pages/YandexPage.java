package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class YandexPage {
    private final String YANDEX_MARKET_BUTTON = "//a[@data-id='market']";

    @FindBy(how = How.XPATH, xpath = YANDEX_MARKET_BUTTON)
    WebElement marketButton;

    public void switchToMarket() {
        marketButton.click();
    }
}
