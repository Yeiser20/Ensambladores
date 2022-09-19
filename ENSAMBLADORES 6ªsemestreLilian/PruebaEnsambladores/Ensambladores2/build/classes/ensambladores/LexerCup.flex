package ensambladores;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol

%cup
%full
%line
%char

separador=[ ,\t,\r]+
L=[a-zA-Z]+
Cadena=[a-zA-Z$.()?¿¡!%#*-_:;, ]+
N=[0-9]+
B=[0-1]+
H=[0-9a-fA-F]+

Instruccion_0 = [STD,std,AAD,aad,CLD,cld,CWD,cwd,IRET,iret,MOVSW,movsw,AAA,aaa,CMPSW,cmpsw,INTO,into,MOVSB,movsb,PUSHA,pusha,PUSHF,pushf,NOP,nop,LODSB,lodsb,HLT,htl,CLC,clc,STI,sti,POPA,popa,AAD,aad,RET,ret,LODSW,lodsw,AAM,aam,STOSB,stosb,SCASW,scasw,POPF,popf,CLI,cli,STOSW,stosw,AAS,aas,STC,stc,DAA,daa,CMC,cmc,CBW,cbw,XLATB,xlatb,LAHF,lahf,DAS,das,CMPSB,cmpsb,CWD,cwd]+
Instruccion_E = [JNS|jns,JS|js,LOOPNE|loopne,JAE|jae,JCXZ|jcxz,JL|jl,JNAE|jnae,JB|jb,JO|jo,JNE|jne,JE|je,LOOP|loop,JNP|jnp,JNLE|jnle,JLE|jle,JBE|jbe,JP|jp,JNZ|jnz,JNB|jnb,JG|jg,LOOPE|loope,JZ|jz,JNG|jng,JMP|jmp,LOOPNZ|loopnz,JNO|jno,JNBE|jnbe,JA|ja,JC|jc,JGE|jge,JNA|jna,JNC|jnc,JNL|jnl]+

%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

{separador} {/*Ignore*/}
(";"(.)*) {/*Ignore*/}
(",") {/*Ignore*/}


(STD | std) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(AAD | aad) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(CLD | cld) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(CWD | cwd) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(IRET | iret) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(MOVSW | movsw) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(AAA | aaa) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(CMPSW | cmpsw) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(INTO | into) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(MOVSB | movsb) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(PUSHA |pusha) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(PUSHF | pushf) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(NOP | nop) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(LODSB | lodsb) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(HLT | htl) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(CLC | clc) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(STI | sti) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(POPA | popa) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(AAD | aad) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(RET | ret) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(LODSW | lodsw) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(AAM | aam) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(STOSB | stosb) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(SCASW | scasw) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(POPF | popf) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(CLI | cli) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(STOSW | stosw) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(AAS | aas) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(STC | stc) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(DAA | daa) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(CMC | cmc) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(CBW | cbw) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(XLATB | xlatb) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(LAHF | lahf) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(DAS | das) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(CMPSB | cmpsb) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}
(CWD | cwd) {return new Symbol(sym.Instruccion_0, yychar, yyline, yytext());}

{Instruccion_0} ({Cadena}|{N}) {return new Symbol(sym.IIO, yychar, yyline, yytext());}

(JNS | jns) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JS | js) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(LOOPNE | loopne) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JAE | jae) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JCXZ | jcxz) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JL | jl) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNAE | jnae) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JB | jb) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JO | jo) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNE | jne) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JE | je) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(LOOP | loop) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNP | jnp) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNLE | jnle) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JLE | jle) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JBE | jbe) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JP | jp) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNZ | jnz) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNB | jnb) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JG | jg) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(LOOPE | loope) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JZ | jz) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNG | jng) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JMP | jmp) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(LOOPNZ | loopnz) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNO | jno) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNBE | jnbe) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JA | ja) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JC |jc) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JGE |jge) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNA | jna) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNC | jnc) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}
(JNL | jnl) {return new Symbol(sym.Instruccion_E, yychar, yyline, yytext());}

{Instruccion_E} ({Cadena}{N}) {return new Symbol(sym.IIE, yychar, yyline, yytext());}

/*(DIV | div) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(IDIV | idiv) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(IMUL | imul) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(POP | pop) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(DEC | dec) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(INT | int) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(NOT | not) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(PUSH | push) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(NEG | neg) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(MUL | mul) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}
(INC | inc) {return new Symbol(sym.Instruccion_1, yychar, yyline, yytext());}

(XCHG | xchg) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(SHL | shl) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(LDS | lds) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(ADD | add) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(ADC | adc) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(CMP | cmp) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(LES | les) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(RCL | rcl) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(ROR | ror) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(LEA | lea) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(SUB | sub) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(OR | or) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(XOR | xor) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(SAR | sar) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(TEST |test) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(MOV | mov) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}
(AND | and) {return new Symbol(sym.Instruccion_2, yychar, yyline, yytext());}*/

/*({L}({L}|{N}))" "({N}) {return new Symbol(sym.Constante, yychar, yyline, yytext());}
({L}({L}|{N}))" "("-"{N}) {return new Symbol(sym.Constante, yychar, yyline, yytext());}

{L}({L}|{N}) {return new Symbol(sym.Variable, yychar, yyline, yytext());}

{L}(({Cadena}|{N})) {return new Symbol(sym.Etiqueta, yychar, yyline, yytext());}*/

 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
