
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
    private Map <Integer,lineaCodigo> mapaCod;
    private Map<Integer,tablaSim> tablaSimbolos;
    private int imapa;
    private int imdir;
    private int imCod;
    private palabra[] linea;
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
        mapaCod =new HashMap<Integer, lineaCodigo>();
        imdir=1;
        imCod=1;
    }

    Map<Integer, tablaSim> getTablaSimbolos() {
        return tablaSimbolos;
    }
    
    public String imprimeTextoDir(HashMap m){
       lineaDir li;
       String texto ="";
       System.out.println("Mapa Dir:" +m.size());
       for(int i=1;i<=m.size();i++){
           li = (lineaDir) m.get(i);
           texto=texto+ li.getDireccion()+"--"+li.getLinea() +" -- "+ Errores(li.getError())+" -- "+li.getCodigo()+"\n";
       }
       return texto;
   }
    public String imprimeTextoCod(HashMap mapa) {
       lineaCodigo li;
       String texto ="";
       System.out.println("Mapa Cod:" +mapa.size());
       for(int i=1;i<=mapa.size();i++){
           li = (lineaCodigo) mapa.get(i);
           texto=texto+ li.getDireccion()+"--"+li.getLinea() +" ....."+Errores(li.getError())+" ....."+li.getCodigo()+"\n";
           System.out.println("entro imprimeTextoCod");
       }
       
       return texto;
    }
    public Map<Integer, lineaDir> Dir(){
       return mapaDir;
   }
    public Map<Integer, lineaCodigo> Cod() {
        return mapaCod;
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
                mapaDir.put(imdir,new lineaDir("----","",12,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo("----","",12,"~")); //35= ~
                imCod++;
            }else{
                /*Análisis del Data segment*/
                while(i<EndDS-2){
                    i=lineaCod(i,map);
                    analisisLineaDS();
                }
                mapaAnalisis.put(imapa, new linea("ends",0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),"ends",0,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),"ends",0,"~")); //35= ~
                imCod++;
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
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
                        imdir++;
                        mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
                        imCod++;
                        contadorPrograma = 0;
                    }else{
                        mapaAnalisis.put(imapa,new linea(generaLinea(),1));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir("----",generaLinea(),1,"~"));
                        imdir++;
                        mapaCod.put(imCod,new lineaCodigo("----",generaLinea(),1,"~")); //35= ~
                        imCod++;
                    }
                }else{
                    if(linea.length>=1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),1));
                        imapa++; 
                        mapaDir.put(imdir,new lineaDir("----",generaLinea(),1,"~"));
                        imdir++;
                        mapaCod.put(imCod,new lineaCodigo("----",generaLinea(),1,"~")); //35= ~
                        imCod++;
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
               mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
               imdir++;
               mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
               imCod++;
               addDSTabla();///
            }else if(checkSODS()){
               mapaAnalisis.put(imapa,new linea(generaLinea(),0));
               imapa++;
               mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
               imdir++;
               mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
               imCod++;
               addSODSTabla();///
            }else if(checkTODS()){
               mapaAnalisis.put(imapa,new linea(generaLinea(),0));
               imapa++;
               mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
               imdir++;
               mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
               imCod++;
               addToDStabla();//
            }else if(checkCODS()){
                mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
                imCod++;
                addCoDStabla();///
            }else if(checkQODS()){
                mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
                imCod++;
                addQoDStabla();///
            }else if(checkSexODS()){
                mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
                imCod++;
                addSexODStabla();
            }else if(checkSepODS()){
                mapaAnalisis.put(imapa,new linea(generaLinea(),0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
                imCod++;
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
           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
           imdir++;
           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~")); //35= ~
           imCod++;
           
       }else if(linea[0].getTipo()!=3){
           /*no es simbolo el primer elemento*/
           if(linea[0].getPalabra().compareToIgnoreCase("data segment")==0 || linea[0].getPalabra().compareToIgnoreCase("DATA SEGMENT")==0){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;  
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"~"));
                        imdir++;
                        mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
                        imCod++;
                        
                    }else{
            mapaAnalisis.put(imapa, new linea(generaLinea(),3));
            imapa++;
            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),3,"~"));
            imdir++;
            mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),3,"~")); //35= ~
            imCod++;
           }
        }else if(linea[1].getTipo()!=1){
            /*no es la pseudoinstruccion correcta*/
            mapaAnalisis.put(imapa, new linea(generaLinea(),4));
            imapa++;
            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),4,"~"));
            imdir++;
            mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),4,"~")); //35= ~
            imCod++;
        }else{
            /*posible error de tamaño o tipo de constante*/
            mapaAnalisis.put(imapa, new linea(generaLinea(),5));
            imapa++;
            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),5,"~"));
            imdir++;
            mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),5,"~")); //35= ~
            imCod++;
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
                mapaDir.put(imdir,new lineaDir("----","",12,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo("----","",12,"~")); //35= ~
                imCod++;
            }else{
              //Análisis de las lineas del data segment
                while(i<fSS-2){
                    i=lineaCod(i,mapa);
                    analizaLineaSS();
                }
                mapaAnalisis.put(imapa, new linea("ends",0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),"ends",0,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo("----","ends",0,"~")); //35= ~
                imCod++;
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
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
                    imdir++;
                    mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
                    imCod++;
                    contadorPrograma = 0;
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),7));
                        imapa++;  ///
                        mapaDir.put(imdir,new lineaDir("----",generaLinea(),7,"~"));
                        imdir++;
                        mapaCod.put(imCod,new lineaCodigo("----",generaLinea(),7,"~")); //35= ~
                        imCod++;
                        
                    }
            }else{
                if(linea.length>1){
                    mapa.put(imapa, new linea(generaLinea(),7));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir("----",generaLinea(),7,"~"));
                    imdir++;
                    mapaCod.put(imCod,new lineaCodigo("----",generaLinea(),7,"~")); //35= ~
                    imCod++;
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
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"~"));
                            imdir++;
                             mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~")); //35= ~
                            imCod++;
                            contadorPrograma = contadorPrograma+ 2 * conv.convierteADecimal(linea[1].getPalabra());
                        }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),5));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),4,"~"));
                            imdir++;
                             mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),5,"~")); //35= ~
                            imCod++;
                        }
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),10));
                        imapa++;  
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),10,"~"));
                        imdir++;
                         mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),10,"~")); //35= ~
                        imCod++;
                    }
                }else{
                   mapaAnalisis.put(imapa, new linea(generaLinea(),5));
                   imapa++; 
                   mapaDir.put(imdir,new lineaDir("----",generaLinea(),5,"~"));
                   imdir++;
                    mapaCod.put(imCod,new lineaCodigo("----",generaLinea(),5,"~")); //35= ~
                    imCod++;
                }
            }else{
                mapaAnalisis.put(imapa, new linea(generaLinea(),8));
                imapa++;  
                mapaDir.put(imdir,new lineaDir("----",generaLinea(),8,"~"));
                imdir++;
                 mapaCod.put(imCod,new lineaCodigo("----",generaLinea(),8,"~")); //35= ~
                imCod++;
            }
        }else{
            if(linea.length>3){
               mapaAnalisis.put(imapa, new linea(generaLinea(),9));
               imapa++; 
               mapaDir.put(imdir,new lineaDir("----",generaLinea(),9,"~"));
               imdir++;
                mapaCod.put(imCod,new lineaCodigo("----",generaLinea(),9,"~")); //35= ~
                imCod++;
            }else{
                mapaAnalisis.put(imapa, new linea(generaLinea(),14));
                imapa++;
                mapaDir.put(imdir,new lineaDir("----",generaLinea(),14,"~"));
                imdir++;
                 mapaCod.put(imCod,new lineaCodigo("----",generaLinea(),14,"~")); //35= ~
                imCod++;
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
                mapaDir.put(imdir,new lineaDir("----","",12,"~"));
                imdir++;
                 mapaCod.put(imCod,new lineaCodigo("----","",12,"~")); //35= ~
                imCod++;
            }else{
              //Análisis de las lineas del data segment
                while(i<endCS-2){
                    i=lineaCod(i,map);
                    analisisLineaCodeS();
                }
                mapaAnalisis.put(imapa, new linea("ends",0));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),"ends",0,"~"));
                imdir++;
                 mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),"ends",0,"~")); //35= ~
                imCod++;
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
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
                    imdir++;
                     mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"~")); //35= ~
                    imCod++;
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),7));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),7,"~"));
                        imdir++;
                         mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"~")); //35= ~
                        imCod++;
                    }
            }else if(linea.length>1){
                mapaAnalisis.put(imapa, new linea(generaLinea(),7));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),7,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"~")); //35= ~
                imCod++;
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
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),20,"~"));
                    imdir++;
                     mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"~")); //35= ~
                        imCod++;
                }else{
                    /*nueva etiqueta, se agrega a tabla de símbolos*/
                    tablaSimbolos.put(its, new tablaSim(linea[0].getPalabra(),"Etiqueta","","",conv.convDECaHEX(contadorPrograma))); 
                    its++;
                    if(linea.length>1){
                      analisisInstruccion(linea[1].getPalabra(),1);
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++; 
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"~"));
                        imdir++;
                         mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"~")); //35= ~
                        imCod++;
                    } 
                }  
            }else if(linea[0].getTipo()==2){ /*Análisis Instrucciones*/
                analisisInstruccion(linea[0].getPalabra(),0);
            }else{
                mapaAnalisis.put(imapa, new linea(generaLinea(),15));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"~"));
                imdir++;
                mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"~")); //35= ~
                imCod++;
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
    
    private String buscaDirEtq(String etiqueta) {
        String dir ="";
        for(int i=1;i<=tablaSimbolos.size();i++){
           tablaSim ts = tablaSimbolos.get(i);
           if(ts.getSimbolo().compareTo(etiqueta.toLowerCase()) == 0){
               dir=ts.getDireccion();
           }
             
       }
       return dir;
    }
    private String buscaDirVar(String variable) {
        String dir ="";
        for(int i=1;i<=tablaSimbolos.size();i++){
           tablaSim ts = tablaSimbolos.get(i);
           if(ts.getSimbolo().compareTo(variable.toLowerCase())== 0){
               dir=ts.getDireccion();
           }
             
       }
        System.out.println("dir var:"+dir);
       return dir;
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
        String inm;
        int n, inmediate;
        switch(ins){
/////////////////////////////////*Instrucciones sin operando*/////////////////////////////////////
            case "scasw":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," AF"));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                 }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imdir++;
                }
                break;     
            case "stosw":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," AB"));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                        mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35," AB")); //35= ~
                        imCod++;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imdir++;
                }
                break; 
            case "cbw":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"98"));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                        mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"98")); //35= ~
                        imCod++;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imdir++;
                    mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imCod++;
                }
                break; 
            case "cmpsb":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"A6"));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                        mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"A6")); //35= ~
                        imCod++;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imdir++;
                    mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imCod++;
                }
                break;    
            case "hlt":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"F4"));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                        mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"F4")); //35= ~
                        imCod++;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imdir++;
                    mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imCod++;
                }
                break; 
            case "lodsw":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," AD "));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                        mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35," AD ")); //35= ~
                        imCod++;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imdir++;
                    mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imCod++;
                }
                break; 
            case "popf":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"9D"));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                        mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"9D")); //35= ~
                        imCod++;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imdir++;
                    mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imCod++;
                }
                break; 
            case "stc":
                if(i==linea.length-1){
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"F9"));
                        imdir++;
                        contadorPrograma =  contadorPrograma+1;
                        mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"F9")); //35= ~
                        imCod++;
                }else{
                    /*Indicamos que no lleva operandos*/
                    mapaAnalisis.put(imapa, new linea(generaLinea(),16));
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imdir++;
                    mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),16,"~"));
                    imCod++;
                }
                break;
