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
import javax.swing.JOptionPane;
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
        gic2fnch.generate(producciones.get(0));
        JOptionPane.showMessageDialog(null, gic2fnch.getChomskyForm());
        
        for(String x : gic2fnch.getChomskyList()) {
            System.out.println(x);
        }
        
        gic2fnch.generate(producciones.get(1));
        JOptionPane.showMessageDialog(null, gic2fnch.getChomskyForm());
        
        for(String x : gic2fnch.getChomskyList()) {
            System.out.println(x);
        }
        
        
    } // Fin del main
    
}
