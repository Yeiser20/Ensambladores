/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyanalisisensamblador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GenerarLexers {
  
    public static void main(String[] args) throws Exception {
       String ruta = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\src\\proyanalisisensamblador\\Lexer.flex";
       String ruta2 = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\src\\proyanalisisensamblador\\LexerCup.flex";
       String ruta3 = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\src\\proyanalisisensamblador\\Tabla.flex";
       String ruta4 = "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\src\\proyanalisisensamblador\\Fase3.flex";
       String[] rutaS = {"-parser","Sintax", "C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\src\\proyanalisisensamblador\\Sintax.flex"};
        generar(ruta, ruta2,ruta3,ruta4, rutaS);

    }
    public static void generar(String ruta, String ruta2,String ruta3,String ruta4, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(ruta);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta2);
        JFlex.Main.generate(archivo);
        archivo = new File(ruta3);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        archivo = new File(ruta4);
        JFlex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        
        Path rutaSym = Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\src\\proyanalisisensamblador\\sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                   Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\sym.java"),
                   Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\src\\proyanalisisensamblador\\sym.java")
                   );
        
        Path rutaSin = Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\src\\proyanalisisensamblador\\Sintax.java");
         if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
         
        Files.move(
                   Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\Sintax.java"),
                   Paths.get("C:\\Users\\danie\\OneDrive\\Documentos\\5ª SEMESTRE FACULTAD\\ENSAMBLADORES\\ProyAnalisisEnsamblador\\src\\proyanalisisensamblador\\Sintax.java")
                   );
    
    }}
