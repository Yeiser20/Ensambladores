.model small
.stack 64

.data                
     coordX db ?    ; cx
     coordY db ?    ; dx
     salto db 10,13  
     msj1 db 10,13,7,"SELECCIONASTE OPCION 1","$"  
     msj2 db 10,13,7, "SELECCIONASTE OPCION 2","$" 
     msj3 db 10,13,7, "SELECCIONASTE OPCION 3","$"
     msj4 db 10,13,7, "SELECCIONASTE OPCION 4","$"
     opc db 10,13,7,"OPCION" 
.code

    main proc far
    mov ax,@data
    mov ds,ax
    
    
       
    MOV CX,0  
    MOV DH,24
    MOV DL,79
    MOV BH,07
    MOV AH,06
    INT 10H
    
    ; primero posicionamos el cursor en pantalla en las coordenadas (0,00)
     mov ah,09h    ; muestra opcion 1
    lea dx,opc
    int 21h
    
    mov ah,02h
    mov bh,00
    mov dl,00d
    mov dh,0h
    int 10h
  
    ;imprimir palabra ocion
    ; imprimimos el caracter de la X ahi 
    mov ah,02h
    mov dl,'1'
    int 21h
    ; primero posicionamos el cursor en pantalla en las coordenadas (0,79)
    mov ah,02h
    mov bh,00
    mov dl,79d  ;coordenada en x de la pantalla (80x25) 
    mov dh,0h ; coordenadaq en y de la pantalla (80x25)
    int 10h
    
    ; imprimimos el caracter de la X ahi 
    mov ah,02h
    mov dl,'2'
    int 21h  
    

    
    mov ah,02h
    mov bh,00
    mov dl,00d
    mov dh,17h
    int 10h   
    ;imprimir palabra ocion
    ; imprimimos el caracter de la X ahi 
    mov ah,02h
    mov dl,'3'
    int 21h 
    
        
    mov ah,02h
    mov bh,00
    mov dl,79d
    mov dh,17h
    int 10h   
    ;imprimir palabra ocion
    ; imprimimos el caracter de la X ahi 
    mov ah,02h
    mov dl,'4'
    int 21h
    
    ; Activamos el handler del mouse
    mov ax,00h
    int 33h
    ; lo hacemos visible e indicamos esta etiqueta que nos sera de utilidad
  
   
    consultar:
    mov ax,01h
    int 33h
    ; Con esta funcion obtenemos el clic con el que se hizo y las coordenadas en pixeles
    ; de donde anda el cursor
    mov ax,03h
    int 33h  
    
    
    
    cmp bx,1    ; si se presiono clic izquierdo
    je izquierda ; ve a una etiqueta que se llama izquierda 
    cmp bx,2    ; si no compara ahora a ver si no es clic derecho
    jmp consultar   ; si si es vuelve a consultar porque solo se debe validar con clic izquierdo
    
     izquierda:
        ; el valor de las lineas horizontales se van del registro cx a ax para dividirlos
        mov ax,cx
        mov bl,8    ; en bl se guarda el valor dde 8 porque para hacer la conversion es necesario
        div bl      ; se conoce como my key el valor obtenido
        mov coordX,al   ; cuardamos el valor de la division en coordX
        
        mov ax,dx       ; aplicamos el mismo proceso 
        mov bl,8
        div bl
        mov coordY,al   ; se guarda en coordY
        
        cmp coordY,0    ; Ahora comprobamos que este en ese rango en este caso en la fila 0
        je comprobarPline   ; si es correcto ve a comprobar que este en la vertical igual 
        
        cmp coordY,23    ; Ahora comprobamos que este en ese rango en este caso en la fila 0
        je comprobarUline   ; si es correcto ve a comprobar que este en la vertical igual
        jmp consultar
            
        comprobarPline:
            cmp coordX,00H  ; 4FH = 79D 
            je opc1      
             cmp coordX,4FH  ; 4FH = 79D 
            je opc2  
            jmp consultar     
        comprobarUline:    
             cmp coordX,00H  ; 4FH = 79D 
            je opc1      
             cmp coordX,4FH  ; 4FH = 79D 
            je opc2      
            jmp consultar
            
    opc1:
        
    mov ax,0600h   ; funcion para dezplazar lineas arriba (limpiar pantalla)
    mov bh,10      ; mantiene la pantalla negra
    mov cx,0000h
    mov dx,184fh
    int 10h
    
    mov ah,09h
    lea dx,msj1     ; muestra en pantalla hola
    int 21h
    
    mov ah ,01h      ; espera una tecla para salir y volver al menu
    int 21h
    jmp main
    jmp consultar
    
    opc2:
     mov ah,02h
    mov bh,00
    mov dl,30d
    mov dh,0Ch
    int 10h
    ; imprimimos el caracter de la X ahi 
    mov ah,09h
    mov dx,offset 2
    int 21h
    jmp consultar  
    
        opc3:
     mov ah,02h
    mov bh,00
    mov dl,30d
    mov dh,0Ch
    int 10h
    ; imprimimos el caracter de la X ahi 
    mov ah,09h
    mov dx,offset msj3
    int 21h
    jmp consultar
    
        opc4:
     mov ah,02h
    mov bh,00
    mov dl,30d
    mov dh,0Ch
    int 10h 
    ; imprimimos el caracter de la X ahi 
    mov ah,09h
    mov dx,offset msj4
    int 21h
    jmp consultar       
   
    salida:
    mov ax,02h
    int 33h
    
    mov ah,4ch
    int 21h
.exit    

main endp
end main