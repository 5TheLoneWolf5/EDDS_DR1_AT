package automationexercise.pagesLogin;

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
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public LoginPage abrir(){
        driver.get(URL);
        return this;
    }
    public LoginPage prencherEmail(String usuario) {
        type(email, usuario);
        return this;
    }
    public LoginPage prencherSenha(String senha) {
        type(password, senha);
        return this;
    }
    public LoginPage submeterLogin(){
        click(btnLogin);
        return this;
    }

    public String lerErro(){
        return $(error).getText().isEmpty() ? "" : $(error).getText();
    }
    public LoginPage esperarAteAparecerError(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(error));
        return this;
    }


}