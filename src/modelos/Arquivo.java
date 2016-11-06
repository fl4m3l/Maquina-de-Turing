package modelos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arquivo
{
    public Arquivo()
    {
        File arquivo = new File( "relatorio.txt");
        arquivo.delete();
    }
    
     public void geraRelatorio(String estadoAnterior, String elementoLindo, String estadoAtual,
                               String elementoEscrito, String movimentoDoCabecote)
    {
        String relatorio = "Estou no estado " + estadoAnterior + " e leio o simbolo " + elementoLindo +
                           " vou para o estado " + estadoAtual + ", escrevo o simbolo " + elementoEscrito + 
                           " e movimento o cabecote para a " + movimentoDoCabecote;
        try {
            FileWriter fw = new FileWriter("relatorio.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(relatorio);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
