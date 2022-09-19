.data segment				 
datasegment
.data segment				 
;Variables
 pkey db "press any key...$"
    var1 db 'hola'
	 pkey db press any key...$"
    var2 dw 0
tecla db 0 
Vtecla BD 0 
7tecla Wd 0 
tecla db 0 
simbolo db 045H
simbolo db 45H
simbolo db 45


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
msg db "hello world $"
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
fin:
ends
.stack segment				 
 dw   128  dup(0)			 
 dw   128  dupy(0)	
pruebabinario
simbolo db a1010101b
simbolo db Aa010101b
simbolo db 01010101b
simbolo db 01010101B
simbolo db 1111111101010101b
simbolo db 111111110101010100000000b
simbolo db 010101b	 
simbolo db 03010101b
simbolo db 03010101b
simbolo db 033b
simbolo db 12345678b
pruebahexa
simbolo db 0A5h
simbolo db 0AFh
simbolo db 0BAh
simbolo db 0Aah
simbolo db 045h
simbolo db 045H
simbolo db 040h
simbolo db 000h
simbolo db 00000h
simbolo db 04545h
simbolo db 0454552H
simbolo db 0454h
simbolo db 045a4h
simbolo db 5h
simbolo dw 04545h
simbolo dw 04545aAh
simbolo dw 04545aGh
pruebadecimal
simbolo dw a23
simbolo dw A23
simbolo dw 123ad
simbolo dw 123d
simbolo dw 123D
simbolo dw 12D
simbolo dw 1
simbolo dw 12bd
simbolo dw 12
simbolo dw 123D
simbolo dw 123d
;pruebainstrucciones
SCASW
HLT
XLATB
INTO
AAD
INT
MOVSW
POP
IMUL
INC
ADC
ADD
SUB
SAR
JBE
JNGE
JNA
JS
JL
JNZ
scasw
hlt
xlatb
into
aad
int
movsw
pop
imul
inc
adc
add
sub
sar
jbe
jnge
jna
js
jl
jnz


IMUL BX
imul bx
IMUL MEMORIA
imul memoria 


INC AX
INC MEMORIA
inc bx
inc memoria
INC BL

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
ADD AH, simbolo
ADD ax, simbolo
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


