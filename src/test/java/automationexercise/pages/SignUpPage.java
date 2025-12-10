package automationexercise.pages;

import automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SignUpPage extends BasePage {
    private static final String url = "https://automationexercise.com/signup";
    private final By firstName =  By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By password = By.cssSelector("[data-qa='password']");
    private final By selectDays = By.cssSelector("[data-qa='days']");
    private final By selectMonths = By.cssSelector("[data-qa='months']");
    private final By selectYears = By.cssSelector("[data-qa='years']");
    private final By address = By.cssSelector("[data-qa='address']");
    private final By selectCountries = By.cssSelector("[data-qa='country']");
    private final By state = By.cssSelector("[data-qa='state']");
    private final By city = By.cssSelector("[data-qa='city']");
    private final By zipcode = By.cssSelector("[data-qa='zipcode']");
    private final By mobileNumber = By.cssSelector("[data-qa='mobile_number']");
    private final By btnSignUp =  By.cssSelector("[data-qa='create-account']");;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }
    public SignUpPage abrir(){
        driver.get(url);
        return this;
    }

    public SignUpPage selectTitle(String titulo){
        WebElement titleButton = $(By.xpath("//input[@value='" + titulo + "' and @name='title']"));

        if (!titleButton.isSelected()) {
            titleButton.click();
        }

        return this;
    }

    public SignUpPage typePassword(String senha){
        type(password, senha);
        return this;
    }

    public SignUpPage pickDateOfBirth(String dia, String mes, String ano){
        WebElement days = $(selectDays);
        new Select(days).selectByVisibleText(dia);

        WebElement months = $(selectMonths);
        new Select(months).selectByVisibleText(mes);

        WebElement years = $(selectYears);
        new Select(years).selectByVisibleText(ano);

        return this;
    }

    public SignUpPage typeFirstName(String primeiroNome){
        type(firstName, primeiroNome);
        return this;
    }

    public SignUpPage typeLastName(String ultimoNome){
        type(lastName, ultimoNome);
        return this;
    }

    public SignUpPage typeAddress(String endereco){
        type(address, endereco);
        return this;
    }

    public SignUpPage selectCountry(String pais){
        WebElement countries = $(selectCountries);
        new Select(countries).selectByVisibleText(pais);
        return this;
    }

    public SignUpPage typeState(String estado){
        type(state, estado);
        return this;
    }

    public SignUpPage typeCity(String cidade){
        type(city, cidade);
        return this;
    }

    public SignUpPage typeZipcode(String _zipcode){
        type(zipcode, _zipcode);
        return this;
    }

    public SignUpPage typeMobileNumber(String numeroCelular){
        type(mobileNumber, numeroCelular);
        return this;
    }

    public AccountCreatedPage submeterSignUp(){
        click(btnSignUp);
        return new AccountCreatedPage(driver);
    }
}