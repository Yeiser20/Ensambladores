STACK SEGMENT
   DW 1024 DUP(0)
ENDS

DATA SEGMENT
   MENSAJE DB 'HOLA DAME UNA CDENA','$'
   MAX DB 20
   LONG DB ?
   RESPUESTA DB 20 DUP(' '),'$'
ENDS

CODE SEGMENT
   
   MOV CX,0  
   MOV DH,24
   MOV DL,79
   MOV BH,07
   MOV AH,06
   INT 10H
   
   
   MOV AX,@DATA
   MOV DS,AX
   MOV AX,0
             
   LEA DX,MENSAJE
   MOV AH,09
   INT 21H      
  
   MOV DX,200H
   MOV BH,0
   MOV AH,02
   INT 10H       
             
   LEA DX,MAX
   MOV AH,0AH
   INT 21H    
          
   MOV DX,400H
   MOV BH,0
   MOV AH,02
   INT 10H        
   
   MOV BH,0
   MOV BL,LONG
   MOV SI,BX
   LEA BX,RESPUESTA
   MOV AH,32
   MOV [BX+SI],AH          
             
   LEA DX,RESPUESTA
   MOV AH,09
   INT 21H
   
   MOV DX,500H
   MOV BH,0
   MOV AH,02
   INT 10H  
   
   MOV DX,0
   MOV BH,0
   MOV AH,02
   INT 10H

     MOV AX,4C00H
   INT 21H

ENDS