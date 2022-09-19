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
   /*String[] datos = "Proyecto de ensambladores, separacion de palabras".split(" ");
        for(String item : datos)
        {
          System.out.println(item);
        }*/
        
        String ruta1 = "C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\src\\ensambladores\\Lexer.flex";
        String ruta2 = "C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\src\\ensambladores\\LexerCup.flex";
        String [] rutaS = {"-parser", "Sintax", "C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\src\\ensambladores\\Sintax.cup"};
        String ruta3 = "C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\src\\ensambladores\\LexeTabla.flex";
        generarLexer(ruta1, ruta2, rutaS, ruta3);
        Fase2 v= new Fase2();
        v.setVisible(true);
        
    }
        public static void generarLexer (String ruta1, String ruta2, String[] rutaS, String ruta3) throws IOException, Exception {
            File archivo;
            archivo = new File(ruta1);
            JFlex.Main.generate(archivo);
            archivo = new File(ruta2);
            JFlex.Main.generate(archivo);
            java_cup.Main.main(rutaS);
            
            Path rutaSym = Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\src\\ensambladores\\sym.java");
            if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
            
            archivo = new File(ruta3);
            JFlex.Main.generate(archivo);
        }
            
            Files.move(
                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\sym.java"), 
                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\src\\ensambladores\\sym.java")
        );
            
            Path rutaSin = Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\src\\ensambladores\\Sintax.java");
            if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
            
            Files.move(
                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\Sintax.java"), 
                Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\PruebaEnsambladores\\Ensambladores2\\src\\ensambladores\\Sintax.java")
        );
        }
}
