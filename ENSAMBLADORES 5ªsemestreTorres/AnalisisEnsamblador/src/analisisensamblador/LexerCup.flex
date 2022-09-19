package analisisensamblador;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol

%cup
%full
%line
%char

L=[a-zA-Z_]+
Cadena=[a-zA-Z$.()?¿¡!%#*-_':, ]+
N=[0-9]+
H=[h-H]+
DUP=[DUP-dup]+
B=[0-1]+
H=[0-9|A-F]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/*Separadores*/
{espacio} {/*ignore*/}
(";"(.)*) {/*ignore*/}

{L}({L}|{N}) {return new Symbol(sym.Variable, yychar, yyline, yytext());}
(db | DB) {return new Symbol(sym.Pseudoinstruccion_D_B, yychar, yyline, yytext());}
(dw | DW) {return new Symbol(sym.Pseudoinstruccion_D_W, yychar, yyline, yytext());}
({DUP} "("{N}")"+) {return new Symbol(sym.Pseudoinstruccion_Dup_0, yychar, yyline, yytext());}
("dupy" "("{N}")"+) {return new Symbol(sym.PseudoInsInco, yychar, yyline, yytext());}
({DUP} ("{N}")+) {return new Symbol(sym.Pseudoinstruccion_Dup_D, yychar, yyline, yytext());}
({DUP} ("\"{L}\"")) | ({DUP}"("\'{L}\'")") {return new Symbol(sym.Pseudoinstruccion_Dup_Caracter, yychar, yyline, yytext());}
({DUP}"0"{N}"H"+) | ({DUP}"0"{N}"h"+) {return new Symbol(sym.Pseudoinstruccion_Dup_H, yychar, yyline, yytext());}
("(-"{N}+")") | {N}+ {return new Symbol(sym.Constante_Numerica_D, yychar, yyline, yytext());}
("-0"{N}"H") | ("0"{N}"H") {return new Symbol(sym.Constante_Numerica_H, yychar, yyline, yytext());}
(\"{Cadena}"\") | (\'{Cadena}"\') {return new Symbol(sym.Constante_Caracter_CS, yychar, yyline, yytext());}
(\"{Cadena}"$"\") | (\'{Cadena}"$"\') {return new Symbol(sym.Constante_Caracter_CD, yychar, yyline, yytext());}
(macro | MACRO) {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}
(endm | ENDM) {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}
(ends | ENDS) {return new Symbol(sym.Pseudoinstruccion_E, yychar, yyline, yytext());}
(proc | PROC) {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}
(endp | ENDP) {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}
("stack segment" | "STACK SEGMENT") {return new Symbol(sym.Pseudoinstruccion_S_S, yychar, yyline, yytext());}
("data segment" | "DATA SEGMENT") {return new Symbol(sym.Pseudoinstruccion_D_S, yychar, yyline, yytext());}
("code segment" | "CODE SEGMENT") {return new Symbol(sym.Pseudoinstruccion_C_S, yychar, yyline, yytext());}
(include | INCLUDE) {return new Symbol(sym.Pseudoinstruccion, yychar, yyline, yytext());}
("byte ptr" | "BYTE PTR") {return new Symbol(sym.Pseudoinstruccion_B, yychar, yyline, yytext());} 
("word ptr" | "WORD PTR") {return new Symbol(sym.Pseudoinstruccion_W, yychar, yyline, yytext());}

(AAA | aaa) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(CMPSW | cmpsw) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(INTO | into) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(MOVSB | movsb) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(PUSHA |pusha) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(DEC | dec) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(INT | int) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(NOT | not) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(ADC | adc) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(CMP | cmp) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(LES | les) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(RCL | rcl) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JA | ja) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JC |jc) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JGE |jge) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNA | jna) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNC | jnc) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNL | jnl) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(ROR | ror) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(LEA | lea) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(SUB | sub) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(OR | or) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(XOR | xor) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(SAR | sar) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(TEST |test) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNAE | jnae) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JB | jb) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JO | jo) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNE | jne) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JE | je) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(LOOP | loop) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNP | jnp) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNLE | jnle) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}	
(JLE | jle) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JBE | jbe) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JP | jp) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNZ | jnz) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}	
(JNB | jnb) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}	
(JG | jg) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(LOOPE | loope) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());} 
(JZ | jz) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNG | jng) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JMP | jmp) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(LOOPNZ | loopnz) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());} 
(JNO | jno) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(JNBE | jnbe) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(PUSHF | pushf) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(NOP | nop) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(LODSB | lodsb) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(HLT | htl) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(CLC | clc) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(STI | sti) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(POPA | popa) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(AAD | aad) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(RET | ret) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(LODSW | lodsw) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(AAM | aam) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(STOSB | stosb) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(SCASW | scasw) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(POPF | popf) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(CLI | cli) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(STOSW | stosw) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(AAS | aas) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(STC | stc) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(DAA | daa) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(CMC | cmc) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(CBW | cbw) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(XLATB | xlatb) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(LAHF | lahf) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(DAS | das) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(CMPSB | cmpsb) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(CWD | cwd) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(PUSH | push) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());} 
(NEG | neg) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(MUL | mul) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(INC | inc) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(MOV | mov) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(AND | and) {return new Symbol(sym.Simbolo, yychar, yyline, yytext());}
(STD | std) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(CLD | cld) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(CWD | cwd) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(IRET | iret) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(MOVSW | movsw) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(DIV | div) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(IDIV | idiv) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(IMUL | imul) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(POP | pop) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(XCHG | xchg) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(SHL | shl) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(LDS | lds) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(ADD | add) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(JNS | jns) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(JS | js) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(LOOPNE | loopne) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(JAE | jae) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(JCXZ | jcxz) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(JL | jl) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}

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
(ss | SS) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(cs | CS) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(ds | DS) {return new Symbol(sym.Registro, yychar, yyline, yytext());}
(es | ES) {return new Symbol(sym.Registro, yychar, yyline, yytext());}

 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
