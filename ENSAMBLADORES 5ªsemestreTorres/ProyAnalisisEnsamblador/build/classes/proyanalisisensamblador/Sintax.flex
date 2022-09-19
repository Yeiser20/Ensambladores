package proyanalisisensamblador;

import java_cup.runtime.Symbol;

parser code
{:
    private Symbol s;

    public void syntax_error(Symbol s){
        this.s = s;
    }

    public Symbol getS(){
        return this.s;
}
:};

terminal Simbolo,
    Pseudoinstruccion,
    ERROR,
    Registro,
    Pseudoinstruccion_Dup_0,    
    PseudoinstruccionInc,
    Pseudoinstruccion_W,
    Pseudoinstruccion_Dup_D,
    Pseudoinstruccion_Dup_Caracter,
    Pseudoinstruccion_Dup_H,
    Constante_Numerica_D,
    Constante_Numerica_H,
    Constante_Caracter_CS,
    Constante_Caracter_CD,
    Pseudoinstruccion_S_S,
    Pseudoinstruccion_D_S,
    Pseudoinstruccion_C_S,
    Pseudoinstruccion_D_B,
    Pseudoinstruccion_D_W,
    Pseudoinstruccion_E,
    Instruccion,
    Interrupcion,
    Sregistro,
    ConstanteCadInc,
    Correcta,
    Incorrecta,
    Numero;

non terminal INICIO, PSEUDOINSTRUCCION, INSTRUCCION, DECLARACION,DUPS;

INICIO ::= Pseudoinstruccion DUPS Pseudoinstruccion Pseudoinstruccion DECLARACION;


DUPS ::= Pseudoinstruccion_D_W Constante_Numerica_D Pseudoinstruccion_Dup_D |
         Pseudoinstruccion_D_W Constante_Numerica_D Pseudoinstruccion_Dup_Caracter | 
         Pseudoinstruccion_D_W Constante_Numerica_D Pseudoinstruccion_Dup_H |
         Pseudoinstruccion_D_W Constante_Numerica_D Pseudoinstruccion_Dup_0;

DECLARACION ::= 
        Simbolo Pseudoinstruccion_D_W Constante_Caracter_CD | 
        Simbolo Pseudoinstruccion_D_B Constante_Caracter_CD | 
        Simbolo Pseudoinstruccion_D_W Constante_Numerica_D | 
        Simbolo Pseudoinstruccion_D_B Constante_Numerica_D |
        Simbolo Pseudoinstruccion_D_B Constante_Caracter_CS 
;




