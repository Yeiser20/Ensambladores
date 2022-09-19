package ensambladores2021;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class lineaDir {
    
    private String direccion;
    private String linea;
    private int error;
    private String codigo;
     /*errores
       0= no hay error   
       1 = No a iniciado data segment
       2 = hay elementos de sobra en la linea
       3 = no es simbolo el primer elemento
       4 = no es la pseudoinstruccion correcta
       5 = posible error de tamanio o tipo de constante
       6 = No hay Stack ni Code Segment
       7 = No a iniciado stack Segment
       12 = No tiene ends el data segment
       13 = No tiene ends el stack segment
       8 = No es pseudoinstruccion el primer elemento de la linea
       9 = Hay elementos de mas en la linea
       10 = No es la pseudoinstruccion dup
       14 = No hay elementos suficientes 
       
       15 = No hay instruccion
       16 = La instruccion no lleva operandos
       17 = Faltan operandos
       18 = Operando incorrecto
       19 = Primer elemento de la linea no es instruccion
       20 = Ya hay una etiqueta igual previa
       21 = no se encotro simbolo en la tabla
       22 = error tamanio de argumento
       23 = error tamanio de constante
       */

    public String getDireccion() {
        return direccion;
    }

    public String getLinea() {
        return linea;
    }

    public int getError() {
        return error;
    }
    public String getCodigo() {
        return codigo;
    }

    /*public lineaDir(String direccion, String linea, int error) {
        this.direccion = direccion;
        this.linea = linea;
        this.error = error;
    }*/

    public lineaDir(String direccion, String linea, int error, String codigo) {
        this.direccion = direccion;
        this.linea = linea;
        this.error = error;
        this.codigo = codigo;
    }
    
    
    
}
