;Menu usando mouse en TASM 
.model SMALL
.stack 64

.data  
     coordX db ?    ; cx
     coordY db ?    ; dx
     salto db 10,13  
     msj1 db "SELECCIONASTE OPCION 1","$"  
     msj2 db "SELECCIONASTE OPCION 2","$" 
     msj3 db "SELECCIONASTE OPCION 3","$"
     msj4 db "SELECCIONASTE OPCION 4","$"
     opc01 db "OPCION 1","$"
     opc02 db "OPCION 2","$"
     opc03 db "OPCION 3","$"
     opc04 db "OPCION 4","$"
     msjs db  "ADIOS PAPUS XD","$"
     
.code


  mov ax,@data
  mov ds,ax

  menu:
      
    ;OPCION 1
    mov ah,02h
    mov bh,00
    mov dl,016d
    mov dh,06h
    int 10h
    mov ah,09h    ; muestra opcion 1
    lea dx,opc01
    int 21h
    ; primero posicionamos el cursor en pantalla en las coordenadas (0,79)
    
    ;OPCION 2
    mov ah,02h
    mov bh,00
    mov dl,56d  ;coordenada en x de la pantalla (80x25) 
    mov dh,06h ; coordenadaq en y de la pantalla (80x25)
    int 10h
    mov ah,09h    ; muestra opcion 1
    lea dx,opc02
    int 21h

    ;OPCION 3
    mov ah,02h
    mov bh,00
    mov dl,16D
    mov dh,12h
    int 10h   
    ;imprimir palabra ocion
    ; imprimimos el caracter de la X ahi 
    mov ah,09h    ; muestra opcion 1
    lea dx,opc03
    int 21h

    ;OPCION 4    
    mov ah,02h
    mov bh,00
    mov dl,56d
    mov dh,12h
    int 10h   
    ;imprimir palabra ocion
    ; imprimimos el caracter de la X ahi 
    mov ah,09h    ; muestra opcion 1
    lea dx,opc04
    int 21h

  click:        ; label para dirigir el click del mouse
    mov ax, 01h ; funcion que activa el cursor del mouse
    int 33h     ; interrupcion para el manejo del mouse
    
    mov ax, 03h ; funcion para obtener que boton del mouse fue presionado 
    int 33h
         ; y coordenadas

    selec:
    ; si fue presionado el btn izquierdo salta a coordenadas
    cmp bx, 1   ; compara con 1 si es que fue presionado el btn izquierdo
    je coordenadas ; si fue presionado el btn izquierdo salta a coordenadas
    cmp bx, 2  ; si fue con click derecho salta de nuevo a la etiqueta click 
    je salir   ; sale si al seleccionar el click es igual a 2 
    jmp click  ; si no es igual a dos salta a click 


  coordenadas:  
    mov ax, cx ; el valor de las coordenadas en X (en pixeles) se guarda en cx 
    mov bl,8   ; asignamos el valor de 8 a bl
    div bl     ; se divide entre 8 para obtener la posicion en pantalla donde ira
    mov coordX, al ; guarda el resultado de la division en coordX

    mov ax, dx  ; el valor de las coordenas en Y (en pixeles se guarda en DX
    mov bl,8    ; mueve a 8
    div bl      ; realiza la division: coordenadaY= pixeles/8 
    mov coordY, al  ;guardalo en coordY

        cmp coordY,06    ; Ahora comprobamos que este en ese rango en este caso en la fila 0
        je comprobarPline   ; si es correcto ve a comprobar que este en la vertical igual 
        cmp coordY,18    ; Ahora comprobamos que este en ese rango en este caso en la fila 0
        je comprobarUline   ; si es correcto ve a comprobar que este en la vertical igual
        jmp salir
            
        comprobarPline:
            cmp coordX,10H  ; 4FH = 00
            je opc1   
            cmp coordX,11H  ; 4FH = 01D 
            je opc1    
            cmp coordX,12H  ; 4FH = 02D 
            je opc1    
            cmp coordX,13H  ; 4FH = 03D 
            je opc1    
            cmp coordX,14H  ; 4FH = 04D 
            je opc1    
            cmp coordX,15H  ; 4FH = 05D 
            je opc1    
            cmp coordX,16H  ; 4FH = 06D 
            je opc1    
            cmp coordX,17H  ; 4FH = 07D 
            je opc1    
               
            cmp coordX,38H  ; 4FH = 71D 
            je opc2
             cmp coordX,39H  ; 4FH = 72D 
            je opc2 
             cmp coordX,3AH  ; 4FH = 73D 
            je opc2 
             cmp coordX,3BH  ; 4FH = 74D 
            je opc2 
             cmp coordX,3CH  ; 4FH = 75D 
            je opc2 
             cmp coordX,3DH  ; 4FH = 76D 
            je opc2 
             cmp coordX,3EH  ; 4FH = 77D 
            je opc2 
              cmp coordX,3FH  ; 4FH = 78D 
            je opc2   
            jmp salir 
                
        comprobarUline:    
            cmp coordX,10H  ; 4FH = 79D 
            je opc3    
            cmp coordX,11H  ; 4FH = 79D 
            je opc3 
            cmp coordX,12H  ; 4FH = 79D 
            je opc3 
            cmp coordX,13H  ; 4FH = 79D 
            je opc3 
            cmp coordX,14H  ; 4FH = 79D 
            je opc3 
            cmp coordX,15H  ; 4FH = 79D 
            je opc3 
            cmp coordX,16H  ; 4FH = 79D 
            je opc3 
            cmp coordX,17H  ; 4FH = 79D 
            je opc3 
            cmp coordX,38H  ; 4FH = 79D 
            je opc4 
            cmp coordX,39H  ; 4FH = 79D 
            je opc4 
            cmp coordX,3AH  ; 4FH = 79D 
            je opc4 
            cmp coordX,3BH  ; 4FH = 79D 
            je opc4 
            cmp coordX,3CH  ; 4FH = 79D 
            je opc4 
            cmp coordX,3DH  ; 4FH = 79D 
            je opc4 
            cmp coordX,3EH  ; 4FH = 79D 
            je opc4
            cmp coordX,3FH  ; 4FH = 79D 
            je opc4
            jmp salir
            
    opc1:    
    mov ah,02h
    mov bh,00
    mov dl,30d
    mov dh,0Ch
    int 10h
    mov ah,09h    ; muestra opcion 1
    lea dx,msj1
    int 21h
    mov ah,01h      ; espera una tecla para salir y volver al menu
    int 21h
    
    JE limpiar_p
    
    
    opc2:
     mov ah,02h
    mov bh,00
    mov dl,30d
    mov dh,0Ch
    int 10h
    ; imprimimos el caracter de la X ahi 
    mov ah,09h
    lea dx,msj2  ; muestra opcion 2
    int 21h
    mov ah,01h      ; espera una tecla para salir y volver al menu
    int 21h
    JE limpiar_p  
    
    opc3:
    mov ah,02h
    mov bh,00
    mov dl,30d
    mov dh,0Ch
    int 10h
   
    mov ah,09h
    lea dx,msj3  ; muestra opcion 2
    int 21h
    mov ah,01h      ; espera una tecla para salir y volver al menu
    int 21h
    JE limpiar_p
    
    opc4:
    mov ah,02h
    mov bh,00
    mov dl,30d
    mov dh,0Ch
    int 10h 
 
   
    mov ah,09h
    lea dx,msj4  ; muestra opcion 4
    int 21h
    mov ah,01h      ; espera una tecla para salir y volver al menu
    int 21h
    JE limpiar_p       
   
   ; se limpian los valores de la pantalla
   limpiar_p:
    mov ah, 02h
    mov dx,0000h
    int 10h
    
    mov ax, 0600h
    mov bh,07h
    mov cx, 0000h
    mov dx, 194FH
    INT 10H 
    je menu
    
   salir:

    mov ax,0600h   ; funcion para dezplazar lineas arriba (limpiar pantalla)
    mov bh,10
    mov cx,0000h
    mov dx,184fh
    int 10h
   
    mov ah,02h
    mov bh,00
    mov dl,30d
    mov dh,0Ch
    int 10h
    mov ah,09h    ; muestra opcion 1
    lea dx,msjs
    int 21h
    
    mov ah,01h  ; espera una tecla para salir
    int 21h
    
    mov ah,4ch  ; funcion que finaliza el programa
    int 21h

.exit
