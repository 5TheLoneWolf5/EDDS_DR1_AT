import net.jqwik.api.*;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.Positive;
import org.example.CalculoIMC;
import org.example.CalculoIMCRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/*
*
* Erros funcionais:
* • Ao inserir um valor não apropriado como um número negativo ou 0, o programa retorna o valor sem aplicar validações (alta prioridade).
* • Ao inserir um tipo de dado não double como String, o programa não trata o erro e informa ao usuário; ao invés disso ele joga uma exceção, imprime informações de
* desenvolvimento e encerra o programa (média prioridade).
* Problemas de usabilidade:
* • Mensagens de erro não são intuitivas ou mesmo criadas no código (alta prioridade).
*
*/

/*
*
* O programa deveria tratar de possíveis erros do usuário, como o tipo do dado e se os valores de peso e altura são maiores que 0.0 (ou outro limite estabelecido).
* • Criar melhor mensagens de erro para o usuário.
* • Além disso, aplicar melhores práticas da programação orientada a objetos e clean code ajudaria a melhorar o estado do código; assim como torná-lo mais legível,
* facilitando possíveis expansões futuras.
*
*/

/*
*
* Regras de entrada e saída para peso e altura:
* • Regras de peso:
*   o Peso deve ser maior do que 0.5kg.
*   o Peso deve ser menor que 700kg.
*   o Tipo do dado deve ser double.
* o Dado não deve ser nulo.
* • Regras de altura:
*   o Altura deve ser maior do que 10cm.
*   o Altura deve ser menor que 3m.
*   o Tipo do dado deve ser double.
*   o Dado não deve ser nulo.
*
*/


/*
*
* Como os erros devem ser tratados (ao levantar exceções no código) e suas mensagens:
* • Erro de dado menor que 0.5kg ou 10cm.
*   o Mensagem: O dado de [peso ou altura] deve ser maior que [limite estabelecido].
* • Erro de dado maior que 700kg ou 3m.
*   o Mensagem: O dado de [peso ou altura] deve ser menor que [limite estabelecido].
* • Erro de tipo de dado inválido.
*   o Mensagem: O valor digitado deve ser um número
* • Erro nulo.
*   o Mensagem: O valor não pode ser nulo.
*
*/

public class CalculoIMCTest {
    private static CalculoIMC calculoIMC;

    @BeforeAll
    static void setUp(){
        calculoIMC = new CalculoIMC();
    }

    @Test
    @DisplayName("Classificar IMC com valor normal deve retornar 'Saudável'")
    public void classificarIMCComValorNormal() {
        double valorIMCSaudavel = 18.5;
        assertTrue(calculoIMC.classificarIMC(valorIMCSaudavel) == "Saudável");
    }

    @Test
    @DisplayName("Classificar IMC com valor extremo deve retornar 'Obesidade Grau III'")
    public void classificarIMCComValorExtremo() {
        double valorIMCObesidadeGrauIII = 50;
        assertTrue(calculoIMC.classificarIMC(valorIMCObesidadeGrauIII) == "Obesidade Grau III");
    }

    // Partições

    @Test
    @DisplayName("Calcular peso com peso válido não deve dar erro")
    public void calcularPesoComPesoValido() {
        double pesoKg = 70;
        assertDoesNotThrow(() -> calculoIMC.calcularPeso(pesoKg, 1.75));
    }

    @Test
    @DisplayName("Calcular peso com altura válida não deve dar erro")
    public void calcularPesoComAlturaValida() {
        double alturaCm = 1.75;
        assertDoesNotThrow(() -> calculoIMC.calcularPeso(70, alturaCm));
    }

    @Test
    @DisplayName("Calcular peso com peso inválido deve dar erro")
    public void calcularPesoComPesoInvalido() {
        double pesoKg = -10;
        assertThrows(IllegalArgumentException.class, () -> calculoIMC.calcularPeso(pesoKg, 1.75));
    }

    @Test
    @DisplayName("Calcular peso com altura inválida deve dar erro")
    public void calcularPesoComAlturaInvalida() {
        double alturaCm = -100;
        assertThrows(IllegalArgumentException.class, () -> calculoIMC.calcularPeso(70, alturaCm));
    }

