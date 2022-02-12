package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class Utilities {

    public static void switchBetweenPage(WebDriver geckoDriver) {
        List<String> listOfPages = new ArrayList<>(geckoDriver.getWindowHandles());
        geckoDriver.switchTo().window(listOfPages.get(1));
    }

    public static void waitFullPage(WebDriver geckoDriver, int waitTime) {
        WebDriverWait wait = new WebDriverWait(geckoDriver, waitTime);
        wait.until((driver -> ((JavascriptExecutor) geckoDriver)
                .executeScript("return document.readyState")
                .equals("complete")));
    }

}
