import org.example.Address;
import org.example.ViaCEP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
*
* Foi utilizado o particionamento e seguinte limites:
* O campo UF só deve ter 2 caracteres.
* Logradouro não pode ser maior do que 100 caracteres.
* Cidade não pode ser maior do que 50 caracteres.
*
* Foram utilizados estes particionamentos levando em conta o potencial granded e extensão que o logradouro pode ter,
* e o máximo de cobertura possível de qualquer cidade brasileira.
* Todas as UFs são 2 caracteres apenas. Nada mais, nada menos.
*
*/

/*
*
* - Tabela de decisão:
*
* +---------------------------------------+----+----+----+----+
* |               Condições               | R1 | R2 | R3 | R4 |
* +---------------------------------------+----+----+----+----+
* | UF diferente de 2 caracteres          | T  | -  | -  | F  |
* | Logradouro com mais de 100 caracteres | -  | T  | -  | F  |
* | Cidade com mais de 50 caracteres      | -  | -  | T  | F  |
* +---------------------------------------+----+----+----+----+
* |                 Ações                 |    |    |    |    |
* +---------------------------------------+----+----+----+----+
* | Jogar IllegalArgumentException        | T  | T  | T  | F  |
* | Retornar Endereços                    | F  | F  | F  | T  |
* +---------------------------------------+----+----+----+----+
*
*/

public class ViaCEPTest {

    @Test
    public void getAddressByCEPDeveDarErroComCEPInvalidos() {
        // CEP com letras
        Assertions.assertThrows(IllegalArgumentException.class, () -> ViaCEP.getAddressByCEP("abc"));
        // CEP vazio
        Assertions.assertThrows(IllegalArgumentException.class, () -> ViaCEP.getAddressByCEP(""));
        // CEP com números inválidos
        Assertions.assertFalse(ViaCEP.getAddressByCEP("8437177365654375").isPresent());
    }

    @Test
    public void getAddressesByAddressDeveRetornarComEnderecos() {
        Address[] endereco = ViaCEP.getAddressesByAddress("SP", "Sao%20Paulo", "Avenida%20Paulista");
        Assertions.assertTrue(endereco.length > 0);
        Assertions.assertFalse(endereco[0].getCep().isEmpty());
    }

    @Test
    public void obterEnderecoComUFDeTamanhoDiferenteDe2DeveDarErro() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ViaCEP.getAddressesByAddress("RSSS",
                "Porto%20Alegre",
                "Avenida%20Assis%20Brasil"));
    }

    @Test
    public void obterEnderecoComLogradouroVazioDeveDarErro() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ViaCEP.getAddressesByAddress("RS",
                "Porto%20Alegre", ""));
    }

    @Test
    public void obterEnderecoComCidadeAcimaDe50CaracteresDeveDarErro() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ViaCEP.getAddressesByAddress("RS",
                "Cidade%20Com%20Muitos,%20Muitos,%20Muitos%20e%20Muitos%20Caracteres",
                "Avenida%20Assis%20Brasil"));
    }

    @Test
    public void obterEnderecoComLogradouroAcimaDe100CaracteresDeveDarErro() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> ViaCEP.getAddressesByAddress("RS",
                "Porto%20Alegre",
                "Logradouro%20Com%20Muitos,%20Muitos,%20Muitos,%20Muitos,%20Muitos,%20Muitos,%20Muitos,%20Muitos,%20Muitos,%20Muitos,%20Muitos%20e%20Muitos%20Caracteres"));
    }

}