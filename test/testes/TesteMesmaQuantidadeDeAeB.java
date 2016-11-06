package testes;

import modelos.Maquina;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TesteMesmaQuantidadeDeAeB
{    
    private Maquina mt;
    
    @Before
    public void criarMT()
    {
        String estados = "q0, q1, q2, q3, q4";
        String simbolosDoAlfabeto = "a, b";
        String simbolosDaFita = "a, b, x, y, $";
        String estadoInicial = "q0";
        String funcaoDeTransicao = "q0-a/q1-x-D, q1-a/q1-a-D, q1-y/q1-y-D, q1-b/q2-y-E, "
        + "q2-y/q2-y-E, q2-a/q2-a-E, q2-x/q0-x-D, q0-y/q3-y-D, q3-y/q3-y-D, q3-$/q4-$-H";
        mt = new Maquina(estados, simbolosDoAlfabeto, simbolosDaFita, estadoInicial, funcaoDeTransicao);
    }
    
    @Test
    public void reconhecePalavraComSucesso()
    {
        assertEquals(1, mt.processar("aabb"));
    }
    
    @Test
    public void falhaAoReconhecerPalavraComSucesso()
    {
        assertEquals(0, mt.processar("aaabb"));
    }
}
