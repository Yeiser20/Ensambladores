
package analisadorlexico;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AnalisadorLexico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       String ruta = "C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/Lexer.flex";
       String ruta2 = "C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/LexerCup.flex";
       String ruta3 = "C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/Tabla.flex";
       String ruta4 = "C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/Fase3.flex";
       String[] rutaS = {"-parser","Sintax", "C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/Sintax.cup"};
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
        
        Path rutaSym = Paths.get("C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                   Paths.get("C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/sym.java"),
                   Paths.get("C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/sym.java")
                   );
        
        Path rutaSin = Paths.get("C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/Sintax.java");
         if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
         
        Files.move(
                   Paths.get("C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/Sintax.java"),
                   Paths.get("C:/Users/danie/OneDrive/Documentos/5ª SEMESTRE FACULTAD/ENSAMBLADORES/AnalisadorLexico/src/analisadorlexico/Sintax.java")
                   );
    
    }
}
