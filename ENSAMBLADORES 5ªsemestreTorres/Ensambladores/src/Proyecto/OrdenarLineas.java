/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.util.Comparator;
/**
 *
 * @author Vanessa Sandoval
 */
public class OrdenarLineas implements Comparator<clasificacionLineas>{
    @Override
    public int compare(clasificacionLineas o1, clasificacionLineas o2) {
        if(o1.getNumeroLinea() > o2.getNumeroLinea()){
            return 1;
        }else if(o1.getNumeroLinea() < o2.getNumeroLinea()){
            return -1;
        }
        return 0;
    }
}
