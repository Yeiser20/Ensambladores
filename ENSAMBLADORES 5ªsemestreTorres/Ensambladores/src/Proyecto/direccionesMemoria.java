/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vanessa Sandoval
 */
public class direccionesMemoria {
  
    public List<lineasDireccion> getDirecciones() {
        return direcciones;
    }

    public direccionesMemoria() {
    }
    
    public void agregarDirecciones(int linea, int direccion){
        this.direcciones.add(new lineasDireccion(linea,direccion));

    }
    private final List<lineasDireccion> direcciones = new ArrayList<lineasDireccion>();

}
