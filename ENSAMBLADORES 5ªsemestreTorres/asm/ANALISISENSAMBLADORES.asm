
; You may customize this and other start-up templates; 
; The location of this template is c:\emu8086\inc\0_com_template.txt

org 100h     

   ;1 sentencia
  
   STD; CORRECTA
   STD ax; incorrecta
   
   ;2 sentencia  
   
   AAD; CORRECTA
   AAD CX; INCORRECTA
   
   ;3 sentencia
   CLD; CORRECTA
   CLD CX,3; INCORRECTA
   
   ;4 sentencia
   CWD; CORRECTA
   CWD AX; INCORRECTA
   
   ;5 sentencia
   IRET ; CORRECTA
   IRET AX, O9H;INCORRECTA 
   
   ;6 sentencia
   MOVSW; CORRECTA
   MOVSW BL; INCORRECTA
   
   ;7 sentencia
   DIV BL; CORRECTA 
   DIV; INCORRECTA
   
   ;8 sentencia   
   IDIV BL; CORRECTA
   IDIV; INCORRECTA 
   
   ;9 sentencia
   ; MOV AL,2
   ; MOV BL,4
   IMUL BL 
   IMUL 10H; INCORRECTA
   
   ;10 sentencia
   POP DX; CORRECTA
   POP ; INCORRECTA
  
   ;11 sentencia
   XCHG  AL,AH; CORRECTA
   XCHG  24H; INCORRECTA
  
   ;12 sentencia
   SHL AL, 1;CORRECTA
   SHL; INCORRECTA 
  
   ;13 sentencia   
   LDS AX, [SI]; CORRECTA
   LDS LABEL1; INCORRECTA  
   
   ;14 sentencia
   ;MOV AL,6 
   ADD AL, -3 CORRECTA
   ADD INCORRECTA
   
   ;15 sentencia 
   JNS LABEL1; CORRECTA
   JNS VAR2,3H; INCORRECTA
   
   ;16 sentencia 
   JS LABEL1; CORRECTA
   JS BL, AX; INCORRECTA
   
   ;17 sentencia 
   LOOPNE LABEL1; CORRECTA
   LOOPNE AX,[SI] ; INCORRECTA
   
   ;18 sentencia 
   JAE LABEL1; CORRECTA
   JAE AX, 09H; INCORRECTA
   
   ;19 sentencia 
   JCXZ LABEL1;CORRECTA
   JCXZ BL;INCORRECTA
   
   ;20 sentencia 
   JL LABEL1 ; CORRECTA
   JL; INCORRECTA        

ret




