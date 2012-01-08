/*  Aplicación que transforma una gramática independiente del contexto en
 *  Forma normal de Chomsky
 *  @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com>
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import Tools.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Aplicación de prueba del algoritmo.
 * @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com>
 * 
 * Los resultados se muestran en el archivo "out.txt"
 * 
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
        try {
            File fileOut = new File("out.txt");
            FileWriter fw = new FileWriter(fileOut);
            
            for(byte i = 0; i < producciones.size(); i++) {
                fw.write("Chomsky para la gic [ " + producciones.get(i) + " ]\n");
                gic2fnch.generate(producciones.get(i));
                fw.write("Resultado: " + gic2fnch.getChomskyForm() + "\n");
                fw.write("Producciones: \n");
                for(String s : gic2fnch.getChomskyList()) {
                    fw.write(s + "\n");
                }
                fw.write("--------------------------------------------------------\n");
            }
            
            fw.flush();
            fw.close();
            
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
