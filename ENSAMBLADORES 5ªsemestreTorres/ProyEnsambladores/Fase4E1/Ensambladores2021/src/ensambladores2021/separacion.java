package ensambladores2021;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


/**
 *
 * @author ivonls
 */
public class separacion {
    private Lista ventana;
    public separacion(Lista var) {
        ventana=var;
    }
    public String imprimeElementos(HashMap fase1){
        String textoSeparado="";
        for(int i=1;i<=fase1.size();i++){
            String aux = (String) fase1.get(i);
            if(aux.compareTo("/*")!=0){
              textoSeparado=textoSeparado+fase1.get(i)+"\n";  
            }
        }
       return textoSeparado;
    }
    
    public String identificacionF2(HashMap fa2){
        palabra p;
        String textoF2="";
        for(int i=1;i<=fa2.size();i++){
            p=(palabra) fa2.get(i);
            if(p.getTipo()!=12 && p.getTipo()!= 13){//que no sea el caracter de fin de linea
                textoF2=textoF2+p.getPalabra();
                if(p.getTipo()==1){
                    textoF2=textoF2+"       [Pseudoinstrucción]\n";
                }else{
                    if(p.getTipo()==2){
                         textoF2=textoF2+"       [Instrucción equipo 5]\n";
                     }else{
                        if(p.getTipo()==3){
                            textoF2=textoF2+"       [Símbolo]\n";
                        }else{
                            if(p.getTipo()==4){
                                textoF2=textoF2+"       [Etiqueta]\n";
                            }else{
                                if(p.getTipo()==5){
                                    textoF2=textoF2+"       [Constante númerica decimal]\n";
                                }else{
                                    if(p.getTipo()==6){
                                        textoF2=textoF2+"       [Constante carácter]\n";
                                    }else{
                                        if(p.getTipo()==7){
                                            textoF2=textoF2+"       [Constante numérica binaria]\n";
                                        }else{
                                            if(p.getTipo()==8){
                                                textoF2=textoF2+"       [Constante numérica hexadecimal]\n";
                                            }else{
                                                if(p.getTipo()==9){
                                                    textoF2=textoF2+"       [Registro]\n";
                                                }else if(p.getTipo()==17){
                                                    textoF2=textoF2+"        [SREG]\n"; 
                                                }else if(p.getTipo()==10){
                                                        textoF2=textoF2+"       [Carácter recervado]\n";
                                                    }else if(p.getTipo()==11){
                                                        textoF2=textoF2+"       [Constante carácter binario]\n";
                                                    }else if(p.getTipo()==14){
                                                        textoF2=textoF2+"       [No reconocido]\n";
                                                    }else if(p.getTipo()==15){
                                                        textoF2=textoF2+"       [Pseudoinstrucción con Constante]\n";
                                                    }else if(p.getTipo()==16){
                                                        textoF2=textoF2+"       []\n";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }
        
        return textoF2;
    }
    public HashMap faseSeparacion(String rArchivo) throws FileNotFoundException, IOException{
      String cadena;
      String texto = "";
      String aux;
      char aux1;
      int j=0;
      System.out.println("Ruta de archivo en separación:"+rArchivo);
      Map<Integer,String> mapa = new HashMap<Integer,String>();
      FileReader file = new FileReader(rArchivo);
      BufferedReader leerA = new BufferedReader(file);
      while((cadena = leerA.readLine())!=null) {
        aux="";/*String auxiliar para almacenar los datos*/
        for(int i=0;i<cadena.length();i++){
            aux1=cadena.charAt(i);
            if(valueOf(aux1).compareTo(";")==0){/*Ignorando los comentarios del txt fuente*/
                i=cadena.length();  
            }else{
                if(valueOf(aux1).compareTo(" ")==0){/*Espacios*/
                    /*Identificando data, stack y code segment y dup mínusculas*/
                    if(aux.compareTo("dup")==0){/*DUP*/
                        aux =aux+ valueOf(aux1);
                        for(int y=i+1;y<cadena.length();y++){  
                            aux1=cadena.charAt(y);
                            if(valueOf(aux1).compareTo(")")==0){
                                aux=aux+valueOf(aux1);
                                y=cadena.length();
                                i=y;
                            }else{
                                aux=aux+valueOf(aux1);
                            }
                        }
                    }
                    if(aux.compareTo("code")==0 || aux.compareTo("stack")==0 || aux.compareTo("data")==0){
                        int y;
                        String aux2="";
                        for(y=i+1;y<=i+7;y++){ //Busca si se encuentra 'segment' por longitud de la cadena
                            aux1=cadena.charAt(y);
                            aux2 = aux2 + valueOf(aux1);
                        }
                        if(aux2.compareTo("segment")==0){
                            aux= aux + " segment"; 
                            i=y;
                        }
                      }else{
                        if(aux.length()>0){
                            j++;
                            mapa.put(j, aux);/*Guardamos en map*/
                        } 
                        aux="";
                    }   
                    /*Identificando data, stack y code segment y dup mayúsculas*/
                    if(aux.compareTo("CODE")==0 || aux.compareTo("STACK")==0 || aux.compareTo("DATA")==0){
                        int y;
                        String aux2="";
                        for(y=i+1;y<=i+7;y++){ //Busca si se encuentra 'segment' por longitud de la cadena
                            aux1=cadena.charAt(y);
                            aux2 = aux2 + valueOf(aux1);
                        }
                        if(aux2.compareTo("SEGMENT")==0){
                            aux= aux + " SEGMENT"; 
                            i=y;
                        }
                      }else{
                        if(aux.length()>0){
                            j++;
                            mapa.put(j, aux);/*Guardamos en map*/
                        } 
                        aux="";
                    }
                    if(aux.compareTo("DUP")==0){
                        aux =aux+ valueOf(aux1);
                        for(int y=i+1;y<cadena.length();y++){  
                            aux1=cadena.charAt(y);
                            if(valueOf(aux1).compareTo(")")==0){
                                aux=aux+valueOf(aux1);
                                y=cadena.length();
                                i=y;
                            }else{
                                aux=aux+valueOf(aux1);
                            }
                        }
                    }
                }else{
                    if(valueOf(aux1).compareTo(",")==0){
                        
                        j++;
                        mapa.put(j, aux);/*guardamos la palabra despues de la coma localizada en el map*/
                        aux="";
                        
                    }else{
                        if(valueOf(aux1).compareTo(":")==0){/*':'*/
                            aux=aux+aux1;
                            j++;
                            mapa.put(j, aux);
                            aux="";
                        }else{
                            if(valueOf(aux1).compareTo("(")==0){// (
                                if(aux.compareTo("dup")==0 || aux.compareTo("DUP")==0 ){
                                    aux =aux+ valueOf(aux1);
                                    for(int y=i+1;y<cadena.length();y++){  
                                        aux1=cadena.charAt(y);
                                        if(valueOf(aux1).compareTo(")")==0){
                                            aux=aux+valueOf(aux1);
                                            y=cadena.length();
                                            i=y;
                                        }else{
                                        aux=aux+valueOf(aux1);
                                        }
                                    }
                                }else{
                                    aux=aux+valueOf(aux1);
                                }
                            }else{// !(
                                if(valueOf(aux1).compareTo("'")==0 || aux1==34){/*Comparación de '' y ""*/
                                    aux=aux+valueOf(aux1);
                                    /*Recorre hasta encontrar el cierre*/
                                    for(int y=i+1;y<cadena.length();y++){  
                                        aux1=cadena.charAt(y);
                                        if(valueOf(aux1).compareTo("'")==0 || aux1==34){
                                            aux=aux+valueOf(aux1);
                                            y=cadena.length();
                                            i=y;
                                        }else{
                                            aux=aux+valueOf(aux1);
                                        }
                                    }
                                }else{
                                    if(aux1==91){ // [
                                        aux=aux+valueOf(aux1); 
                                        char ayuda=0;
                                        while(ayuda!=93){ //]
                                            i++;
                                            ayuda=cadena.charAt(i);
                                            aux=aux+valueOf(ayuda);
                                            if(i==cadena.length()){
                                                ayuda=93;
                                            }
                                        }
                                    }else{
                                        if(aux1>=32) aux=aux+valueOf(aux1);
                                    }
                                }                             
                            }
                        }
                    }
                }
            }           
            if (aux.compareToIgnoreCase("WORD") == 0) {
                    int y;
                    String aux2 = "";
                    for (y = i + 1; y <= i + 0; y++) { //Busca si se encuentra 'segment' por longitud de la cadena
                        aux1 = cadena.charAt(y);
                        aux2 = aux2 + valueOf(aux1);
                    }
                    if (aux2.compareTo("") == 0) {
                        aux = aux + " ";
                        i = y;
                    }
                } 
        }/*Fin de la cadena*/
        if(aux.length()>0){
            j++;
            mapa.put(j, aux);//map
        }
          j++;
         mapa.put(j,"/*"); //La cadena /* indicara un fin de linea
      }
        return (HashMap) mapa; 
    }
   
    public HashMap F2(HashMap mapSeparar) throws FileNotFoundException, IOException{
        Map<Integer,palabra> mapa = new HashMap<Integer,palabra>();
        Integer tam;
        tam = mapSeparar.size();
        String auxi;
        for(int i=1;i<=tam;i++){
           auxi=(String) mapSeparar.get(i);
           System.out.println("Separada:"+auxi);
           Integer t = revisa(auxi);
           mapa.put(i, new palabra(auxi,t));
        }
        return (HashMap) mapa;
    } 
    private String obtenerInterior(String pal){
        String aux="";
        for(int i=1;i<pal.length()-1;i++){
            aux= aux+ pal.charAt(i);
        }
        return aux;
    }

    Map<Integer, String> faseSeparacion(BufferedReader leerArch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Map<Integer, String> faseSeparacion(String nArchivo, String rArchivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Integer revisa(String pal) {
        identificadorConstantes ic = new identificadorConstantes();
        if(pal.compareTo("/*")==0){
            return 12;
        }else if(ic.esPseudo(pal)){
            if(pal.contains("dup") || pal.contains("DUP")){
                return ic.descomponeDup(pal);
            }
            return 1;
        }else if(ic.esIns(pal)){
           return 2;
        }else if (ic.esReg(pal)){ 
            return 9;
        }else if (ic.esSReg(pal)){ 
            return 17;
        }else if(ic.esSimb(pal)){
            if(ic.esEtiq(pal)){
                return 4;
            }else  return 3;
        }else if(ic.esCC(pal)){
            return 6;
        }else if (ic.esCNB(pal)){ 
            return 7;
        }else if (ic.esCNH(pal)){ 
            return 8;
        }else if(ic.esConsNum(pal)){
            return 5;
        }else if (ic.esCarRe(pal)){ 
            return 10;
        }else if (ic.esCCB(pal)){ 
            return 11;
        }if(pal.startsWith("[")&&pal.endsWith("]")){
            String aux = obtenerInterior(pal);
            return revisa(aux);
        }else{
            return 14;   
        }
    }
}
