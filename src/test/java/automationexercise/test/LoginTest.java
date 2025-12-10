package automationexercise.test;

/*
*
* Não houveram falhas nos testes, portanto, não foi capturado screenshots.
* *
*/

import automationexercise.core.BaseTest;
import automationexercise.pages.HomePage;
import automationexercise.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class LoginTest extends BaseTest {
    @Test
    public void loginComUsuarioErrado(){
        LoginPage retorno = new LoginPage(driver)
                .abrir()
                .prencherEmail("teste@teste.com")
                .prencherSenha("123456")
                .submeterLoginErrado()
                .esperarAteAparecerError();
        assertThat(retorno.lerErro(), containsString("Your email or password is incorrect!"));
    }
    @Test
    public void loginComUsuarioCerto(){
        HomePage retorno = new LoginPage(driver)
                .abrir()
                .prencherEmail("conta_nova_teste@teste.com")
                .prencherSenha("12345678")
                .submeterLoginCerto()
                .esperarAteLogoutAparecer();
        assertThat(retorno.lerLogout(), containsString("Logout"));
    }
}