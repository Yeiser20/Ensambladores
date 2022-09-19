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
public class codigoMaquina {
    public codigoMaquina() {
    }
    
    public String convertirHexadecimal(String codigoBin,String mod,String reg, String rm){
        int codigoDecInstr = Integer.parseInt(codigoBin,2);
        int codigoDecimal = Integer.parseInt((mod+reg+rm),2);
        String codigoMaquina = Integer.toHexString(codigoDecInstr)+" "+this.addCeros(Integer.toHexString(codigoDecimal));
        return codigoMaquina.toUpperCase();
    }
    
    public String addCeros(String numeroHexa){
        String ceros = "";
        String codigoMaquina = numeroHexa;
        if(numeroHexa.length() < 2){
            for(int i = 0 ; i < (2-numeroHexa.length()) ; i++){
                ceros += "0";
            }
            codigoMaquina = (ceros+numeroHexa);
        }else if(numeroHexa.length() > 2){
            for(int i = 0 ; i < (4-numeroHexa.length()) ; i++){
                ceros += "0";
            }
            codigoMaquina = (ceros+numeroHexa).toUpperCase();            
        }
        return codigoMaquina;
    }
}
