/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import modelos.Maquina;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author fl4
 */
public class TesteSomaDoisNumeros
{    
    private Maquina mt;
    
    @Before
    public void criarMT()
    {
        String estados = "q0, q1, q2, q3, q4";
        String simbolosDoAlfabeto = "1";
        String simbolosDaFita = "1, 0, $";
        String estadoInicial = "q0";
        String funcaoDeTransicao = "q0-1/q0-1-D, q0-0/q1-1-D, q1-1/q1-1-D, q1-$/q2-$-E, "
        + "q2-1/q3-$-E, q3-1/q3-1-E, q3-#/q4-#-H";
        mt = new Maquina(estados, simbolosDoAlfabeto, simbolosDaFita, estadoInicial, funcaoDeTransicao);
    }
    
    @Test
    public void reconhecePalavraComSucesso()
    {
        assertEquals(1, mt.processar("1101"));
    }
    
}
