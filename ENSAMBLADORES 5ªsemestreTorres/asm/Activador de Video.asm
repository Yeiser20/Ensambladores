  include 'emu8086.inc'
  
   DATA SEGMENT   
          
    MENSAJE DB " ACTIVADOR DE MODOS DE VIDEO  $"
    MSG01 DB "         -------------MENU-----------...$"
    MSG02 DB "    1.- TEXTO 40 x 25...$"
    MSG03 DB "    2.- TEXTO 80 x 25...$"
    MSG04 DB "    3.- MODO GRAFICO...$"
    MSG05 DB "    4.- SALIR...$"
    MSG06 DB "    SELECCIONA LA OPCION:  $"               
    MSG07 DB "PRESIONA CUALQUIER TECLA PARA SALIR...$" 
    MSG08 DB " TEXTO 40 X 25 $"
    MSG081 DB " TEXTO 80 X 25 $" 
    MSG09 DB " MODO GRAFICO $"
    
    RESPUESTA DB 40 DUP(' '),'$'
    ; add your code here   
    
 ENDS 
                    
  STACK SEGMENT
   DW 128 DUP(0)  
   
  ENDS
  

CODE segment 
    
  START :   
   MOV CX,00H ; inicializo contador en cero
   MOV AL,00H  
   MOV BP,1 
    
   MOV AX,DATA
   MOV DS,AX
   MOV ES,AX
     
 MENU PROC 
   
   
   ;INTERLINEADO
   printn ""
   
   ;PIMER MENSAJE 
   MOV AH,09H
   LEA DX, MENSAJE
   INT 21H  
           
   printn ""
   
   ;SEGUNDO MENSAJE 
   MOV AH,09H
   LEA DX, MSG01
   INT 21H
   
   ;INTERLINEADO
    printn ""
  
   ; TERCER MENSAJE
   MOV AH, 09H
   LEA DX, MSG02
   INT 21H   
      
   ;INTERLINEADO
    printn ""
 
   ;CUARTO MENSAJE
   MOV AH, 09H
   LEA DX, MSG03
   INT 21H  
   
   ;INTERLINEADO
    printn ""  
       
   ;QUINTO MENSAJE
   MOV AH, 09H
   LEA DX, MSG04
   INT 21H 
   
   ;INTERLINEADO
    printn ""
       
   ;SEXTO MENSAJE
   MOV AH, 09H
   LEA DX, MSG05
   INT 21H 
           
   ;INTERLINEADO
    printn "" 
    printn ""       
           
   ;SEPTIMO MENSAJE
   MOV AH, 09H
   LEA DX, MSG06
   INT 21H
   
   ;LEER EL TECLADO 

    MOV AH,01
    INT 21H
    SUB AL,30H
    MOV RESPUESTA, AL
    
   ;VERIFICANDO OPCION
   
    CMP RESPUESTA, 1            
     JE CALL TEXTOUNO
   
    CMP RESPUESTA, 2
     JE  CALL TEXTODOS
     
    CMP RESPUESTA, 3
     JE CALL MODOGRA
    
    CMP RESPUESTA ,4 
    JE CALL SALIR
  
   
   MENU ENDP  
 
 
   TEXTOUNO: 
   PRINTN ""
   PRINTN ""
   MOV AH, 09H
   LEA DX, MSG08
   INT 21H  
  
   ;CONFIGURAR MODO
   MOV AH,0 
   MOV AL, 00H
   INT 10H
   
   
   JE CALL MENU 
   
   
 
   RET
   
   TEXTODOS :
   PRINTN ""
   PRINTN ""
   MOV AH, 09H
   LEA DX, MSG081
   INT 21H    
   ;CONFIGURAR MODO
   MOV AH,0 
   MOV AL, 03H 
   INT 10H
   
   JE CALL MENU
   RET
   
   MODOGRA:
   PRINTN ""
   PRINTN ""
   MOV AH, 09H
   LEA DX, MSG09
   INT 21H 
   JE CALL MENU 
   RET
   
   
   SALIR:
   PRINTN ""
   PRINTN ""   
      MOV AH, 09H
      LEA DX, MSG07
      INT 21H
      MOV AH,0H
      INT 16H
      MOV AX,4C00H
      INT 21H
      RET
          
  ENDS






