import org.example.DepthFirstSearch;
import org.example.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class DepthFirstSearchTest {
    private DepthFirstSearch depthFirstSearch;
    private Node<Integer> arvore;

    @BeforeEach
    public void setUp() {
        depthFirstSearch = new DepthFirstSearch();
        arvore = new Node<Integer>(1);
    }

    @Test
    public void depthFirstSearchEncontrarNoEsperado() {
        Node<Integer> no1 = new Node<Integer>(2);
        Node<Integer> no2 = new Node<Integer>(3);
        arvore.addChild(no1);
        arvore.addChild(no2);
        no1.addChild(new Node<Integer>(5));
        no2.addChild(new Node<Integer>(7));

        int valorEsperado = 7;

        Optional<Node<Integer>> resultado = depthFirstSearch.recursiveSearch(arvore, valorEsperado);

        Assertions.assertEquals(resultado.get().getValue(), valorEsperado);
    }

    @Test
    public void nodeNullDeveRetornarOptionalObject() {
        Node nodeNull = null;

        int valorEsperado = 3;

        Optional<Node> resultado = depthFirstSearch.recursiveSearch(nodeNull, valorEsperado);
        Assertions.assertTrue(resultado.isEmpty());
    }

    @Test
    public void getVisitedDeveRetornarNosVisitados() {
        depthFirstSearch.recursiveSearch(arvore, 1);
        Assertions.assertEquals(depthFirstSearch.getVisited().get(0), 1);
        Assertions.assertEquals(depthFirstSearch.getVisited().size(), 1);
    }
}