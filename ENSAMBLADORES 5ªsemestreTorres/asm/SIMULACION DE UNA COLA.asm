  
 
  include 'emu8086.inc'
  
   DATA SEGMENT   
          
    MENSAJE DB "SIMULADOR DE UNA COLA CON NUMEROS...$"
    MSG01 DB "-------------MENU-----------...$"
    MSG02 DB "    1.- INSERTAR VALORES...$"
    MSG031 DB "    2.- MOSTRAR...$"
    MSG03 DB "    3.- ELIMINAR...$"
    MSG04 DB "    4.- SALIR...$"
    MSG05 DB "    SELECCIONA LA OPCION$"
    NUM DB 40 DUP(' '),'$'
    RESPUESTA DB 40 DUP(' '),'$'
    ARRAY db 10 dup(0) 
    ; add your code here   
    
 ENDS 
                    
  STACK SEGMENT
   DW 1024 DUP(0)  
   
  ENDS
  

CODE segment 
    MAIN PROC  
        PRINTN ""
        CALL MENU
        RET   
        
        MAIN ENDP
    
   MENU PROC  
   MOV CX,00H ; inicializo contador en cero
   MOV AL,00H  
   MOV BP,1 
    
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
    print "SIMULADOR DE UNA COLA ...$"
    printn ""
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
    
   ;CUARTO MENSAJE
   MOV AH, 09H
   LEA DX, MSG031
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
  
   
   ;LEER EL TECLADO 

    MOV AH,01
    INT 21H
    SUB AL,30H
    MOV RESPUESTA, AL
    
   ;VERIFICANDO OPCION
   
   CMP RESPUESTA, 1            
   JE CALL INSERTAR 
   
   CMP RESPUESTA, 2
   JE CALL MOSTRAR     
   
   
   CMP RESPUESTA, 3 
   JE CALL ELIMINAR  
   
   
   CMP RESPUESTA ,4
   JE CALL SALIR   
   
   MENU ENDP 
   
   INSERTAR PROC : 
     CALL VECTOR  
   INSERTAR ENDP 
             
    VECTOR PROC :
     
    CALL INICIALIZAR
    CALL LLENAR
                
        VECTOR ENDP
             
LLENAR PROC : 
    
    PRINTN ""                   
    PRINT "NUMERO: "  
    
    MOV AH,1
    INT 21H 
    MOV [ARRAY+SI],AL  
    ADD SI, 1 
    INC CL
    CMP CL, 10 
    JE MENU
    JNE LLENAR
    
    RET          
    LLENAR ENDP 


MOSTRAR PROC :  
     
  CALL IMPRIMIR_VECTOR  
    
    MOSTRAR ENDP
    

ELIMINAR PROC :  
         
    
    ELIMINAR ENDP
    

SALIR PROC :
      PRINTN ""   
      PRINTN "PRESIONA CUALQUIER TECLA PARA SALIR"
      MOV AH,0H
      INT 16H
      MOV AX,4C00H
      INT 21H
      RET    
        
   SALIR ENDP

INICIALIZAR PROC :
   
    LEA SI, ARRAY
    MOV AX,@DATA
    MOV DS, AX
    PRINTN ""  
    PRINT "VALORES DEL VECTOR" 
    PRINTN ""
 
    RET
                    
  INICIALIZAR ENDP  

IMPRIMIR_VECTOR PROC :
                       
       PRINTN "" 
       
       MOV SI, 14
       MOV CX, 8  
       CALL PRINT1  
        
       PRINTN ""
       CALL MENU
       
        
       RET               
                       
    IMPRIMIR_VECTOR ENDP 

       PRINT1 PROC : 
        MOV AX, ARRAY[SI]
        LEA dx, ARRAY      
        MOV ah,9
        INT 21h
        ADD SI, 2
        LOOP PRINT1
       MOV SI , 0
       MOV CX, 0
       PRINT1 ENDP
        
         
  ENDS






