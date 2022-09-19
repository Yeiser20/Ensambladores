package ensambladores2021;


import static java.lang.Math.pow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ivonls
 */
public class convertidorCN {
      
    public int convierteADecimal(String numero){
        int n=0;
        System.out.println("Numero a convertir:"+numero);
        identificadorConstantes ic = new identificadorConstantes();
        if(ic.esCNB(numero)){
            n = convierteBinADec(numero);
        }else{
            if(ic.esCNH(numero)){
                n= convierteHexaADec(numero);
            }else{
                n=convierteDecADec(numero);
            }
        }
       System.out.println("Numero conv:"+n);
        return n;
    }
    private int convierteDecADec(String n){
        int nf=0;
        for(int i=0;i<n.length();i++){
           int aux=obtieneDigDec(n.charAt(n.length()-1-i));
           nf=(int) (nf+(aux*pow(10,i)));
        }
        return nf;
    }
    private int obtieneDigDec(char c){
        if(c==48){
            return 0;
        }
        if(c==49){
            return 1;
        }
        if(c==50){
            return 2;
        }
        if(c==51){
            return 3;
        }
        if(c==52){
            return 4;
        }
        if(c==53){
            return 5;
        }
        if(c==54){
            return 6;
        }
        if(c==55){
            return 7;
        }
        if(c==56){
            return 8;
        }
        if(c==57){
            return 9;
        }
        return 0;
    }
    public String convierteABinario(int n){
        String nu="";
        boolean sigue=true;
        while(sigue){
            int r=n%2;
            if(r!=0){
                nu=nu+"1";
            }else{
                nu=nu+"0";
            }
            n=n/2;
            if(n<1){
                sigue=false;
            }
        }
        nu=invierte(nu);
        return nu;
    }
    private String invierte(String n){
        String aux="";
        for(int i=n.length()-1;i>=0;i--){
            aux=aux+n.charAt(i);
        }
        return aux;
    }
    public int obtieneTamanio(String cons){
        identificadorConstantes ic = new identificadorConstantes();
    //  System.out.println(cons);
        if(ic.esConsNum(cons)){
            int n = convierteADecimal(cons);
            System.out.println("n:"+n);
            if(n>=-128 && n<=1000){
            //if(n>=-128 && n<=256){
                return 0;
            }else{
                if(n>=-32768 && n<=65535){
                    return 1;
                }else{
                    return 2;
                }
            }
      }else {
            return 2;
        }   
    }
    int convierteHexaADec(String n){
        String nNum="";
        //quitar la "h"
        for(int i=0;i<n.length()-1;i++){
            nNum=nNum+n.charAt(i);
        }
        //convierte num
        int nu=0;
        for(int i=0;i<nNum.length();i++){
            int aux;
            aux= oDigHexa(nNum.charAt(nNum.length()-1-i));
            nu=(int) (nu+ (aux*pow(16,i)));
        }
        return nu;
    }
    public String convDECaHEX(int decimal){
        boolean sigue =true;
        int auxi=0,oaux=0;
        String hex="";
        while(sigue){
             auxi = decimal / 16;//division entera
             oaux = decimal - (auxi*16);//residuo
             decimal = auxi;
             if(oaux<=9){
               hex = hex + Integer.toString(oaux);  
             }else{
                 hex = hex + oLetHex(oaux);
             }
             if(decimal==0){
                 sigue = false;
             }
        }
        //girar palabra
        int t=0;
        String hexaDec="";
           if(hex.length()<4){
               t = 4 -  hex.length();
           }
           for(int i=0;i<t;i++){
               hexaDec = hexaDec+"0";
           }
           for(int i = hex.length()-1;i>=0;i--){
               hexaDec = hexaDec + hex.charAt(i);
           }
           return hexaDec;
    }
    private String oLetHex(int oaux){
        switch(oaux){
                     case 10:
                         return "A";
                     case 11:
                         return "B";
                     case 12:
                         return "C";
                     case 13:
                         return "D";
                     case 14:
                         return "E";
                     case 15:
                         return "F";
                 }
        return "";
    }
    private int oDigHexa(char c){
        if(c==48){
            return 0;
        }
        if(c==49){
            return 1;
        }
        if(c==50){
            return 2;
        }
        if(c==51){
            return 3;
        }
        if(c==52){
            return 4;
        }
        if(c==53){
            return 5;
        }
        if(c==54){
            return 6;
        }
        if(c==55){
            return 7;
        }
        if(c==56){
            return 8;
        }
        if(c==57){
            return 9;
        }
        if(c==65 || c==97){
            return 10;
        }
        if(c==66 || c==98){
            return 11;
        }
        if(c==67 || c==99){
            return 12;
        }
        if(c==68 || c==100){
            return 13;
        }
        if(c==69 || c==101){
            return 14;
        }
        if(c==70 || c==102){
            return 15;
        }
        return -1;
    }
    int convierteBinADec(String n){
        String nNum="";
        //quitar la "b"
	n = n.substring(0, n.length()-1);
        //convierte num
        int con=Integer.parseInt(n), resto, dec=0,i=0;
        while (con != 0){
           resto = con % 10;
           dec = dec + (resto * (int) Math.pow(2, i));
           i++;
           con = con / 10;
        }        
        System.out.println("n en convierteBinADec:"+dec);
        return dec;
    }
}
