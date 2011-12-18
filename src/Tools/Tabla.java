package Tools;

import java.util.Hashtable;

/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 16, 2011 */
public class Tabla {
    
    public Hashtable tabla;
    
    public Tabla() {
        tabla = new Hashtable();
    }
    
    public Tabla(Hashtable tabla) {
        this.tabla = tabla;
    }
    
    public void put(String chomskyStr, Elemento t) {
        this.tabla.put(chomskyStr, t);
    }
    
    public Hashtable getTabla() {
        return tabla;
    }
    
}
