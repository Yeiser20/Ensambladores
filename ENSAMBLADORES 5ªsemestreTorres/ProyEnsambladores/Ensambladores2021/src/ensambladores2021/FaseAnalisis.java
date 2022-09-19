
package ensambladores2021;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
/**
 *
 * @author ivonls
 */
public class FaseAnalisis {
    private JFrame ventana;
    private Map<Integer,linea> mapaAnalisis;
    private Map<Integer,lineaDir> mapaDir;
    private int imapa;
    private int imdir;
    private palabra[] linea;
    private Map<Integer,tablaSim> tablaSimbolos;
    private int its;
    private int contadorPrograma = 0;  
    private identificadorConstantes idec;
    private String txt="";
    private identificadorConstantes ic;
    private convertidorCN conv;
   

    FaseAnalisis(Lista aThis) {
        ventana=aThis;
        mapaAnalisis=new HashMap<Integer,linea>();
        tablaSimbolos = new HashMap<Integer,tablaSim>();
        imapa=1;
        its=1;
        conv= new convertidorCN();
        idec=new identificadorConstantes();
        
        mapaDir = new HashMap<Integer,lineaDir>();
        imdir=1;
    }

    Map<Integer, tablaSim> getTablaSimbolos() {
        return tablaSimbolos;
    }
    
    public String imprimeTextoDir(HashMap m){
       lineaDir li;
       String texto ="";
       for(int i=1;i<=m.size();i++){
           li = (lineaDir) m.get(i);
           texto=texto+ li.getDireccion()+"--"+li.getLinea() +" ....."+ Errores(li.getError())+"\n";
       }
       return texto;
   }
    
    public Map<Integer, lineaDir> Dir(){
       return mapaDir;
   }

