package modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maquina
{
    private final String _estados, _simbolosDoAlfabeto, _simbolosDaFita, 
                   _codigoDoEstadoInicial, _funcaoDeTransicao;
    private final List<String> _funcaoDeTransicaoArray;
    private Fita _fita;
            
    public Maquina (String estados, String simbolosDoAlfabeto, String simbolosDaFita,
              String codigoDoEstadoInicial, String funcaoDeTransicao)
    {
        _funcaoDeTransicaoArray = new ArrayList<>();
        _estados = estados;
        _simbolosDoAlfabeto = simbolosDoAlfabeto;
        _simbolosDaFita = simbolosDaFita;
        _codigoDoEstadoInicial = codigoDoEstadoInicial;
        _funcaoDeTransicao = funcaoDeTransicao;
        _funcaoDeTransicaoArray.addAll(Arrays.asList(_funcaoDeTransicao.split(", ")));
    }
    
    private String[] funcaoDeTransicao(String estadoAtual, String elementoLido)
    {
        for (String regra : _funcaoDeTransicaoArray)
        {
            if (regra.substring(0, regra.indexOf("/")).equals(estadoAtual + "-" + elementoLido))
                return regra.substring(regra.indexOf("/") + 1).split("-");
        }
        return null;
    }
    
    public int processar(String entrada)
    {
        String estadoAtual = _codigoDoEstadoInicial, 
               movimentoDoCabecote = "";
        ArrayList<String> entradaEsaida = pseudoFita(entrada);
        int cabecote = ((entradaEsaida.size() - 1) / 2) + 1;
        Arquivo relatorio = new Arquivo();
        while (!movimentoDoCabecote.equals("H"))
        {
            String elementoLido = entradaEsaida.get(cabecote);
            String resultadoDaTransicao[] = funcaoDeTransicao(estadoAtual, elementoLido);
            String estadoAnterior = estadoAtual;
            if (resultadoDaTransicao == null)
            {
                _fita = new Fita(_estados, _simbolosDoAlfabeto, _simbolosDaFita, _codigoDoEstadoInicial, _funcaoDeTransicao, entradaEsaida);
                return 0;
            }
            estadoAtual = resultadoDaTransicao[0];
            String elementoEscrito = resultadoDaTransicao[1];
            entradaEsaida.add(cabecote, elementoEscrito);
            if (elementoLido.equals("$"))
                entradaEsaida.add("$");
            movimentoDoCabecote = resultadoDaTransicao[2];
            cabecote = movimentoDoCabecote.equals("E") ? cabecote - 1 : cabecote + 1;
            relatorio.geraRelatorio(estadoAnterior, elementoLido, estadoAtual, elementoEscrito, movimentoDoCabecote);
        }
        _fita = new Fita(_estados, _simbolosDoAlfabeto, _simbolosDaFita, _codigoDoEstadoInicial, _funcaoDeTransicao, entradaEsaida);
        return 1;
    }
    
    public ArrayList<String> pseudoFita(String entrada)
    {
        ArrayList<String> entradaEsaida = new ArrayList<>();
        char[] separada = entrada.toCharArray();
        for (char ch : separada)
            entradaEsaida.add(String.valueOf(ch));
        entradaEsaida.add("#");
        for (char ch : separada)
            entradaEsaida.add(String.valueOf(ch));
        entradaEsaida.add("$");
        return entradaEsaida;
    }
    
    public String fitaEmBinario()
    {
        return _fita.saidaEmBinario();
    }
}
