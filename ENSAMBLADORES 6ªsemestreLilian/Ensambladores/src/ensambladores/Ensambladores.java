/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensambladores;

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
        String ruta1 = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyectoVvannw\\Ensambladores\\src\\ensambladores\\Lexer.flex";
        String ruta2 = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyectoVvannw\\Ensambladores\\src\\ensambladores\\LexerCup.flex";
        String [] rutaS = {"-parser", "Sintax", "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyectoVvannw\\Ensambladores\\src\\ensambladores\\Sintax.cup"};
        generarLexer(ruta1, ruta2, rutaS);    
        
        
    }
        public static void generarLexer (String ruta1, String ruta2, String[] rutaS) throws IOException, Exception  {
            File archivo;
            archivo = new File(ruta1);
            JFlex.Main.generate(archivo);
            archivo = new File(ruta2);
            JFlex.Main.generate(archivo);
            java_cup.Main.main(rutaS);
            
            Path rutaSym = Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyectoVvannw\\Ensambladores\\src\\ensambladores\\sym.java");
            if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
            
            Files.move(
                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyectoVvannw\\Ensambladores\\src\\ensambladores\\sym.java"), 
                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyectoVvannw\\Ensambladores\\src\\ensambladores\\sym.java")
        );
            
            Path rutaSin = Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyectoVvannw\\Ensambladores\\src\\ensambladores\\Sintax.java");
            if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
            
            Files.move(
                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyectoVvannw\\Ensambladores\\src\\ensambladores\\Sintax.java"), 
                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyectoVvannw\\Ensambladores\\src\\ensambladores\\Sintax.java")
        );
            
        }
}