    public HashMap Identificacion(HashMap map){
        int i=-1;
        i=checkDataSegment(i,map);
        if(i>map.size()){
            mapaAnalisis.put(imapa, new linea("No hay Stack ni Data Segment",6));
            imapa++;
        }else{
            i=checkStackSegment(i,map);
            if(i>map.size()){
                mapaAnalisis.put(imapa, new linea("No hay Stack Segment",6));
            }
            else{
                //Análizamos code segment
                checkCodeSegment(i,map);
            }
        }
        
        return (HashMap) mapaAnalisis;
    }
/*ANÁLISIS DE DATA SEGMENT*/
    private int checkDataSegment(int i, HashMap map) {
        int EndDS;
        i=buscaIndiceDataSegment(i,map);
        if(i<map.size()){
            EndDS=buscaFinDataSegment(i,map);
            if(EndDS>map.size()){
                mapaAnalisis.put(imapa,new linea("",12));/*El DS no contiene ends*/
                imapa++;
                mapaDir.put(imdir,new lineaDir("----","",12));
                imdir++;
            }else{
                /*Análisis del Data segment*/
                while(i<EndDS-2){
                    i=lineaCod(i,map);
                    analisisLineaDS();
                }
                mapaAnalisis.put(imapa, new linea("ends",0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),"ends",0));
                imdir++;
                i+=2;
            }
        }
        return i;
    }

    private int buscaIndiceDataSegment(int i, HashMap mapa) {
        boolean sig=true;
        while(sig){
            i++;
            i=lineaCod(i,mapa);
            System.out.println(i);
            sig=false;
            if(i>mapa.size()){
                sig=false;
            }else{
                if(linea.length==1){
                    //if(linea[0].getPalabra().contains("data segment") || linea[0].getPalabra().contains("DATA SEGMENT")){
                    if(linea[0].getPalabra().compareToIgnoreCase("data segment")==0 || linea[0].getPalabra().compareToIgnoreCase("DATA SEGMENT")==0){
                        sig=false;
                        mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                        imapa++;
                        // mapaDir.put(imdir,new lineaDir("----",generaLinea(),0));
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma = 0;
                    }else{
                        mapaAnalisis.put(imapa,new linea(generaLinea(),1));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir("----",generaLinea(),1));
                        imdir++;
                    }
                }else{
                    if(linea.length>=1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),1));
                        imapa++; 
                        mapaDir.put(imdir,new lineaDir("----",generaLinea(),1));
                        imdir++;
                   }
                }
            }
        }
        return i;
    }

    private int buscaFinDataSegment(int i, HashMap mapa) {
        boolean sig=true;
        while(sig){
            i=lineaCod(i,mapa);
            if(linea.length==1){
                if(linea[0].getPalabra().compareToIgnoreCase("ends")==0)sig=false;
            }
            if(i>mapa.size()) sig=false;
        }
        return i;
    }

    private int lineaCod(int i, HashMap mapa) {
        boolean sig=true;
        linea=new palabra[checkCantLin(i,mapa)];
        int indLinea=0;
        if(i>mapa.size()) sig=false;
        while(sig){
            i++;
            palabra p=(palabra)mapa.get(i);
            if(p.getTipo()!=12){
                linea[indLinea]=p;
                indLinea++;
            }else{
                sig=false;
            }
            if(i>mapa.size()) sig=false;
        }
        return i;
    }
    
    private int checkCantLin(int i,HashMap m){
        boolean sig=true;
        int el=0;
        if(i>m.size())sig=false;
        while(sig){
            i++;
            System.out.println(i);
            palabra p=(palabra) m.get(i);
            if(p.getTipo()!=12){ /*No es fin de linea*/
                el++;
            }else{
                sig=false;
            }
            if(i>m.size())sig=false;
        }
        return el;
        
    }

    private String generaLinea() {
        String lin="";
        for(int i=0;i<linea.length;i++){
           lin = lin +" "+linea[i].getPalabra();
        }
        return lin;
    
    }

    private void analisisLineaDS() {
        if(linea.length!=0){
            if(checkPODS()){/*CONSTANTES CARACTERES*/
               mapaAnalisis.put(imapa,new linea(generaLinea(),0));
               imapa++;
               mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
               imdir++;
               addDSTabla();///
            }else if(checkSODS()){
               mapaAnalisis.put(imapa,new linea(generaLinea(),0));
               imapa++;
               mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
               imdir++;
               addSODSTabla();///
            }else if(checkTODS()){
               mapaAnalisis.put(imapa,new linea(generaLinea(),0));
               imapa++;
               mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
               imdir++;
               addToDStabla();//
            }else if(checkCODS()){
                mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                imdir++;
                addCoDStabla();///
            }else if(checkQODS()){
                mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                imdir++;
                addQoDStabla();///
            }else if(checkSexODS()){
                mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                imdir++;
                addSexODStabla();
            }else if(checkSepODS()){
                mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                imdir++;
                addSepODStabla();
            }else{
                buscaError();
            }
        }
    }

    private void addDSTabla() {             /*SÍMBOLO-TIPO-TAMAÑO-VALOR*/                                                 
        tablaSimbolos.put(its, new tablaSim(linea[0].getPalabra(),"Variable",linea[2].getPalabra(),"db",conv.convDECaHEX(contadorPrograma)));
        its++;
        contadorPrograma+=(linea[2].getPalabra().length()-2);
    }

    private boolean checkPODS() {
        identificadorConstantes idecons= new identificadorConstantes();
        if(linea.length==3){
            if(linea[0].getTipo()==3){
                System.out.println("Entro aqui en DS db");
                if(linea[1].getPalabra().compareToIgnoreCase("db")==0){
                    if(idecons.esCC(linea[2].getPalabra())){
                        return true;
                    }
                } 
            }
        }
        return false;
   }

    private boolean checkSODS() {
        identificadorConstantes ic = new identificadorConstantes();
        convertidorCN ccn = new convertidorCN();
        if(linea.length==3){
            if(linea[0].getTipo()==3){/*simbolo*/
                if(linea[1].getPalabra().compareToIgnoreCase("db")==0){
                    /*checa que sea una constante*/
                    if(linea[2].getTipo()==5 ||linea[2].getTipo()==6 ||linea[2].getTipo()==7 ||linea[2].getTipo()==8 ||linea[2].getTipo()==10 || linea[2].getTipo()==11 ){
                        /*Tiene que ser un decimal*/
                        if(ccn.obtieneTamanio(linea[2].getPalabra())==0){
                          return true;
                        }
                    }
                }
            }
        }
       return false;
    }

    private void addSODSTabla() {
        tablaSimbolos.put(its, new tablaSim(linea[0].getPalabra(),"Variable",linea[2].getPalabra(),"db",conv.convDECaHEX(contadorPrograma)));
        its++;
        contadorPrograma++;
    }

    private boolean checkTODS() {
        identificadorConstantes ic = new identificadorConstantes();
        convertidorCN ccn = new convertidorCN();
        if(linea.length==4){
            if(linea[0].getTipo()==3){/*simbolo*/
                if(linea[2].getTipo()==5 ||linea[2].getTipo()==6 ||linea[2].getTipo()==7 ||linea[2].getTipo()==8 ||linea[2].getTipo()==10 || linea[2].getTipo()==11 ){
                    if(linea[1].getPalabra().compareToIgnoreCase("db")==0){
                        if(ccn.obtieneTamanio(linea[2].getPalabra())==0)return true;
                    }
                }
            }
       }
       return false;
    }

    private void addToDStabla() {
        tablaSimbolos.put(its, new tablaSim(linea[0].getPalabra(),"Variable",linea[2].getPalabra() +" "+ linea[3].getPalabra(),"db",conv.convDECaHEX(contadorPrograma)));
        its++;
        String inte = idec.descomponeDUP(linea[3].getPalabra());
        
        int c = idec.obCNDup(linea[2].getPalabra());
        System.out.println("C:"+c);
        System.out.println("Cp antes en db:"+contadorPrograma);
        contadorPrograma+= c;
        System.out.println("Cp despues en db:"+contadorPrograma);
    }

    private boolean checkCODS() {
       identificadorConstantes ic = new identificadorConstantes();
       convertidorCN ccn = new convertidorCN();
       if(linea.length==4){
           if(linea[0].getTipo()==3){
               if(linea[1].getPalabra().compareToIgnoreCase("db")==0){
                   if(linea[2].getTipo()==5){
                       if(linea[3].getPalabra().contains("dup")){
                           String aux  = ic.descomponeDUP(linea[3].getPalabra());
                           if(ic.esConsNum(aux) && ccn.obtieneTamanio(aux)==0){
                               return true;
                            }
                        }
                    }
                }
            }
        }
       return false;
    }

    private void addCoDStabla() {
        tablaSimbolos.put(its, new tablaSim(linea[0].getPalabra(),"Variable",linea[2].getPalabra() + " "+linea[3].getPalabra(),"db",conv.convDECaHEX(contadorPrograma)));
        its++;
        int c = idec.obCNDup(linea[2].getPalabra());
        contadorPrograma = contadorPrograma + c;
    }

    private boolean checkQODS() {
       identificadorConstantes ic= new identificadorConstantes();
       convertidorCN cn = new convertidorCN();
       if(linea.length==3){
           if(linea[0].getTipo()==3){
               if(linea[1].getPalabra().compareToIgnoreCase("dw")==0){
                   if(ic.esConsNum(linea[2].getPalabra())){
                       if(cn.obtieneTamanio(linea[2].getPalabra())==1 || cn.obtieneTamanio(linea[2].getPalabra())==0){
                           return true;
                        }
                    }
                }
            }
        }
       return false;
    }

    private void addQoDStabla() {
        tablaSimbolos.put(its, new tablaSim(linea[0].getPalabra(),"Variable",linea[2].getPalabra(),"dw",conv.convDECaHEX(contadorPrograma)));
        its++;
        contadorPrograma +=2;   
    }

    private boolean checkSexODS() {
       identificadorConstantes ic= new identificadorConstantes();
       convertidorCN cn = new convertidorCN();
       if(linea.length==4){
           if(linea[0].getTipo()==3){
               if(linea[1].getPalabra().compareToIgnoreCase("dw")==0){
                   System.out.println("entra aquí dup dw");
                   if(cn.obtieneTamanio(linea[2].getPalabra())==0){
                       if(linea[3].getPalabra().contains("dup")){
                           String aux = ic.descomponeDUP(linea[3].getPalabra());
                           if(cn.obtieneTamanio(aux)==0){
                               return true;
                           }
                       }
                   }
               }
           }
       }
       return false;
    }

    private void addSexODStabla() {
        tablaSimbolos.put(its, new tablaSim(linea[0].getPalabra(),"Variable",linea[2].getPalabra() + " "+linea[3].getPalabra(),"dw",conv.convDECaHEX(contadorPrograma)));
        its++;
        int c = idec.obCNDup(linea[2].getPalabra());
        contadorPrograma += (c*2);
    }

    private boolean checkSepODS() {
        convertidorCN cn = new convertidorCN();
        if(linea.length==3){
           if(linea[0].getTipo()==3){
               if(linea[1].getPalabra().compareToIgnoreCase("equ")==0){
                   if(cn.obtieneTamanio(linea[2].getPalabra())==1 || cn.obtieneTamanio(linea[2].getPalabra())==0)return true;
               }
            }
        }
       return false;
    }

    private void addSepODStabla() {
        tablaSimbolos.put(its, new tablaSim(linea[0].getPalabra(),"Constante",linea[2].getPalabra(),"dw",""));
        its++;
    }
    
    private void buscaError() {
        if(linea.length>4){
            /*hay elementos de sobra en la linea*/
            
           mapaAnalisis.put(imapa, new linea(generaLinea(),2));
           imapa++;
           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2));
           imdir++;
           
       }else if(linea[0].getTipo()!=3){
           /*no es simbolo el primer elemento*/
           if(linea[0].getPalabra().compareToIgnoreCase("data segment")==0 || linea[0].getPalabra().compareToIgnoreCase("DATA SEGMENT")==0){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;  
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        
                    }else{
            mapaAnalisis.put(imapa, new linea(generaLinea(),3));
            imapa++;
            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),3));
            imdir++;
           }
        }else if(linea[1].getTipo()!=1){
            /*no es la pseudoinstruccion correcta*/
            mapaAnalisis.put(imapa, new linea(generaLinea(),4));
            imapa++;
            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),4));
            imdir++;
        }else{
            /*posible error de tamaño o tipo de constante*/
            mapaAnalisis.put(imapa, new linea(generaLinea(),5));
            imapa++;
            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),5));
            imdir++;
        }
    }
    
    /*ANÁLISIS STACK SEGMENT*/

    private int checkStackSegment(int i, HashMap mapa) {
        i = buscaIniSS(i,mapa);
        contadorPrograma = 0;
        if(i<mapa.size()){
            int fSS = buscaFinSS(i,mapa);
            if(fSS>mapa.size()){
                mapaAnalisis.put(imapa, new linea("",12));
                imapa++;
                mapaDir.put(imdir,new lineaDir("----","",12));
                imdir++;
            }else{
              //Análisis de las lineas del data segment
                while(i<fSS-2){
                    i=lineaCod(i,mapa);
                    analizaLineaSS();
                }
                mapaAnalisis.put(imapa, new linea("ends",0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),"ends",0));
                imdir++;
                i=fSS;
            }
        }
        return i;
    }

    private int buscaIniSS(int i, HashMap mapa) {
        boolean sig=true;
        while(sig){
               i++;
               i=lineaCod(i,mapa);
               sig=false;
            if(i>mapa.size()){
               sig=false;
            }else if(linea.length==1){
                if(linea[0].getPalabra().compareToIgnoreCase("stack segment")==0 || linea[0].getPalabra().compareToIgnoreCase("STACK SEGMENT")==0){
                    sig=false;
                    mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                    imdir++;
                    contadorPrograma = 0;
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),7));
                        imapa++;  ///
                        mapaDir.put(imdir,new lineaDir("----",generaLinea(),7));
                        imdir++;
                    }
            }else{
                if(linea.length>1){
                    mapa.put(imapa, new linea(generaLinea(),7));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir("----",generaLinea(),7));
                    imdir++;
                }
            }
        }
       return i;
    }

    private int buscaFinSS(int i, HashMap mapa) {
        boolean sig =true;
        while(sig){
            i=lineaCod(i,mapa);
            if(linea.length==1){
                if(linea[0].getPalabra().compareToIgnoreCase("ends")==0)sig=false;
            }
        }
        if(i>mapa.size())sig=false;
        return i;    
    }

    private void analizaLineaSS() {
        if(linea.length==3){
           if(linea[0].getPalabra().compareToIgnoreCase("dw")==0){
                convertidorCN cn = new convertidorCN();
                if(cn.obtieneTamanio(linea[1].getPalabra())==0){
                    /*Análisando si hay DUP*/
                    if(linea[2].getPalabra().contains("dup")){
                        identificadorConstantes idec =new identificadorConstantes();  
                        String aux = idec.descomponeDUP(linea[2].getPalabra());
                        if(cn.obtieneTamanio(aux)==0){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma = contadorPrograma+ 2 * conv.convierteADecimal(linea[1].getPalabra());
                        }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),5));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),5));
                            imdir++;
                        }
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),10));
                        imapa++;  
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),10));
                        imdir++;
                    }
                }else{
                   mapaAnalisis.put(imapa, new linea(generaLinea(),5));
                   imapa++; 
                   mapaDir.put(imdir,new lineaDir("----",generaLinea(),5));
                   imdir++;
                }
            }else{
                mapaAnalisis.put(imapa, new linea(generaLinea(),8));
                imapa++;  
                mapaDir.put(imdir,new lineaDir("----",generaLinea(),8));
                imdir++;
            }
        }else{
            if(linea.length>3){
               mapaAnalisis.put(imapa, new linea(generaLinea(),9));
               imapa++; 
               mapaDir.put(imdir,new lineaDir("----",generaLinea(),9));
               imdir++;
            }else{
                mapaAnalisis.put(imapa, new linea(generaLinea(),14));
                imapa++;
                mapaDir.put(imdir,new lineaDir("----",generaLinea(),14));
                imdir++;
            }
        }
    }
    
    /*ANÁLISIS CODE SEGMENT  (fase 2 parte 1)*/
    private void checkCodeSegment(int i, HashMap map) {
        i=buscaIniCodeSegment(i,map);
        contadorPrograma = 0;
        if(i<map.size()){
            int endCS=buscaFinCS(i,map);
            if(endCS>map.size()){
                mapaAnalisis.put(imapa, new linea("",12));
                imapa++;
                mapaDir.put(imdir,new lineaDir("----","",12));
                imdir++;
            }else{
              //Análisis de las lineas del data segment
                while(i<endCS-2){
                    i=lineaCod(i,map);
                    analisisLineaCodeS();
                }
                mapaAnalisis.put(imapa, new linea("ends",0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),"ends",0));
                imdir++;
                i=endCS;
            }
        }
    }
    
    private int buscaIniCodeSegment(int i, HashMap map) {
        boolean sig=true;
        while(sig){
            i++;
            i=lineaCod(i,map);
            sig=false;
            if(i>map.size()){
               sig=false;
            }else if(linea.length==1){
                if(linea[0].getPalabra().compareToIgnoreCase("code segment")==0 || linea[0].getPalabra().compareToIgnoreCase("CODE SEGMENT")==0){
                    sig=false;
                    mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                    imdir++;
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),7));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),7));
                        imdir++;
                    }
            }else if(linea.length>1){
                mapaAnalisis.put(imapa, new linea(generaLinea(),7));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),7));
                imdir++;
            }
        }
       return i;
    }
  
    private int buscaFinCS(int i, HashMap map) {
        boolean sig =true;
        while(sig){
            i=lineaCod(i,map);
            if(linea.length==1){
                if(linea[0].getPalabra().compareToIgnoreCase("ends")==0)sig=false;
            }
        }
        if(i>map.size())sig=false;
        return i;  
    }
   
    private void analisisLineaCodeS() {
        if(linea.length>0){
            if(linea[0].getTipo()==4){ /*verificamos si es una etiqueta*/
                if(buscaEtiqueta(linea[0].getPalabra())){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),20));/*existe una etiqueta igual*/
                    imapa++; 
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),20));
                    imdir++;
                }else{
                    /*nueva etiqueta, se agrega a tabla de símbolos*/
                    tablaSimbolos.put(its, new tablaSim(linea[0].getPalabra(),"Etiqueta","","",conv.convDECaHEX(contadorPrograma))); 
                    its++;
                    if(linea.length>1){
                      analisisInstruccion(linea[1].getPalabra(),1);
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++; 
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                    } 
                }  
            }else if(linea[0].getTipo()==2){ /*Análisis Instrucciones*/
                analisisInstruccion(linea[0].getPalabra(),0);
            }else{
                mapaAnalisis.put(imapa, new linea(generaLinea(),15));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),15));
                imdir++;
            }
        }
    }

    private boolean buscaEtiqueta(String etiqueta) {
        for(int i=1;i<=tablaSimbolos.size();i++){
           tablaSim ts = tablaSimbolos.get(i);
           if(ts.getTipo().compareTo("Etiqueta")==0){
              if(ts.getSimbolo().equalsIgnoreCase(etiqueta)){    
                  return true;
              }   
           }
       }
       return false;
    }
    
    private boolean buscaSimb(String s){
       for(int i=1;i<=tablaSimbolos.size();i++){
           tablaSim ts = tablaSimbolos.get(i);
           if(ts.getTipo().compareTo("Variable")==0 && ts.getSimbolo().compareToIgnoreCase(s)==0){
               return true; //ya esta en la tabla de simbolos
           }
       }
       return false; //no esta en la tabla de simbolos
    }
    
    private int bTamSimb(String s){
       for(int i=1;i<=tablaSimbolos.size();i++){
           tablaSim ts = tablaSimbolos.get(i);
           if(ts.getTipo().compareTo("Variable")==0 && ts.getSimbolo().compareToIgnoreCase(s)==0){
               if(ts.getTamanio().compareToIgnoreCase("db")==0){
                   return 1;
               }else{
                   return 2;
               }
           }
       }
       return 0;
    }
      
    private void analisisInstruccion(String instruccion, int i) {
        String ins= instruccion.toLowerCase();
        int n;
        switch(ins){
/////////////////////////////////*Instrucciones sin operando*/////////////////////////////////////
            case "scasw":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16));
                    imdir++;
                }
                break;    
            case "stosw":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16));
                    imdir++;
                }
                break; 
            case "cbw":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16));
                    imdir++;
                }
                break; 
            case "cmpsb":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16));
                    imdir++;
                }
                break;    
            case "hlt":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16));
                    imdir++;
                }
                break; 
            case "lodsw":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16));
                    imdir++;
                }
                break; 
            case "popf":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16));
                    imdir++;
                }
                break; 
            case "stc":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16));
                    imdir++;
                }
                break;
