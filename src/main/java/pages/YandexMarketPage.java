package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class YandexMarketPage {
    private final String CATALOG_BUTTON = "//button[@id = 'catalogPopupButton']";
    private final String COMPUTERS_BUTTON = "//span[(text()='Компьютеры')]";
    private final String NOTEBOOK_BUTTON = "//a[(text()='Ноутбуки')]";
    private final String PRICE_BEGIN = "//input[@id='glpricefrom']";
    private final String PRICE_END = "//input[@id='glpriceto']";
    private final String DROPDOWN_SHOW = "//button[contains(@id,'dropdown-control')]";
    private final String SEARCH_COLLECTION = "//article[@data-autotest-id='product-snippet']";
    private final String TITLE_COLLECTION = "//h3[@data-zone-name='title']/a";
    private final String SEARCH_FIELD = "//input[@id='header-search']";
    private final String SEARCH_BUTTON = "//button[@data-r='search-button']";
    private String firstElement;

    private WebDriver geckoDriver;

    public YandexMarketPage(WebDriver geckoDriver) {
        this.geckoDriver = geckoDriver;
    }

    @FindBy(how = How.XPATH, xpath = CATALOG_BUTTON)
    WebElement catalogButton;

    @FindBy(how = How.XPATH, xpath = COMPUTERS_BUTTON)
    WebElement computerButton;

    @FindBy(how = How.XPATH, xpath = NOTEBOOK_BUTTON)
    WebElement notebookButton;

    @FindBy(how = How.XPATH, xpath = PRICE_BEGIN)
    WebElement priceBegin;

    @FindBy(how = How.XPATH, xpath = PRICE_END)
    WebElement priceEnd;

    @FindBy(how = How.XPATH, xpath = DROPDOWN_SHOW)
    WebElement dropdownMenu;

    @FindBy(how = How.XPATH, xpath = SEARCH_COLLECTION)
    List<WebElement> listOfSearchResult;

    @FindBy(how = How.XPATH, xpath = TITLE_COLLECTION)
    List<WebElement> titleCollection;

    @FindBy(how = How.XPATH, xpath = SEARCH_FIELD)
    WebElement searchField;

    @FindBy(how = How.XPATH, xpath = SEARCH_BUTTON)
    WebElement searchButton;

    public YandexMarketPage pressCatalogButton() {
        catalogButton.click();
        return this;
    }

    @Step("Перейти в раздел 'Компьютеры'")
    public YandexMarketPage selectComputers() {
        WebDriverWait wait = new WebDriverWait(geckoDriver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[(text()='Компьютеры')]")));
        computerButton.click();
        return this;
    }

    @Step("Перейти в раздел 'Ноутбуки'")
    public YandexMarketPage selectNotebooks() {
        WebDriverWait wait = new WebDriverWait(geckoDriver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[(text()='Ноутбуки')]")));
        notebookButton.click();
        return this;
    }

    @Step("Задать ценовой диапазон")
    public YandexMarketPage sendPricesBounds(String from, String to) {
        WebDriverWait wait = new WebDriverWait(geckoDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PRICE_BEGIN)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PRICE_END)));
        priceBegin.sendKeys(from);
        priceEnd.sendKeys(to);
        return this;
    }

    @Step("Выбор производителя {brand}")
    public YandexMarketPage setManufacturer(String brand) {
        geckoDriver.findElement(By.xpath("//span[text()='" + brand + "']")).click();
        return this;
    }

    @Step("Задать количество элементов на странице")
    public YandexMarketPage setVisibility(String option) {
        dropdownMenu.click();
        String xpath = "//button[(text()='" + option + "')]";
        WebElement element = geckoDriver.findElement(By.xpath(xpath));
        element.click();
        return this;
    }

    @Step("Ожидание обновления результатов поиска")
    public YandexMarketPage waitLoaderInvisibility() {
        String LOADER = "//div[@class='_2Lvbi _1oZmP']";
        WebDriverWait wait = new WebDriverWait(geckoDriver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOADER)));
        return this;
    }

    @Step("Скроллинг до нужного элемента")
    public YandexMarketPage scrollingToDropdownMenu() {
        JavascriptExecutor je = (JavascriptExecutor) geckoDriver;
        je.executeScript("arguments[0].scrollIntoView(true);", dropdownMenu);
        return this;
    }

    @Step("Проверить, что на странице отобразилось {size} элементов")
    public YandexMarketPage checkSizeOfSearch(String size) {
        Assertions.assertEquals(listOfSearchResult.size(), Integer.parseInt(size), "Размер не совпадает");
        return this;
    }

    @Step("Запоминаем первый элемент поиска")
    public YandexMarketPage getFirstElement() {
        firstElement = titleCollection.get(0).getAttribute("title");
        return this;
    }

    @Step("Поиск запомненного значения через строку поиска")
    public YandexMarketPage searchFirstElement() {
        searchField.sendKeys(firstElement);
        searchButton.click();
        return this;
    }

    @Step("Сравнение запомненного значения с первым значением результата поиска")
    public void checkResult() {
        Assertions.assertEquals(firstElement, titleCollection.get(0).getAttribute("title"),
                "Результаты сравнения сохраненного значения" +
                        " и первого значения " +
                        "в списке результатов не совпадают");
    }

}
