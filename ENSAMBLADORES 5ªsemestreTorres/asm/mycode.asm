  
   DATA SEGMENT   
          
    MENSAJE DB "SIMULADOR DE UNA COLA ...$"
    MSG01 DB "-------------MENU-----------...$"
    MSG02 DB "    1.- INSERTAR...$"
    MSG03 DB "    2.- ELIMINAR...$"
    MSG04 DB "    3.- SALIR...$"
    MSG05 DB "    DAME UN NUMERO...$"
    MSG06 DB "SELECCIONE UNA OPCION ...$"
    MENSAJEF DB " LOS DATOS SON...$"
    NUM DB ?
    RESPUESTA DB 40 DUP(' '),'$'

    MAX DB 10
    LONG DB ?   
    VECTOR_INICIAL DB ?,?,?,?,?,?,?,?,?,?
    VECTOR_FINAL DB 10 DUP(0) 
    ; add your code here   
    
 ENDS 
                    
  STACK SEGMENT
   DW 1024 DUP(0)  
   
  ENDS
  

   CODE segment
    
   MENU PROC  
          ;LIMPIAR LA TERMINAL
           
   MOV CX,0  
   MOV DH,24
   MOV DL,79
   MOV BH,07
   MOV AH,06
   INT 10H
    
   MOV AX,DATA
   MOV DS,AX
   MOV ES,AX 
   
   ;INTERLINEADO
   MOV AH,02H
   MOV DL, 10
   INT 21H
   MOV AH, 02H
   MOV DL, 13 
   INT 21H 
   
   ; PIMER MENSAKE 
   MOV AH, 09H
   LEA DX, MENSAJE
   INT 21H
   
   ;INTERLINEADO
   MOV AH,02H
   MOV DL, 10
   INT 21H
   MOV AH, 02H
   MOV DL, 13 
   INT 21H 
   
   MOV AH,02H
   MOV DL, 10
   INT 21H
   MOV AH, 02H
   MOV DL, 13 
   INT 21H 
   
   ;SEGUNDO MENSAJE 
   MOV AH,09H
   LEA DX, MSG01
   INT 21H
   
   ;INTERLINEADO
   MOV AH,02H
   MOV DL, 10
   INT 21H
   MOV AH, 02H
   MOV DL, 13 
   INT 21H 
   ; TERCER MENSAJE
   MOV AH, 09H
   LEA DX, MSG02
   INT 21H   
      
   ;INTERLINEADO
   MOV AH,02H
   MOV DL, 10
   INT 21H
   MOV AH, 02H
   MOV DL, 13 
   INT 21H  
   
   ;CUARTO MENSAJE
   MOV AH, 09H
   LEA DX, MSG03
   INT 21H  
   
   ;INTERLINEADO
   MOV AH,02H
   MOV DL, 10
   INT 21H
   MOV AH, 02H
   MOV DL, 13 
   INT 21H           
       
   ;QUINTO MENSAJE
   MOV AH, 09H
   LEA DX, MSG04
   INT 21H 
   
      ;INTERLINEADO
   MOV AH,02H
   MOV DL, 10
   INT 21H
   MOV AH, 02H
   MOV DL, 13 
   INT 21H           
   
   
   MOV AH,02H
   MOV DL, 10
   INT 21H
   MOV AH, 02H
   MOV DL, 13 
   INT 21H           

       
   ;SEXTO MENSAJE
   MOV AH, 09H
   LEA DX, MSG05
   INT 21H 
   
  
    
   
   ;LEER EL TECLADO 

                 
   LEA DX,RESPUESTA
   MOV AH,0AH
   INT 21H 
           
   MOV DX,400H
   MOV BH,0
   MOV AH,02
   INT 10H    

   LEA DX,RESPUESTA
   MOV AH,09
   INT 21H     
    
   ;VERIFICANDO OPCION
   CMP RESPUESTA, 1
   JZ INSERTAR
   
   CMP AL, 2 
   JZ ELIMINAR 
   
   CMP RESPUESTA , 3
   JZ SALIR   
   
INSERTAR :

    JMP MENU
    
ELIMINAR :  
    JMP MENU
    
SALIR:      RET

MENU ENDP
         
         MOV AX, 4C00H; SALIR AL SISTEMA OPERATIVO
         INT 21H    
         
   ENDS


ret




