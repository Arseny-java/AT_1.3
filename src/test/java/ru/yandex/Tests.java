package ru.yandex;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.YandexMarketPage;
import pages.YandexPage;
import utilities.Utilities;


public class Tests extends BaseTests {
    @Test
    public void test1() throws InterruptedException {
        geckoDriver.get("https://www.yandex.ru");
        YandexPage pageFactory = PageFactory.initElements(geckoDriver, YandexPage.class);
        pageFactory.switchToMarket();

        Utilities.switchBetweenPage(geckoDriver);
        Utilities.waitFullPage(geckoDriver,20);

        YandexMarketPage yp = PageFactory.initElements(geckoDriver, YandexMarketPage.class);
        yp.pressCatalogButton()
                .waitCustomMethod(geckoDriver,20,"//span[(text()='Компьютеры')]")
                .selectComputers()
                .waitCustomMethod(geckoDriver,20,"//a[(text()='Ноутбуки')]")
                .selectNotebooks()
                .sendPricesBounds("10000", "90000")
                .setManufacturer(geckoDriver,"HP")
                .setManufacturer(geckoDriver,"Lenovo")
                //.waitCustomMethod(geckoDriver,20,"//button[contains(@id, 'dropdown-control')]")
                .waitFull(geckoDriver,5000)
                .setShownQuantity(geckoDriver);



    }
    @Test
    public void test2(){
        geckoDriver.get("https://market.yandex.ru/catalog--noutbuki-v-ekaterinburge/54544/list?glfilter=7893318%3A152722%2C152981&hid=91013&pricefrom=10000&priceto=90000&onstock=0&local-offers-first=0");
        WebElement element = geckoDriver.findElement(By.xpath("//button[contains(@id, 'dropdown-control')]"));
        JavascriptExecutor je = (JavascriptExecutor)geckoDriver;
        je.executeScript("arguments[0].scrollIntoView(true);",element);
        WebDriverWait wait = new WebDriverWait(geckoDriver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@id, 'dropdown-control')]")));
        element.click();

    }

}
