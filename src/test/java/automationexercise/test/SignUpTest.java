package automationexercise.test;

/*
 *
 * Não houveram falhas nos testes, portanto, não foi capturado screenshots.
 * *
 */

import automationexercise.core.BaseTest;
import automationexercise.pages.AccountCreatedPage;
import automationexercise.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class SignUpTest extends BaseTest {
    @Test
    public void registroComUsuarioCerto(){
        AccountCreatedPage accountCreatedPage = new LoginPage(driver)
                .abrir()
                .prencherNomeSignUp("12345678")
                .prencherEmailSignUp("conta_nova_teste@teste.com")
                .submeterSignUp()
                .marcarTitle()
                .typePassword()
                .pickDateOfBirth()
                .typeFirstName()
                .typeLastName()
                .typeAddress()
                .typeState()
                .typeCity()
                .typeZipcode()
                .submeterSignUp();
        assertThat(accountCreatedPage.lerContaCriada(), containsString("Account Created!"));
    }
}