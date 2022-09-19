package Proyecto;
import java_cup.runtime.Symbol;
import java.util.ArrayList;

%%
%type java_cup.runtime.Symbol
%cup
%class LexerCup
%public
%line
%column
%char
%full
%state ETIQUETAS
%state ERRORES
Letras = [A-Z_]
Hexadecimal= ("0"?((([A-F]|[0-9])+)"H"))  
Numeros = [0-9]
Signo = [+-]
Espacios = [ \t\r\f]
ENTER = [\n]
 
%{
    public ArrayList<String> nombreEtiquetas = new ArrayList<String>();
    String etiquetasconInstruccion = "";
    private Symbol symbol(int type){
        return new Symbol(type,yyline,yycolumn);
    }
    private Symbol symbol(int type, Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }

    private int TamanioVariable(String variable){
        if(variable.length()<=10){
            return 1;
        }
        return 0;
    }

%}
%%


<YYINITIAL>{
    //------------> Aqui se declararon algunas de las instrucciones en ensamblador
    "AAD" {return new Symbol(sym.cbw,yyline,yycolumn,yytext());}
    "INTO" {return new Symbol(sym.cwd,yyline,yycolumn,yytext());}
    "NOP" {return new Symbol(sym.pusha,yyline,yycolumn,yytext());}
    "RET" {return new Symbol(sym.stosw,yyline,yycolumn,yytext());}
    "IMUL" {return new Symbol(sym.lds,yyline,yycolumn,yytext());}
    "INC" {return new Symbol(sym.push,yyline,yycolumn,yytext());}
    "ADC" {return new Symbol(sym.mov,yyline,yycolumn,yytext());}
    "XOR" {return new Symbol(sym.sub,yyline,yycolumn,yytext());}
    "JCXZ" {return new Symbol(sym.ja,yyline,yycolumn,yytext());}
    "JLE" {return new Symbol(sym.jnc,yyline,yycolumn,yytext());}
    "JNG" {return new Symbol(sym.jnp,yyline,yycolumn,yytext());}
    "LOOPNE" {return new Symbol(sym.jnl,yyline,yycolumn,yytext());}

    
    //------------> Aqui se declararon los registros que se utilizan en ensamblador
    "AX" {return new Symbol(sym.ax,yyline,yycolumn,yytext());}
    "AH" {return new Symbol(sym.ah,yyline,yycolumn,yytext());}
    "AL" {return new Symbol(sym.al,yyline,yycolumn,yytext());}
    "BX" {return new Symbol(sym.bx,yyline,yycolumn,yytext());}
    "BH" {return new Symbol(sym.bh,yyline,yycolumn,yytext());}
    "BL" {return new Symbol(sym.bl,yyline,yycolumn,yytext());}
    "CX" {return new Symbol(sym.cx,yyline,yycolumn,yytext());}
    "CH" {return new Symbol(sym.ch,yyline,yycolumn,yytext());}
    "CL" {return new Symbol(sym.cl,yyline,yycolumn,yytext());}
    "DX" {return new Symbol(sym.dx,yyline,yycolumn,yytext());}
    "DH" {return new Symbol(sym.dh,yyline,yycolumn,yytext());}
    "DL" {return new Symbol(sym.dl,yyline,yycolumn,yytext());}
    "SI" {return new Symbol(sym.si,yyline,yycolumn,yytext());}
    "DI" {return new Symbol(sym.di,yyline,yycolumn,yytext());}
    "SP" {return new Symbol(sym.sp,yyline,yycolumn,yytext());}
    "BP" {return new Symbol(sym.bp,yyline,yycolumn,yytext());}
    "SS" {return new Symbol(sym.ss,yyline,yycolumn,yytext());}
    "CS" {return new Symbol(sym.cs,yyline,yycolumn,yytext());}
    "DS" {return new Symbol(sym.ds,yyline,yycolumn,yytext());}
    "ES" {return new Symbol(sym.es,yyline,yycolumn,yytext());}

    //-----------> Aqui estan declarados los separadores
    "," {return new Symbol(sym.coma,yyline,yycolumn,yytext());}
    ":" {return new Symbol(sym.doblepunto,yyline,yycolumn,yytext());}
    "(" {return new Symbol(sym.parenNA,yyline,yycolumn,yytext());}
    ")" {return new Symbol(sym.parenNC,yyline,yycolumn,yytext());}
    "[" {return new Symbol(sym.parenCA,yyline,yycolumn,yytext());}
    "]" {return new Symbol(sym.parenCC,yyline,yycolumn,yytext());}
    //------------------> Aqui con estas expresiones se declararon solo algunas pseudoinstrucciones en ensamblador

    "EQU" {return new Symbol(sym.equ,yyline,yycolumn,yytext());}
    "MACRO" {return new Symbol(sym.macro,yyline,yycolumn,yytext());}
    "ENDM" {return new Symbol(sym.endm,yyline,yycolumn,yytext());}
    "PROC" {return new Symbol(sym.proc,yyline,yycolumn,yytext());}
    "ENDP" {return new Symbol(sym.endp,yyline,yycolumn,yytext());}

    //---------------->Aqui se declararon las pseudointrucciones 

    "DATA SEGMENT" {return new Symbol(sym.datasegment,yyline,yycolumn,yytext());}
    "STACK SEGMENT" {return new Symbol(sym.stacksegment,yyline,yycolumn,yytext());}
    "CODE SEGMENT" {return new Symbol(sym.codesegment,yyline,yycolumn,yytext());}
    "ENDS" {return new Symbol(sym.ends,yyline,yycolumn,yytext());}
    "DB" {return new Symbol(sym.db,yyline,yycolumn,yytext());}
    "DW" {return new Symbol(sym.dw,yyline,yycolumn,yytext());}
    "BYTE PTR" {return new Symbol(sym.byteptr,yyline,yycolumn,yytext());}
    "WORD PTR" {return new Symbol(sym.wordptr,yyline,yycolumn,yytext());}
    "ENDP" {return new Symbol(sym.endp,yyline,yycolumn,yytext());}
    "DUP" {return new Symbol(sym.dup,yyline,yycolumn,yytext());}

    //AQUI SE DECLARA EL SIGNO POSITIVO Y NEGATIVO

    {Signo} {return new Symbol(sym.signos,yyline,yycolumn,yytext());}
    
    //----------------------> Aqui con estas expreciones regulares se ignoraran los saltos de linea y espacios 
    {Espacios} {/*Ignorar*/}
    ("NULLINE....\n") {/*Ignorar*/}

    {ENTER} {return new Symbol(sym.jump,yyline,yycolumn,yytext());}

    //-------------------->Aqui se declararon tanto las variables como constantes
    ({Letras}({Letras}|{Numeros})*) { String variable = yytext();
                                if(TamanioVariable(variable)==0){
                                    yybegin(YYINITIAL);
                                    return new Symbol(sym.variableGrande,yyline,yycolumn,yytext());                                
                                }
                                yybegin(YYINITIAL);
                                return new Symbol(sym.variable,yyline,yycolumn,yytext());
                                }
    [\"].*[\"] {return new Symbol(sym.cadenacomillasdoble,yyline,yycolumn,yytext());}
    [\'].*[\'] {return new Symbol(sym.cadenacomillassimples,yyline,yycolumn,yytext());}
    ({Numeros}+) {return new Symbol(sym.enteros,yyline,yycolumn,yytext());}
    //AQUI SE DEFINEN LAS CONSTANTES NUMERICAS
    ((("0"|"1")+)"B") {return new Symbol(sym.noBinarios,yyline,yycolumn,yytext());}
    {Hexadecimal} {return new Symbol(sym.noHexadecimales,yyline,yycolumn,yytext());}
    //---------------------------------------------------------------------------
    (({Numeros}+)(({Letras}|{Numeros})+)) {return new Symbol(sym.constanteInvalida,yyline,yycolumn,yytext());}

    //AQUI SE SE DECLARA LA EXPRESION REGULAR PARA LOS NOMBRE DE LAS ETIQUETAS
    (({Letras}({Letras}|{Numeros})*)":") {nombreEtiquetas.add(yytext());return new Symbol(sym.etiquetas,yyline,yycolumn,yytext());}

    //AQUI SE MARCARAN ERRORES DE LAS INSTRUCCIONES
    ("CBW"({Espacios}*)( "[" | "]" | [A-Z0-9¿?,.:_#\"\'])+) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" CBW NO REQUIERE PARAMETROS");}
    ("CWD"({Espacios}*)( "[" | "]" | [A-Z0-9¿?,.:_#\"\'])+) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" CWD NO REQUIERE PARAMETROS");}
    "STOSW"({Espacios}*)(( "[" | "]" | [A-Z0-9¿?,.:_#\"\'])+) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" STOSW NO REQUIERE PARAMETROS");}
    ("PUSHA"({Espacios}*)(( "[" | "]" | [A-Z0-9¿?,.:_#\"\'])+)) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" PUSHA NO REQUIERE PARAMETROS");}
    (("LDS"{Espacios}+))","(({Espacios}*)(( "[" | "]" | [A-Z0-9¿?.:_#\"\'])+)) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" PARAMETROS INCORRECTOS");}
    (("LDS"{Espacios}+)) "," ({Espacios}*) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" FALTAN AMBOS PARAMETROS");}
    (("LDS"{Espacios}+)) "," ({Espacios}*) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" FALTAN AMBOS PARAMETROS");}
    ("JA"({Espacios}+)("[".*"]")) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" SE NECESITA EL NOMBRE DE UN LABEL");}
    ("JNC"({Espacios}+)("[".*"]")) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" SE NECESITA EL NOMBRE DE UN LABEL");}
    ("JNP"({Espacios}+)("[".*"]")) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" SE NECESITA EL NOMBRE DE UN LABEL");}
    ("JNL"({Espacios}+)("[".*"]")) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" SE NECESITA EL NOMBRE DE UN LABEL");}
    ([\"](("[" | "]" | "-" | [A-Z0-9¿?,.:_#;$! \t\r\f])+)) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" FALTA CERRAR COMILLAS");}
    ([\'](("[" | "]" | "-" | [A-Z0-9¿?,.:_#;$! \t\r\f])+)) {return symbol(sym.errorEnsamblador,"ERROR EN LA LINEA: "+(yyline+1)+" FALTA CERRAR COMILLAS");}


    [^] {yybegin(YYINITIAL); return new Symbol(sym.errorEnsamblador,yyline,yycolumn,"ERROR EN LA LINEA: "+(yyline+1)+" EN EL TEXTO: "+yytext());}

}
