/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 16, 2011 */

/* Contiene la cadena y el orden en que aparece en la primera transformación a Chomsky */
package Tools;

public class Elemento {
    
    private String chomskyStr;
    private boolean definido;
    
    public Elemento(String chomString, boolean definido) {
        this.chomskyStr = chomString;
        this.definido = definido;
    }
    
    public String getChomskyStr() {
        return chomskyStr;
    }
    
    public boolean getDefined() {
        return definido;
    }
    
    public Elemento setDefined(boolean definido) {
        this.definido = definido;
        return this;
    }
    
}
