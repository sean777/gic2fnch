/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 17, 2011 */

package Tools;

import java.util.ArrayList;

public class Gic2FnCH {
    
    private String chomsky;
    private String parteA;
    private String parteB;
    private String finalS;
    private ArrayList<String> chomskyList = null;
    
    public Gic2FnCH() {
        chomsky = "";
        parteA = "";
        parteB = "";
        finalS = "";
        chomskyList = new ArrayList<String>();
    }
    
    public void generate(String gic) {
        
        chomsky = "";
        ArrayList<Elemento> list = new ArrayList<Elemento>();
        ArrayList<String> definidos = new ArrayList<String>();
        Tabla tabla = new Tabla();
        
        for(String s : Tools.getCases(gic).split("\\|")) {
            if(Tools.isChomsky(s)) {
                chomsky += s + "|";
            } else {
                parteA = s.substring(0, s.length()/2);
                parteB = s.substring(s.length()/2, s.length());
                
                if(Tools.isTerminal(parteA)) {
                    chomsky += parteA;
                } else {
                    chomsky += "{" + parteA + "}";
                    if(!tabla.tabla.containsKey("{" + parteA + "}")) {
                        tabla.put("{" + parteA + "}", new Elemento("{" + parteA + "}", false));
                        list.add(new Elemento("{" + parteA + "}", false));
                    }
                }
                
                if(Tools.isTerminal(parteB)) {
                    chomsky += parteB;
                } else {
                    chomsky +=  "{" + parteB + "}|";
                    
                    if(!tabla.tabla.containsKey("{" + parteB + "}")) {
                        tabla.put("{" + parteB + "}", new Elemento("{" + parteB + "}", false));
                        list.add(new Elemento("{" + parteB + "}", false));
                    }
                }
            }
        }
        
        chomsky = chomsky.substring(0, chomsky.length() - 1);
        setChomsky(Tools.getNameProduction(gic) + " --> " + chomsky);
        
        int i = 0;
        while(isListFull(list) == false) {
                finalS = "";
                parteA = "";
                parteB = "";
            
                if(Tools.isChomsky(Tools.getStringBtwn(list.get(i).getChomskyStr()))) {
                
                // Agregar a los definidos
                definidos.add(list.get(i).getChomskyStr() + "->" + Tools.getStringBtwn(list.get(i).getChomskyStr()));
                list.get(i).setDefined(true);
                
            } else {            // Proceso hediondo:
                
                list.get(i).setDefined(true);
                parteA = Tools.getStringBtwn(list.get(i).getChomskyStr()).substring(0, Tools.getStringBtwn(list.get(i).getChomskyStr()).length() / 2);
                parteB = Tools.getStringBtwn(list.get(i).getChomskyStr()).substring(Tools.getStringBtwn(list.get(i).getChomskyStr()).length() / 2, Tools.getStringBtwn(list.get(i).getChomskyStr()).length());
                
                if(Tools.isTerminal(parteA)) { // [A-Z]$
                    finalS += parteA;
                } else {
                    finalS += "{" + parteA + "}";
                    if(!tabla.tabla.containsKey("{" + parteA + "}")) {
                        tabla.put("{" + parteA + "}", new Elemento("{" + parteA + "}", false));
                        list.add(new Elemento("{" + parteA + "}", false));
                    }
                }
                
                if(Tools.isTerminal(parteB)) { // [A-Z]$
                    finalS += parteB;
                } else {
                    finalS += "{" + parteB + "}";
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
        this.chomsky = s;
    }
    
    private void setChomskyList(ArrayList<String> definidos) {
        this.chomskyList = definidos;
    }
    
    public ArrayList<String> getChomskyList() {
        return chomskyList;
    }
    
    public String getChomskyForm() {
        return chomsky;
    }
    
    public static boolean isListFull(ArrayList<Elemento> lista) {
        for(byte i = 0; i < lista.size(); i++) {
            if(lista.get(i).getDefined() == false) {
                return false;
            }
        }
        return true;
    }
    
    public static int getIndex(ArrayList<Elemento> lista) {
        for(byte i = 0; i < lista.size(); i++) {
            if(lista.get(i).getDefined() == false) {
                return i;
            }
        }
        return -1;
    }
}
