package automationexercise.pages;

import automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountCreatedPage extends BasePage {
    private static final String url = "https://automationexercise.com/account_created";
    private final By contaCriada = By.xpath("//b[text()='Account Created!']");
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

    public AccountCreatedPage esperarAteAccountCreatedAparecer(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(contaCriada));
        return this;
    }
}
