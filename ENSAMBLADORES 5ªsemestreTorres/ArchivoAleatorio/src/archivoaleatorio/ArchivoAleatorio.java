package archivoaleatorio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class ArchivoAleatorio {
    static RandomAccessFile archivo;
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
       Scanner entrada = new Scanner(System.in);
       int op;
       
       String nombreArchivo;
      
       AccesoAleatorio archivoAleatorio = new AccesoAleatorio(archivo);
       
       do{
           System.out.println("MENU");
           System.out.println("1.Crear Archivo/Pedir Claves");
           System.out.println("2.Mostrar Claves");
           System.out.println("3.Buscar una Clave (pedir posicion)");
           System.out.println("4.Salir");
           System.out.println("Opcion :");
           op = entrada.nextInt();
           entrada.nextLine();
           System.out.println(op);
           switch(op){
               case 1:
                   System.out.println("Dame el nombre del Archivo para trabajar: ");
                   nombreArchivo = entrada.nextLine();
                   archivo = new RandomAccessFile(nombreArchivo,"rw");
                   archivoAleatorio.crearArchivo(nombreArchivo);
                   break;
               case 2:
                   archivoAleatorio.mostrarArchivo();
                   break;
               case 3:
                   System.out.println("Dame la posicion a buscar: ");
                   archivoAleatorio.buscarDato(entrada.nextInt()); 
                   break;
           }
       }while(op != 4);
    }
    
}
