package modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maquina {
    private final String _estados, _simbolosDoAlfabeto, _simbolosDaFita, 
                   _codigoDoEstadoAtual, _funcaoDeTransicao;
    private final List<String> _funcaoDeTransicaoArray;
    private Fita _fita;
            
    public Maquina (String estados, String simbolosDoAlfabeto, String simbolosDaFita,
              String codigoDoEstadoInicial, String funcaoDeTransicao) {
        _funcaoDeTransicaoArray = new ArrayList<>();
        _estados = estados;
        _simbolosDoAlfabeto = simbolosDoAlfabeto;
        _simbolosDaFita = simbolosDaFita;
        _codigoDoEstadoAtual = codigoDoEstadoInicial;
        _funcaoDeTransicao = funcaoDeTransicao;
        _funcaoDeTransicaoArray.addAll(Arrays.asList(_funcaoDeTransicao.split(", ")));
    }
    
    private String[] funcaoDeTransicao(String estadoAtual, String elementoLido) {
        for (String regra : _funcaoDeTransicaoArray)
            if (regra.substring(0, regra.indexOf("/")).equals(estadoAtual + "-" + elementoLido))
                return regra.substring(regra.indexOf("/") + 1).split("-");
        return null;
    }
    
    public int processar(String entrada) {
        String estadoAtual = _codigoDoEstadoAtual, movimentoDoPonteiro = "";
        ArrayList<String> saida = saidaEmArray(entrada);
        int ponteiro = 1;
        Arquivo relatorio = new Arquivo();
        while (naoEhHalt(movimentoDoPonteiro)) {
            String simboloLido = saida.get(ponteiro), estadoAnterior = estadoAtual, 
            resultadoDaFuncaoDeTransicao[] = funcaoDeTransicao(estadoAtual, simboloLido), simboloEscrito;
            if (simboloLido.equals("$"))    
                saida.add("$");
            if (resultadoDaFuncaoDeTransicao == null) {
                criarFita(entrada, saida);
                return 0;
            }
            estadoAtual = resultadoDaFuncaoDeTransicao[0];
            simboloEscrito = resultadoDaFuncaoDeTransicao[1];
            movimentoDoPonteiro = resultadoDaFuncaoDeTransicao[2];
            saida.remove(ponteiro);
            saida.add(ponteiro, simboloEscrito);
            ponteiro += movimentoDoPonteiro.equals("E") ? - 1 : 1;
            relatorio.geraRelatorio(estadoAnterior, simboloLido, estadoAtual, simboloEscrito, movimentoDoPonteiro);
        }
        criarFita(entrada, saida);
        return 1;
    }

    private void criarFita(String entrada, ArrayList<String> saida) {
        _fita = new Fita(_estados, _simbolosDoAlfabeto, _simbolosDaFita, _codigoDoEstadoAtual, 
                         _funcaoDeTransicao, entrada, saida);
    }

    private static boolean naoEhHalt(String movimentoDoPonteiro) {
        return !movimentoDoPonteiro.equals("H");
    }
    
    private ArrayList<String> saidaEmArray(String entrada) {
        ArrayList<String> saida = new ArrayList<>();
        for (int i = 0; i < entrada.length(); i++)
            saida.add(entrada.substring(i, i + 1));
        saida.add(0, "#");
        saida.add("$");
        return saida;
    }
    
    public String fitaEmBinario() {
        return _fita.saidaEmBinario();
    }
}
