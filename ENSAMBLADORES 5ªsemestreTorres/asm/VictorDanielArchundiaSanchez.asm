
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt
 
NAME "VICTOR DANIEL ARCHUNDIA SANCHEZ"
org 100h

 INT 010H
ARGUMENTO EQU 45



DATA SEGMENT
    FLUYE DB 20 DUP(1010B)
     ENDS
STACK SEGMENT 
       DW 128 DUP (0)
ENDS      

CODE SEGMENT
      PROGRAMA DB 10 DUP('Q')
QUIZA DB 0
      ENDS 
        CALL LLAMADO
   LETRA:           
ADD CX,SEMANA
AND [DI+BP],AL 
   ENCUENTRA DB "HOLA"
 SEMANA DW 1000


     MACRO EXTINTO RAYA,
    GUION

      PINTURA:NOP
POSIBLE DW 045H DUP(023H)

EXTINTO 1,2



LLAMADO ENDP

MOV AX,BX

PROC LLAMADO

RET

SUB QUIZA,10  
ENDM         
; add your code here

ret                   





