STACK SEGMENT
DW 128 DUP (0)
ENDS

DATA SEGMENT
;Nombre
NOMBRE DB "VICTOR DANIEL" 
PAPELLIDO DB "ARCHUNDIA" 
SAPELLIDO DB "SANCHEZ" 
;---------------------
ENCUENTRA DB "HOLA"   
PROGRAMA DB 10 DUP('Q')
FLUYE DB 20 DUP(1010B) 
POSIBLE DW 045H DUP(023H)
QUIZA DB 0
ARGUMENTO EQU 45
SEMANA DW 1000 
    ENDS 

MACRO EXTINTO RAYA,GUION
SUB QUIZA,10
    ENDM

CODE SEGMENT        
MOV AX,BX   
AND [DI+BP],AL     
LETRA:
INT 010H 
PINTURA:NOP 
EXTINTO 1,2
CALL LLAMADO    
    ENDS

PROC LLAMADO 
  ADD CX,SEMANA 
    RET   
LLAMADO ENDP

    



 

