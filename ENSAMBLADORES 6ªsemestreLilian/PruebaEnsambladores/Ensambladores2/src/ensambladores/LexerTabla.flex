package ensambladores;
import static ensambladores.Tokens.*;
%%
%class Lexer
%type Tokens

%{
    public String lexeme;
%}
%%

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

(STD | std) {lexeme=yytext(); return Instruccion_0;}
(AAD | aad) {lexeme=yytext(); return Instruccion_0;}
(CLD | cld) {lexeme=yytext(); return Instruccion_0;}
(CWD | cwd) {lexeme=yytext(); return Instruccion_0;}
(IRET | iret) {lexeme=yytext(); return Instruccion_0;}
(MOVSW | movsw) {lexeme=yytext(); return Instruccion_0;}

/*(DIV | div) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(IDIV | idiv) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(IMUL | imul) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(POP | pop) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(XCHG | xchg) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(SHL | shl) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(LDS | lds) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}
(ADD | add) {return new Symbol(sym.Instruccion, yychar, yyline, yytext());}*/

(JNS | jns) {lexeme=yytext(); return Instruccion_E;}
(JS | js) {lexeme=yytext(); return Instruccion_E;}
(LOOPNE | loopne) {lexeme=yytext(); return Instruccion_E;}
(JAE | jae) {lexeme=yytext(); return Instruccion_E;}
(JCXZ | jcxz) {lexeme=yytext(); return Instruccion_E;}
(JL | jl) {lexeme=yytext(); return Instruccion_E;}

 . {return ERROR;}