/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensambladores2021;

/**
 *
 * @author ivonls
 */
public class lineaCodigo {
    private String direccion;
    private String linea;
    private int error;
    private String codigo;

    public String getDireccion() {
        return direccion;
    }

    public String getLinea() {
        return linea;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getError() {
        return error;
    }

    public lineaCodigo(String direccion, String linea, int error, String codigo) {
        this.direccion = direccion;
        this.linea = linea;
        this.error = error;
        this.codigo = codigo;
    }
    
}
