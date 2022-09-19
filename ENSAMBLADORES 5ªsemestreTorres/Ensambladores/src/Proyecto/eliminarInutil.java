/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Vanessa Sandoval
 */
public class eliminarInutil {
    public eliminarInutil() {
    }
    
    public String eliminarInecesario(String Codigo){
        String resultado = "";
        String [] lineas = Codigo.split("\n");
        Pattern p;
        Matcher matcher;
        p = Pattern.compile(";.*");
        for(int i = 0 ; i < lineas.length ; i++){
            matcher = p.matcher(lineas[i]);
            lineas[i] = matcher.replaceAll("");
        }
        p = Pattern.compile("[ \t\r\f]*");
        for(int i = 0 ; i < lineas.length ; i++){
            matcher = p.matcher(lineas[i]);
            if(matcher.matches()){
                lineas[i] = matcher.replaceFirst("NULLINE....");
            }
        }
        for (String linea : lineas) {
            resultado += linea + "\n";            
        }

        return resultado;
    }
}
