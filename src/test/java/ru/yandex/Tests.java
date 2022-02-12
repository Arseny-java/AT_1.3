package ru.yandex;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.PageFactory;
import pages.YandexMarketPage;
import pages.YandexPage;
import utilities.Utilities;


public class Tests extends BaseTests {
    @Test
    public void test1() throws InterruptedException {
        geckoDriver.get("https://www.yandex.ru");
        YandexPage pageFactory = PageFactory.initElements(geckoDriver, YandexPage.class);
        pageFactory.switchToMarket();
        Thread.sleep(5000);

        Utilities.switchBetweenPage(geckoDriver);
        Utilities.waitFullPage(geckoDriver,20);

        YandexMarketPage yandexMarketPage = PageFactory.initElements(geckoDriver, YandexMarketPage.class);
        yandexMarketPage.pressCatalogButton()
                .selectComputers()
                .selectNotebooks()
                .sendPricesBounds("10000", "90000")
                .setManufacturer("HP")
                .setManufacturer("Lenovo")
                .waitLoaderInvisibility()
                .scrollingToDropdownMenu()
                .setVisibility("Показывать по 12")
                .waitLoaderInvisibility()
                .checkSizeOfSearch(12)
                .getFirstElement()
                .searchFirstElement()
                .waitLoaderInvisibility()
                .checkResult();

    }

}
