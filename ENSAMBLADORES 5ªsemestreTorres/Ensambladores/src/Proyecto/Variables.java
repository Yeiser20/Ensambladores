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
public class Variables {
    public String getNombreVariable() {
        return nombreVariable;
    }

    public String getTipoVriable() {
        return tipoVriable;
    }

    public String getValorVariables() {
        return valorVariables;
    }

    public Variables() {}

    public void datosVariables(String nombreVariable,String tipoVriable,String valorVariables){
        this.nombreVariable = nombreVariable;
        this.tipoVriable = tipoVriable;
        this.valorVariables = valorVariables;
    }
    
    private String nombreVariable;
    private String tipoVriable;
    private String valorVariables;
}
