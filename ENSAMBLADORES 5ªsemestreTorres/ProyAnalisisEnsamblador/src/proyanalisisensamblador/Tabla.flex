package proyanalisisensamblador;

import static analisadorlexico.Tokens.*;

%%
%class Tabla
%type Tokens

DUP=[DUP-dup]+
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ , \t, \r]+
H=[h,H]+
Cadena=[a-zA-Z$.()?¿¡!%#*-_:, ]+
PseuDB=[DB,db]+
PseuDW=[dw,DW]+
Reg=[AX,BX,CX,DX,AH,AL,BL,BH,CH,CL,DH,DL,DI,SI,BP,SP,ax,bx,cx,dx,ah,al,bl,bh,ch,cl,dh,dl,di,si,bp,sp]+
NoOp=[AAD,CLD,CWD,IRET,MOVSW,STD,aad,cld,cwd,iret,movsw,std]+
SReg=[DS,ES,SS,CS]+

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


// SIN OPERANDOS
{NoOp} {lexeme=yytext(); return Correcta;}
({NoOp}" "({D}{L})) {lexeme=yytext(); return Incorrecta;}
({NoOp}" "({D})) {lexeme=yytext(); return Incorrecta;}
({NoOp}" "({L}({L}|{D}))) {lexeme=yytext(); return Incorrecta;}


 //ETIQUETAS

((JNS | jns)" "({D}{L}":")) {lexeme=yytext(); return Correcta;}
((JNS | jns)" "({L}({L}|{D})":")) {lexeme=yytext(); return Correcta;}

((JS | jns)" "({D}{L}":")) {lexeme=yytext(); return Correcta;}
((JS | js)" "({L}({L}|{D})":")) {lexeme=yytext(); return Correcta;}

((JAE | jae)" "({D}{L}":")) {lexeme=yytext(); return Correcta;}
((JAE | jae)" "({L}({L}|{D})":")) {lexeme=yytext(); return Correcta;}

((JCXZ | jcxz)" "({L}({L}|{D})":")) {lexeme=yytext(); return Correcta;}
((JCXZ | jcxz)" "({L}({L}|{D})":")) {lexeme=yytext(); return Correcta;}

((JL | jl)" "({D}{L}":")) {lexeme=yytext(); return Correcta;}
((JL | jl)" "({L}({L}|{D})":")) {lexeme=yytext(); return Correcta;}

((LOOPNE | loopne)" "({D}{L}":")) {lexeme=yytext(); return Correcta;}
((LOOPNE | loopne)" "({L}({L}|{D})":")) {lexeme=yytext(); return Correcta;}


//REGISTRO , REGISTRO
((XCHG | xchg)" "({Reg})","({Reg})) {lexeme=yytext(); return Correcta;}


//REGISTRO,MEMORIA

((ADD | add)" "({Reg})",""["({Reg}"+"{Reg})"]") {lexeme=yytext(); return Correcta;}
((DIV | div)" "({Reg})",""["({Reg}"+"{Reg})"]") {lexeme=yytext(); return Correcta;}
((IDIV | idiv)" "({Reg})",""["({Reg}"+"{Reg})"]") {lexeme=yytext(); return Correcta;}
((IMUL | imul)" "({Reg})",""["({Reg}"+"{Reg})"]") {lexeme=yytext(); return Correcta;}
((LDS | lds)" "({Reg})",""["({Reg}"+"{Reg})"]") {lexeme=yytext(); return Correcta;}
((XCHG | xchg)" "({Reg})",""["({Reg}"+"{Reg})"]") {lexeme=yytext(); return Correcta;}


//MEMORIA, REGISTRO

((ADD | add)" ""["({Reg}"+"{Reg})"]"","({Reg})) {lexeme=yytext(); return Correcta;}
((XCHG | XCHG)" ""["({Reg}"+"{Reg})"]"","({Reg})) {lexeme=yytext(); return Correcta;}



//REGISTRO, REGISTRO

((ADD | add)" "({Reg})","({Reg})) {lexeme=yytext(); return Correcta;}



//MEMORIA, INMEDIATO

((ADD | add)" ""["({Reg}"+"{Reg})"]"","({D}{H})) {lexeme=yytext(); return Correcta;}
((ADD | add)" ""["({Reg}"+"{Reg})"]"","({D})) {lexeme=yytext(); return Correcta;}
((ADD | add)" ""["({Reg}"+"{Reg})"]"","("-"{D})) {lexeme=yytext(); return Correcta;}
((ADD | add)" ""["({Reg}"+"{Reg})"]"","({D}("b"|"B"))) {lexeme=yytext(); return Correcta;}
((ADD | add)" ""["({Reg}"+"{Reg})"]"","({D}{L}{H})) {lexeme=yytext(); return Correcta;}

((SHL | shl)" ""["({Reg}"+"{Reg})"]"","({D}{H})) {lexeme=yytext(); return Correcta;}
((SHL | shl)" ""["({Reg}"+"{Reg})"]"","({D})) {lexeme=yytext(); return Correcta;}
((SHL | shl)" ""["({Reg}"+"{Reg})"]"","("-"{D})) {lexeme=yytext(); return Correcta;}
((SHL | shl)" ""["({Reg}"+"{Reg})"]"","({D}("b"|"B"))) {lexeme=yytext(); return Correcta;}
((SHL | shl)" ""["({Reg}"+"{Reg})"]"","({D}{L}{H})) {lexeme=yytext(); return Correcta;}



//REGISTRO, INMEDIATO

((ADD | add)" "({Reg})","({D}{H})) {lexeme=yytext(); return Correcta;}
((ADD | add)" "({Reg})","({D})) {lexeme=yytext(); return Correcta;}
((ADD | add)" "({Reg})","("-"{D})) {lexeme=yytext(); return Correcta;}
((ADD | add)" "({Reg})","({D}("b"|"B"))) {lexeme=yytext(); return Correcta;}
((ADD | add)" "({Reg})","({D}{L}{H})) {lexeme=yytext(); return Correcta;}

((SHL | shl)" "({Reg})","({D}{H})) {lexeme=yytext(); return Correcta;}
((SHL | shl)" "({Reg})","({D})) {lexeme=yytext(); return Correcta;}
((SHL | shl)" "({Reg})","("-"{D})) {lexeme=yytext(); return Correcta;}
((SHL | shl)" "({Reg})","({D}("b"|"B"))) {lexeme=yytext(); return Correcta;}
((SHL | shl)" "({Reg})","({D}{L}{H})) {lexeme=yytext(); return Correcta;}

//POP
((ADD | add)" "{Reg}) {lexeme=yytext(); return Correcta;}
((ADD | add)" "{SReg}) {lexeme=yytext(); return Correcta;}
((ADD | add)" ""["({Reg}"+"{Reg})"]") {lexeme=yytext(); return Correcta;}

//CL
((SHL | shl)" "({Reg})","("CL")) {lexeme=yytext(); return Correcta;}
((SHL | shl)" "({Reg})","("cl")) {lexeme=yytext(); return Correcta;}

((SHL | shl)" ""["({Reg}"+"{Reg})"]"","("CL")) {lexeme=yytext(); return Correcta;}
((SHL | shl)" ""["({Reg}"+"{Reg})"]"","("cl")) {lexeme=yytext(); return Correcta;}



{espacio} {/*Ignore*/}
(";"(.)*) {/*Ignore*/}

("\n") {return Linea;}
({D}{L}":") {lexeme=yytext(); return Etiqueta;}
({L}({L}|{D})":") {lexeme=yytext(); return Etiqueta;}

/*Instrucciones validar*/
({L}({L}|{D})" "{PseuDB}" "\"{Cadena}\") {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDB}" ""'"{Cadena}"'") {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDB}" "{D}) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDW}" "\"{Cadena}\") {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDW}" ""'"{Cadena}"'") {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDW}" "{D}) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDW}" "\"{Cadena}\") {lexeme=yytext(); return InstruccionCorr;}

(({D}{L})" "{PseuDB}" "\"{Cadena}\") {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDB}" ""'"{Cadena}"'") {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDB}" "{D}) {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDW}" "\"{Cadena}\") {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDW}" ""'"{Cadena}"'") {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDW}" "{D}) {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDW}" "\"{Cadena}\") {lexeme=yytext(); return InstruccionCorr;}


. {return ERROR;}
