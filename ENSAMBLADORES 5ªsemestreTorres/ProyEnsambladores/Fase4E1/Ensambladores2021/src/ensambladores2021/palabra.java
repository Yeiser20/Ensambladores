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
public class palabra {
     private String palabra;
      private Integer tipo;
    public palabra(String palabra, Integer tipo) {
        this.palabra = palabra;
        this.tipo = tipo;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
      
}
