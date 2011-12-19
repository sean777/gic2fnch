/*  Aplicación que transforma una gramática independiente del contexto en
 *  Forma normal de Chomsky
 *  @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com>
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import Tools.*;

/**
 * Aplicación de prueba del algoritmo.
 * @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com>
 */
public class Main {
    public static void main(String[] args) {
        
        ArrayList<String> producciones = new ArrayList<String>();
        try {
            FileInputStream fstream = new FileInputStream("gic.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                producciones.add(strLine);
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        Gic2FnCH gic2fnch = new Gic2FnCH();
        for(byte i = 0; i < producciones.size(); i++) {
            System.out.println("Chomsky para la gic [ " + producciones.get(i) + " ]");
            gic2fnch.generate(producciones.get(i));
            System.out.println("Resultado: " + gic2fnch.getChomskyForm());
            System.out.println("Producciones: ");
            for(String s : gic2fnch.getChomskyList()) {
                System.out.println(s);
            }
            System.out.println("--------------------------------------------------------\n");
        }
        
    }
    
}
