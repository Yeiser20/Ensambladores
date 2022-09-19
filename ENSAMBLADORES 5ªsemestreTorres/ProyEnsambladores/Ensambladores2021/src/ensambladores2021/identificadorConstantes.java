package ensambladores2021;


/**
 *
 * @author ivonls
 */
public class identificadorConstantes {
    public boolean esPseudo(String pal){ //checa si es pseudoinstruccion
        if(pal.compareToIgnoreCase("data segment")==0 || pal.compareToIgnoreCase("stack segment")==0||pal.compareToIgnoreCase("code segment")==0
                ||pal.compareToIgnoreCase("ends")==0||pal.compareToIgnoreCase("dw")==0||pal.compareToIgnoreCase("db")==0||pal.compareToIgnoreCase("equ")==0
                ||pal.compareToIgnoreCase("byte prt")==0||pal.compareToIgnoreCase("word prt")==0||pal.compareToIgnoreCase("macro")==0||pal.compareToIgnoreCase("endm")==0
                ||pal.compareToIgnoreCase("proc")==0||pal.compareToIgnoreCase("endp")==0){
            return true;
        }else{
            if(pal.contains("dup")){
                return true;
            }else{
                return false;
            }
        }
    }
    public boolean esIns(String ins){ //checa si es instruccion de equipo 5
        if(ins.compareToIgnoreCase("scasw")==0||ins.compareToIgnoreCase("cmpsb")==0||ins.compareToIgnoreCase("popf")==0||ins.compareToIgnoreCase("dec")==0||ins.compareToIgnoreCase("div")==0||
                ins.compareToIgnoreCase("stosw")==0||ins.compareToIgnoreCase("hlt")==0||ins.compareToIgnoreCase("stc")==0||ins.compareToIgnoreCase("int")==0||ins.compareToIgnoreCase("pop")==0||
                ins.compareToIgnoreCase("cbw")==0||ins.compareToIgnoreCase("lodsw")==0 ||ins.compareToIgnoreCase("lea")==0||ins.compareToIgnoreCase("or")==0 ||ins.compareToIgnoreCase("sar")==0 ||ins.compareToIgnoreCase("test")==0 ||ins.compareToIgnoreCase("jbe")==0
                ||ins.compareToIgnoreCase("jg")==0||ins.compareToIgnoreCase("jmp")==0 ||ins.compareToIgnoreCase("jnbe")==0 ||ins.compareToIgnoreCase("jnge")==0 ||ins.compareToIgnoreCase("jnp")==0 ||ins.compareToIgnoreCase("jp")==0 ||ins.compareToIgnoreCase("loope")==0){
            return true;
        }else{
            return false;
        }
    }
    public boolean esSimb(String simb){//checa si es simbolo o una instrucción que no corresponde al equipo
       char comienzo = simb.charAt(0);
       if(esEtiq(simb)){
           return true;
       }else{
           /*Verifica que es una palabra por su inicio de cadena*/
          if(((comienzo>=65 && comienzo<=90)||(comienzo>=97&&comienzo<=122))&&simb.length()<11){
           return true;
          }   
        }
       return false;
    }
    public boolean esEtiq(String simb){
        if(simb.endsWith(":")){
            return true;
        }else{
            return false;
        }
    }
    public boolean esConsNum(String cons){
        if(cons.startsWith("0") ||cons.startsWith("1")||cons.startsWith("2")|| cons.startsWith("3") || cons.startsWith("4") ||cons.startsWith("5")|| cons.startsWith("6") || cons.startsWith("7") ||cons.startsWith("8")|| cons.startsWith("9")){
            for( int i=1;i<=cons.length();i++){
                if(cons.startsWith("0")||cons.startsWith("1")||cons.startsWith("2")|| cons.startsWith("3") || cons.startsWith("4") ||cons.startsWith("5")|| cons.startsWith("6") || cons.startsWith("7") ||cons.startsWith("8")|| cons.startsWith("9")){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }  
        return false;
    }
    public boolean esCons(String cons){
       if(esCC(cons)){
           return true;
        }else{
           if(esCNB(cons)){
               return true;    
            }else{
               if(esCND(cons)){
                   return true;
                }else{
                   if(esCNH(cons)){
                       return true;
                    }else{
                        return false;
                    }
                }
            }
        }
    }
    public boolean esCC(String cons){ //constante caracter
        if(cons.length()>0){
            if(cons.charAt(0)==34 && cons.charAt(cons.length()-1)==34){
              return true;
            }else{
                if(cons.charAt(0)==39 && cons.charAt(cons.length()-1)==39){
                    return true;
                }else{
                    return false;
                } 
            }     
        }
        return false;
    }
    public boolean esCNB(String cn){ //Cosntante numerica binario
        for(int i=0;i<=cn.length()-2;i++){
            if(cn.charAt(i)!=48 && cn.charAt(i)!=49){
                return false;
            }
        }
        if(cn.endsWith("b")||cn.endsWith("B")){
            return true;
        }else{
            return false;
        }
    }
     public boolean esCND(String cn){ //constante numerica decimal
        for(int i=0;i<cn.length();i++){
            if(cn.charAt(i)<48 || cn.charAt(i)>57){
                return false;
            }
        }
        return true;
    }
    public boolean esCNH(String cn){//constante numerica hexadecimal
        if(cn.startsWith("0")){
            for(int i=1;i<=cn.length()-2;i++){
                if((cn.charAt(i)<48 || cn.charAt(i)>57)&&(cn.charAt(i)<65)||cn.charAt(i)>70){
                    return false;
                }
            }
            if(cn.endsWith("h")||cn.endsWith("H")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }  
    }
     public boolean esReg(String reg){
        if(reg.compareToIgnoreCase("al")==0||reg.compareToIgnoreCase("ah")==0||reg.compareToIgnoreCase("bl")==0||reg.compareToIgnoreCase("bh")==0||reg.compareToIgnoreCase("cl")==0||reg.compareToIgnoreCase("ch")==0||reg.compareToIgnoreCase("dl")==0||
               reg.compareToIgnoreCase("dh")==0||reg.compareToIgnoreCase("ax")==0||reg.compareToIgnoreCase("BX")==0||reg.compareToIgnoreCase("cx")==0||reg.compareToIgnoreCase("dx")==0||reg.compareToIgnoreCase("si")==0||reg.compareToIgnoreCase("di")==0||
                reg.compareToIgnoreCase("sp")==0||reg.compareToIgnoreCase("bp")==0){
          return true;    
        }
        return false;
    }
      public boolean esSReg(String reg){
        if(reg.compareToIgnoreCase("ss")==0||reg.compareToIgnoreCase("cs")==0||reg.compareToIgnoreCase("ds")==0||reg.compareToIgnoreCase("es")==0){
          return true;    
        }
        return false;
    }
    public boolean esCarRe(String cr){//Caracter especial
        if(cr.compareTo("?")==0){
            return true;
        }
        return false;
    }
    public String descomponeDUP(String dup){
        String contenido="";
        for(int i = dup.indexOf("(")+1;i<dup.length()-1;i++){
            contenido=contenido+dup.charAt(i);
        }
        System.out.println("contenido dup"+contenido);
        return contenido;
        
    }
    public int obCNDup(String dup){
        convertidorCN cn = new convertidorCN();
        return cn.convierteADecimal(dup);
    }
    public boolean esCCB(String c){
        if(c.length()==3){
            return true;
        }
        return false;
    }
    public int descomponeDup(String dup){        
        String contenido="";
        for(int i=dup.indexOf("(")+1;i<dup.length()-1;i++){
            contenido=contenido+dup.charAt(i);
        }
        if(esCons(contenido)){
            return 15;
        }else{
            if(esCarRe(contenido)){
                return 16;
            }
        }
        return 0;
    }
    
}
