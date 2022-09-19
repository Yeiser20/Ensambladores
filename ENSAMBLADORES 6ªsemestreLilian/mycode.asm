NAME "DECIMAL A BINARIO"

org 100h

jmp start

MSG1 db 0Dh, 0Ah, "Ingrese un numero decimal: $"
MSG2 db 0Dh, 0Ah, "El numero en hexadecimal es: $ "

BUFFER db 7,?, 5 dup (0), 0, 0

BINARY dw ?

start:

MOV DX, OFFSET MSG1
MOV AH, 9
INT 21H                                     

MOV DX, OFFSET buffer
MOV AH, 0ah
INT 21h  
; presionar 0 para terminar:

MOV BX, 0
MOV BL, BUFFER[1]
MOV BUFFER[BX+2],0
                   
                   
                   
LEA SI, BUFFER + 2 
CALL TOBIN         
 

MOV BINARY, CX 

JCXZ STOP    
;IMPRIME EL PRERESULTADO

MOV DX, OFFSET MSG2
MOV AH, 9
INT 21H

; IMPRIMIR EN FORMA HEXADECIMAL
MOV BX, BINARY
MOV CX, 16

PRINT : MOV AH, 2
        MOV DL, '0'
        TEST BX,1000000000000000b
        JZ ZERO
        MOV DL, '1'

ZERO:INT 21H
    SHL BX, 1

LOOP PRINT
MOV DL,'b'
INT 21H 


JMP START
STOP:
RET

TOBIN PROC NEAR
 PUSH DX
 PUSH AX
 PUSH SI 
 
JMP PROCESS

MAKE_MINUS DB ?
TEN DW 10

PROCESS:
MOV CX, 0
MOV CS:MAKE_MINUS,0

NEXT_DIGIT:

MOV AL,[SI]
INC SI

    CMP AL,0
    JNE NOT_END
    JMP STOP_INPUT
    
NOT_END:

    CMP AL, '-'
    JNE OK_DIGIT
    MOV CS:MAKE_MINUS,1 
    JMP NEXT_DIGIT
    
OK_DIGIT:

    PUSH AX
    MOV AX, CX
    MUL CS: TEN 
    MOV CX, AX
    POP AX 
     
    SUB AL, 30h
    MOV AH, 0
    MOV DX, CX 
    ADD CX, AX
               
    JMP NEXT_DIGIT

STOP_INPUT:
         
   CMP CS:MAKE_MINUS,0
   JE NOT_MINUS
   NEG CX
   
NOT_MINUS :
    
    POP SI
    POP AX 
    POP DX
    RET
TOBIN       ENDP
    
     
    


