package ensambladores;
import static ensambladores.Tokens.*;
%%
%class Lexer
%type Tokens

espacio=[ ,\t,\r]+
L=[a-zA-Z]+
Cadena=[a-zA-Z0-9$.()?¿¡!%#*-_:;, ]+
N=[0-9]+
B=[0-1]+
H=[0-9a-fA-F]+

Instruccion_0 = [STD,std,AAD,aad,CLD,cld,CWD,cwd,IRET,iret,MOVSW,movsw,AAA,aaa,CMPSW,cmpsw,INTO,into,MOVSB,movsb,PUSHA,pusha,PUSHF,pushf,NOP,nop,LODSB,lodsb,HLT,htl,CLC,clc,STI,sti,POPA,popa,AAD,aad,RET,ret,LODSW,lodsw,AAM,aam,STOSB,stosb,SCASW,scasw,POPF,popf,CLI,cli,STOSW,stosw,AAS,aas,STC,stc,DAA,daa,CMC,cmc,CBW,cbw,XLATB,xlatb,LAHF,lahf,DAS,das,CMPSB,cmpsb,CWD,cwd]+
Instruccion_E = [JNS|jns,JS|js,LOOPNE|loopne,JAE|jae,JCXZ|jcxz,JL|jl,JNAE|jnae,JB|jb,JO|jo,JNE|jne,JE|je,LOOP|loop,JNP|jnp,JNLE|jnle,JLE|jle,JBE|jbe,JP|jp,JNZ|jnz,JNB|jnb,JG|jg,LOOPE|loope,JZ|jz,JNG|jng,JMP|jmp,LOOPNZ|loopnz,JNO|jno,JNBE|jnbe,JA|ja,JC|jc,JGE|jge,JNA|jna,JNC|jnc,JNL|jnl]+
Instruccion_1 = [DIV|div,IDIV|idiv,DEC|dec,IMUL|imul,NOT|not,NEG|neg,MUL|mul,INC|inc]+
Instruccion_II2 = [POP|pop]+
Instruccion_II3 = [INT|int]+
Instruccion_II4 = [PUSH|push]+
Instruccion_I1 = [RCL|rcl,ROR|ror,SHL|shl,SAR|sar]+
Instruccion_I2 = [LEA|lea,LDS|lds,LES|les]+
Instruccion_I3 = [SUB|sub,ADD|add,ADC|adc,CMP|cmp,OR|or,XOR|xor,TEST|test,AND|and]+
Instruccion_I4 = [XCHG|xchg,ADD|add]+
Instruccion_I5 = [MOV|mov]+


Registro = [AX,BX,CX,DX,AH,AL,BL,BH,CH,CL,DH,DL,DI,SI,BP,SP]+
SREG = [DS,ES,SS,CS]+
Memoria = [BX,SI,BP,DI]+
Byte = [0-255]+
Word = [256-65535]+
%{
    public String lexeme;
%}
%%

{espacio} {/*Ignore*/}
(";"(.)*) {/*Ignore*/}
(",") {/*Ignore*/}


(STD | std) {lexeme=yytext(); return Instruccion_0;}
(AAD | aad) {lexeme=yytext(); return Instruccion_0;}
(CLD | cld) {lexeme=yytext(); return Instruccion_0;}
(CWD | cwd) {lexeme=yytext(); return Instruccion_0;}
(IRET | iret) {lexeme=yytext(); return Instruccion_0;}
(MOVSW | movsw) {lexeme=yytext(); return Instruccion_0;}
(AAA | aaa) {lexeme=yytext(); return Instruccion_0;}
(CMPSW | cmpsw) {lexeme=yytext(); return Instruccion_0;}
(INTO | into) {lexeme=yytext(); return Instruccion_0;}
(MOVSB | movsb) {lexeme=yytext(); return Instruccion_0;}
(PUSHA |pusha) {lexeme=yytext(); return Instruccion_0;}
(PUSHF | pushf) {lexeme=yytext(); return Instruccion_0;}
(NOP | nop) {lexeme=yytext(); return Instruccion_0;}
(LODSB | lodsb) {lexeme=yytext(); return Instruccion_0;}
(HLT | htl) {lexeme=yytext(); return Instruccion_0;}
(CLC | clc) {lexeme=yytext(); return Instruccion_0;}
(STI | sti) {lexeme=yytext(); return Instruccion_0;}
(POPA | popa) {lexeme=yytext(); return Instruccion_0;}
(AAD | aad) {lexeme=yytext(); return Instruccion_0;}
(RET | ret) {lexeme=yytext(); return Instruccion_0;}
(LODSW | lodsw) {lexeme=yytext(); return Instruccion_0;}
(AAM | aam) {lexeme=yytext(); return Instruccion_0;}
(STOSB | stosb) {lexeme=yytext(); return Instruccion_0;}
(SCASW | scasw) {lexeme=yytext(); return Instruccion_0;}
(POPF | popf) {lexeme=yytext(); return Instruccion_0;}
(CLI | cli) {lexeme=yytext(); return Instruccion_0;}
(STOSW | stosw) {lexeme=yytext(); return Instruccion_0;}
(AAS | aas) {lexeme=yytext(); return Instruccion_0;}
(STC | stc) {lexeme=yytext(); return Instruccion_0;}
(DAA | daa) {lexeme=yytext(); return Instruccion_0;}
(CMC | cmc) {lexeme=yytext(); return Instruccion_0;}
(CBW | cbw) {lexeme=yytext(); return Instruccion_0;}
(XLATB | xlatb) {lexeme=yytext(); return Instruccion_0;}
(LAHF | lahf) {lexeme=yytext(); return Instruccion_0;}
(DAS | das) {lexeme=yytext(); return Instruccion_0;}
(CMPSB | cmpsb) {lexeme=yytext(); return Instruccion_0;}
(CWD | cwd) {lexeme=yytext(); return Instruccion_0;}
{Instruccion_0} ({Cadena}|{N}) {lexeme=yytext(); return IIO;}

(JNS | jns) {lexeme=yytext(); return Instruccion_E;}
(JS | js) {lexeme=yytext(); return Instruccion_E;}
(LOOPNE | loopne) {lexeme=yytext(); return Instruccion_E;}
(JAE | jae) {lexeme=yytext(); return Instruccion_E;}
(JCXZ | jcxz) {lexeme=yytext(); return Instruccion_E;}
(JL | jl) {lexeme=yytext(); return Instruccion_E;}
(JNAE | jnae) {lexeme=yytext(); return Instruccion_E;}
(JB | jb) {lexeme=yytext(); return Instruccion_E;}
(JO | jo) {lexeme=yytext(); return Instruccion_E;}
(JNE | jne) {lexeme=yytext(); return Instruccion_E;}
(JE | je) {lexeme=yytext(); return Instruccion_E;}
(LOOP | loop) {lexeme=yytext(); return Instruccion_E;}
(JNP | jnp) {lexeme=yytext(); return Instruccion_E;}
(JNLE | jnle) {lexeme=yytext(); return Instruccion_E;}
(JLE | jle) {lexeme=yytext(); return Instruccion_E;}
(JBE | jbe) {lexeme=yytext(); return Instruccion_E;}
(JP | jp) {lexeme=yytext(); return Instruccion_E;}
(JNZ | jnz) {lexeme=yytext(); return Instruccion_E;}
(JNB | jnb) {lexeme=yytext(); return Instruccion_E;}
(JG | jg) {lexeme=yytext(); return Instruccion_E;}
(LOOPE | loope) {lexeme=yytext(); return Instruccion_E;}
(JZ | jz) {lexeme=yytext(); return Instruccion_E;}
(JNG | jng) {lexeme=yytext(); return Instruccion_E;}
(JMP | jmp) {lexeme=yytext(); return Instruccion_E;}
(LOOPNZ | loopnz) {lexeme=yytext(); return Instruccion_E;}
(JNO | jno) {lexeme=yytext(); return Instruccion_E;}
(JNBE | jnbe) {lexeme=yytext(); return Instruccion_E;}
(JA | ja) {lexeme=yytext(); return Instruccion_E;}
(JC |jc) {lexeme=yytext(); return Instruccion_E;}
(JGE |jge) {lexeme=yytext(); return Instruccion_E;}
(JNA | jna) {lexeme=yytext(); return Instruccion_E;}
(JNC | jnc) {lexeme=yytext(); return Instruccion_E;}
(JNL | jnl) {lexeme=yytext(); return Instruccion_E;}
{Instruccion_E} ({Cadena}{N}) {lexeme=yytext(); return IIE;}

// un operando

(DIV | div) {lexeme=yytext(); return Instruccion_1;}
(IDIV | idiv) {lexeme=yytext(); return Instruccion_1;}
(DEC | dec) {lexeme=yytext(); return Instruccion_1;}
(IMUL | imul) {lexeme=yytext(); return Instruccion_1;}
(NOT | not) {lexeme=yytext(); return Instruccion_1;}
(NEG | neg) {lexeme=yytext(); return Instruccion_1;}
(MUL | mul) {lexeme=yytext(); return Instruccion_1;}
(INC | inc) {lexeme=yytext(); return Instruccion_1;}
{Instruccion_1} ({Registro}) {lexeme=yytext(); return II1;}
{Instruccion_1} ("[BX + SI]"|"[BX + DI]"|"[BP + SI]"|"[BP + DI]") {lexeme=yytext(); return II1;}
{Instruccion_1} ({Word}|"[SI]"|"[DI]"|"[BX]") {lexeme=yytext(); return II1;}
{Instruccion_1} ("["{Memoria}" + "{Memoria}" + "{Byte}"]") {lexeme=yytext(); return II1;}
{Instruccion_1} ("["{Memoria}" + "{Byte}"]") {lexeme=yytext(); return II1;}
{Instruccion_1} ("["{Memoria}" + "{Memoria}" + "{Word}"]") {lexeme=yytext(); return II1;}
{Instruccion_1} ("["{Memoria}" + "{Word}"]") {lexeme=yytext(); return II1;}

(POP | pop) {lexeme=yytext(); return Instruccion_II2;} 
{Instruccion_II2} ({Registro}|{SREG}) {lexeme=yytext(); return II2;}
{Instruccion_II2} ("[BX + SI]"|"[BX + DI]"|"[BP + SI]"|"[BP + DI]") {lexeme=yytext(); return II2;}
{Instruccion_II2} ({Word}|"[SI]"|"[DI]"|"[BX]") {lexeme=yytext(); return II2;}
{Instruccion_II2} ("["{Memoria}" + "{Memoria}" + "{Byte}"]") {lexeme=yytext(); return II2;}
{Instruccion_II2} ("["{Memoria}" + "{Byte}"]") {lexeme=yytext(); return II2;}
{Instruccion_II2} ("["{Memoria}" + "{Memoria}" + "{Word}"]") {lexeme=yytext(); return II2;}
{Instruccion_II2} ("["{Memoria}" + "{Word}"]") {lexeme=yytext(); return II2;}

(INT | int) {lexeme=yytext(); return Instruccion_II3;}
{Instruccion_II3} ({N}) {lexeme=yytext(); return II3;}

(PUSH | push) {lexeme=yytext(); return Instruccion_II4;}
{Instruccion_II4} ({Registro}|{SREG}|{N}) {lexeme=yytext(); return II4;}
{Instruccion_II4} ("[BX + SI]"|"[BX + DI]"|"[BP + SI]"|"[BP + DI]") {lexeme=yytext(); return II4;}
{Instruccion_II4} ({Word}|"[SI]"|"[DI]"|"[BX]") {lexeme=yytext(); return II4;}
{Instruccion_II4} ("["{Memoria}" + "{Memoria}" + "{Byte}"]") {lexeme=yytext(); return II4;}
{Instruccion_II4} ("["{Memoria}" + "{Byte}"]") {lexeme=yytext(); return II4;}
{Instruccion_II4} ("["{Memoria}" + "{Memoria}" + "{Word}"]") {lexeme=yytext(); return II4;}
{Instruccion_II4} ("["{Memoria}" + "{Word}"]") {lexeme=yytext(); return II4;}

// dos operandos

(RCL | rcl) {lexeme=yytext(); return Instruccion_I1;}
(ROR | ror) {lexeme=yytext(); return Instruccion_I1;}
(SHL | shl) {lexeme=yytext(); return Instruccion_I1;}
(SAR | sar) {lexeme=yytext(); return Instruccion_I1;}
//{Instruccion_I1} ({Memoria}", "{Inmediato}|{Registro}", "{Inmediato}|{Memoria}", CL"|{Registro}", CL") {lexeme=yytext(); return I1;}
{Instruccion_I1} (("[BX + SI]"|"[BX + DI]"|"[BP + SI]"|"[BP + DI]") ", "{Cadena}|{Registro}", "{Cadena}|("[BX + SI]"|"[BX + DI]"|"[BP + SI]"|"[BP + DI]") ", CL"|{Registro}", CL") {lexeme=yytext(); return I1;}
{Instruccion_I1} (({Word}|"[SI]"|"[DI]"|"[BX]") ", "{Cadena}|{Registro}", "{Cadena}|({Word}|"[SI]"|"[DI]"|"[BX]") ", CL"|{Registro}", CL") {lexeme=yytext(); return I1;}
{Instruccion_I1} ("["{Memoria}" + "{Memoria}" + "{Byte}"]" ", "{Cadena}|{Registro}", "{Cadena}|"["{Memoria}" + "{Memoria}" + "{Byte}"]" ", CL"|{Registro}", CL") {lexeme=yytext(); return I1;}
{Instruccion_I1} ("["{Memoria}" + "{Byte}"]" ", "{Cadena}|{Registro}", "{Cadena}|"["{Memoria}" + "{Byte}"]" ", CL"|{Registro}", CL") {lexeme=yytext(); return I1;}
{Instruccion_I1} ("["{Memoria}" + "{Memoria}" + "{Word}"]" ", "{Cadena}|{Registro}", "{Cadena}|"[BX + SI]"|"["{Memoria}" + "{Memoria}" + "{Word}"]" ", CL"|{Registro}", CL") {lexeme=yytext(); return I1;}
{Instruccion_I1} ("["{Memoria}" + "{Word}"]" ", "{Cadena}|{Registro}", "{Cadena}|"["{Memoria}" + "{Word}"]" ", CL"|{Registro}", CL") {lexeme=yytext(); return I1;}

(LEA | lea) {lexeme=yytext(); return Instruccion_I2;}
(LDS | lds) {lexeme=yytext(); return Instruccion_I2;}
(LES | les) {lexeme=yytext(); return Instruccion_I2;}
{Instruccion_I2} ({Registro}", "{Memoria}) {lexeme=yytext(); return I2;}


(SUB | sub) {lexeme=yytext(); return Instruccion_I3;}
(ADD | add) {lexeme=yytext(); return Instruccion_I3;}
(ADC | adc) {lexeme=yytext(); return Instruccion_I3;}
(CMP | cmp) {lexeme=yytext(); return Instruccion_I3;}
(OR | or) {lexeme=yytext(); return Instruccion_I3;}
(XOR | xor) {lexeme=yytext(); return Instruccion_I3;}
(TEST |test) {lexeme=yytext(); return Instruccion_I3;}
(AND | and) {lexeme=yytext(); return Instruccion_I3;}
{Instruccion_I3} ({Registro}", "{Memoria}|{Memoria}", "{Registro}|{Registro}", "{Registro}|{Memoria}", "{Cadena}|{Registro}", "{Cadena}) {lexeme=yytext(); return I3;}


(XCHG | xchg) {lexeme=yytext(); return Instruccion_I4;}
(ADD | add) {lexeme=yytext(); return Instruccion_I4;}
{Instruccion_I4} ({Registro}", "{Memoria}|{Memoria}", "{Registro}|{Registro}", "{Registro}) {lexeme=yytext(); return I4;}


(MOV | mov) {lexeme=yytext(); return Instruccion_I5;}
{Instruccion_I5} ({Registro}", "{Memoria}|{Memoria}", "{Registro}|{Registro}", "{Registro}|{Memoria}", "{Cadena}|{Registro}", "{Cadena}) {lexeme=yytext(); return I5;}
{Instruccion_I5} ({SREG}", "{Memoria}|{Memoria}", "{SREG}|{Registro}", "{SREG}|{SREG}", "{Registro}) {lexeme=yytext(); return I5;}


/*({L}({L}|{N})" "({N})) {lexeme=yytext(); return Constante;}
({L}({L}|{N})" "("-"{N})) {lexeme=yytext(); return Constante;}

{L}({L}|{N}) {lexeme=yytext(); return Variable;}

{L}(({Cadena}|{N})) {lexeme=yytext(); return Etiqueta;}*/

 . {return ERROR;}