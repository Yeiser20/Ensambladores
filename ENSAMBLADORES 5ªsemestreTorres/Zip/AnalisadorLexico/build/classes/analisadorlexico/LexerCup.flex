package analisadorlexico;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char

DUP=[DUP-dup]+
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
H=[h-H]+
Cadena=[a-zA-Z$.()?¿¡!%#*-_:, ]+
Pseudtd=[dw,DW,DB,db]+

%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }

    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }

%}
%%



("db" | "DB") {return new Symbol(sym.Pseudoinstruccion_D_B, yychar, yyline, yytext());}
("dw" | "DW") {return new Symbol(sym.Pseudoinstruccion_D_W, yychar, yyline, yytext());}

"data segment"|"DATA SEGMENT" {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}
"code segment"|"CODE SEGMENT" {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}
"stack segment"|"STACK SEGMENT" {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}

({DUP}"("{D}")"+) {return new Symbol(sym.Pseudoinstruccion_Dup_0, yychar, yyline, yytext());}
({DUP}"(""'"{Cadena}"'"")"+) {return new Symbol(sym.Pseudoinstruccion_Dup_Caracter, yychar, yyline, yytext());}
({DUP}"("{Cadena}")"+) {return new Symbol(sym.Pseudoinstruccion_Dup_Caracter, yychar, yyline, yytext());}
({DUP}"("{D}"H"")"+) {return new Symbol(sym.Pseudoinstruccion_Dup_H, yychar, yyline, yytext());}

(\"{Cadena}"$"\") | (\'{Cadena}"$"\') {return new Symbol(sym.Constante_Caracter_CD, yychar, yyline, yytext());}
(\"{Cadena}"\") | (\'{Cadena}"\') {return new Symbol(sym.Constante_Caracter_CS, yychar, yyline, yytext());}
({Cadena}"$") {return new Symbol(sym.ConstanteCadInc, yychar, yyline, yytext());}
(\"{Cadena}"$"\") {return new Symbol(sym.Constante_Caracter_CD, yychar, yyline, yytext());}
("'"{Cadena}"'") {return new Symbol(sym.Constante_Caracter_CD, yychar, yyline, yytext());}



("dupy("{D}")"+) {return new Symbol(sym.PseudoinstruccionInc, yychar, yyline, yytext());}

("(-"{D}+")") | {D}+ {return new Symbol(sym.Constante_Numerica_D, yychar, yyline, yytext());}
("-0"{D}"H") | ("0"{D}"H") {return new Symbol(sym.Constante_Numerica_H, yychar, yyline, yytext());}
({D}"h") | ({D}"H") {return new Symbol(sym.Constante_Numerica_H, yychar, yyline, yytext());}
(int| INT) {return new Symbol(sym.Interrupcion, yychar, yyline, yytext());}

(ax | AX) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(ah | AH) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(al | AL) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(bx | BX) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(bh | BH) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(bl | BL) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(cx | CX) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(ch | CH) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(cl | CL) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(dx | DX) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(dh | DH) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(dl | DL) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(si | SI) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(di | DI) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(sp | SP) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(bp | BP) {return new Symbol(sym.Registro, yychar, yyline, yytext());}

(ss | SS) {return new Symbol(sym.Sregistro, yychar, yyline, yytext());}
(ds | DS) {return new Symbol(sym.Sregistro, yychar, yyline, yytext());}
(es | ES) {return new Symbol(sym.Sregistro, yychar, yyline, yytext());}
(cs | CS) {return new Symbol(sym.Sregistro, yychar, yyline, yytext());}

(int| INT) {return new Symbol(sym.Interrupcion, yychar, yyline, yytext());}
(std | STD) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(aad | AAD) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(cld | CLD) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(cwd | CWD) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(iret | IRET) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(movsw | MOVSW) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(div | DIV) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(imul | IMUL) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(pop | POP) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(xchg | XCHG) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(shl | SHL) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(lds | LDS) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(add | ADD) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(jns | JNS) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(js | JS) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(loopne | LOOPNE) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(jae | JAE) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(jcxz | JCXZ) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(jl | JL ) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}

(MOV | mov ) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(ends | ENDS ) {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}
(endp | ENDP ) {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}
(ret | RET ) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}

{espacio} {/*ignore*/}
(";"(.)*) {/*ignore*/}

({L}({L}|{D})" "{Pseudtd}" ""{Cadena}") {return new Symbol(sym.Correcta, yychar, yyline, yytext());}


({D}{L})* {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
{L}({L}|{D})* {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
. {return new Symbol(sym.ERROR, yychar, yyline, yytext());}

