/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 17, 2011 */

package Tools;

import java.util.ArrayList;

/**
 * Algoritmo que teniendo una cadena en la forma
 * S->a|aaB|Bab, devuelve la forma normal de Chomsky correspondiente.
 * @author Leonardo Gutiérrez Ramírez | leogutierrezramirez@gmail.com <a href="mailto:leogutierrezramirez@gmail.com">Leonardo Gutiérrez Ramírez</a>
 * @version 1.0
 */

public class Gic2FnCH {
    
    private StringBuilder chomsky;
    private String parteA;
    private String parteB;
    private StringBuilder finalS;
    private ArrayList<String> chomskyList = null;
    
    /**
     * Constructor predeterminado.
     */
    public Gic2FnCH() {
        chomsky = new StringBuilder("");
        finalS = new StringBuilder("");
        parteA = "";
        parteB = "";
        chomskyList = new ArrayList<String>();
    }
    
    /**
     * El siguiente método genera la forma normal de Chomsky basado en un String.
     * Para ver el resultado ejecute el método getChomskyForm().
     * 
     * {.*} - Simbolo NO Terminal no definido, es decir, que no tiene FNCH
     * [a-z] - Terminal
     * 
     * @param gic 
     */
    public void generate(String gic) {
        
        chomsky.delete(0, chomsky.length());
        ArrayList<Elemento> list = new ArrayList<Elemento>();
        ArrayList<String> definidos = new ArrayList<String>();
        Tabla tabla = new Tabla();
        
        for(String s : Tools.getCases(gic).split("\\|")) {
            
            if(Tools.isChomsky(s)) {
                
                chomsky.append(s).append("|");
                
            } else {
                parteA = s.substring(0, s.length()/2);
                parteB = s.substring(s.length()/2, s.length());
                
                if(Tools.isTerminal(parteA)) {
                    
                    chomsky.append(parteA);
                    
                } else {
                    
                    chomsky.append("{").append(parteA).append("}");
                    if(!tabla.tabla.containsKey("{" + parteA + "}")) {
                        tabla.put("{" + parteA + "}", new Elemento("{" + parteA + "}", false));
                        list.add(new Elemento("{" + parteA + "}", false));
                    }
                    
                }
                
                if(Tools.isTerminal(parteB)) {
                    chomsky.append(parteB).append("|");
                } else {
                    
                    chomsky.append("{").append(parteB).append("}");
                    
                    if(!tabla.tabla.containsKey("{" + parteB + "}")) {
                        tabla.put("{" + parteB + "}", new Elemento("{" + parteB + "}", false));
                        list.add(new Elemento("{" + parteB + "}", false));
                    }
                }
            }
        }
        
        StringBuilder temp = new StringBuilder(chomsky.substring(0, chomsky.length() - 1));
        chomsky.delete(0, chomsky.length());
        chomsky.append(temp.toString());
        
        setChomsky(Tools.getNameProduction(gic) + " --> " + chomsky);
        
        int i = 0;
        while(isListFull(list) == false) {
            
                finalS.delete(0, finalS.length());
                parteA = "";
                parteB = "";
            
                if(Tools.isChomsky(Tools.getStringBtwn(list.get(i).getChomskyStr()))) {
                
                    // Agregar a los definidos
                    definidos.add(list.get(i).getChomskyStr() + "->" + Tools.getStringBtwn(list.get(i).getChomskyStr()));
                    list.get(i).setDefined(true);
                
                } else {
                
                    list.get(i).setDefined(true);
                    parteA = Tools.getStringBtwn(list.get(i).getChomskyStr()).substring(0, Tools.getStringBtwn(list.get(i).getChomskyStr()).length() / 2);
                    parteB = Tools.getStringBtwn(list.get(i).getChomskyStr()).substring(Tools.getStringBtwn(list.get(i).getChomskyStr()).length() / 2, Tools.getStringBtwn(list.get(i).getChomskyStr()).length());
                    
                    if(Tools.isTerminal(parteA)) {
                        finalS.append(parteA);
                    } else {
                        
                        finalS.append("{").append(parteA).append("}");
                        
                        if(!tabla.tabla.containsKey("{" + parteA + "}")) {
                        
                            tabla.put("{" + parteA + "}", new Elemento("{" + parteA + "}", false));
                            list.add(new Elemento("{" + parteA + "}", false));
                            
                        }
                    }
                
                    if(Tools.isTerminal(parteB)) {
                        finalS.append(parteB);
                    } else {
                        
                        finalS.append("{").append(parteB).append("}");
                        
                        if(!tabla.tabla.containsKey("{" + parteB + "}")) {
                            
                            tabla.put("{" + parteB + "}", new Elemento("{" + parteB + "}", false));
                            list.add(new Elemento("{" + parteB + "}", false));
                            
                        }
                    }
                    
                    definidos.add(list.get(i).getChomskyStr() + "->" + finalS);
                    
                }
            i = getIndex(list);
            
        }
        setChomskyList(definidos);
    }
    
    private void setChomsky(String s) {
        this.chomsky = new StringBuilder(s);
    }
    
    private void setChomskyList(ArrayList<String> definidos) {
        this.chomskyList = definidos;
    }
    
    /**
     * Devuelve las definiciones basándose en la forma normal de chomsky generada
     * @return ArrayList<String>
     */
    public ArrayList<String> getChomskyList() {
        return chomskyList;
    }
    
    /**
     * Devuelve la forma normal de chomsky después de haberla generado con el método generate().
     * @return String
     */
    public String getChomskyForm() {
        return chomsky.toString();
    }
    
    private static boolean isListFull(ArrayList<Elemento> lista) {
        for(byte i = 0; i < lista.size(); i++) {
            if(lista.get(i).getDefined() == false) {
                return false;
            }
        }
        return true;
    }
    
    private static int getIndex(ArrayList<Elemento> lista) {
        for(byte i = 0; i < lista.size(); i++) {
            if(lista.get(i).getDefined() == false) {
                return i;
            }
        }
        return -1;
    }
}
