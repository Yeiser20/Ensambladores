  
 
  include 'emu8086.inc'
  
   DATA SEGMENT   
          
    MENSAJE DB "SIMULADOR DE UNA COLA CON NUMEROS...$"
    MSG01 DB "-------------MENU-----------...$"
    MSG02 DB "    1.- INSERTAR VALORES...$"
    MSG03 DB "    2.- ELIMINAR...$"
    MSG04 DB "    3.- SALIR...$"
    MSG05 DB "    SELECCIONA LA OPCION:  $"
    NUM DB 40 DUP(' '),'$'
    RESPUESTA DB 40 DUP(' '),'$'
    ARRAY DB 10 dup(0) 
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
   
    CALL LIMPIAR     
    PRINTN ""
    CALL IMPRIMIR_VECTOR 
   
   
   
   ;INTERLINEADO
   MOV AH,02H
   MOV DL, 10
   INT 21H
   MOV AH, 02H
   MOV DL, 13 
   INT 21H 
   
   ; PIMER MENSAJE 
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
       
   ;QUINTO MENSAJE
   MOV AH, 09H
   LEA DX, MSG04
   INT 21H 
   
   ;INTERLINEADO
    printn ""
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
   JE CALL DELETE
    
   CMP RESPUESTA ,3
   JE CALL SALIR   
   
   
   MENU ENDP 
   
    
    
   INSERTAR PROC : 
   
     CALL AGREGAR  
   
   INSERTAR ENDP 
             
    
            
            
                            
   VECTOR PROC :
     
    PRINTN ""  
    PRINT "VALORES DEL VECTOR" 
    PRINTN "" 
    PRINTN ""
    
    CALL LLENAR
                
   VECTOR ENDP  
   
             
    
    
    LLENAR PROC : 
    
    PRINTN ""                   
    PRINT "NUMERO: "  
    INC CL
    
    MOV AH,1
    INT 21H 
    MOV ARRAY[SI],AL  
    ADD SI, 1 
    PRINTN ""
    
    CMP CL, 10 
    JE MENU
    JNE MENU
     
    
    AGREGAR:
    CMP CL,10      ;COMPROBAMOS SI HAY UN 10
    JE NOPERMITIR
    JNE VECTOR

    
    NOPERMITIR:
      
    PRINTN ""
    PRINT "VECTOR LLENO"
    PRINTN ""
    CALL IMPRIMIR_VECTOR
    ;RET
    CALL MENU

    RET      
    
        
    LLENAR ENDP
    

    ELIMINAR PROC :  
                                          
        PRINT ""
        PRINT "ELIIMINANDO ....."
        
        DEC SI                 
        DEC CL                  
        MOV ARRAY[SI], 00H     
    
        CALL MENU  
    
    ELIMINAR ENDP
    
   
   
    
    DELETE PROC :
        
    CMP CL,0        ;COMPARAR SI HAY 0 ELEMENTOS DENTRO DEL VECTOR
    JE NOBORRAR
    JNE ELIMINAR
    
    DELETE ENDP 
    
    

      SALIR PROC :
      PRINTN ""   
      PRINTN "PRESIONA CUALQUIER TECLA PARA SALIR"
      MOV AH,0H
      INT 16H
      MOV AX,4C00H
      INT 21H
      RET    
        
      SALIR ENDP    
      
    NOBORRAR:
    PRINTN ""
    PRINT "NO HAY ELEMENTOS QUE SE PUEDAN BORRAR"
    PRINTN ""
    CALL MENU


   LIMPIAR:
    MOV AH, 0FH
    INT 10H
    MOV AH,0
    INT 10H
    RET
  
    IMPRIMIR_VECTOR PROC :
    
                      
    LEA DX, ARRAY       
	MOV AH,9
	INT 21H  
	RET        
                        
    IMPRIMIR_VECTOR ENDP    
    
    ORDENAR_VECTOR PROC :
    
    PRINT "VECTOR ORDENADO"
    MOV SI,14
    MOV CX, 8
    
    IMPRIMIR : 
        MOV AX, ARRAY[SI]   
        CALL IMPRIMIR_VECTOR
        PRINTN ""
        ADD SI, 2
        LOOP IMPRIMIR 
     MOV SI, 0
     MOV CX, 0
     PRINTN ""
     PRINTN "TERMINADO"
     
    
    ORDENAR_VECTOR ENDP
    
    

         
  ENDS






