/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;
/**
 *
 * @author Vanessa Sandoval
 */
public class clasificacionLineas {
    
    public clasificacionLineas(int numeroLinea, String clasifiacion) {
        this.numeroLinea = numeroLinea;
        this.clasifiacion = clasifiacion;
    }


    public int getNumeroLinea() {
        return numeroLinea;
    }

    public String getClasifiacion() {
        return clasifiacion;
    }
    
    private  int numeroLinea;
    private  String clasifiacion;
}
