package analisadorlexico;

import static analisadorlexico.Tokens.*;

%%
%class Lexer
%type Tokens

DUP=[DUP-dup]+
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ , \t, \r]+
H=[h-H]+
Cadena=[a-zA-Z$.()?¿¡!%#*-_:, ]+
PseuDB=[DB,db]+
PseuDW=[dw,DW]+

%{
 public String lexeme;

%}

%%

({PseuDB}) {lexeme=yytext(); return Pseudoinstruccion_D_B;}
({PseuDW}) {lexeme=yytext(); return Pseudoinstruccion_D_W;}

"data segment"|"DATA SEGMENT" {lexeme=yytext(); return Pseudoinstruccion;}
"code segment"|"CODE SEGMENT" {lexeme=yytext(); return Pseudoinstruccion;}
"stack segment"|"STACK SEGMENT" {lexeme=yytext(); return Pseudoinstruccion;}

({DUP}"(""'"{Cadena}"'"")"+) {lexeme=yytext(); return Pseudoinstruccion_Dup_Caracter;}
({DUP}"("{Cadena}")"+) {lexeme=yytext(); return Pseudoinstruccion_Dup_Caracter;}
({DUP}"("{D}"H"")"+) {lexeme=yytext(); return Pseudoinstruccion_Dup_H;}
({DUP}"("{D}")"+) {lexeme=yytext(); return Pseudoinstruccion_Dup_0;}

(\"{Cadena}"\") | (\'{Cadena}"\') {lexeme=yytext(); return Constante_Caracter_CS;}
(\"{Cadena}"$"\") | (\'{Cadena}"$"\') {lexeme=yytext(); return Constante_Caracter_CD;}
(\"{Cadena}"$") {lexeme=yytext(); return ConstanteCadInc;}
(\"{Cadena}"$"\") {lexeme=yytext(); return Constante_Caracter_CD;}
("'"{Cadena}"'") {lexeme=yytext(); return Constante_Caracter_CS;}




("dupy("{D}")"+) {lexeme=yytext(); return PseudoinstruccionInc;}

("(-"{D}+")") | {D}+ {lexeme=yytext(); return Constante_Numerica_D;}
("-0"{D}"H") | ("0"{D}"H") {lexeme=yytext(); return Constante_Numerica_H;}
({D}"h") | ({D}"H") {lexeme=yytext(); return Constante_Numerica_H;}



(ax | AX) {lexeme=yytext(); return Registro;}
(ah | AH) {lexeme=yytext(); return Registro;}
(al | AL) {lexeme=yytext(); return Registro;}
(bx | BX) {lexeme=yytext(); return Registro;}
(bh | BH) {lexeme=yytext(); return Registro;}
(bl | BL) {lexeme=yytext(); return Registro;}
(cx | CX) {lexeme=yytext(); return Registro;}
(ch | CH) {lexeme=yytext(); return Registro;}
(cl | CL) {lexeme=yytext(); return Registro;}
(dx | DX) {lexeme=yytext(); return Registro;}
(dh | DH) {lexeme=yytext(); return Registro;}
(dl | DL) {lexeme=yytext(); return Registro;}
(si | SI) {lexeme=yytext(); return Registro;}
(di | DI) {lexeme=yytext(); return Registro;}
(sp | SP) {lexeme=yytext(); return Registro;}
(bp | BP) {lexeme=yytext(); return Registro;}

(DS | ds) {lexeme=yytext(); return Sregistro;}
(ES | es) {lexeme=yytext(); return Sregistro;}
(SS | ss) {lexeme=yytext(); return Sregistro;}
(CS | SS) {lexeme=yytext(); return Sregistro;}

(int | INT) {lexeme=yytext(); return Instruccion;}
(std | STD) {lexeme=yytext(); return Instruccion;}
(aad | AAD) {lexeme=yytext(); return Instruccion;}
(cld | CLD) {lexeme=yytext(); return Instruccion;}
(cwd | CWD) {lexeme=yytext(); return Instruccion;}
(iret | IRET) {lexeme=yytext(); return Instruccion;}
(movsw | MOVSW) {lexeme=yytext(); return Instruccion;}
(div | DIV) {lexeme=yytext(); return Instruccion;}
(imul | IMUL) {lexeme=yytext(); return Instruccion;}
(pop | POP) {lexeme=yytext(); return Instruccion;}
(xchg | XCHG) {lexeme=yytext(); return Instruccion;}
(shl | SHL) {lexeme=yytext(); return Instruccion;}
(lds | LDS) {lexeme=yytext(); return Instruccion;}
(add | ADD) {lexeme=yytext(); return Instruccion;}
(jns | JNS) {lexeme=yytext(); return Instruccion;}
(js | JS) {lexeme=yytext(); return Instruccion;}
(loopne | LOOPNE) {lexeme=yytext(); return Instruccion;}
(jae | JAE) {lexeme=yytext(); return Instruccion;}
(jcxz | JCXZ) {lexeme=yytext(); return Instruccion;}
(jl | JL ) {lexeme=yytext(); return Instruccion;}

(MOV | mov ) {lexeme=yytext(); return Instruccion;}
(ENDS | ends ) {lexeme=yytext(); return Pseudoinstruccion;}
(ENDP | endp ) {lexeme=yytext(); return Pseudoinstruccion;}
(RET | ret ) {lexeme=yytext(); return Instruccion;}


{espacio} {/*Ignore*/}
(";"(.)*) {/*Ignore*/}

("\n") {return Linea;}
({D}{L}) {lexeme=yytext(); return Simbolo;}
{L}({L}|{D}) {lexeme=yytext(); return Simbolo;}


. {return ERROR;}





