package ru.yandex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    protected WebDriver geckoDriver;

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.gecko.driver", System.getenv("GECKO_DRIVER"));
        geckoDriver = new FirefoxDriver();

        geckoDriver.manage().window().maximize();

    }

    @AfterEach
    public void after() {
        //geckoDriver.quit();
    }
}