    // Limítrofes

    @Test
    @DisplayName("Calcular peso abaixo do limite de peso de 0.5kg deve dar erro")
    public void calcularPesoComAlturaAbaixoDoLimite() {
        assertThrows(IllegalArgumentException.class, () -> calculoIMC.calcularPeso(0.4, 1.90));
    }

    @Test
    @DisplayName("Calcular peso abaixo do limite de altura de 20cm deve dar erro")
    public void calcularPesoComPesoAbaixoDoLimite() {
        assertThrows(IllegalArgumentException.class, () -> calculoIMC.calcularPeso(70, 0.19));
    }

    @Test
    @DisplayName("Calcular peso dentro do limite de peso entre (ou igual a) 0.5kg e 700kg deve funcionar")
    public void calcularPesoComAlturaDentroDoLimite() {
        double resultadoEsperado = 166.20498615;
        assertEquals(resultadoEsperado, calculoIMC.calcularPeso(600, 1.90), 0.000000001);
    }

    @Test
    @DisplayName("Calcular peso dentro do limite de altura entre (ou igual a) 20cm e 300cm deve funcionar")
    public void calcularPesoComPesoDentroDoLimite() {
        double resultadoEsperado = 21.4691504822;
        assertEquals(resultadoEsperado, calculoIMC.calcularPeso(65, 1.74), 0.000000001);
    }

    @Test
    @DisplayName("Calcular peso acima do limite de peso de 700kg deve dar erro")
    public void calcularPesoComAlturaAcimaDoLimite() {
        assertThrows(IllegalArgumentException.class, () -> calculoIMC.calcularPeso(701, 1.55));
    }

    @Test
    @DisplayName("Calcular peso acima do limite de altura de 300cm deve dar erro")
    public void calcularPesoComPesoAcimaDoLimite() {
        assertThrows(IllegalArgumentException.class, () -> calculoIMC.calcularPeso(72, 301));
    }

    @Property
    public void imcNuncaDeveSerNegativo(@ForAll @Positive double peso, @ForAll @Positive double altura) {
        double imc = calculoIMC.calcularPeso(peso, altura);
        assertTrue(imc >= 0);
    }

    @Property
    public void passarAlturaForaDe20cmE300cmDeveDarErro(@ForAll @Positive double peso, @ForAll("alturasExtremas") double altura) {
        assertThrows(IllegalArgumentException.class, () -> calculoIMC.calcularPeso(peso, altura));
    }

    @Provide
    Arbitrary<Double> alturasExtremas() {
        return Arbitraries.of(0.5, 350.00, 0.15);
    }

    @Property(tries = 200)
    public void passarPesoForaDe05kgE300kgDeveDarErro(@ForAll("pesosExtremos") double peso, @ForAll @Positive double altura) {
        assertThrows(IllegalArgumentException.class, () -> calculoIMC.calcularPeso(peso, altura));
    }

    @Provide
    Arbitrary<Double> pesosExtremos() {
        return Arbitraries.oneOf(
                Arbitraries.doubles().lessOrEqual(0.4),
                Arbitraries.doubles().greaterOrEqual(701)
        );
    }

    @Property
    void imcDeveRetornarValorMaiorQue0(@ForAll double peso, @ForAll double altura) {
        System.out.println("Peso: " + peso + " - Altura: " + altura);
        assertTrue(calculoIMC.calcularPeso(peso, altura) > 0);
    }

    @Test
    @DisplayName("Salvar IMC anônimo de usuário deve funcionar")
    void salvarIMCdoUsuarioDeveFuncionar() {
        CalculoIMCRepository calculoRepository = Mockito.mock(CalculoIMCRepository.class);
        Mockito.doNothing().when(calculoRepository).salvarIMCUsuario(20.0);
    }

    @Example
    public void passarPesoEntre05kgE300kgDeveFuncionar(@ForAll @DoubleRange(min=0.5, max=700.00) double peso, @ForAll @Positive double altura) {
        assertDoesNotThrow(() -> calculoIMC.calcularPeso(peso, altura));
    }

}