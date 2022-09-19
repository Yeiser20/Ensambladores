/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisensamblador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.Lexer;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author danie
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vista
     */
    public Vista() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
        private void analizaLexico() throws IOException{
        int cont = 1;
        
        String expr = (String) txtArchivo.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA " + cont + "\t\t\tSIMBOLO\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtSeparador.setText(resultado);//.setText(resultado);
                return;
            }
            switch (token) {
                case Linea:
                    cont++;
                    resultado += "LINEA ->\t\t" + cont + "\n";
                    break;
                case Pseudoinstruccion:
                    resultado += "  Pseudoinstruccion ->\t\t\t\t\t" + lexer.lexeme + "\n";
                    break;
//                case Simbolo:
//                    resultado += "  Simbolo ->\t\t\t" + lexer.lexeme + "\n";
//                    break;
//                case Instruccion:
//                    resultado += "  Instruccion ->\t\t" + lexer.lexeme + "\n";
//                    break;
//                case Registro:
//                    resultado += "  Registro ->\t\t\t" + lexer.lexeme + "\n";
//                    break;
                case Pseudoinstruccion_D_B:
                    resultado += "  Pseudoinstruccion Byte ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_D_W:
                    resultado += "  Pseudoinstruccion Word ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_0:
                    resultado += "  Pseudoinstruccion Dup ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_D:
                    resultado += "  Pseudoinstruccion Dup D  ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_Caracter:
                    resultado += "  Pseudoinstruccion Dup Caracter ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_H:
                    resultado += "  Pseudoinstruccion Dup Hexadecimal ->\t" + lexer.lexeme + "\n";
                    break;
                case Constante_Numerica_D:
                    resultado += "  Constante Numerica Decimal ->\t" + lexer.lexeme + "\n";
                    break;
                case Constante_Numerica_H:
                    resultado += "  Constante Numerica Hexadecimal ->\t" + lexer.lexeme + "\n";
                    break;
                case Constante_Caracter_CS:
                    resultado += "  Constante Caracter ->\t\t" + lexer.lexeme + "\n";
                    break; 
                case Constante_Caracter_CD:
                    resultado += "  Constante Caracter ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_S_S:
                    resultado += "  Pseudoinstruccion ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_D_S:
                    resultado += "  Pseudoinstruccion ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_C_S:
                    resultado += "  Pseudoinstruccion ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_B:
                    resultado += "  Pseudoinstruccion Byte ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_W:
                    resultado += "  Pseudoinstruccion Word ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Variable:
                    resultado += " Simbolo ->\t\t\t" + lexer.lexeme + "\n";
                    break;         
                case Pseudoinstruccion_E:
                    resultado += "  Pseudoinstruccion_E ->\t\t" + lexer.lexeme + "\n";
                    break;     
                case ERROR:
                    resultado += "  Elemento no identificado >\n";
                    break; 
                default:
                    resultado += " " + lexer.lexeme + " \n";
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSeparador = new javax.swing.JTextArea();
        btnBuscarArchivo = new javax.swing.JButton();
        btnSeparacion = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLexico = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        btnLimpiarTodo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Analisis de un archivo asm");

        txtArchivo.setColumns(20);
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        txtSeparador.setColumns(20);
        txtSeparador.setRows(5);
        jScrollPane2.setViewportView(txtSeparador);

        btnBuscarArchivo.setText("Abrir archivo");
        btnBuscarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarArchivoActionPerformed(evt);
            }
        });

        btnSeparacion.setText("Separacion");
        btnSeparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeparacionActionPerformed(evt);
            }
        });

        txtLexico.setColumns(20);
        txtLexico.setRows(5);
        jScrollPane3.setViewportView(txtLexico);

        jButton3.setText("Analisis");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jButton4.setText("Desplegar la tabla de valores");

        btnLimpiarTodo.setText("LIMPIAR TODO");
        btnLimpiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(btnBuscarArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSeparacion)
                .addGap(237, 237, 237))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(199, 199, 199))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(474, 474, 474)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(527, 527, 527)
                        .addComponent(btnLimpiarTodo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarArchivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addComponent(btnLimpiarTodo))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnBuscarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarArchivoActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //Establecemos el tipo de extenciÃ³n de archivos que abrira
        FileNameExtensionFilter asmFilter = new FileNameExtensionFilter("Ensamblador","asm"); 
        fc.setFileFilter(asmFilter);
        fc.showOpenDialog(null);
        File archivo = fc.getSelectedFile();
         try {
            FileReader fr2 = new FileReader(archivo);
            //lee la variable archivo
            BufferedReader br2 = new BufferedReader(fr2);
            //Contenedor para todo el texto del archivo
             String texto = "";
             String linea = "";
            while ((linea = br2.readLine()) != null) {
                texto += linea + "\n";
            }
            System.out.println(texto);
            txtArchivo.setText(texto);
            //tAreaCodigo.setText(texto);
            
     
            //AreaMatriz.setText(texto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }//GEN-LAST:event_btnBuscarArchivoActionPerformed

    private void btnSeparacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeparacionActionPerformed
                try {
            // TODO add your handling code here:
            analizaLexico();
        } catch (IOException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSeparacionActionPerformed

    private void btnLimpiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTodoActionPerformed
      txtLexico.setText(null);
      txtArchivo.setText(null);
      txtSeparador.setText(null);
    }//GEN-LAST:event_btnLimpiarTodoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarArchivo;
    private javax.swing.JButton btnLimpiarTodo;
    private javax.swing.JButton btnSeparacion;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtArchivo;
    private javax.swing.JTextArea txtLexico;
    private javax.swing.JTextArea txtSeparador;
    // End of variables declaration//GEN-END:variables
}
