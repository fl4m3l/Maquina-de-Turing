package modelos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal {

    public static void main(String[] args) 
    {
        Maquina maq = new Maquina("q0, q1, q2, q3, q4", "a, b", "a, b, x, y, $", "q0", 
        "q0-a/q1-x-D, q1-a/q1-a-D, q1-y/q1-y-D, q1-b/q2-y-E, "
      + "q2-y/q2-y-E, q2-a/q2-a-E, q2-x/q0-x-D, q0-y/q3-y-D, q3-y/q3-y-D, q3-$/q4-$-H");
        System.out.println(maq.processar("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"));
        try {
            Runtime.getRuntime().exec("gedit relatorio.txt");
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Fita em binario -> " + maq.fitaEmBinario());
    }
    
}
