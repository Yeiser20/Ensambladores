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
public class lineasDireccion {
    public int getLinea() {
        return Linea;
    }

    public int getDireccion() {
        return Direccion;
    }

    public lineasDireccion(int Linea, int Direccion) {
        this.Linea = Linea;
        this.Direccion = Direccion;
    }
    private final int Linea;
    private final int Direccion;
}
