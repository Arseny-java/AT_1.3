package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utilities;

import java.util.List;


public class YandexMarketPage {
    private final String CATALOG_BUTTON = "//button[@id = 'catalogPopupButton']";
    private final String COMPUTERS_BUTTON = "//span[(text()='Компьютеры')]";
    private final String NOTEBOOK_BUTTON = "//a[(text()='Ноутбуки')]";
    private final String PRICE_BEGIN = "//input[@id='glpricefrom']";
    private final String PRICE_END = "//input[@id='glpriceto']";
    private final String DROPDOWN_SHOW = "//button[contains(@id,'dropdown-control')]";
    private final String F = "//article[@data-autotest-id='product-snippet']";

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

    @FindBy(how = How.XPATH, xpath = F)
    List<WebElement> waitingResult;

    public YandexMarketPage pressCatalogButton() {
        catalogButton.click();
        return this;
    }

    public YandexMarketPage selectComputers() {
        computerButton.click();
        return this;
    }

    public YandexMarketPage selectNotebooks() {
        notebookButton.click();
        return this;
    }

    public YandexMarketPage sendPricesBounds(String from, String to) {
        priceBegin.sendKeys(from);
        priceEnd.sendKeys(to);
        return this;
    }

    public YandexMarketPage setManufacturer(WebDriver geckoDriver, String brand) {
        geckoDriver.findElement(By.xpath("//span[text()='" + brand + "']")).click();
        return this;
    }

    public YandexMarketPage setShownQuantity(WebDriver geckoDriver) throws InterruptedException {
        JavascriptExecutor je = (JavascriptExecutor) geckoDriver;
        je.executeScript("arguments[0].scrollIntoView(false);", dropdownMenu);
        //Utilities.wait(geckoDriver, 20, DROPDOWN_SHOW);
        //dropdownMenu.click();
        //Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(geckoDriver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DROPDOWN_SHOW)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DROPDOWN_SHOW)));

        geckoDriver.findElement(By.xpath(DROPDOWN_SHOW)).click();
        return this;
    }


    public YandexMarketPage waitCustomMethod(WebDriver geckoDriver, int waitTime, String xpath) {
        WebDriverWait wait = new WebDriverWait(geckoDriver, waitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return this;
    }

    public YandexMarketPage waitFull(WebDriver geckoDriver, int waitTime) {
        WebDriverWait wait = new WebDriverWait(geckoDriver, waitTime);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//article[@data-autotest-id='product-snippet']"),1));
        return this;
    }


}
