package automationexercise.pages;

import automationexercise.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private static final String URL = "https://automationexercise.com/login";
    private final By email =  By.cssSelector("[data-qa='login-email']");
    private final By password = By.cssSelector("[data-qa='login-password']");
    private final By btnLogin = By.cssSelector("[data-qa='login-button']");
    private final By error = By.xpath("//p[text()='Your email or password is incorrect!']");
    private final By signUpEmail =  By.cssSelector("[data-qa='signup-email']");
    private final By signUpName = By.cssSelector("[data-qa='signup-name']");
    private final By btnSignUp = By.cssSelector("[data-qa='signup-button']");
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public LoginPage abrir(){
        driver.get(URL);
        return this;
    }
    public LoginPage prencherEmail(String _email) {
        type(email, _email);
        return this;
    }
    public LoginPage prencherSenha(String senha) {
        type(password, senha);
        return this;
    }
    public LoginPage submeterLoginErrado(){
        click(btnLogin);
        return this;
    }

    public HomePage submeterLoginCerto(){
        click(btnLogin);
        return new HomePage(driver);
    }

    public String lerErro(){
        return $(error).getText().isEmpty() ? "" : $(error).getText();
    }
    public LoginPage esperarAteAparecerError(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(error));
        return this;
    }

    public LoginPage prencherNomeSignUp(String signUpNome) {
        type(signUpName, signUpNome);
        return this;
    }
    public LoginPage prencherEmailSignUp(String _signUpEmail) {
        type(signUpEmail, _signUpEmail);
        return this;
    }
    public SignUpPage submeterSignUp(){
        click(btnSignUp);
        return new SignUpPage(driver);
    }

}