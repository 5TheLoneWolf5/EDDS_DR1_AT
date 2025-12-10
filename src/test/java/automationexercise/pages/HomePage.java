package automationexercise.pages;

import automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private static final String URL = "https://automationexercise.com/";
    private final By logout = By.xpath("//a[@href='/logout']");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public HomePage abrir(){
        driver.get(URL);
        return this;
    }

    public String lerLogout(){
        return $(logout).getText().isEmpty() ? "" : $(logout).getText();
    }

    public HomePage esperarAteLogoutAparecer(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logout));
        return this;
    }
}
