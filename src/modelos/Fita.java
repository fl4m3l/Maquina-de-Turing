package modelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fita {
    private ArrayList<String> _fita;
    
    public Fita(String estados, String simbolosDoAlfabeto, String simbolosDaFita,
              String codigoDoEstadoInicial, String funcaoDeTransicao, String entrada, 
              ArrayList<String> saida) {
       _fita = new ArrayList<>();
       _fita.addAll(Arrays.asList(estados.split(", ")));
       _fita.add("#");
       _fita.addAll(Arrays.asList(simbolosDoAlfabeto.split(", ")));
       _fita.add("#");
       _fita.addAll(Arrays.asList(simbolosDaFita.split(", ")));
       _fita.add("#");
       _fita.add(codigoDoEstadoInicial);
       _fita.add("#");
       for (String str : funcaoDeTransicao.replace(" ", "").split("[-]|[/]|[, ]")) {
           _fita.add(str);
           if (str.equals("D") || str.equals("E"))
                _fita.add("#");
       }
       _fita.add("#");
       char entradaSeparada[] = entrada.toCharArray();
       for (char ch : entradaSeparada)
           _fita.add(String.valueOf(ch));
       for (String str : saida)
            _fita.add(str);
        System.out.println(_fita);
    }
    
    public String saidaEmBinario() {
        String binario = "";
        for (String str : _fita)
            binario += converterEmBinario(str);
        return binario;
    }
    
    private String converterEmBinario(String elemento) {
        byte bytes[] = elemento.getBytes();
        String binario = "";
        for (byte b : bytes)
            binario += Integer.toBinaryString(b);
        return binario;
    }
}
