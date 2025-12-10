import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;

import net.jqwik.api.constraints.Size;
import org.example.MathFunctions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathFunctionsTest {

    @Property
    public void multiplyByTwoDeveSerSemprePar(@ForAll @Positive int number) {
        assertTrue(MathFunctions.MultiplyByTwo(number) % 2 == 0);
    }

    @Property
    public void generateMultiplicationTableDeveGerarMultiplosDoNumero(@ForAll @Positive @IntRange(max=1000) int number, @ForAll @IntRange(min=5, max=20) int limit) {
        int[] multiplicationTable = MathFunctions.GenerateMultiplicationTable(number, limit);
        for (int value : multiplicationTable) {
            assertTrue(value % number == 0);
        }
    }

    @Property(tries = 100)
    public void isPrimeNumeroNaoPodeTerDivisoresAlemDe1EOMesmoNumero(@ForAll @Positive int number) {
        if (MathFunctions.IsPrime(number)) {
            assertTrue(number > 1);
            for (int i = 2; i < number; i++) {
                assertTrue(number % i != 0);
            }
        }
    }

    @Property
    public void calculateAverageDeveRetornarResultadoEntreMenorEMaiorValor(@ForAll @Size(min=3, max=30) int[] numeros) {
        OptionalDouble mediaResultado = MathFunctions.CalculateAverage(numeros);
        List<Integer> listaDeNumeros = Arrays.stream(numeros).boxed().toList();
        if (mediaResultado.isPresent()) {
            int mediaResultadoInteiro = (int) mediaResultado.getAsDouble();
            assertTrue(mediaResultadoInteiro > Collections.min(listaDeNumeros)
                    && mediaResultadoInteiro < Collections.max(listaDeNumeros));
        }
    }
}