/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyanalisisensamblador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Victor D Archundia
 */
public class Vista extends javax.swing.JFrame {

    /**
     * Creates new form Vista
     */
    public Vista() {
        initComponents();
         this.setLocationRelativeTo(null);
    }
   //analisar las sentencias
    
     private void analizaLexico() throws IOException{
        
        int contador = 0;
        
        String expr = (String) txtArchivo.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA" + contador + "\t\t\tSIMBOLO\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtFase1.setText(resultado);
                return;
            }
            
            switch (token) {
                case Linea:
                    contador++; 
                    resultado += "LINEA ->\t\t" + contador + "\n";
                    break;
                case Pseudoinstruccion:
                    resultado += "  Pseudoinstruccion ->\t\t" + lexer.lexeme + "\n";
                    break;
                case Registro:
                    resultado += "  Registro ->\t\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_0:
                    resultado += "  Pseudoinstruccion_DUP0->\t\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_Caracter:
                    resultado += "  Pseudoinstruccion_DUP_Ca->\t" + lexer.lexeme + "\n";
                    break;
                case Pseudoinstruccion_Dup_H:
                    resultado += "  Pseudoinstruccion_DUP_hex->\t\t" + lexer.lexeme + "\n";
                    break;    
                case PseudoinstruccionInc:
                    resultado += "  Pseudoinstruccion incorrecta->\t" + lexer.lexeme + "\n";
                    break;
                case Simbolo:
                    resultado += " Simbolo ->\t\t\t" + lexer.lexeme + "\n";
                    break;  
                case Constante_Numerica_D:
                    resultado += " Constante NumericaDec ->\t\t" + lexer.lexeme + "\n";
                    break;  
                case Constante_Numerica_H:
                    resultado += " Constante NumericaHex->\t\t" + lexer.lexeme + "\n";
                    break; 
                case Constante_Caracter_CS:
                    resultado += " Constante Caracter CS->\t\t" + lexer.lexeme + "\n";
                    break;  
                case Constante_Caracter_CD:
                    resultado += " Constante Cadena->\t\t" + lexer.lexeme + "\n";
                    break; 
                case Pseudoinstruccion_D_W:
                    resultado += " Pseudoinstruccion WORD->\t\t" + lexer.lexeme + "\n";
                    break; 
                case Pseudoinstruccion_D_B:
                    resultado += " Pseudoinstruccion BYTE->\t\t" + lexer.lexeme + "\n";
                    break; 
                case ConstanteCadInc:
                    resultado += " Constante Cadena incorrecta->\t\t" + lexer.lexeme + "\n";
                    break; 
                case Interrupcion:
                    resultado += " Interrupcion->\t\t\t" + lexer.lexeme + "\n";
                    break; 
                case Instruccion:
                    resultado += " Instruccion->\t\t\t" + lexer.lexeme + "\n";
                    break; 
                case Sregistro:
                    resultado += " Sregistro->\t\t\t" + lexer.lexeme + "\n";
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnOpenFile = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtFase1 = new javax.swing.JTextArea();
        btnFase1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtFase2 = new javax.swing.JTextArea();
        btnFase2 = new javax.swing.JButton();
        btnFase3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtFase3 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnLimpiarf1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 24)); // NOI18N
        jLabel1.setText("PROYECTO ANALISIS ENAMBLADOR");

        txtArchivo.setColumns(20);
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        jLabel2.setText("SELECCIONAR");

        btnOpenFile.setText("BUSCAR");
        btnOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFileActionPerformed(evt);
            }
        });

        txtFase1.setColumns(20);
        txtFase1.setRows(5);
        jScrollPane2.setViewportView(txtFase1);

        btnFase1.setText("GENERAR FASE 1");
        btnFase1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFase1ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jLabel3.setText("ANALISIS");

        txtFase2.setColumns(20);
        txtFase2.setRows(5);
        jScrollPane4.setViewportView(txtFase2);

        btnFase2.setText("GENERAR FASE 2");
        btnFase2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFase2ActionPerformed(evt);
            }
        });

        btnFase3.setText("GENERAR FASE 3");

        txtFase3.setColumns(20);
        txtFase3.setRows(5);
        jScrollPane5.setViewportView(txtFase3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SIMBOLO", "VALOR", "TAMAÑO", "DIRECCION"
            }
        ));
        jScrollPane6.setViewportView(jTable1);

        btnLimpiarf1.setText("LIMPIAR FASE1");

        jButton1.setText("LIMPIAR FASE 2");

        jButton2.setText("LIMPIAR FASE 3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(405, 405, 405))
            .addGroup(layout.createSequentialGroup()
                .addGap(386, 386, 386)
                .addComponent(btnLimpiarf1)
                .addGap(268, 268, 268)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(31, 31, 31)
                                        .addComponent(btnOpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(151, 151, 151)
                                        .addComponent(btnFase1))))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(btnFase2)
                                .addGap(222, 222, 222)
                                .addComponent(btnFase3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jLabel3)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOpenFile)
                    .addComponent(jLabel2)
                    .addComponent(btnFase1)
                    .addComponent(btnFase2)
                    .addComponent(btnFase3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiarf1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFileActionPerformed
        // Abre el archivo        
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
            txtArchivo.setText(texto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnOpenFileActionPerformed

    private void btnFase2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFase2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFase2ActionPerformed

    private void btnFase1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFase1ActionPerformed
        try {
            analizaLexico();
        } catch (IOException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFase1ActionPerformed

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
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFase1;
    private javax.swing.JButton btnFase2;
    private javax.swing.JButton btnFase3;
    private javax.swing.JButton btnLimpiarf1;
    private javax.swing.JButton btnOpenFile;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea txtArchivo;
    private javax.swing.JTextArea txtFase1;
    private javax.swing.JTextArea txtFase2;
    private javax.swing.JTextArea txtFase3;
    // End of variables declaration//GEN-END:variables
}
