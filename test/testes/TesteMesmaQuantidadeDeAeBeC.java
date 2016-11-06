package testes;

import modelos.Maquina;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TesteMesmaQuantidadeDeAeBeC {
    
    private Maquina mt;
    
    @Before
    public void criarMT() {
        String estados = "q0, q1, q2, q3, q4, q5";
        String simbolosDoAlfabeto = "a, b, c";
        String simbolosDaFita = "a, b, x, y, z, $";
        String estadoInicial = "q0";
        String funcaoDeTransicao = "q0-a/q1-x-D, q1-a/q1-a-D, q1-b/q2-y-D, q2-b/q2-b-D, q2-c/q3-z-E, "
                + "q3-b/q3-b-E, q3-y/q3-y-E, q3-a/q3-a-E, q3-x/q0-x-D, q1-y/q1-y-D, q2-z/q2-z-D, q3-z/q3-z-E, "
                + "q0-y/q4-y-D, q4-y/q4-y-D, q4-z/q4-z-D, q4-$/q5-$-H";
        mt = new Maquina(estados, simbolosDoAlfabeto, simbolosDaFita, estadoInicial, funcaoDeTransicao);
    }
    
    @Test
    public void reconhecePalavraComSucesso() {
        assertEquals(1, mt.processar("aaaabbbbcccc"));
    }
    
    @Test
    public void falhaAoReconhecerPalavraComSucesso() {
        assertEquals(0, mt.processar("aaabbbcc"));
    }
    
}
