package ensambladores2021;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ivonls
 */
public class tablaSim {
      private String simbolo;
      private String tipo;
      private String tam;
      private String valor;
      private String direccion;

    public tablaSim(String simbolo, String tipo, String tam, String valor,String dir) {
        this.simbolo = simbolo;
        this.tipo = tipo;
        this.tam = tam;
        this.valor = valor;
        this.direccion=dir;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTamanio() {
        return tam;
    }

    public String getValor() {
        return valor;
    }
      
}
