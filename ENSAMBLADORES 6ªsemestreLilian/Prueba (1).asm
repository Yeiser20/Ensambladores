.data segment				 
datasegment
.data segment				 
;Variables
pkey db "press any key...$"
var1 db 'hola'
pkey1 db "press any key...$
msg db "hello world $"
var2 dw 0
tecla db 0 
Vtecla BD 0 
7tecla Wd 0 
tecla db 0 
simbolo1 db 045H
simbolo2 db a1010101b
simbolo3 db 01010101b
simbolo4 db 01010101B
simbolo db 01010101B
simbo db 01110020B
sin db 010101b
simbolo5 db 033b
simbolo6 db 12345678b
simbolo7 db 0BAh
simbolo8 db 045H
simbolo9 db 0454h
simbolo10 dw 04545aGh
simbolo11 dw a23
simbolo12 dw 12
ends

.code segment
code
mov ah, 1 ;Leer un caracter de la entrada estandar
int 21h;Llamada al sistema operativo (DOS)
mov tecla, al
mov ah, 2 ;imprime un simbolo a la consola
mov dl, simbolo ;el caracter a mostrar, en este caso la E
int 21h;Llamada al DOS

inc tecla
mov ah, 7 ;NO imprime un simbolo a la consola
mov dl, tecla ; 
int 21h;Llamada al DOS
ret
;ah = 1 guarda caracter en al
;ah = 2 escribe un caracter en la consola. El ascii del cacacter a imprimir se pone el dl
;AH = 7 es igual a el ah=2 pero el resultado no se ve en pantalla 
;ah = 9 imprime una cadena en la consola. Considera el caracter $ como fin de cadena.
;La direccion de la cadena se expresa en 
mov msg[2], 34H
mov dx, offset msg 
mov ah, 9
int 21h
ret
lectura:

 mov ah,7
 int 21h
 mov tecla, al
 cmp al,13
cmp tecla, 122 ;si tecla es mayor a 122 entonces ir a fin3 (tecla > 122)
ja fin3
cmp tecla,96 ;si tecla no es mayor a 96 ir a fin3 (tecla <= 96)
jng fin3
sub tecla, 32 ;si es 'a' hasta 'z' entonces restarle 32
fin3: 
mov ah,2
add ax, var1
mov dl,tecla
int 21h
jmp lectura
int 021h
int 21h
int 045aah
int 04h
INT 0454545AAH
int 0g5h
int 66h
int 0ggh
int 21h
int 21h

sar
jbe
jnge
jna
js
jl
jnz

fin:
IMUL BX
imul bx
IMUL MEMORIA
imul memoria 


INC AX
INC MEMORIA
inc bx
inc memoria
INC BL



pop al
POP BL
pop ax
pop memoria
POP BX
POP MEMORIA

SAR AX,1
sar bl, 1
SAR CX,CL
sar dh, CL
SAR MEMORIA
sar ax

ADC AX, AX
adc ax,ax
ADC mem, ax
adc mem,ax
ADC AX,MEM
adc ax,mem
ADC AX
adc memoria

ADD AX,BX
ADD cl, dl
ADD AX,MEMORIA
ADD AX, MEMORIA
add memoria, bl
add memoria,bl
ADD AX
add memoria
add memoria,memoria

SUB AX,BX
sub cl, dl
SUB AX,MEMORIA
sub AX, MEMORIA
SUB memoria, bl
sub memoria,bl
SUB AX
sub memoria
SUB memoria,memoria

JBE SALTO:
jbe salto:
JNGE SALTO:
jnge salto:
JNGA SALTO:
jnga salto:
JS SALTO:
js salto:
JL SALTO:
jl salto:
JNZ SALTO:
jnz salto:

jbe salto
jbe ax, bx
jbe bl,cl
jbe ax
jbe 333


jnga salto
JS SALTO
jl salto
jnz salto
ends
.stack segment				 
dw 128 dup(0)			 
dw 128 dupy(0)	


