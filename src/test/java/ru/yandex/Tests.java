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
 * ���� �������� ������� �������� �� ����������� ������ ������������ ������������ ��������.
 * �� ������ �������� � ������������� �����������, ��� ��� ������ �� ������.������ �� �����
 * ������ �������� 15.6" ������� HP 250 G8 (1366x768, Intel Celeron 1.1 ���, RAM 4 ��, SSD 128 ��, Win10 Pro).
 * � ��� �������, ����� ������ � ������� ����������� ������ ������, �� ���� ��������.
 * � ����� ��������� ��������� ���� ������, ��� ��������� �� ������ ������ ������ ��������� � ��������� ������ ������ �������.
 */

public class Tests extends BaseTests {

    @Feature("�������� ������.������")
    @DisplayName("�������� ����������� ������")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"10000, 90000, HP, Lenovo, ���������� �� 12, 12"})
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
