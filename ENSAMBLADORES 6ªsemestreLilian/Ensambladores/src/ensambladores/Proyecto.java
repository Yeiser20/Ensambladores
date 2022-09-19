/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ensambladores;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Vanessa Sandoval
 */
public class Proyecto extends javax.swing.JFrame {

    /**
     * Creates new form Proyecto
     */
    public Proyecto() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void analizaLexico() throws IOException{
        int cont = 1;
        
        String expr = (String) txtArchivo.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA " + cont + "\t\tSIMBOLO\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtLexico.setText(resultado);//.setText(resultado);
                return;
            }
            switch (token) {
                case Linea:
                    cont++;
                    resultado += "LINEA " + cont + "\n";
                    break;
                case Pseudoinstruccion:
                    resultado += "  Pseudoinstruccion ->\t" + lexer.lexeme + "\n";
                    break;
                case Simbolo:
                    resultado += "  Simbolo ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion:
                    resultado += "  Instruccion ->\t" + lexer.lexeme + "\n";
                    break;
                case Registro:
                    resultado += "  Registro ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_D_B:
                    resultado += "  Pseudoinstruccion Byte ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_D_W:
                    resultado += "  Pseudoinstruccion Word ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_0:
                    resultado += "  Pseudoinstruccion Dup ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_D:
                    resultado += "  Pseudoinstruccion Dup D  ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_Caracter:
                    resultado += "  Pseudoinstruccion Dup Caracter ->\t" + lexer.lexeme + "\n";
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
                    resultado += "  Constante Caracter ->\t" + lexer.lexeme + "\n";
                    break; 
                case Constante_Caracter_CD:
                    resultado += "  Constante Caracter ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_S_S:
                    resultado += "  Pseudoinstruccion ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_D_S:
                    resultado += "  Pseudoinstruccion ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_C_S:
                    resultado += "  Pseudoinstruccion ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_B:
                    resultado += "  Pseudoinstruccion Byte ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_W:
                    resultado += "  Pseudoinstruccion Word ->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_E:
                    resultado += "  Pseudoinstruccion_E ->\t" + lexer.lexeme + "\n";
                    break;
                /*case Corchete_a:
                    resultado += "  <Corchete de apertura>\t" + lexer.lexeme + "\n";
                    break;
                case Corchete_c:
                    resultado += "  <Corchete de cierre>\t" + lexer.lexeme + "\n";
                    break;
                case Main:
                    resultado += "  <Reservada main>\t" + lexer.lexeme + "\n";
                    break;
                case P_coma:
                    resultado += "  <Punto y coma>\t" + lexer.lexeme + "\n";
                    break;
                case Identificador:
                    resultado += "  <Identificador>\t\t" + lexer.lexeme + "\n";
                    break;
                case Numero:
                    resultado += "  <Numero>\t\t" + lexer.lexeme + "\n";
                    break;*/
                case ERROR:
                    resultado += "  Elemento no identificado >\t\n";
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

        BtnArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLexico = new javax.swing.JTextArea();
        btnDesplegar = new javax.swing.JButton();
        BtnSiguiente = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSintatico = new javax.swing.JTextArea();
        BtnIdentificador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnArchivo.setBackground(new java.awt.Color(0, 153, 153));
        BtnArchivo.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        BtnArchivo.setText("Archivo");
        BtnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnArchivoActionPerformed(evt);
            }
        });

        txtArchivo.setColumns(20);
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Proyecto Ensambladores");

        txtLexico.setEditable(false);
        txtLexico.setColumns(20);
        txtLexico.setRows(5);
        jScrollPane2.setViewportView(txtLexico);

        btnDesplegar.setBackground(new java.awt.Color(0, 153, 153));
        btnDesplegar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        btnDesplegar.setText("Desplegar Lista");
        btnDesplegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesplegarActionPerformed(evt);
            }
        });

        BtnSiguiente.setBackground(new java.awt.Color(204, 204, 204));
        BtnSiguiente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnSiguiente.setText("Siguiente");
        BtnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSiguienteActionPerformed(evt);
            }
        });

        BtnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        BtnLimpiar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        txtSintatico.setColumns(20);
        txtSintatico.setRows(5);
        jScrollPane3.setViewportView(txtSintatico);

        BtnIdentificador.setBackground(new java.awt.Color(0, 153, 153));
        BtnIdentificador.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        BtnIdentificador.setText("Identificar Lineas");
        BtnIdentificador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIdentificadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDesplegar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSiguiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnIdentificador, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesplegar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnArchivoActionPerformed
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
    }//GEN-LAST:event_BtnArchivoActionPerformed

    private void btnDesplegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesplegarActionPerformed
        try {
            // TODO add your handling code here:
            analizaLexico();
        } catch (IOException ex) {
            Logger.getLogger(Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDesplegarActionPerformed

    private void BtnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSiguienteActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_BtnSiguienteActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        // TODO add your handling code here:
        txtLexico.setText(null);
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnIdentificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIdentificadorActionPerformed
        // TODO add your handling code here:
        String ST = txtLexico.getText();
        Sintax s = new Sintax(new ensambladores.LexerCup(new StringReader(ST)));
        
        try {
            s.parse();
            txtSintatico.setText("Correcta");
            txtSintatico.setForeground(Color.CYAN);
        } catch (Exception ex) {
            Symbol sym = s.getS();
            txtSintatico.setText("Incorrecto " /*+ (sym.right + 1) + " columna: " + (sym.left + 1) + ", Texto: \"" + sym.value "\""*/);
            txtSintatico.setForeground(Color.RED);
        }
    }//GEN-LAST:event_BtnIdentificadorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proyecto().setVisible(true);
            }
        });
        
        /*String[] datos = "Proyecto de ensambladores, separacion de palabras".split(" ");
        for(String item : datos)
        {
          System.out.println(item);
        }*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnArchivo;
    private javax.swing.JButton BtnIdentificador;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnSiguiente;
    private javax.swing.JButton btnDesplegar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtArchivo;
    private javax.swing.JTextArea txtLexico;
    private javax.swing.JTextArea txtSintatico;
    // End of variables declaration//GEN-END:variables
}