//////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////Con etiquetas////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
            case "jbe":
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24));
                   imdir++;
               }else{
                   if(i+2<=linea.length){
                       if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                           imdir++;
                           contadorPrograma= contadorPrograma+4;
                         //contadorPrograma = contadorPrograma+2;
                       }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18));
                           imdir++;
                       }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2));
                       imdir++;
                   }
               }
               break;    
            
            case "jmp":
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24));
                   imdir++;
               }else{
                   if(i+2<=linea.length){
                       if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                           imdir++;
                           contadorPrograma= contadorPrograma+4;///////////creo que aqui son 2
                       }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18));
                           imdir++;
                       }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2));
                       imdir++;
                   }
               }
               break;
            
             case "jg":
               if(i==linea.length-1){
                   /*falta operando*/
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24));
                   imdir++;
               }else{
                    if(i+2<=linea.length){
                        /*Verifica que tenga una etiqueta*/
                        if(buscaEtiqueta(linea[i+1].getPalabra()+":")){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }else{
                            /*el operando no es un a etiqueta*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18));
                            imdir++;
                        }
                    } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2));
                       imdir++;
                    }
                }
               break;
               
               case "jnbe":
               if(i==linea.length-1){
                   /*falta operando*/
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24));
                   imdir++;
               }else{
                    if(i+2<=linea.length){
                        /*Verifica que tenga una etiqueta*/
                        if(buscaEtiqueta(linea[i+1].getPalabra()+":")){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }else{
                            /*el operando no es un a etiqueta*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18));
                            imdir++;
                        }
                    } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2));
                       imdir++;
                    }
                }
               break;
               
               case "jnge":
               if(i==linea.length-1){
                   /*falta operando*/
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24));
                   imdir++;
               }else{
                    if(i+2<=linea.length){
                        /*Verifica que tenga una etiqueta*/
                        if(buscaEtiqueta(linea[i+1].getPalabra()+":")){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }else{
                            /*el operando no es un a etiqueta*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18));
                            imdir++;
                        }
                    } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2));
                       imdir++;
                    }
                }
               break;
               
               case "jnp":
               if(i==linea.length-1){
                   /*falta operando*/
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24));
                   imdir++;
               }else{
                    if(i+2<=linea.length){
                        /*Verifica que tenga una etiqueta*/
                        if(buscaEtiqueta(linea[i+1].getPalabra()+":")){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }else{
                            /*el operando no es un a etiqueta*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18));
                            imdir++;
                        }
                    } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2));
                       imdir++;
                    }
                }
               break;
               
               case "jp":
               if(i==linea.length-1){
                   /*falta operando*/
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24));
                   imdir++;
               }else{
                    if(i+2<=linea.length){
                        /*Verifica que tenga una etiqueta*/
                        if(buscaEtiqueta(linea[i+1].getPalabra()+":")){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }else{
                            /*el operando no es un a etiqueta*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18));
                            imdir++;
                        }
                    } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2));
                       imdir++;
                    }
                }
               break;
               
               case "loope":
               if(i==linea.length-1){
                   /*falta operando*/
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24));
                   imdir++;
               }else{
                    if(i+2<=linea.length){
                        /*Verifica que tenga una etiqueta*/
                        if(buscaEtiqueta(linea[i+1].getPalabra()+":")){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+2;
                        }else{
                            /*el operando no es un a etiqueta*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18));
                            imdir++;
                        }
                    } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2));
                       imdir++;
                    }
                }
               
                break;
 //////////////////////////////////////////////////////////////////////////////////////
  ///////////////////          /*Con registro*///////////////////////////////////////
 //////////////////////////////////////////////////////////////////////////
             case "int":  // immediate (dec , bin o hex)
                if(i==linea.length-1){  // immediate (dec , bin o hex)
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                    imdir++;
                }else{
                   if ( linea[i+1].getTipo()==5 || linea[i+1].getTipo()==7 || linea[i+1].getTipo()==8){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma= contadorPrograma+2;
                    } else {
                     mapaAnalisis.put(imapa, new linea(generaLinea(),25));
                     imapa++;
                     mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),25));
                     imdir++;
                   }     
                } 
                break;   
                
            case "dec":  //// reg / memory  (detalles de la memoria)
                if(i==linea.length -1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                    imdir++;
                }else{
                   if(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra()) ){ //registro
                    //if(linea[i+1].getTipo()==9){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma= contadorPrograma+2;
                        
                    } else{ 
                        if(esMem(linea[i+1].getPalabra() ) ){/*variable o memoria*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),26));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),26));
                            imdir++;
                            System.out.println("No es registro ni memoria");
                        }
                    }           
                } 
                break; 
            case "div":  //// reg / memory  (detalles de la memoria)
                if(i==linea.length -1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                    imdir++;
                }else{
                   if(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra()) ){ //registro
                    //if(linea[i+1].getTipo()==9){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                        imdir++;
                        contadorPrograma= contadorPrograma+2;
                    } else{ 
                        if(esMem(linea[i+1].getPalabra() ) ){/*variable o memoria*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),26));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),26));
                            imdir++;
                            System.out.println("No es registro ni memoria");
                        }
                    }           
                } 
                break;
            case "pop":  //// reg / memory / sreg (detalles de la memoria)
                if(i==linea.length -1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                    imdir++;
                }else{
                    if(linea[i+1].getTipo()==9 ) { //registro
                       if(esReg16(linea[i+1].getPalabra())){
                                mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                imapa++;
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                imdir++;
                                contadorPrograma= contadorPrograma+2;
                        }else if(esMem(linea[i+1].getPalabra() ) ){/*variable o memoria*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),33));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                    } else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),31));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),31));
                            imdir++;
                        }
                    } else if(linea[i+1].getTipo()==17){ //SREG
                        
                        if ( esSRegVPOP(linea[i+1].getPalabra())){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+3;
                        }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),27));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),27));
                            imdir++;
                        }
                        
                    }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),26));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),26));
                            imdir++;
                            System.out.println("No es registro ni memoria");
                        } 
                } 
                break;
            case "or" : //   reg,memory / memory,reg / reg,reg / memory,inmediate / reg,inmediate
                if(i==linea.length-1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                    imdir++;
                }else {
                    if(i+3<=linea.length){
                        if(i+2==linea.length){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                            imdir++;
                        }else{
                            if(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra())){ /*comprobar que el primero sea registro */
                        
                                if(esMem(linea[i+2].getPalabra())||(linea[i+2].getTipo()==9 && esRegV(linea[i+2].getPalabra()))){/*reg,memoria*/
                                    //aqui hace falta un if para saber si sera +2 o +4 o +3 (aunque el +3 casi no lo vimos) parecido a los jump
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+3;
                                }else if(linea[i+2].getTipo()==9 && esRegV(linea[i+2].getPalabra())){/*reg,reg*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+2;
                                }else if(linea[i+2].getTipo()==5|| linea[i+2].getTipo()==7 || linea[i+2].getTipo()==8){/*reg,inmediate*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                    imdir++;
                                    if(linea[i+2].getTipo()==5){//El inmediate es decimal
                                        
                                         n=Integer.parseInt(linea[i+2].getPalabra());
                                         if(n >= 0 && n <=255){
                                             contadorPrograma+= 4;
                                             }else{
                                             contadorPrograma+= 5;
                                             }
                                         }else{
                                        contadorPrograma+=3;
                                        }
                                    contadorPrograma= contadorPrograma+3;
                                }
                            } else if( esMem(linea[i+1].getPalabra() )){/*primero es memoria*/
                                if(linea[i+2].getTipo()==9 && esRegV(linea[i+2].getPalabra())){/*memory,reg*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+4;
                                }else if(linea[i+2].getTipo()==5|| linea[i+2].getTipo()==7 || linea[i+2].getTipo()==8){/*reg,inmediate*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                    imdir++;
                                   
                                   if(linea[i+2].getTipo()==5){//El inmediate es decimal
                                       n=Integer.parseInt(linea[i+2].getPalabra());
                                       if(n >= 0 && n <=255){
                                           contadorPrograma+= 3;
                                           }else{
                                           contadorPrograma+= 4;
                                           }
                                       }else{
                                       contadorPrograma+=3;
                                       }
                                contadorPrograma= contadorPrograma+3;
                                }
                            }else if (linea[i+1].getTipo()==5|| linea[i+1].getTipo()==7 || linea[i+1].getTipo()==8){
                                mapaAnalisis.put(imapa, new linea(generaLinea(),30)); //Primer operando no puede ser IMMEDIATE
                                imapa++;
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),30));
                                imdir++;
                            }else{
                                mapaAnalisis.put(imapa, new linea(generaLinea(),28));/*NO ES REGISTRO*/
                                imapa++; 
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),28));
                                imdir++;
                            }
                        }

                    }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                            imdir++;
                    }
                }  
                
            break;
        case "test" : //   reg,memory / memory,reg / reg,reg / memory,inmediate / reg,inmediate
                if(i==linea.length-1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                    imdir++;
                }else {
                    if(i+3<=linea.length){
                        if(i+2==linea.length){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                            imdir++;
                        }else{
                            if(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra())){ /*comprobar que el primero sea registro */
                        
                                if((esMem(linea[i+2].getPalabra()))==true/*||(linea[i+2].getTipo()==9 && esRegV(linea[i+2].getPalabra()))*/){/*reg,memoria*/
                  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                    if((esMem(linea[i+2].getPalabra()))==true){
                                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                        imapa++;
                                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                        imdir++;
                                        contadorPrograma= contadorPrograma+4;
                                    }else{
                                        mapaAnalisis.put(imapa, new linea(generaLinea(),34));
                                        imapa++;
                                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),34));
                                        imdir++;
                                    }
                  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                 
                                    //mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    //imapa++;
                                }else if(linea[i+2].getTipo()==9 && esRegV(linea[i+2].getPalabra())){/*reg,reg*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+3;
                                }else if(linea[i+2].getTipo()==5|| linea[i+2].getTipo()==7 || linea[i+2].getTipo()==8){/*reg,inmediate*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                    imdir++;
                                    
                                    if(linea[i+2].getTipo()==5){//El inmediate es decimal
                                        n=Integer.parseInt(linea[i+2].getPalabra());
                                        if(n >= 0 && n <=255){
                                            contadorPrograma+= 3;
                                            }else{
                                            contadorPrograma+= 4;
                                            }
                                        }else{
                                        contadorPrograma+=3;
                                        }
                                    /*
                                    if(inmediato < = 255 && > = 0){
                                        contadorPrograma = contadorPrograma+4;
                                    }else{
                                        contadorPrograma = contadorPrograma+5;
                                    }
                                    */
                                    contadorPrograma= contadorPrograma+3;
                                }
                    } else if( esMem(linea[i+1].getPalabra() )){/*primero es memoria*/
                        if(linea[i+2].getTipo()==9 && esRegV(linea[i+2].getPalabra())){/*memory,reg*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                            contadorPrograma= contadorPrograma+2;// no hay memoria registro
                        }else if(linea[i+2].getTipo()==5|| linea[i+2].getTipo()==7 || linea[i+2].getTipo()==8){/*reg,inmediate*/
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                            imdir++;
                                
                            if(linea[i+2].getTipo()==5){//El inmediate es decimal
                                n=Integer.parseInt(linea[i+2].getPalabra());
                                if(n >= 0 && n <=255){
                                    contadorPrograma+= 3;
                                }else{
                                    contadorPrograma+= 4;
                                }
                            }else{
                                contadorPrograma+=3;
                            }
                            /*
                                    if(inmediato < = 255 && > = 0){
                                        contadorPrograma = contadorPrograma+3;
                                    }else{
                                        contadorPrograma = contadorPrograma+4;
                                    }
                                    */
                            //contadorPrograma= contadorPrograma+3;
                        }
                    }else if (linea[i+1].getTipo()==5|| linea[i+1].getTipo()==7 || linea[i+1].getTipo()==8){
                         mapaAnalisis.put(imapa, new linea(generaLinea(),30)); //Primer operando no puede ser IMMEDIATE
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),30));
                            imdir++;
                    }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),28));/*NO ES REGISTRO*/
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),28));
                            imdir++;
                            }
                        }
                    }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                            imdir++;
                    }

            }  
                
            break;
            case "lea" : //   reg,memory 
                if(i==linea.length-1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                    imdir++;
                }else {
                    if(i+3<=linea.length){
                        if(i+2==linea.length){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),17)); /* FALTAN OPERANDOS  */
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                            imdir++;
                        }else{
                            if(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra())){ /*comprobar que el primero sea registro */
                        
                                if(esMem(linea[i+2].getPalabra())||(linea[i+2].getTipo()==9 && esRegV(linea[i+2].getPalabra()))){/*reg,memoria*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+4;
                                }
                            }else if (esMem(linea[i+1].getPalabra())||(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra()))){
                                mapaAnalisis.put(imapa, new linea(generaLinea(),32)); //Primer operando no puede ser MEMORY
                                imapa++; 
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),32));
                                imdir++;
                            }else{
                                mapaAnalisis.put(imapa, new linea(generaLinea(),28));/*NO ES REGISTRO*/
                                imapa++; 
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),28));
                                imdir++;
                            }
                        }
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),17));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                        imdir++;
                    }

            }      
            break;
            
            case "sar":  // memory,inmediate / reg,immediate / memory,cl / reg,cl 
                if(i==linea.length-1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17));
                    imdir++;
                }
                
            break;
            
            default:
                mapaAnalisis.put(imapa, new linea(generaLinea(),15));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),15));
                imdir++;
        }
    }
   
    private int checaMem2(String re){
       if(ic.esConsNum(re)){
          return conv.obtieneTamanio(re)+1;
       }
       return 0;
    }
    
    private boolean esSRegVPOP(String sreg) {
         if(sreg.compareToIgnoreCase("ss")==0||sreg.compareToIgnoreCase("ds")==0||sreg.compareToIgnoreCase("es")==0 ){
           return true;
       }else{
           return false;
       }
    }

    private int bTamMem(String mem){
       if(buscaSimb(mem)){
           return bTamSimb(mem);
        }else{
           if(mem.startsWith("[") && mem.endsWith("]")){
               String auxi = "";
               int bandMas = 0;
               int tamanio=0;
               for(int i=1;i<mem.length()-1;i++){
                   char x = mem.charAt(i);
                 if((x>=48 && x<=57) || (x>=65 && x<=90) || (x>=97 && x<=122)){ //si es digito o caracter
                       auxi = auxi + x;
                   }else{
                       if(x==32){//"espacio en blanco"
                           //bandMas= 1;
                       }else{
                           if(x==43){// "+"
                 //              System.out.println(auxi);
                                 if(ic.esConsNum(auxi)){
                                    tamanio = tamanio + conv.convierteADecimal(auxi);
                                 }
                                 auxi="";
                           }else{
                               return -1;
                           }
                       }    
                   }
                  
               }
               if(ic.esConsNum(auxi)){
                  tamanio = tamanio + conv.convierteADecimal(auxi);
               }
               if(tamanio==0){
                   return 0;
               }else{
                  return checaMem2(Integer.toString(tamanio));   
               }
          }else{
   
           return -1;
          }
       }
    }
    
    private boolean esMem(String mem){
        /*debemos comprobar aquí el tamaño de variables y memoria */
        boolean ban=false;
       if(buscaSimb(mem)){/*Busca si es variable*/
           return ban=true;
       }else{
           if(mem.startsWith("[") && mem.endsWith("]")){
//               System.out.println("Checa corchetes memoria CS");
                String auxi = "", auxi2="";
                int acum=0; /*1=1 oper bien, 2=2oper bien ...*/
                for(int i=1;i<mem.length()-1;i++){
                    char x = mem.charAt(i);
                    if(Character.isDigit(x) || Character.isLetter(x)){
                        auxi+=x; 
                    }else{ 
                        if(x!=32){/*' '*/
                            if(x==43){/*'+'*/
                                System.out.println(auxi+"Checa auxi");
                                if(checaMEM(auxi)){/*falta el d8 y d16*/
                                    
                                    
                                    return ban=false;
                                }else{
                                    auxi2=auxi;
                                    /*falta checar que si pueda sumarse
                                    [BX + SI],[BX + DI],[BP + SI],[BP + DI] */
                                    
                                    auxi="";
                                    return ban=true;
                                }
                            }
                        }
                    }
                }
                if(auxi.length()==2){/*Checa: [BX],[SI],[DI], d16* */
                    if(regMemo(auxi))return ban=true;
                }else{
                    return ban;
                }
               return ban;
            }else{
                return ban;
            }
       } 
    }
   
    private boolean regMemo(String re){
       if(re.compareToIgnoreCase("si")==0 || re.compareToIgnoreCase("di")==0 || re.compareToIgnoreCase("bx")==0){
            return true;     
       }
       return false;
    }
   
    /*private boolean checaMEM(String re){
       if(re.compareToIgnoreCase("si")==0 || re.compareToIgnoreCase("di")==0 || re.compareToIgnoreCase("bp")==0 || re.compareToIgnoreCase("bx")==0 || ic.esConsNum(re)){
            return true;     
       }
       return false;
    }*/
    private boolean checaMEM(String re){
        System.out.println("re:"+re);
        //AX, BX, CX, DX, AH, AL, BL, BH, CH, CL, DH, DL, DI, SI, BP, SP
        //BX, SI, DI, BP  memoria 

       if(re.compareToIgnoreCase("ax")==0 || re.compareToIgnoreCase("cx")==0 || re.compareToIgnoreCase("dx")==0 || re.compareToIgnoreCase("ah")==0 
               || re.compareToIgnoreCase("bh")==0 || re.compareToIgnoreCase("cl")==0 ||re.compareToIgnoreCase("dh")==0 || re.compareToIgnoreCase("dl")==0 
               ||re.compareToIgnoreCase("sp")==0 ){
            return true;     
       } 
       return false;
    }  
    
 
    //AX, BX, CX, DX, AH, AL, BL, BH, CH, CL, DH, DL, DI, SI, BP, SP.
    private boolean esRegV(String reg){
       if(reg.compareToIgnoreCase("ax")==0 || reg.compareToIgnoreCase("bx")==0 ||
               reg.compareToIgnoreCase("cx")==0 || reg.compareToIgnoreCase("dx")==0  || 
               reg.compareToIgnoreCase("ah")==0 || reg.compareToIgnoreCase("bh")==0 || reg.compareToIgnoreCase("ch")==0 ||
               reg.compareToIgnoreCase("al")==0  || reg.compareToIgnoreCase("bl")==0 || reg.compareToIgnoreCase("cl")==0 ||
               reg.compareToIgnoreCase("dh")==0 || reg.compareToIgnoreCase("dl")==0 || reg.compareToIgnoreCase("di")==0 || 
               reg.compareToIgnoreCase("si")==0 || reg.compareToIgnoreCase("bp")==0 || reg.compareToIgnoreCase("sp")==0 ){
           return true;
       }else{
           return false;
       }
    }
    
    private boolean esReg16(String reg){
       if(     reg.compareToIgnoreCase("ax")==0 || reg.compareToIgnoreCase("bx")==0 ||
               reg.compareToIgnoreCase("cx")==0 || reg.compareToIgnoreCase("dx")==0 || 
               reg.compareToIgnoreCase("di")==0 || reg.compareToIgnoreCase("si")==0 || 
               reg.compareToIgnoreCase("bp")==0 || reg.compareToIgnoreCase("sp")==0    ){
           return true;
       }else{
           return false;
       }
    }
    
    //DS,ES,SS,CS
    private boolean esSRegV(String reg){
       if(reg.compareToIgnoreCase("ss")==0||reg.compareToIgnoreCase("cs")==0||reg.compareToIgnoreCase("ds")==0||reg.compareToIgnoreCase("es")==0 ){
           return true;
       }else{
           return false;
       }
    }
    
    private int checaCons(String c){
       if(ic.esCC(c)){
           return c.length()-2;
       }else{
           int c1 = conv.convierteADecimal(c);
           int t = conv.obtieneTamanio(Integer.toString(c1))+1;
           if(t==3){
               return -1;
           }else{
               return t;
           }
       }
   }
    
    
    /*MÉTDOSOS PARA FUNCIÓN GENERAL*/
    
    String imprimeTexto(HashMap mapa) {
        linea li;
        String texto ="";
        for(int i=1;i<=mapa.size();i++){
           li = (linea) mapa.get(i);
           texto=texto+ li.getLinea() +"-->"+ Errores(li.getError())+"\n";
        }
       return texto;
    }

    private String Errores(int error) {
        if (error ==0 ){
            return "~CORRECTO~";
        }else if(error == 1){
            return "No se encuentra inicializado el DATA SEGMENT";
        }else if(error == 2){
            return "Elementos extra en la línea";
        }else if(error == 3){
            return "No es símbolo el primer elemento";
        }else if(error == 4){
            return "Pseudoinstricción incorrecta";
        }else if(error == 5){
            return "Error: Tamaño incorrecto o tipo de constante";
        }else if(error == 6){
            return "No hay Data ni Stack segment";
        }else if(error == 7){
            return "No se encuentra inicializado el STACK SEGMENT";
        }else if(error == 8){
            return "No es pseudoinstrución el primer elemento de la línea";
        }else if(error == 9){
            return "Elementos extra en la línea";
        }else if(error == 10){
            return "No es pseudonsitrcicción DUP";
        }else if(error == 12){
            return "No se localiza el ENDS";
        }else if(error == 14){
            return "Elementos insuficientes";
        }else if(error == 15){
            return "Instrucción no identificada";
        }else if(error == 16){
            return "La instrucción no debe llevar operandos";
        }else if(error == 17){
            return "Faltan operandos";
        }else if(error == 18){
            return "Operando incorrecto";
        }else if(error == 19){
            return "El primer elemento de la línea no es una instrucción";
        }else if(error == 20){
            return "Existe una etiqueta igual";
        }else if(error == 21){
            return "No existe el símbolo en la tabla de símbolos";
        }else if(error == 22){
            return "Error de tamaño en el desplazamiento";
        }else if(error == 23){
            return "Error en el tamaño de la constante";
        }else if(error == 24){
            return "Faltan operandos, se esperaba una etiqueta";
        }else if(error == 25){
            return "Operando incorrecto, se esperaba un immediate";
        }else if(error == 26){
            return "Tamaño de memoria incorrecto";
        }else if(error == 27){
            return "No admitido 'CS'"; 
        }else if(error == 28){
            return "Error en los operandos, no son REG ni MEMORIA";
        }else if(error == 29){
            return "El segundo operando no es REG ni MEMORIA";
        }
        else if(error == 30){
            return "El primer operando no puede ser IMMEDIATE";
        }else if(error == 31){
            return "No admitido REG no es de 16 bits ";
        }else if(error == 32){
            return "El primer operando no puede ser MEMORY ";
        }else if(error == 33){
            return "~CORRECTO~ (es memoria) ";
        }else if(error == 34){
            return "ERROR en formato de memoria ";
        }
        return "";
        
    }

   
    
}
