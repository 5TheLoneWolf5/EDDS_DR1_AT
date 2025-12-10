package automationexercise.pages;

import automationexercise.core.BasePage;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {
    private static final String url = "https://automationexercise.com/signup";

    public SignUpPage(WebDriver driver) {
        super(driver);
    }
    public SignUpPage abrir(){
        driver.get(url);
        return this;
    }

    public SignUpPage marcarTitle(){
        driver.get(url);
        return this;
    }

    public SignUpPage typePassword(){
        driver.get(url);
        return this;
    }

    public SignUpPage pickDateOfBirth(){
        driver.get(url);
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

    public SignUpPage selectCountry(){
        driver.get(url);
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