//////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////Con etiquetas////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
            case "jbe":
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imdir++;
                   mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imCod++;
               }else{
                   if(i+2<=linea.length){
                       if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           String dirIns=conv.convDECaHEX(contadorPrograma);
                           mapaDir.put(imdir,new lineaDir(dirIns,generaLinea(),35,"0F 86 "+ dir));
                           
                           imdir++;
                           contadorPrograma= contadorPrograma+4;
                           
                       }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imdir++;
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imCod++;
                       }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imdir++;
                       mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imCod++;
                   }
               }
               break;    
            case "jmp":               
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imdir++;
                   mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imCod++;
               }else{
                   if(i+2<=linea.length){
                       if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," E9 "+ dir));
                           imdir++;
                           //checar cual es el codigo correcto 
                           contadorPrograma= contadorPrograma+3;
                           //String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35," E9 "+ dir));
                           imCod++;
                         //contadorPrograma = contadorPrograma+2;
                       }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imdir++;
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imCod++;
                       }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imdir++;
                       mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imCod++;
                   }
               }
               break;
               case "jg":
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imdir++;
                   mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imCod++;
               }else{
                   if(i+2<=linea.length){
                       if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 0F 8F "+ dir));
                           imdir++;
                           //checar cual es el codigo correcto 
                           contadorPrograma= contadorPrograma+4;
                           //String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 0F 8F "+ dir));
                           imCod++;
                         //contadorPrograma = contadorPrograma+2;
                       }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imdir++;
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imCod++;
                       }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imdir++;
                       mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imCod++;
                   }
               }
               break;
            case "jnbe":
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imdir++;
                   mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imCod++;
               }else{
                   if(i+2<=linea.length){
                       if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 0F 87 "+ dir));
                           imdir++;
                           //checar cual es el codigo correcto 
                           contadorPrograma= contadorPrograma+4;
                           //String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 0F 87 "+ dir));
                           imCod++;
                         //contadorPrograma = contadorPrograma+2;
                       }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imdir++;
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imCod++;
                       }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imdir++;
                       mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imCod++;
                   }
               }
               break; 
               case "jnge":
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imdir++;
                   mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imCod++;
               }else{
                   if(i+2<=linea.length){
                       if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 0F 8C "+ dir));
                           imdir++;
                           //checar cual es el codigo correcto 
                           contadorPrograma= contadorPrograma+4;
                           //String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 0F 8C "+ dir));
                           imCod++;
                         //contadorPrograma = contadorPrograma+2;
                       }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imdir++;
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imCod++;
                       }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imdir++;
                       mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imCod++;
                   }
               }
               break;   
            
            case "jnp":
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imdir++;
                   mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imCod++;
               }else{
                   if(i+2<=linea.length){
                       if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 0F 8B "+ dir));
                           imdir++;
                           //checar cual es el codigo correcto 
                           contadorPrograma= contadorPrograma+4;
                           //String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 0F 8B "+ dir));
                           imCod++;
                         //contadorPrograma = contadorPrograma+2;
                       }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imdir++;
                           mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imCod++;
                       }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imdir++;
                       mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imCod++;
                   }
               }
               break;
            
            case "jp":
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imdir++;
                   mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imCod++;
               }else{
                   if(i+2<=linea.length){
                       if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 0F 8A "+ dir));
                           imdir++;
                           contadorPrograma= contadorPrograma+4;
                        }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imdir++;
                        }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imdir++;
                   }
               }
               break;
            case "loope":
               if(i==linea.length-1){
                   mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                   imapa++;
                   mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imdir++;
                   mapaCod.put(imCod,new lineaCodigo(conv.convDECaHEX(contadorPrograma),generaLinea(),24,"~"));
                   imCod++;
               }else{
                   if(i+2<=linea.length){
                        if(buscaEtiqueta(linea[i+1].getPalabra()+":")){ //checar lo de 4-byte address
                           mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                           imapa++;
                           String dir = buscaDirEtq(linea[i+1].getPalabra()+":");
                           String dirIns=conv.convDECaHEX(contadorPrograma);
                           String dirCodigo= conversionCod(dirIns,dir);
                           mapaDir.put(imdir,new lineaDir(dirIns,generaLinea(),35," E1 "+ dirCodigo.replaceAll("00","")));
                           imdir++;
                           contadorPrograma= contadorPrograma+2;
                       }else{
                           mapaAnalisis.put(imapa, new linea(generaLinea(),18));
                           imapa++;
                           mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),18,"~"));
                           imdir++;
                       }
                   } else{
                       mapaAnalisis.put(imapa, new linea(generaLinea(),2));
                       imapa++;
                       mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),2,"~"));
                       imdir++;
                   }
               }
            break;    
 
           default:
                analisisInstruccion2(ins,i);
                
        }
    }
   private void analisisInstruccion2(String instruccion, int i) {
        String ins= instruccion.toLowerCase();
        String inm;
        int n, inmediate;
        switch(ins){
            case "int":  // immediate (dec , bin o hex)
                if(i==linea.length-1){  // immediate (dec , bin o hex)
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17,"~"));
                    imdir++;
                }else{
                   if ( linea[i+1].getTipo()==5 || linea[i+1].getTipo()==7 || linea[i+1].getTipo()==8){
                        String in=linea[i+1].getPalabra();
                        System.out.println("Inmediato get:"+in);
                        if(linea[i+1].getTipo()==5){/*es decimal*/
                            inm=conv.convDECaHEX( Integer.parseInt (in));//convertimos el inm a hex
                            if(Integer.parseInt (in)>= 0 && Integer.parseInt (in) <=255){ //verificamos que sea 1 byte
                                System.out.println("Inmediato hexa:"+inm);
                                mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                imapa++;
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," CD "+inm.replaceAll("00","")));
                                imdir++;
                                contadorPrograma= contadorPrograma+2;
                            }else{/*No es de un byte*/
                                mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                                imapa++;
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),23,"~"));
                                imdir++;
                            }
                        }else if(linea[i+1].getTipo()==7){//binario
                            inmediate=conv.convierteBinADec(in);
                            if(inmediate>= 0 && inmediate <=255){ //verificamos que sea 1 byte
                                inm=conv.convDECaHEX( Integer.parseInt (String.valueOf(inmediate)));
                                System.out.println("Inmediato hexa:"+inm);
                                mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                imapa++;
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," CD "+inm.replaceAll("00","")));
                                imdir++;
                                contadorPrograma= contadorPrograma+2;
                            }else{/*No es de un byte*/
                                mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                                imapa++;
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),23,"~"));
                                imdir++;
                            }
                        }else if(linea[i+1].getTipo()==8){// hexadecimal 
                            inmediate=conv.convierteHexaADec(in);
                                if(inmediate>= 0 && inmediate <=255){ //verificamos que sea 1 byte
                                    inm=conv.convDECaHEX( Integer.parseInt (String.valueOf(inmediate)));
                                    System.out.println("Inmediato hexa:"+in);
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," CD "+inm.replaceAll("00","")));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+2;
                                    }else{/*No es de un byte*/
                                        mapaAnalisis.put(imapa, new linea(generaLinea(),24));
                                        imapa++;
                                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),23,"~"));
                                        imdir++;
                                    }
                                }
                    } else {
                     mapaAnalisis.put(imapa, new linea(generaLinea(),25));
                     imapa++;
                     mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),25,"~"));
                     imdir++;
                   }     
                } 
            break;
            case "pop":  //// reg / memory / sreg (detalles de la memoria)
                if(i==linea.length -1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17,"~"));
                    imdir++;
                }else{
                    if(linea[i+1].getTipo()==9 ) { //registro
                        String reg=linea[i+1].getPalabra();
                       if(esReg16(reg)){
                                mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                imapa++;
                                if(reg.equals("AX")){
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F C0"));
                                }else if(reg.equals("CX")){
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F C1"));
                                }else if(reg.equals("DX")){
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F C2"));
                                }else if(reg.equals("BX")){
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F C3"));
                                }else if(reg.equals("SP")){
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F C4"));
                                }else if(reg.equals("BP")){
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F C5"));
                                }else if(reg.equals("SI")){
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F C6"));
                                }else if(reg.equals("DI")){
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F C7"));
                                }
                                imdir++;
                                contadorPrograma= contadorPrograma+2;
                        }
                    } else if(linea[i+1].getTipo()==17){ //SREG
                        if ( esSRegVPOP(linea[i+1].getPalabra())){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35,"Códificación en corto"));
                            imdir++;
                            contadorPrograma= contadorPrograma+1;
                        }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),27));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),27,"~"));
                            imdir++;
                        } 
                    }else if(linea[i+1].getTipo() == 3 ){/*variable o memoria*/
                        System.out.println(linea[i+1].getPalabra());
                        String memo=linea[i+1].getPalabra();
                        String dirVar = buscaDirVar(linea[i+1].getPalabra() );
                        if(dirVar.length()==0){ //no la localizo, entonces es memoria 
                            int tipoMemo=memoValida(memo);
                            if(tipoMemo==1){ //memoria sin inmediato y sin desplazamiento
                                mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                                imapa++;
                                String dirP=dirPop(memo);
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F "+dirP));
                                imdir++;
                                contadorPrograma= contadorPrograma+2;
                            }else if(tipoMemo == 2){//hay inmediato y hay desplazamiento
				String mem=descMemo(memo);//numero del inmediato int
                                mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                                imapa++;
                                String dirP=dirPop(memo);
                                inm=conv.convDECaHEX( Integer.parseInt (mem));//convertimos el inm a hex
                                if(Integer.parseInt (mem)>= 0 && Integer.parseInt (mem) <=255){ // inm=1
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F "+dirP+" "+inm.replaceAll("00","")));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+3;
                                }else{ //inm=2
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F "+dirP+" "+inm));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+4;
                                }
                            }else{//la memoría no es valida
                                mapaAnalisis.put(imapa, new linea(generaLinea(),26));
                                imapa++;
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),26,"~"));
                                imdir++;
                            }
                        }else{// es variable
                            mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                            imapa++;
                            System.out.println("Nombre var:"+linea[i+1].getPalabra());
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," 8F 06 "+ dirVar));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }
                    }else{
                            mapaAnalisis.put(imapa, new linea(generaLinea(),26));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),26,"~ memo 2"));
                            imdir++;
                            System.out.println("No es registro ni memoria");
                    } 
                } 
              break;
           
            case "dec":  //// reg / memory  (detalles de la memoria)
                if(i==linea.length -1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17,"~"));
                    imdir++;
                }else{
                    String reg=linea[i+1].getPalabra();
                
                   if(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra()) ){ //registro
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        if (reg.equals("AX")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FF C8"));
                        } else if (reg.equals("CX")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FF C9"));
                        } else if (reg.equals("DX")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FF CA"));
                        } else if (reg.equals("BX")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FF CB"));
                        } else if (reg.equals("SP")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FF CC"));
                        } else if (reg.equals("BP")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FF CD"));
                        } else if (reg.equals("SI")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FF CE"));
                        } else if (reg.equals("DI")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FF CF"));
                        } else if (reg.equals("AL")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FE C8"));
                        } else if (reg.equals("CL")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FE C9"));
                        } else if (reg.equals("DL")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FE CA"));
                        } else if (reg.equals("BL")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FE CB"));
                        } else if (reg.equals("AH")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FE CC"));
                        } else if (reg.equals("CH")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FE CD"));
                        } else if (reg.equals("DH")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FE CE"));
                        } else if (reg.equals("BH")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "FE CF"));
                        }
                        imdir++;
                        contadorPrograma= contadorPrograma+2;
                        
                    }else if(linea[i+1].getTipo() == 3 ){/*variable o memoria*/
                        System.out.println(linea[i+1].getPalabra());
                        String memo=linea[i+1].getPalabra();
                        String dirVar = buscaDirVar(linea[i+1].getPalabra() );
                        if(dirVar.length()==0){ //no la localizo, entonces es memoria 
                            int tipoMemo=memoValida(memo);
                            if(tipoMemo==1){ //memoria sin inmediato y sin desplazamiento
                                mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                                imapa++;
                                String dirDecc=dirDec(memo);
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," FE "+dirDecc));
                                imdir++;
                                contadorPrograma= contadorPrograma+2;
                            }else if(tipoMemo == 2){//hay inmediato y hay desplazamiento
				String mem=descMemo(memo);//numero del inmediato int
                                mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                                imapa++;
                                String dirDecc=dirDec(memo);
                                inm=conv.convDECaHEX( Integer.parseInt (mem));//convertimos el inm a hex
                                if(Integer.parseInt (mem)>= 0 && Integer.parseInt (mem) <=255){ // inm=1
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," FE "+dirDecc+" "+inm.replaceAll("00","")));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+3;
                                }else{ //inm=2
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," FE "+dirDecc+" "+inm));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+4;
                                }
                            }else{//la memoría no es valida
                                mapaAnalisis.put(imapa, new linea(generaLinea(),26));
                                imapa++;
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),26,"~"));
                                imdir++;
                            }
                        }else{// es variable
                            mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                            imapa++;
                            System.out.println("Nombre var:"+linea[i+1].getPalabra());
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," FF 0E "+ dirVar));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),26));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),26,"~"));
                            imdir++;
                            System.out.println("No es registro ni memoria");
                    }       
                } 
                break; 
            case "div":  //// reg / memory  (detalles de la memoria)
                if(i==linea.length -1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17,"~"));
                    imdir++;
                }else{
                    String reg=linea[i+1].getPalabra();
                   if(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra()) ){ //registro
                        mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                        imapa++;
                        if (reg.equals("AX")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F7 F0"));
                        } else if (reg.equals("CX")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F7 F1"));
                        } else if (reg.equals("DX")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F7 F2"));
                        } else if (reg.equals("BX")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F7 F3"));
                        } else if (reg.equals("SP")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F7 F4"));
                        } else if (reg.equals("BP")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F7 F5"));
                        } else if (reg.equals("SI")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F7 F6"));
                        } else if (reg.equals("DI")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F7 F7"));
                        } else if (reg.equals("AL")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F6 F0"));
                        } else if (reg.equals("CL")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F6 F1"));
                        } else if (reg.equals("DL")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F6 F2"));
                        } else if (reg.equals("BL")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F6 F3"));
                        } else if (reg.equals("AH")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F6 F4"));
                        } else if (reg.equals("CH")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F6 F5"));
                        } else if (reg.equals("DH")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F6 F6"));
                        } else if (reg.equals("BH")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "F6 F7"));
                        }
                        imdir++;
                        contadorPrograma= contadorPrograma+2;
                        
                    }else if(linea[i+1].getTipo() == 3 ){/*variable o memoria*/
                        System.out.println(linea[i+1].getPalabra());
                        String memo=linea[i+1].getPalabra();
                        String dirVar = buscaDirVar(linea[i+1].getPalabra() );
                        if(dirVar.length()==0){ //no la localizo, entonces es memoria 
                            int tipoMemo=memoValida(memo);
                            if(tipoMemo==1){ //memoria sin inmediato y sin desplazamiento
                                mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                                imapa++;
                                String dirD=dirDiv(memo);
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," F6 "+dirD));
                                imdir++;
                                contadorPrograma= contadorPrograma+2;
                            }else if(tipoMemo == 2){//hay inmediato y hay desplazamiento
				String mem=descMemo(memo);//numero del inmediato int
                                mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                                imapa++;
                                String dirD=dirDiv(memo);
                                inm=conv.convDECaHEX( Integer.parseInt (mem));//convertimos el inm a hex
                                if(Integer.parseInt (mem)>= 0 && Integer.parseInt (mem) <=255){ // inm=1
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," F6 "+dirD+" "+inm.replaceAll("00","")));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+3;
                                }else{ //inm=2
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," F6 "+dirD+" "+inm));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+4;
                                }
                            }else{//la memoría no es valida
                                mapaAnalisis.put(imapa, new linea(generaLinea(),26));
                                imapa++;
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),26,"~"));
                                imdir++;
                            }
                        }else{// es variable
                            mapaAnalisis.put(imapa, new linea(generaLinea(),33));
                            imapa++;
                            System.out.println("Nombre var:"+linea[i+1].getPalabra());
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),35," F6 06 "+ dirVar));
                            imdir++;
                            contadorPrograma= contadorPrograma+4;
                        }   
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),26));
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),26,"~"));
                            imdir++;
                            System.out.println("No es registro ni memoria");
                    }       
                } 
                break; 
                 case "or": //   reg,memory / memory,reg / reg,reg / memory,inmediate / reg,inmediate
                if (i == linea.length - 1) {
                    mapaAnalisis.put(imapa, new linea(generaLinea(), 17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 17, "~"));
                    imdir++;
                } else {
                    if (i + 3 <= linea.length) {
                        if (i + 2 == linea.length) {
                            mapaAnalisis.put(imapa, new linea(generaLinea(), 17));/* FALTAN OPERANDOS  */
                            imapa++;
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 17, "~"));
                            imdir++;
                        } else {
                            if (linea[i + 1].getTipo() == 9 && esRegV(linea[i + 1].getPalabra())) {
                                /*comprobar que el primero sea registro */

                                if (esMem(linea[i + 2].getPalabra()) || (linea[i + 2].getTipo() == 9 && esRegV(linea[i + 2].getPalabra()))) {/*reg,memoria*/
                                    //aqui hace falta un if para saber si sera +2 o +4 o +3 (aunque el +3 casi no lo vimos) parecido a los jump
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                    String reg = linea[i + 1].getPalabra();
                                    String var = buscaDirVar(linea[i + 2].getPalabra());
                                    if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C8 "+var));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C9 "+var));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B CA "+var));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B CB "+var));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B CC "+var));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B CD "+var));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B CE "+var));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B CF "+var));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C8 "+var));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C9 "+var));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A CA "+var));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A CB "+var));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A CC "+var));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A CD "+var));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A CE "+var));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A CF "+var));
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
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "no toca en esta fase"));
                                    imdir++;
                                    contadorPrograma = contadorPrograma + 2;
                                } else if (linea[i + 2].getTipo() == 9 && esRegV(linea[i + 2].getPalabra())) {/*reg,reg*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                    String reg = linea[i + 1].getPalabra();
                                    String reg2 = linea[i + 2].getPalabra();
                                    if (reg.equals("AX")) {

                                        if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (reg.equals("CX")) {
                                            if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        } else {
                                            if (reg.equals("DX")) {
                                                if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                            } else {
                                                if (reg.equals("BX")) {
                                                    if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0BC9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0B D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                           if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A C9"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "0A D2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A C9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A D2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A DB"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A E4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A ED"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A FF"));
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
                                    imdir++;
                                    contadorPrograma = contadorPrograma + 2;
                                } else if (linea[i + 2].getTipo() == 5 || linea[i + 2].getTipo() == 7 || linea[i + 2].getTipo() == 8) {/*reg,inmediate*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                  inm = linea[i + 2].getPalabra();
                                    String reg = linea[i + 1].getPalabra();
                                    if(linea[i + 2].getTipo() == 5){
                                        String inme = conv.convDECaHEX(Integer.parseInt(inm));
                                        if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inme));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inme));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inme));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inme));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inme));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inme));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inme));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inme));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inme));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inme));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inme));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inme));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inme));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inme));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inme));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inme));
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
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    }else if(linea[i + 2].getTipo() == 7){
                                         String inme = conv.convierteABinario(Integer.parseInt(inm));
                                         String inmed = conv.convDECaHEX(Integer.parseInt(inme));
                                         if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inmed));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inmed));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inmed));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inmed));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inmed));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inmed));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inmed));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inmed));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inmed));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inmed));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inmed));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inmed));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inmed));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inmed));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inmed));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inmed));
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
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    }
                                    
                                    
                                    if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inm));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inm));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inm));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inm));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inm));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inm));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inm));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0B "+inm));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inm));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inm));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inm));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inm));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inm));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inm));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inm));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0A "+inm));
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
                                                    }
                                                }
                                            }
                                        }
                                    }


                                    
                                    
                                    
                                    imdir++;
                                    if (linea[i + 2].getTipo() == 5) {//El inmediate es decimal

                                        n = Integer.parseInt(linea[i + 2].getPalabra());
                                        if (n >= 0 && n <= 255) {
                                            contadorPrograma += 4;
                                        } else {
                                            contadorPrograma += 5;
                                        }
                                    } else {
                                        //contadorPrograma+=2;
                                    }
                                    // contadorPrograma= contadorPrograma+3;
                                }
                            } else if (esMem(linea[i + 1].getPalabra())) {/*primero es memoria*/
                                if (linea[i + 2].getTipo() == 9 && esRegV(linea[i + 2].getPalabra())) {/*memory,reg*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                    String var = buscaDirVar(linea[i + 1].getPalabra());
                                    String reg = linea[i + 2].getPalabra();
                                    
                                    if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, " 09 C0 "+var));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 09 C9 "+var));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 09 D2 "+var));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 09 DB "+var));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 09 E4 "+var));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 09 ED "+var));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 09 F6 " +var));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 09 FF "+var));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 08 C0 "+var));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 08 C9 "+var));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 08 D2 "+var));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 08 DB "+var));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, " 08 E4 "+var));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, " 08 ED "+var));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, " 08 F6 " +var));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35," 08 FF "+var));
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
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    
                                   // mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "no toca en esta fase"));
                                    
                                    imdir++;
                                    contadorPrograma = contadorPrograma + 4;
                                } else if (linea[i + 2].getTipo() == 5 || linea[i + 2].getTipo() == 7 || linea[i + 2].getTipo() == 8) {/*reg,inmediate*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                    
                                   inm = linea[i + 2].getPalabra();
                                    String var = buscaDirVar(linea[i + 1].getPalabra());
                                    
                                    if(linea[i + 2].getTipo() == 5){
                                        String inme = conv.convDECaHEX(Integer.parseInt(inm));
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " "+inme));

                                    }else if(linea[i + 2].getTipo() == 7){
                                         String inme = conv.convierteABinario(Integer.parseInt(inm));
                                         String inmed = conv.convDECaHEX(Integer.parseInt(inme));
                                         
                                         mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " "+inmed));
                                         
                                    }else if(linea[i + 2].getTipo() == 8){
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " "+inm));
                                    }
                                    
                                    
                                    imdir++;

                                    if (linea[i + 2].getTipo() == 5) {//El inmediate es decimal
                                        n = Integer.parseInt(linea[i + 2].getPalabra());
                                        if (n >= 0 && n <= 255) {
                                            contadorPrograma += 3;
                                        } else {
                                            contadorPrograma += 4;
                                        }
                                    } else {
                                        //contadorPrograma+=2;
                                    }
                                    //contadorPrograma= contadorPrograma+3;
                                }
                            } else if (linea[i + 1].getTipo() == 5 || linea[i + 1].getTipo() == 7 || linea[i + 1].getTipo() == 8) {
                                mapaAnalisis.put(imapa, new linea(generaLinea(), 30)); //Primer operando no puede ser IMMEDIATE
                                imapa++;
                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 30, "no toca en esta fase"));
                                imdir++;
                            } else {
                                mapaAnalisis.put(imapa, new linea(generaLinea(), 28));/*NO ES REGISTRO*/
                                imapa++;
                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 28, "no toca en esta fase"));
                                imdir++;
                            }
                        }

                    } else {
                        mapaAnalisis.put(imapa, new linea(generaLinea(), 17));/* FALTAN OPERANDOS  */
                        imapa++;
                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 17, "no toca en esta fase"));
                        imdir++;
                    }
                }

                break;
                
                case "lea": //   reg,memory 
                if (i == linea.length - 1) {
                    mapaAnalisis.put(imapa, new linea(generaLinea(), 17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 17, "no toca en esta fase"));
                    imdir++;
                } else {
                    if (i + 3 <= linea.length) {
                        if (i + 2 == linea.length) {
                            mapaAnalisis.put(imapa, new linea(generaLinea(), 17));
                            /* FALTAN OPERANDOS  */
                            imapa++;
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 17, "no toca en esta fase"));
                            imdir++;
                        } else {
                            if (linea[i + 1].getTipo() == 9 && esRegV(linea[i + 1].getPalabra())) {
                                /*comprobar que el primero sea registro */

                                if (esMem(linea[i + 2].getPalabra()) || (linea[i + 2].getTipo() == 9 && esRegV(linea[i + 2].getPalabra()))) {/*reg,memoria*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                    
                                    String reg = linea[i + 1].getPalabra();
                                    String var = buscaDirVar(linea[i + 2].getPalabra());
                                    
                        if (reg.equals("AX")) {
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 06 "+var));
                        } else {
                            if (reg.equals("CX")) {
                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 0E "+var));
                            } else {
                                if (reg.equals("DX")) {
                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 16 "+var));
                                } else {
                                    if (reg.equals("BX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 1E "+var));
                                    } else {
                                        if (reg.equals("SP")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 26 "+var));
                                        } else {
                                            if (reg.equals("BP")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 2E "+var));
                                            } else {
                                                if (reg.equals("SI")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 36 "+var));
                                                } else {
                                                    if (reg.equals("DI")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 3E "+var));
                                                    } else {
                                                        if (reg.equals("AL")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 06 "+var));
                                                        } else {
                                                            if (reg.equals("CL")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 0E "+var));
                                                            } else {
                                                                if (reg.equals("DL")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 16 "+var));
                                                                } else {
                                                                    if (reg.equals("BL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 1E "+var));
                                                                    } else {
                                                                        if (reg.equals("AH")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 26 "+var));
                                                                        } else {
                                                                            if (reg.equals("CH")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 2E "+var));
                                                                            } else {
                                                                                if (reg.equals("DH")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 36 "+var));
                                                                                } else {
                                                                                    if (reg.equals("BH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "8D 3E "+var));
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
                                        }
                                    }
                                }
                            }
                        }
                                    
                                    imdir++;
                                    contadorPrograma = contadorPrograma + 4;
                                }
                            } else if (esMem(linea[i + 1].getPalabra()) || (linea[i + 1].getTipo() == 9 && esRegV(linea[i + 1].getPalabra()))) {
                                mapaAnalisis.put(imapa, new linea(generaLinea(), 32)); //Primer operando no puede ser MEMORY
                                imapa++;
                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 32, "no toca en esta fase"));
                                imdir++;
                            } else {
                                mapaAnalisis.put(imapa, new linea(generaLinea(), 28));/*NO ES REGISTRO*/
                                imapa++;
                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 28, "no toca en esta fase"));
                                imdir++;
                            }
                        }
                    } else {
                        mapaAnalisis.put(imapa, new linea(generaLinea(), 17));
                        imapa++;
                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 17, "no toca en esta fase"));
                        imdir++;
                    }

                }
                break;
                
            case "sar":  // memory,inmediate / reg,immediate / memory,cl / reg,cl 
                if(i==linea.length-1){
                    mapaAnalisis.put(imapa, new linea(generaLinea(),17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17,"no toca en esta fase"));
                    imdir++;
                }else {
                    if(i+3<=linea.length){
                        if(i+2==linea.length){
                            mapaAnalisis.put(imapa, new linea(generaLinea(),17)); /* FALTAN OPERANDOS  */
                            imapa++;
                            mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17,"no toca en esta fase"));
                            imdir++;
                        }else{
                            if(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra())){ /*comprobar que el primero sea registro */
                        
                                if(esMem(linea[i+2].getPalabra())||(linea[i+2].getTipo()==9 && esRegV(linea[i+2].getPalabra()))){/*reg,memoria*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    imapa++;
                                    mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),0,"no toca en esta fase"));
                                    imdir++;
                                    contadorPrograma= contadorPrograma+4;
                                }
                            }else if (esMem(linea[i+1].getPalabra())||(linea[i+1].getTipo()==9 && esRegV(linea[i+1].getPalabra()))){
                                mapaAnalisis.put(imapa, new linea(generaLinea(),32)); //Primer operando no puede ser MEMORY
                                imapa++; 
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),32,"no toca en esta fase"));
                                imdir++;
                            }else{
                                mapaAnalisis.put(imapa, new linea(generaLinea(),28));/*NO ES REGISTRO*/
                                imapa++; 
                                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),28,"no toca en esta fase"));
                                imdir++;
                            }
                        }
                    }else{
                        mapaAnalisis.put(imapa, new linea(generaLinea(),17));
                        imapa++;
                        mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),17,"no toca en esta fase"));
                        imdir++;
                    }
                }
                break;
                
            case "test": //   reg,memory / memory,reg / reg,reg / memory,inmediate / reg,inmediate
                if (i == linea.length - 1) {
                    mapaAnalisis.put(imapa, new linea(generaLinea(), 17));/* FALTAN OPERANDOS  */
                    imapa++;
                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 17, "no toca en esta fase"));
                    imdir++;
                } else {
                    if (i + 3 <= linea.length) {
                        if (i + 2 == linea.length) {
                            mapaAnalisis.put(imapa, new linea(generaLinea(), 17));/* FALTAN OPERANDOS  */
                            imapa++;
                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 17, "no toca en esta fase"));
                            imdir++;
                        } else {
                            if (linea[i + 1].getTipo() == 9 && esRegV(linea[i + 1].getPalabra())) {
                                /*comprobar que el primero sea registro */

                                if ((esMem(linea[i + 2].getPalabra())) == true/*||(linea[i+2].getTipo()==9 && esRegV(linea[i+2].getPalabra()))*/) {/*reg,memoria*/
                                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                    if ((esMem(linea[i + 2].getPalabra())) == true) {
                                        mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                        imapa++;
                                        
                                        String reg = linea[i + 1].getPalabra();
                                    String var = buscaDirVar(linea[i + 2].getPalabra());
                                    if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0 "+var));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0 "+var));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F2 "+var));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3 "+var));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4 "+var));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5 "+var));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6 "+var));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7 "+var));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 FO "+var));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F1 "+var));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F2 "+var));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3 "+var));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4 "+var));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5 "+var));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6 "+var));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7 "+var));
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
                                                    }
                                                }
                                            }
                                        }
                                    }

                                        
                                        imdir++;
                                        contadorPrograma = contadorPrograma + 4;
                                    } else {
                                        mapaAnalisis.put(imapa, new linea(generaLinea(), 34));
                                        imapa++;
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 34, "no toca en esta fase"));
                                        imdir++;
                                    }
                                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                 
                                    //mapaAnalisis.put(imapa, new linea(generaLinea(),0));
                                    //imapa++;
                                } else if (linea[i + 2].getTipo() == 9 && esRegV(linea[i + 2].getPalabra())) {/*reg,reg*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                    
                                    
                                    String reg = linea[i + 1].getPalabra();
                                    String reg2 = linea[i + 2].getPalabra();
                                    if (reg.equals("AX")) {

                                        if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        if (reg.equals("CX")) {
                                            if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        } else {
                                            if (reg.equals("DX")) {
                                                if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                            } else {
                                                if (reg.equals("BX")) {
                                                    if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "0BC9"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "81 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                           if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
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
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    if (reg2.equals("AX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F0"));
                                        } else {
                                            if (reg2.equals("CX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F1"));
                                            } else {
                                                if (reg2.equals("DX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 35, "80 F2"));
                                                } else {
                                                    if (reg2.equals("BX")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                    } else {
                                                        if (reg2.equals("SP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                        } else {
                                                            if (reg2.equals("BP")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                            } else {
                                                                if (reg2.equals("SI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                } else {
                                                                    if (reg2.equals("DI")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
                                                                    } else {
                                                                        if (reg2.equals("AL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F0"));
                                                                        } else {
                                                                            if (reg2.equals("CL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F1"));
                                                                            } else {
                                                                                if (reg2.equals("DL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F2"));
                                                                                } else {
                                                                                    if (reg2.equals("BL")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F3"));
                                                                                    } else {
                                                                                        if (reg2.equals("AH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F4"));
                                                                                        } else {
                                                                                            if (reg2.equals("CH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F5"));
                                                                                            } else {
                                                                                                if (reg2.equals("DH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F6"));
                                                                                                } else {
                                                                                                    if (reg2.equals("BH")) {
                                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 F7"));
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

                                    
                                    imdir++;
                                    contadorPrograma = contadorPrograma + 2;
                                } else if (linea[i + 2].getTipo() == 5 || linea[i + 2].getTipo() == 7 || linea[i + 2].getTipo() == 8) {/*reg,inmediate*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                    
                                   inm = linea[i + 2].getPalabra();
                                   System.out.println("Inmediato test:"+inm);
                                    String reg = linea[i + 1].getPalabra();
                                    if(linea[i + 2].getTipo() == 5){
                                        String inme = conv.convDECaHEX(Integer.parseInt(inm));
                                        System.out.println("Inmediato test hxa:"+inme);
                                        if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inme));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inme));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inme));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inme));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inme));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inme));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inme));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inme));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inme));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inme));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inme));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inme));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inme));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inme));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inme));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inme));
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
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    }else if(linea[i + 2].getTipo() == 7){
				   inm = linea[i + 2].getPalabra();
                                  reg = linea[i + 1].getPalabra();
                                         String inme = conv.convierteABinario(Integer.parseInt(inm));
                                         String inmed = conv.convDECaHEX(Integer.parseInt(inme));
                                         if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inmed));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inmed));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inmed));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inmed));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inmed));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inmed));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inmed));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inmed));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inmed));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inmed));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inmed));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inmed));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inmed));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inmed));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inmed));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inmed));
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
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    }
                                    
                                    /*
                                    if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inm));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inm));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inm));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inm));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inm));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inm));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inm));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "81 "+inm));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inm));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inm));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inm));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inm));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inm));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inm));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inm));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, "80 "+inm));
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
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    */
                                    imdir++;

                                    if (linea[i + 2].getTipo() == 5) {//El inmediate es decimal
                                        n = Integer.parseInt(linea[i + 2].getPalabra());
                                        if (n >= 0 && n <= 255) {
                                            contadorPrograma += 3;
                                        } else {
                                            contadorPrograma += 4;
                                        }
                                    } else {
                                        contadorPrograma += 3;
                                    }
                                    /*
                                    if(inmediato < = 255 && > = 0){
                                        contadorPrograma = contadorPrograma+4;
                                    }else{
                                        contadorPrograma = contadorPrograma+5;
                                    }
                                     */
                                  
                                }
                            } else if (esMem(linea[i + 1].getPalabra())) {/*primero es memoria*/
                                if (linea[i + 2].getTipo() == 9 && esRegV(linea[i + 2].getPalabra())) {/*memory,reg*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                    
                                    String var = buscaDirVar(linea[i + 1].getPalabra());
                                    String reg = linea[i + 2].getPalabra();
                                    
                                    if (reg.equals("AX")) {
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, " "+var+" C8"));
                                    } else {
                                        if (reg.equals("CX")) {
                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " C9"));
                                        } else {
                                            if (reg.equals("DX")) {
                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " CA"));
                                            } else {
                                                if (reg.equals("BX")) {
                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, var+" CB"));
                                                } else {
                                                    if (reg.equals("SP")) {
                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " CC"));
                                                    } else {
                                                        if (reg.equals("BP")) {
                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, var+" CD"));
                                                        } else {
                                                            if (reg.equals("SI")) {
                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, var+" CE"));
                                                            } else {
                                                                if (reg.equals("DI")) {
                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, var+" CF"));
                                                                } else {
                                                                    if (reg.equals("AL")) {
                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " C8"));
                                                                    } else {
                                                                        if (reg.equals("CL")) {
                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, var+" C9"));
                                                                        } else {
                                                                            if (reg.equals("DL")) {
                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, var+" CA"));
                                                                            } else {
                                                                                if (reg.equals("BL")) {
                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " CB"));
                                                                                } else {
                                                                                    if (reg.equals("AH")) {
                                                                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, var+" CC"));
                                                                                    } else {
                                                                                        if (reg.equals("CH")) {
                                                                                            mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " CD"));
                                                                                        } else {
                                                                                            if (reg.equals("DH")) {
                                                                                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, var+" CE"));
                                                                                            } else {
                                                                                                if (reg.equals("BH")) {
                                                                                                    mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0, var+" CF"));
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
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    
                                    imdir++;
                                    contadorPrograma = contadorPrograma + 3;// no hay memoria registro
                                } else if (linea[i + 2].getTipo() == 5 || linea[i + 2].getTipo() == 7 || linea[i + 2].getTipo() == 8) {/*reg,inmediate*/
                                    mapaAnalisis.put(imapa, new linea(generaLinea(), 0));
                                    imapa++;
                                            
                                   inm = linea[i + 2].getPalabra();
                                    String var = buscaDirVar(linea[i + 1].getPalabra());
                                    
                                    if(linea[i + 2].getTipo() == 5){
                                        String inme = conv.convDECaHEX(Integer.parseInt(inm));
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " "+inme));

                                    }else if(linea[i + 2].getTipo() == 7){
                                         String inme = conv.convierteABinario(Integer.parseInt(inm));
                                         String inmed = conv.convDECaHEX(Integer.parseInt(inme));
                                         
                                         mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " "+inmed));
                                         
                                    }else if(linea[i + 2].getTipo() == 8){
                                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 0,var+ " "+inm));
                                    }        
                                    imdir++;

                                    if (linea[i + 2].getTipo() == 5) {//El inmediate es decimal
                                        n = Integer.parseInt(linea[i + 2].getPalabra());
                                        if (n >= 0 && n <= 255) {
                                            contadorPrograma += 3;
                                        } else {
                                            contadorPrograma += 4;
                                        }
                                    } else {
                                        contadorPrograma += 3;
                                    }
                                    /*
                                    if(inmediato < = 255 && > = 0){
                                        contadorPrograma = contadorPrograma+3;
                                    }else{
                                        contadorPrograma = contadorPrograma+4;
                                    }
                                     */
                               
                                }
                            } else if (linea[i + 1].getTipo() == 5 || linea[i + 1].getTipo() == 7 || linea[i + 1].getTipo() == 8) {
                                mapaAnalisis.put(imapa, new linea(generaLinea(), 30)); //Primer operando no puede ser IMMEDIATE
                                imapa++;
                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 30, "no toca en esta fase"));
                                imdir++;
                            } else {
                                mapaAnalisis.put(imapa, new linea(generaLinea(), 28));/*NO ES REGISTRO*/
                                imapa++;
                                mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 28, "no toca en esta fase"));
                                imdir++;
                            }
                        }
                    } else {
                        mapaAnalisis.put(imapa, new linea(generaLinea(), 17));/* FALTAN OPERANDOS  */
                        imapa++;
                        mapaDir.put(imdir, new lineaDir(conv.convDECaHEX(contadorPrograma), generaLinea(), 17, "no toca en esta fase"));
                        imdir++;
                    }

                }

                break;
            default:
                mapaAnalisis.put(imapa, new linea(generaLinea(),15));
                imapa++;
                mapaDir.put(imdir,new lineaDir(conv.convDECaHEX(contadorPrograma),generaLinea(),15,"~"));
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
//              /* System.out.println("Checa corchetes memoria CS");
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
                                    /*falta checar que si pueda sumarse*
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
               return ban=true;
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
               reg.compareToIgnoreCase("bp")==0 || reg.compareToIgnoreCase("sp")==0   ){
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
            return "CORRECTO";
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
            return " CORRECTO  (es memoria) ";
        }else if(error == 34){
            return "ERROR en formato de memoria ";
        }else if(error == 35){
            return "~";
        }
        return "";
        
    }

    private String conversionCod(String dirIns, String dir) {
        int dirinst=conv.convierteHexaADec(dirIns+"h");
        int dirEt=conv.convierteHexaADec(dir+"h");
        int rest=(dirinst-dirEt)+2;
        int complemento= (255-rest)+1;
        String cod=conv.convDECaHEX(complemento);
        return  cod;
    }

    private String descMemo(String memo) {
        String n="";
        if(memo.startsWith("[") && memo.endsWith("]")){
            char [] cadMemoDiv= memo.toCharArray();
            for(int i=0; i<cadMemoDiv.length;i++){
                if(Character.isDigit(cadMemoDiv[i])){
                    n+=cadMemoDiv[i];
                }
            }
        }
        System.out.println("Inmediato memoria:"+n);
        return n;
    }

    private int memoValida(String memo) {
        String inmediato=descMemo(memo);
     
        if( memo.compareToIgnoreCase("[SI]")== 0 || memo.compareToIgnoreCase("[DI]")==0 ||memo.compareToIgnoreCase("[BX]")==0 ||
            memo.compareToIgnoreCase("[BX+SI]")== 0 || memo.compareToIgnoreCase("[SI+BX]")==0 ||
            memo.compareToIgnoreCase("[BX+DI]")== 0 || memo.compareToIgnoreCase("[DI+BX]")==0 ||
            memo.compareToIgnoreCase("[BP+SI]")== 0 || memo.compareToIgnoreCase("[SI+BP]")==0 ||
            memo.compareToIgnoreCase("[BP+DI]")== 0 || memo.compareToIgnoreCase("[DI+BP]")==0 
            ){
            System.out.println("memoValida:1");
            return 1;     
        }else if(
            /*2:con inmediato*/
            memo.compareToIgnoreCase("[SI+"+inmediato+"]")== 0 || memo.compareToIgnoreCase("[DI+"+inmediato+"]")==0 ||memo.compareToIgnoreCase("[BX+"+inmediato+"]")==0 ||
            memo.compareToIgnoreCase("["+inmediato+"+SI]")== 0 || memo.compareToIgnoreCase("["+inmediato+"+DI]")==0 ||memo.compareToIgnoreCase("["+inmediato+"+BX]")==0 ||
            memo.compareToIgnoreCase("[BX+SI+"+inmediato+"]")==0 || memo.compareToIgnoreCase("["+inmediato+"+BX+SI]")==0 || memo.compareToIgnoreCase("[BX+"+inmediato+"+SI]")==0 ||
            memo.compareToIgnoreCase("[SI+BX+"+inmediato+"]")==0 || memo.compareToIgnoreCase("["+inmediato+"+SI+BX]")==0 || memo.compareToIgnoreCase("[SI+"+inmediato+"+BX]")==0 ||
            memo.compareToIgnoreCase("[BX+DI+"+inmediato+"]")==0 || memo.compareToIgnoreCase("["+inmediato+"+BX+DI]")==0 || memo.compareToIgnoreCase("[BX+"+inmediato+"+DI]")==0 ||
            memo.compareToIgnoreCase("[DI+BX+"+inmediato+"]")==0 || memo.compareToIgnoreCase("["+inmediato+"+DI+BX]")==0 || memo.compareToIgnoreCase("[DI+"+inmediato+"+BX]")==0 ||
            memo.compareToIgnoreCase("[BP+SI+"+inmediato+"]")==0 || memo.compareToIgnoreCase("["+inmediato+"+BP+SI]")==0 || memo.compareToIgnoreCase("[BP+"+inmediato+"+SI]")==0 ||   
            memo.compareToIgnoreCase("[SI+BP+"+inmediato+"]")==0 || memo.compareToIgnoreCase("["+inmediato+"+SI+BP]")==0 || memo.compareToIgnoreCase("[SI+"+inmediato+"+BP]")==0 ||
            memo.compareToIgnoreCase("[BP+DI+"+inmediato+"]")==0 || memo.compareToIgnoreCase("["+inmediato+"+BP+DI]")==0 || memo.compareToIgnoreCase("[BP+"+inmediato+"+DI]")==0 ||
            memo.compareToIgnoreCase("[DI+BP+"+inmediato+"]")==0 || memo.compareToIgnoreCase("["+inmediato+"+DI+BP]")==0 || memo.compareToIgnoreCase("[DI+"+inmediato+"+BP]")==0 
            ){ 
            System.out.println("memoValida:2");
            return 2;
        }
        System.out.println("memoValida:0");
        return 0; //0:No esta permitido
    }

    private String dirPop(String memo) {
        String inmediato=descMemo(memo);
        
        if(memo.compareToIgnoreCase("[SI]")== 0){
            return "04";
        }else if(memo.compareToIgnoreCase("[DI]")== 0){
            return "05";         
        }else if(memo.compareToIgnoreCase("[BX]")== 0){
            return "07";         
        }else if(memo.compareToIgnoreCase("[BX+SI]")== 0 ||memo.compareToIgnoreCase("[SI+BX]")== 0 ){
            return "00";         
        }else if(memo.compareToIgnoreCase("[BX+DI]")== 0 ||memo.compareToIgnoreCase("[DI+BX]")== 0 ){
            return "01";         
        }else if(memo.compareToIgnoreCase("[BP+SI]")== 0 || memo.compareToIgnoreCase("[SI+BP]")== 0){
            return "02";         
        }else if(memo.compareToIgnoreCase("[BP+DI]")== 0 || memo.compareToIgnoreCase("[DI+BP]")== 0){
            return "03";         
        } else if(memo.compareToIgnoreCase("[SI+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "44";
            }else return "84";//d16
        }else if(memo.compareToIgnoreCase("[DI+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "45";
            }else return "85";//d16
        }else if(memo.compareToIgnoreCase("[BP+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "46";
            }else return "86";//d16
        }else if(memo.compareToIgnoreCase("[BX+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "47";
            }else return "87";//d16
        }else if(memo.compareToIgnoreCase("[BX+SI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BX+SI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[SI+BX+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[SI+"+inmediato+"+BX]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "40";
            }else return "80";//d16
        }else if(memo.compareToIgnoreCase("[BX+DI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BX+DI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[DI+BX+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[DI+"+inmediato+"+BX]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "41";
            }else return "81";//d16
        }else if(memo.compareToIgnoreCase("[BP+SI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BP+SI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[SI+BP+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[SI+"+inmediato+"+BP]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "42";
            }else return "82";//d16
        }else if(memo.compareToIgnoreCase("[BP+DI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BP+DI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[DI+BP+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[DI+"+inmediato+"+BP]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "43";
            }else return "83";//d16
        }
        return "";
    }
    
    private String dirDiv(String memo) {
        String inmediato=descMemo(memo);
        
        if(memo.compareToIgnoreCase("[SI]")== 0){
            return "34";
        }else if(memo.compareToIgnoreCase("[DI]")== 0){
            return "35";         
        }else if(memo.compareToIgnoreCase("[BX]")== 0){
            return "37";         
        }else if(memo.compareToIgnoreCase("[BX+SI]")== 0 ||memo.compareToIgnoreCase("[SI+BX]")== 0 ){
            return "30";         
        }else if(memo.compareToIgnoreCase("[BX+DI]")== 0 ||memo.compareToIgnoreCase("[DI+BX]")== 0 ){
            return "31";         
        }else if(memo.compareToIgnoreCase("[BP+SI]")== 0 || memo.compareToIgnoreCase("[SI+BP]")== 0){
            return "32";         
        }else if(memo.compareToIgnoreCase("[BP+DI]")== 0 || memo.compareToIgnoreCase("[DI+BP]")== 0){
            return "33";         
        } else if(memo.compareToIgnoreCase("[SI+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "74";
            }else return "B4";//d16
        }else if(memo.compareToIgnoreCase("[DI+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "75";
            }else return "B5";//d16
        }else if(memo.compareToIgnoreCase("[BP+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "76";
            }else return "B6";
        }else if(memo.compareToIgnoreCase("[BX+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "77";
            }else return "B7";//d16
        }else if(memo.compareToIgnoreCase("[BX+SI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BX+SI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[SI+BX+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[SI+"+inmediato+"+BX]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "70";
            }else return "B0";//d16
        }else if(memo.compareToIgnoreCase("[BX+DI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BX+DI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[DI+BX+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[DI+"+inmediato+"+BX]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "71";
            }else return "B1";//d16
        }else if(memo.compareToIgnoreCase("[BP+SI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BP+SI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[SI+BP+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[SI+"+inmediato+"+BP]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "72";
            }else return "B2";//d16
        }else if(memo.compareToIgnoreCase("[BP+DI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BP+DI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[DI+BP+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[DI+"+inmediato+"+BP]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "73";
            }else return "B3";//d16
        }
        return "";
    }
    private String dirDec(String memo) {
        String inmediato=descMemo(memo);
        
        if(memo.compareToIgnoreCase("[SI]")== 0){
            return "0C";
        }else if(memo.compareToIgnoreCase("[DI]")== 0){
            return "0C";         
        }else if(memo.compareToIgnoreCase("[BX]")== 0){
            return "0F";         
        }else if(memo.compareToIgnoreCase("[BX+SI]")== 0 ||memo.compareToIgnoreCase("[SI+BX]")== 0 ){
            return "08";         
        }else if(memo.compareToIgnoreCase("[BX+DI]")== 0 ||memo.compareToIgnoreCase("[DI+BX]")== 0 ){
            return "09";         
        }else if(memo.compareToIgnoreCase("[BP+SI]")== 0 || memo.compareToIgnoreCase("[SI+BP]")== 0){
            return "0A";         
        }else if(memo.compareToIgnoreCase("[BP+DI]")== 0 || memo.compareToIgnoreCase("[DI+BP]")== 0){
            return "0B";         
        } else if(memo.compareToIgnoreCase("[SI+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "4C";
            }else return "8C";//d16
        }else if(memo.compareToIgnoreCase("[DI+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "4D";
            }else return "8D";//d16
        }else if(memo.compareToIgnoreCase("[BP+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "4E";
            }else return "8E";
        }else if(memo.compareToIgnoreCase("[BX+"+inmediato+"]")== 0){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "4F";
            }else return "8F";//d16
        }else if(memo.compareToIgnoreCase("[BX+SI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BX+SI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[SI+BX+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[SI+"+inmediato+"+BX]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "48";
            }else return "88";//d16
        }else if(memo.compareToIgnoreCase("[BX+DI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BX+DI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[DI+BX+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[DI+"+inmediato+"+BX]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "49";
            }else return "89";//d16
        }else if(memo.compareToIgnoreCase("[BP+SI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BP+SI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[SI+BP+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[SI+"+inmediato+"+BP]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "4A";
            }else return "8A";//d16
        }else if(memo.compareToIgnoreCase("[BP+DI+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[BP+DI+"+inmediato+"]")== 0 ||
                 memo.compareToIgnoreCase("[DI+BP+"+inmediato+"]")== 0 ||memo.compareToIgnoreCase("[DI+"+inmediato+"+BP]")== 0
                ){
            if(Integer.parseInt(inmediato)>=0 &&Integer.parseInt(inmediato)<=255 ){//d8
                return "4B";
            }else return "8B";//d16
        }
        return "";
    }
}
