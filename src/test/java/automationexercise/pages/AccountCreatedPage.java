package automationexercise.pages;

import automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {
    private static final String url = "https://automationexercise.com/";
    private final By contaCriada = By.xpath("//p[text()='Account Created!']");
    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }
    public AccountCreatedPage abrir(){
        driver.get(url);
        return this;
    }

    public String lerContaCriada(){
        return $(contaCriada).getText().isEmpty() ? "" : $(contaCriada).getText();
    }
}
