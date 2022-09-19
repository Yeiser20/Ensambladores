
 JNS fin:
  JNS fin:
   JAE fin:
    JCXZ fin:
     JL fin:
       
      
      AAD
      CLD
      CWD
      IRET
      MOVSW
      MOVSW 3
      MOVSW ax
      STD  
      
      ADD BX,[BX+SI]
      ADD [BX+SI],BX
      ADD BX,AX                               
      ADD [BX+SI],21H
      ADD [BX+SI],21
      ADD [BX+SI],010011B
      ADD [BX+SI],-2
      ADD [BX+SI],2FH
      
      ADD BX,21H 
      ADD BX,21
      ADD BX,010011B
      ADD BX,-2
      ADD BX,2FH 
      
      DIV AX,[BX+SI]    
      IDIV AX,[BX+SI]   