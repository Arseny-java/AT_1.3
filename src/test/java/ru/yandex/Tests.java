package ru.yandex;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.support.PageFactory;
import pages.YandexMarketPage;
import pages.YandexPage;
import utilities.Utilities;

/**
 * Тест проверки первого значения из результатов поиска относительно запомненного значения.
 * Не всегда проходит с положительным результатом, так как отчего то Яндекс.Маркет не любит
 * модель ноутбука 15.6" Ноутбук HP 250 G8 (1366x768, Intel Celeron 1.1 ГГц, RAM 4 ГБ, SSD 128 ГБ, Win10 Pro).
 * В тех случаях, когда первой в списках оказывается другая модель, то тест проходит.
 * В целом фреймворк выполняет свою задачу, все претензии по поводу данной модели относятся к неведомой логике самого Маркета.
 */

public class Tests extends BaseTests {

    @Feature("Проверка Яндекс.Маркет")
    @DisplayName("Проверка результатов поиска")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"10000, 90000, HP, Lenovo, Показывать по 12, 12"})
    public void yandexMarketTest(String from, String to, String firstBrand, String secondBrand, String view, String count) {
        geckoDriver.get("https://www.yandex.ru");
        YandexPage pageFactory = PageFactory.initElements(geckoDriver, YandexPage.class);
        pageFactory.switchToMarket();

        Utilities.switchBetweenPage(geckoDriver,1);
        Utilities.waitFullPage(geckoDriver, 20);

        YandexMarketPage yandexMarketPage = PageFactory.initElements(geckoDriver, YandexMarketPage.class);
        yandexMarketPage.pressCatalogButton()
                .selectComputers()
                .selectNotebooks()
                .sendPricesBounds(from, to)
                .setManufacturer(firstBrand)
                .setManufacturer(secondBrand)
                .waitLoaderInvisibility()
                .scrollingToDropdownMenu()
                .setVisibility(view)
                .waitLoaderInvisibility()
                .checkSizeOfSearch(count)
                .getFirstElement()
                .searchFirstElement()
                .waitLoaderInvisibility()
                .checkResult();
    }
}
