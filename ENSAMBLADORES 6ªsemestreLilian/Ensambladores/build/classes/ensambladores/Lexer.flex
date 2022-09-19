package ensambladores;
import static ensambladores.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z]+
Cadena=[a-zA-Z$.()?¿¡!%#*-_:;, ]+
N=[0-9]+
H=[h-H]+
DUP=[DUP-dup]+
B=[0-1]+
H=[0-9|A-F]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
%}
%%

(db | DB) {lexeme=yytext(); return Pseudoinstruccion_D_B;}
(dw | DW) {lexeme=yytext(); return Pseudoinstruccion_D_W;}
({DUP} "("{N}")"+) {lexeme=yytext(); return Pseudoinstruccion_Dup_0;}
({DUP} ("{N}")+) {lexeme=yytext(); return Pseudoinstruccion_Dup_D;}
({DUP} ("\"{L}\"")) | ({DUP}"("\'{L}\'")") {lexeme=yytext(); return Pseudoinstruccion_Dup_Caracter;}
({DUP}"0"{N}"H"+) | ({DUP}"0"{N}"h"+) {lexeme=yytext(); return Pseudoinstruccion_Dup_H;}
("(-"{N}+")") | {N}+ {lexeme=yytext(); return Constante_Numerica_D;}
("-0"{N}"H") | ("0"{N}"H") {lexeme=yytext(); return Constante_Numerica_H;}
(\"{Cadena}"\") | (\'{Cadena}"\') {lexeme=yytext(); return Constante_Caracter_CS;}
(\"{Cadena}"$"\") | (\'{Cadena}"$"\') {lexeme=yytext(); return Constante_Caracter_CD;}
("equ(0-9a-z)" | "EQU(0-9|a-z)") {lexeme=yytext(); return Pseudoinstruccion;}
(macro | MACRO) {lexeme=yytext(); return Pseudoinstruccion;}
(endm | ENDM) {lexeme=yytext(); return Pseudoinstruccion;}
(ends | ENDS) {lexeme=yytext(); return Pseudoinstruccion_E;}
(proc | PROC) {lexeme=yytext(); return Pseudoinstruccion;}
(endp | ENDP) {lexeme=yytext(); return Pseudoinstruccion;}
("stack segment" | "STACK SEGMENT") {lexeme=yytext(); return Pseudoinstruccion_S_S;}
("data segment" | "DATA SEGMENT") {lexeme=yytext(); return Pseudoinstruccion_D_S;}
("code segment" | "CODE SEGMENT") {lexeme=yytext(); return Pseudoinstruccion_C_S;}
(include | INCLUDE) {lexeme=yytext(); return Pseudoinstruccion;}
("byte ptr" | "BYTE PTR") {lexeme=yytext(); return Pseudoinstruccion_B;} 
("word ptr" | "WORD PTR") {lexeme=yytext(); return Pseudoinstruccion_W;}
//{L}({L}|{N})* {lexeme=yytext(); return Simbolo;}

(AAA | aaa) {lexeme=yytext(); return Simbolo;}
(CMPSW | cmpsw) {lexeme=yytext(); return Simbolo;}
(INTO | into) {lexeme=yytext(); return Simbolo;}
(MOVSB | movsb) {lexeme=yytext(); return Simbolo;}
(PUSHA |pusha) {lexeme=yytext(); return Simbolo;}
(DEC | dec) {lexeme=yytext(); return Simbolo;}
(INT | int) {lexeme=yytext(); return Simbolo;}
(NOT | not) {lexeme=yytext(); return Simbolo;}
(ADC | adc) {lexeme=yytext(); return Simbolo;}
(CMP | cmp) {lexeme=yytext(); return Simbolo;}
(LES | les) {lexeme=yytext(); return Simbolo;}
(RCL | rcl) {lexeme=yytext(); return Simbolo;}
(JA | ja) {lexeme=yytext(); return Simbolo;}
(JC |jc) {lexeme=yytext(); return Simbolo;}
(JGE |jge) {lexeme=yytext(); return Simbolo;}
(JNA | jna) {lexeme=yytext(); return Simbolo;}
(JNC | jnc) {lexeme=yytext(); return Simbolo;}
(JNL | jnl) {lexeme=yytext(); return Simbolo;}
(ROR | ror) {lexeme=yytext(); return Simbolo;}
(LEA | lea) {lexeme=yytext(); return Simbolo;}
(SUB | sub) {lexeme=yytext(); return Simbolo;}
(OR | or) {lexeme=yytext(); return Simbolo;}
(XOR | xor) {lexeme=yytext(); return Simbolo;}
(SAR | sar) {lexeme=yytext(); return Simbolo;}
(TEST |test) {lexeme=yytext(); return Simbolo;}
(JNAE | jnae) {lexeme=yytext(); return Simbolo;}
(JB | jb) {lexeme=yytext(); return Simbolo;}
(JO | jo) {lexeme=yytext(); return Simbolo;}
(JNE | jne) {lexeme=yytext(); return Simbolo;}
(JE | je) {lexeme=yytext(); return Simbolo;}
(LOOP | loop) {lexeme=yytext(); return Simbolo;}
(JNP | jnp) {lexeme=yytext(); return Simbolo;}
(JNLE | jnle) {lexeme=yytext(); return Simbolo;}	
(JLE | jle) {lexeme=yytext(); return Simbolo;}
(JBE | jbe) {lexeme=yytext(); return Simbolo;}
(JP | jp) {lexeme=yytext(); return Simbolo;}
(JNZ | jnz) {lexeme=yytext(); return Simbolo;}	
(JNB | jnb) {lexeme=yytext(); return Simbolo;}	
(JG | jg) {lexeme=yytext(); return Simbolo;}
(LOOPE | loope) {lexeme=yytext(); return Simbolo;} 
(JZ | jz) {lexeme=yytext(); return Simbolo;}
(JNG | jng) {lexeme=yytext(); return Simbolo;}
(JMP | jmp) {lexeme=yytext(); return Simbolo;}
(LOOPNZ | loopnz) {lexeme=yytext(); return Simbolo;} 
(JNO | jno) {lexeme=yytext(); return Simbolo;}
(JNBE | jnbe) {lexeme=yytext(); return Simbolo;}
(PUSHF | pushf) {lexeme=yytext(); return Simbolo;}
(NOP | nop) {lexeme=yytext(); return Simbolo;}
(LODSB | lodsb) {lexeme=yytext(); return Simbolo;}
(HLT | htl) {lexeme=yytext(); return Simbolo;}
(CLC | clc) {lexeme=yytext(); return Simbolo;}
(STI | sti) {lexeme=yytext(); return Simbolo;}
(POPA | popa) {lexeme=yytext(); return Simbolo;}
(AAD | aad) {lexeme=yytext(); return Simbolo;}
(RET | ret) {lexeme=yytext(); return Simbolo;}
(LODSW | lodsw) {lexeme=yytext(); return Simbolo;}
(AAM | aam) {lexeme=yytext(); return Simbolo;}
(STOSB | stosb) {lexeme=yytext(); return Simbolo;}
(SCASW | scasw) {lexeme=yytext(); return Simbolo;}
(POPF | popf) {lexeme=yytext(); return Simbolo;}
(CLI | cli) {lexeme=yytext(); return Simbolo;}
(STOSW | stosw) {lexeme=yytext(); return Simbolo;}
(AAS | aas) {lexeme=yytext(); return Simbolo;}
(STC | stc) {lexeme=yytext(); return Simbolo;}
(DAA | daa) {lexeme=yytext(); return Simbolo;}
(CMC | cmc) {lexeme=yytext(); return Simbolo;}
(CBW | cbw) {lexeme=yytext(); return Simbolo;}
(XLATB | xlatb) {lexeme=yytext(); return Simbolo;}
(LAHF | lahf) {lexeme=yytext(); return Simbolo;}
(DAS | das) {lexeme=yytext(); return Simbolo;}
(CMPSB | cmpsb) {lexeme=yytext(); return Simbolo;}
(CWD | cwd) {lexeme=yytext(); return Simbolo;}
(PUSH | push) {lexeme=yytext(); return Simbolo;} 
(NEG | neg) {lexeme=yytext(); return Simbolo;}
(MUL | mul) {lexeme=yytext(); return Simbolo;}
(INC | inc) {lexeme=yytext(); return Simbolo;}
(MOV | mov) {lexeme=yytext(); return Simbolo;}
(AND | and) {lexeme=yytext(); return Simbolo;}
(STD | std) {lexeme=yytext(); return Instruccion;}
(CLD | cld) {lexeme=yytext(); return Instruccion;}
(CWD | cwd) {lexeme=yytext(); return Instruccion;}
(IRET | iret) {lexeme=yytext(); return Instruccion;}
(MOVSW | movsw) {lexeme=yytext(); return Instruccion;}
(DIV | div) {lexeme=yytext(); return Instruccion;}
(IDIV | idiv) {lexeme=yytext(); return Instruccion;}
(IMUL | imul) {lexeme=yytext(); return Instruccion;}
(POP | pop) {lexeme=yytext(); return Instruccion;}
(XCHG | xchg) {lexeme=yytext(); return Instruccion;}
(SHL | shl) {lexeme=yytext(); return Instruccion;}
(LDS | lds) {lexeme=yytext(); return Instruccion;}
(ADD | add) {lexeme=yytext(); return Instruccion;}
(JNS | jns) {lexeme=yytext(); return Instruccion;}
(JS | js) {lexeme=yytext(); return Instruccion;}
(LOOPNE | loopne) {lexeme=yytext(); return Instruccion;}
(JAE | jae) {lexeme=yytext(); return Instruccion;}
(JCXZ | jcxz) {lexeme=yytext(); return Instruccion;}
(JL | jl) {lexeme=yytext(); return Instruccion;}

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
(ss | SS) {lexeme=yytext(); return Registro;}
(cs | CS) {lexeme=yytext(); return Registro;}
(ds | DS) {lexeme=yytext(); return Registro;}
(es | ES) {lexeme=yytext(); return Registro;}

/*Separadores*/
{espacio} {/*Ignore*/}
(";"(.)*) {/*Ignore*/}
("\n") {return Linea;}

/*("(,)") {lexeme=yytext(); return Coma;}
(":") {lexeme=yytext(); return Dos_Puntos;}
("[") {lexeme=yytext(); return Corchete_a;}
("]") {lexeme=yytext(); return Corchete_c;}
("(") {lexeme=yytext(); return Parentesis_a;}
(")") {lexeme=yytext(); return Parentesis_c;}
("'") {lexeme=yytext(); return Comilla_Simple;}
("\"") {lexeme=yytext(); return Comilla_Doble;}
("$") {lexeme=yytext(); return Fin_Cadena;}
("\""+*({L}|{D}|{Espacio}{S})+"\""+","+"\""+"$"+"\"") {lexeme=yytext(); return Constante;}
("\""+*({L}|{D}|{Espacio}{S})+"$""\"") {lexeme=yytext(); return Constante;}
("'"+*({L}|{D}|{Espacio}|{S})+"$""'"") {lexeme=yytext(); return Constante;}
("'"+*({L}|{D}|{Espacio}|{S})+"'"+","+"'"+"$"+"'") {lexeme=yytext(); return Constante;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Constante_Decimal;}
{B}*{B}"B"+ {lexeme=yytext(); return Constante_Binaria;}
"0"+({D}|{H})"H"+ {lexeme=yytext(); return Constante_Hexadecimal;}*/
 . {return ERROR;}