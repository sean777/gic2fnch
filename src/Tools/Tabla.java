package Tools;

/**
 * Clase que contiene una hashtable.
 * La hashtable usa como clave o key un String, y como valor un Elemento
 * Un Elemento contiene un String y un booleano que indica si cada definición está ya definida, valga la redundancia.
 */
import java.util.Hashtable;

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
