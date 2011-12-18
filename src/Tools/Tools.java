/* @author Leonardo Gutiérrez Ramírez <leogutierrezramirez.gmail.com> */
/* Dec 15, 2011 */
package Tools;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tools {
    
    public static boolean isProduction(String s) {
        return Pattern.matches(".*->.*", s);
    }    
    public static boolean isProductionList(ArrayList<String> productions) {
        
        Iterator it = productions.iterator();
        while(it.hasNext()) {
            if(isProduction((String)it.next()) == false) {
                return false;
            }
        }
        return true;
    }
    public static byte getNumberCases(String s) {
        return (byte)new StringTokenizer(s, "|").countTokens();
    }
    public static String getNameProduction(String s) {
        Pattern pattern = Pattern.compile("(.*)->");
        Matcher matcher = pattern.matcher(s);
        
        if(matcher.find())
            return matcher.group(1);
        else
            return null;
    }
    public static String getCases(String s) {
        
        Pattern pattern = Pattern.compile("->(.*)");
        Matcher matcher = pattern.matcher(s);
        
        if(matcher.find())
            return matcher.group(1);
        else
            return null;
	
    }
    public static ArrayList<String> getCasesList(String s) {
        ArrayList<String> t = new ArrayList<String>();
        
        for(String x : s.split("\\|"))
            t.add(x);
        
        return t;
    }
    public static boolean isChomsky(String s) {
        return Pattern.matches("[a-z]|[A-Z]{2}", s);
    }
    
    public static boolean isTerminal(String s) {
        return Pattern.matches("^[A-Z]$", s);
    }

    // Devuelve el texto en una cadena delimitado por {}
    public static String getStringBtwn(String s) {
        Pattern pattern = Pattern.compile("\\{(.*)\\}");
        Matcher matcher = pattern.matcher(s);
        
        if(matcher.find())
            return matcher.group(1);
        else
            return null;
    }
}
