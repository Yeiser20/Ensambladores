/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author Vanessa Sandoval
 */
public class Ensambladores {
    public static void main(String[] args) throws IOException, Exception {

 
        
    String ruta1 = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\Lexer.flex";
    String ruta2 = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\LexerCup.flex";
    String[] rutas = new String [7]; 
    rutas[0] = "-destdir";
    rutas[1] = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto";
    rutas[2] ="-symbols";
    rutas[3] = "sym";
    rutas[4] = "-parser";
    rutas[5] = "Sintax";
    rutas[6] = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\Sintax.cup";
    File Archivo;
    Archivo = new File(ruta1);
    JFlex.Main.generate(Archivo);
    Archivo  = new File(ruta2);
    JFlex.Main.generate(Archivo);
    java_cup.Main.main(rutas);
        
       
//        String ruta1 = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\Lexer.flex";
//        String ruta2 = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\LexerCup.flex";
//        String [] rutaS = {"-parser","Sintax","C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\Sintax.cup"};
//        generarLexer(ruta1, ruta2, rutaS);
//    }
//        public static void generarLexer (String ruta1, String ruta2, String[] rutaS) throws IOException, Exception {
//            File archivo;
//            archivo = new File(ruta1);
//            JFlex.Main.generate(archivo);
//            archivo = new File(ruta2);
//            JFlex.Main.generate(archivo);
//            java_cup.Main.main(rutaS);
//            
//            Path rutaSym = Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\sym.java");
//            if (Files.exists(rutaSym)) {
//            Files.delete(rutaSym);
//        }
//            
//            Files.move(
//                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\sym.java"), 
//                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\sym.java")
//        );
//            
//            Path rutaSin = Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\Sintax.java");
//            if (Files.exists(rutaSin)) {
//            Files.delete(rutaSin);
//        }
//            
//            Files.move(
//                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\Sintax.java"), 
//                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\Ensambladores\\src\\Proyecto\\Sintax.java")
//        );
            
        }
}
