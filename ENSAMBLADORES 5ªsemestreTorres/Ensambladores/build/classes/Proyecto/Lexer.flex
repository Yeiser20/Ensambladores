package Proyecto;
import static Proyecto.Tokens.*;
%%
%class Lexer
%type Tokens

Letras = [A-Z_]
Binario = ([0-1]+"B")
Hexadecimal=("0"([A-F]|[0-9])+"H")  
Numeros = [0-9]
Signo = [+-]
Espacios = [ \t\r]

%{
    public String LexEnsamblador;
%}
%%

//Aqui se declararon algunas de las instrucciones en ensamblador
<YYINITIAL> CBW|CWD|PUSHA|STOSW|LDS|PUSH|MOV|SUB|JA|JNC|JNP|JNL {LexEnsamblador = yytext(); return Instrucciones;}

//Aqui se declararon los registros que se utilizan en ensamblador
<YYINITIAL> AX|AH|AL|BX|BH|BL|CX|CH|CL|DX|DH|DL|SI|DI|SP|BP|SS|CS|DS|ES| 
            "("AX")"|"("AH")"|"("AL")"|"("BX")"|"("BH")"|"("BL")"|"("CX")"|"("CH")"|"("CL")"|"("DX")"|"("DH")"|"("DL")"|
            "("SI")"|"("DI")"|"("SP")"|"("BP")"|"("SS")"|"("CS")"|"("DS")"|"("ES")"| 
            "["AX"]"|"["AH"]"|"["AL"]"|"["BX"]"|"["BH"]"|"["BL"]"|"["CX"]"|"["CH"]"| "["CL"]"|"["DX"]"|"["DH"]"|"["DL"]"|
            "["SI"]"|"["DI"]"|"["SP"]"|"["BP"]"|"["SS"]"|"["CS"]"|"["DS"]"|"["ES"]" {LexEnsamblador = yytext(); return Registros;}

<YYINITIAL> ";".* {/*Ignore*/}
<YYINITIAL>"\n" {LexEnsamblador = yytext(); return SaltoLinea;}
<YYINITIAL> "," {/*IGNORE*/}
<YYINITIAL> ":" {LexEnsamblador = yytext(); return DoblePunto;}
<YYINITIAL> "(" {LexEnsamblador = yytext(); return ParentesisAbrir;}
<YYINITIAL> ")" {LexEnsamblador = yytext(); return ParentesisCerrar;}
<YYINITIAL> "[" {LexEnsamblador = yytext(); return ParentesisCAbrir;}
<YYINITIAL> "]" {LexEnsamblador = yytext(); return ParentesisCCerrar;}

//Aqui con estas expresiones se declararon solo algunas pseudoinstrucciones en ensamblador
<YYINITIAL> "DATA SEGMENT"|"STACK SEGMENT"|"CODE SEGMENT"|ENDS|DB|DW|EQU|
<YYINITIAL> "BYTE PTR"|"WORD PTR"|{Espacios}"DUP"({Espacios}*) "["(((['].*['])|([\"].*[\"]))|{Numeros}+)"]"|
<YYINITIAL> {Espacios}"DUP"{Espacios}*"("(((['].*['])|([\"].*[\"]))|{Numeros}+|{Hexadecimal})")"|
            {Espacios}+"DUP"{Espacios}*"["(((['].*['])|([\"].*[\"]))|{Numeros}+|{Hexadecimal})"]"|
            MACRO|ENDM|PROC|ENDP {LexEnsamblador = yytext(); return PseudoInstrucciones;}

{Espacios} {/*Ignore*/}

/*Aqui con esta expresion regular {Letras}({Letras}|{Numeros})* se pueden crear variables como en 
cualquier programa por ejemplo (a, var1, aux2, etc)
*/

<YYINITIAL> {Letras}({Letras}|{Numeros})* {LexEnsamblador = yytext(); return Variables;}

/*Aqui se declararon las expresiones reguales para lo que serian las consatantes
ya sean cadenas de caracteres ('Hola mundo') numeros decimales (164, 2, 123, etc)
{Signo}?{Numeros}{Numeros}* aqui el signo de interrogacion nos dice que pude venier un signo o no 
puede venir (-1234,+233,9,etc) al igual que las constantes de tipo binario como las hexadecimales
recordando que en binario deven que terminar con "B" al final y en el caso de hexadecimal 
deve de empezar con un "0" al inicio y terminar con "H"
*/
<YYINITIAL> (['].*['])|([\"].*[\"])|{Signo}?{Numeros}{Numeros}*|{Binario}|
<YYINITIAL> {Hexadecimal} {LexEnsamblador = yytext(); return Constantes;}

<YYINITIAL> . {return ERROR;}
