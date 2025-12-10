package automationexercise.test;

/*
*
* Não houveram falhas nos testes, portanto, não foram capturados screenshots.
*
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
                .prencherNomeSignUp("Adam Stone")
                .prencherEmailSignUp("conta_nova_teste@teste.com")
                .submeterSignUp()
                .selectTitle("Mr")
                .typePassword("12345678")
                .pickDateOfBirth("3", "May", "1998")
                .typeFirstName("Adam")
                .typeLastName("Stone")
                .typeAddress("Times Square N10")
                .selectCountry("United States")
                .typeState("New York")
                .typeCity("New York City")
                .typeZipcode("8473292553")
                .typeMobileNumber("5431-3593")
                .submeterSignUp()
                .esperarAteAccountCreatedAparecer();
        assertThat(accountCreatedPage.lerContaCriada(), containsString("ACCOUNT CREATED!"));
    }
}