package analisadorlexico;

import static analisadorlexico.Tokens.*;

%%
%class Fase3
%type Tokens

DUP=[DUP,dup]+
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ , \t, \r]+
H=[H,h]+
Cadena=[a-zA-Z$.()?¿¡!%#*-_:, ]+
PseuDB=[DB,db]+
PseuDW=[dw,DW]+
Byte=[0-255_]+
Word=[0-65535_]+
caracter=[a,b,c,d,e,f,g,h,i,j,k,l,m,n,ñ,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,Ñ,O,P,Q,R,S,T,U,V,W,X,Y,Z]+
Reg=[AX,BX,CX,DX,AH,AL,BL,BH,CH,CL,DH,DL,DI,SI,BP,SP,ax,bx,cx,dx,ah,al,bl,bh,ch,cl,dh,dl,di,si,bp,sp]+
NoOp=[AAD,CLD,CWD,IRET,MOVSW,STD,aad,cld,cwd,iret,movsw,std]+
SReg=[DS,ES,SS,CS]+
Cons=[EQU,equ]+


%{
 public String lexeme;

%}

%%

"data segment"|"DATA SEGMENT" {lexeme=yytext(); return InstruccionCorr;}
"ENDS"|"ends" {lexeme=yytext(); return InstruccionCorr;}
"code segment"|"CODE SEGMENT" {lexeme=yytext(); return InstruccionCorr;}
"stack segment"|"STACK SEGMENT" {lexeme=yytext(); return InstruccionCorr;}

{espacio} {/*Ignore*/}
(";"(.)*) {/*Ignore*/}

("\n") {return Linea;}
({D}{L}":") {lexeme=yytext(); return Etiqueta;}
({L}({L}|{D})":") {lexeme=yytext(); return Etiqueta;}


/*Instrucciones validar*/

({L}({L}|{D})" "{PseuDB}" "({Byte})" "({DUP}"(""'"{Cadena}"'"")")) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDB}" "({Byte})" "({DUP}"("{Cadena}")")) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDB}" "({Byte})" "({DUP}"("{Byte}"H"")")) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDB}" "({Byte})" "({DUP}"("{Byte}")")) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDB}" "({Word})" "({DUP}"("{Byte}")")) {lexeme=yytext(); return  InstruccionCorr;}

({L}({L}|{D})" "{PseuDW}" "({Word})" "({DUP}"(""'"{Cadena}"'"")")) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDW}" "({Word})" "({DUP}"("{Cadena}")")) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDW}" "({Word})" "({DUP}"("{Word}"H"")")) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDW}" "({Word})" "({DUP}"("{Word}")")) {lexeme=yytext(); return  InstruccionCorr;}

({PseuDW}" "({Word})" "({DUP}"(""'"{Cadena}"'"")")) {lexeme=yytext(); return  InstruccionCorr;}
({PseuDW}" "({Word})" "({DUP}"("{Cadena}")")) {lexeme=yytext(); return  InstruccionCorr;}
({PseuDW}" "({Word})" "({DUP}"("{Word}"H"")")) {lexeme=yytext(); return  InstruccionCorr;}
({PseuDW}" "({Word})" "({DUP}"("{Word}")")) {lexeme=yytext(); return  InstruccionCorr;}

({Cons}) {lexeme=yytext(); return  Constante;}
({L}({L}|{D})" "{Cons}) {lexeme=yytext(); return  Constante;}

({L}({L}|{D})" "{Cons}" "{Byte}) {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{Cons}" ""4000000") {lexeme=yytext(); return  Incorrecta;}


({L}({L}|{D})" "{PseuDB}" ""1000") {lexeme=yytext(); return  Incorrecta;}
({L}({L}|{D})" "{PseuDW}" ""100000") {lexeme=yytext(); return  Incorrecta;}
({L}({L}|{D})" "{PseuDB}" "{Reg}) {lexeme=yytext(); return  Incorrecta;}
({L}({L}|{D})" "{PseuDW}" "{Reg}) {lexeme=yytext(); return  Incorrecta;}
({L}({L}|{D})" "{PseuDW}" "{D}{H}) {lexeme=yytext(); return  Incorrecta;}



({L}({L}|{D})" "{PseuDB}" "\"{Cadena}\") {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDB}" ""'"{Cadena}"'") {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDB}" "{Byte}) {lexeme=yytext(); return  InstruccionCorr;}


({L}({L}|{D})" "{PseuDW}" "\"{Cadena}\") {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDW}" ""'"{Cadena}"'") {lexeme=yytext(); return  InstruccionCorr;}
({L}({L}|{D})" "{PseuDW}" "{Word}) {lexeme=yytext(); return  InstruccionCorr;}

({L}({L}|{D})" "{PseuDW}" "\"{Cadena}\") {lexeme=yytext(); return InstruccionCorr;}


(({D}{L})" "{PseuDB}" "\"{Cadena}\") {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDB}" ""'"{Cadena}"'") {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDB}" "{Byte}) {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDW}" "\"{Cadena}\") {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDW}" ""'"{Cadena}"'") {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDW}" "{Word}) {lexeme=yytext(); return  InstruccionCorr;}
(({D}{L})" "{PseuDW}" "\"{Cadena}\") {lexeme=yytext(); return InstruccionCorr;}



. {return ERROR;}
