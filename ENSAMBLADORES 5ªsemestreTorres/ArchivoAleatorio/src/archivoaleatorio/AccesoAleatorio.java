//Abimael Tellez Guadarrama
//Practica #24
package archivoaleatorio;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class AccesoAleatorio {
    String nombre;
    RandomAccessFile archivoAleatorio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AccesoAleatorio(RandomAccessFile archivoAleatorio) {
        this.archivoAleatorio = archivoAleatorio;
    }
    
    public void crearArchivo(String nombre) throws FileNotFoundException, IOException{
        Scanner entrada = new Scanner(System.in);
        int total, clave = 0;
        
        this.setNombre(nombre);
        
        System.out.println("Dame el total de claves: ");
        total = entrada.nextInt();
        entrada.nextLine();
        
        for(int i = 1; i <= total; i++){
            System.out.println("Clave " + i + ": ");
            clave = entrada.nextInt();
            
           
        }
        archivoAleatorio.seek(archivoAleatorio.length());
        archivoAleatorio.writeInt(clave);
    }
        
    public void mostrarArchivo() throws IOException{
        int clave;
        
        try{
            archivoAleatorio.seek(0);
            while(true){
                clave = archivoAleatorio.readInt();
                System.out.println(clave);
            }
        }catch(EOFException e){
            System.out.println("Fin de Fichero");
        }
    }
    public void buscarDato(int n) throws IOException{
        int clave;
        try{
            archivoAleatorio.seek(0);
            archivoAleatorio.seek((n-1)*4);
            clave = archivoAleatorio.readInt();
            System.out.println("La clave en la posicion " + n + " es: " + clave);
        }catch(EOFException e){
            System.out.println("Fin de fichero, no existe esa posicion");
        }
    }
}

