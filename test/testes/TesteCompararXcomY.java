package testes;

import modelos.Maquina;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TesteCompararXcomY
{    
    private Maquina mt;
    
    @Before
    public void criarMT()
    {
        String estados = "q0, q1, q2, q3, q4, qf, qv, q>=, q<";
        String simbolosDoAlfabeto = "1, 0";
        String simbolosDaFita = "1, 0, x, y, $";
        String estadoInicial = "q0";
        String funcaoDeTransicao = "q0-1/q1-x-D, q0-0/q4-0-D, q1-1/q1-1-D, q1-0/q2-0-D, "
        + "q2-1/q3-y-E, q2-y/q2-y-D, q2-$/qv-$-E, q3-y/q3-y-E, q3-0/q3-0-E, q3-1/q3-1-E, "
        + "q3-x/q0-x-D, q4-y/q4-y-D, q4-1/qf-1-E, q4-$/qv-$-E, qf-0/qf-0-E, qf-y/qf-1-E, "
        + "qf-x/qf-1-E, qf-#/q<-#-H, qv-y/qv-1-E, qv-x/qv-1-E, qv-0/qv-0-E, qv-1/qv-1-E, "
        + "qv-#/q>=-#-H";
        mt = new Maquina(estados, simbolosDoAlfabeto, simbolosDaFita, estadoInicial, funcaoDeTransicao);
    }
    
    @Test
    public void reconhecePalavraComSucessoXMaiorQueY()
    {
        /* 
        x = 1111 = 4  
        y = 11 = 2
        */
        assertEquals(1, mt.processar("1111011"));
    }
    
    @Test
    public void reconhecePalavraComSucessoXIgualAY()
    {
        /* 
        x = 111 = 3  
        y = 111 = 3
        */
        assertEquals(1, mt.processar("1110111"));
    }
    
    @Test
    public void reconhecePalavraComSucessoXMenorQueY()
    {
        /* 
        x = 111 = 3  
        y = 111111 = 6
        */
        assertEquals(1, mt.processar("1110111111"));
    }
    
}
