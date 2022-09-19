stack segment 
 dw   128  dup(0)			 
 dw   128  dupy(0)	
ends

data segment
datos dw 0
files db 0 
pkey db "press any key...$"
var1 db 'hola'
const equ 12
pkey12 db "press any key...$
    var2 dw 0
tecla db 0 
Vtecla BD 21 
7tecla Wd 0
          
ends

code segment
;       intstrucciones
;SIN OPERANDOS
;-----interrupcion-----
;correctas 
INT 21h
SCASW
HLT
XLATB
INTO
AAD

;incorrectas 
int
SCASW datos
HLT datos,bx
XLATB files
INTO cx,cx
AAD bx,25h   
MOVSW ax,21h 

;-------transferencia de datos------
;correcta
MOVSW  
POP bx
POP files

;incorrecta
MOVSW dx,files
POP
POP 21h
;CON OPERANDOS
;-----Aritmetica-----
;correctas
IMUL var2
INC datos
INC bx
ADC bx,cx
ADC var2,cx
ADC vtecla
ADC ah,21h

ADD ax,bx
ADD vtecla, bx
ADD bx, datos
ADD files, 21
ADD ah, 13

SUB ax,cx 
SUB datos, bx
SUB bx,files
SUB files, 13
SUB ah,15

;incorrectas
IMUL
INC
        
INC datos,bx
INC 
ADC 
ADC var2,files
ADC vtecla,21h

ADD 
ADD vtecla,12
ADD si, datos

SUB  
SUB datos,files
SUB bx,ds
    
    
;------logica-----    
;correctas
SAR files,1
SAR bx, cl
SAR cx,02

;incorrectas
SAR
SAR bx,BX
SAR files,[SI]

;------salto condicional----
;correctas
JBE etiqueta
JNGE etiqueta
JNA etiqueta
JS etiqueta
JL etiqueta
JNZ etiqueta

;incorrectas
JBE cx
JNGE 
JNA dx,cl
JS files
JL 13
JNZ [si],bx

 etiqueta:
    
ends
                  