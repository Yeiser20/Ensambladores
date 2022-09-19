/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisadorlexico;

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
 * @author Victor D Archundia
 */
public class Vista extends javax.swing.JFrame {
    
    TerceraFase tf= new TerceraFase();
    String txt;
    public Vista() {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }
        
    private void analizaLexico() throws IOException{
        
        int contador = 0;
        
        String expr = (String) txtFile.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA" + contador + "\t\t\tSIMBOLO\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtAnaliazr.setText(resultado);
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
    
        public void analizarSentencias() throws IOException{
          int contador = 0;
        
        String expr = (String) txtFile.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "Validacion" + contador + "\t\t\tSentencia\n";
        while (true) {
            Tokens token = lexer.yylex();
            
            if (token == null) {
                txtAnalisisS.setText(resultado);
                return;
            }
           
            switch (token) {
//                case Linea:
//                    contador++; 
//                    resultado += "LINEA ->\t\t" + contador + "\n";
//                    break;
                case Correcta:
                    resultado += "  CORRECTA->\t\t" + lexer.lexeme + "\n";
                    break;
                case ERROR:
                    resultado += "  INCORRECTA->\t\t\t" + lexer.lexeme + "\n";
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnOf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtFile = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnaliazr = new javax.swing.JTextArea();
        btnA = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnalisisS = new javax.swing.JTextArea();
        btnAnalisisR = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Fase2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        jMenuItem1.setText("jMenuItem1");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jButton2.setText("Fase3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PRIMERA FASE ");

        btnOf.setText("Open File");
        btnOf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOfActionPerformed(evt);
            }
        });

        txtFile.setColumns(20);
        txtFile.setRows(5);
        jScrollPane1.setViewportView(txtFile);

        txtAnaliazr.setEditable(false);
        txtAnaliazr.setColumns(20);
        txtAnaliazr.setRows(5);
        jScrollPane2.setViewportView(txtAnaliazr);

        btnA.setText("Separar");
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAActionPerformed(evt);
            }
        });

        txtAnalisisS.setColumns(20);
        txtAnalisisS.setRows(5);
        jScrollPane3.setViewportView(txtAnalisisS);

        btnAnalisisR.setText("RealizarAnalisis");
        btnAnalisisR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisRActionPerformed(evt);
            }
        });

        jButton1.setText("LimpiarCampos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Fase2.setText("Fase2");
        Fase2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Fase2ActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnOf, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(303, 303, 303))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(37, 37, 37))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Fase2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton1))
                                            .addGap(125, 125, 125)))))
                            .addComponent(btnAnalisisR)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(433, 433, 433)
                        .addComponent(jLabel1)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Fase2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(btnAnalisisR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOfActionPerformed
  
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
            txtFile.setText(texto);
          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      
    }//GEN-LAST:event_btnOfActionPerformed

public String txt(){
return this.txt=(String) txtFile.getText();
}
    
    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed
        try {
            analizaLexico();
        } catch (IOException e) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE,null,e);
        }
    }//GEN-LAST:event_btnAActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       txtAnaliazr.setText(null);
       txtAnalisisS.setText(null);
       txtFile.setText(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAnalisisRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisisRActionPerformed
      
        String ST = txtFile.getText();
        Sintax s= new Sintax(new analisadorlexico.LexerCup(new StringReader((ST))));
        
        try {
            s.parse();
            txtAnalisisS.setText("Analisis realizado correctamente");
            txtAnalisisS.setForeground(new Color(25,111,61));
        } catch (Exception ex) {
           
            Symbol sym = s.getS();
            txtAnalisisS.setText("Error de sintaxis ,  Linea"+(sym.right+1)+", columna:"+(sym.left+1)+", Texto: \""+sym.value+ "\"");
            txtAnalisisS.setForeground(Color.RED);        
        }

    }//GEN-LAST:event_btnAnalisisRActionPerformed

    private void Fase2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Fase2ActionPerformed
      TablaDeSimbolos tb = new TablaDeSimbolos();
      tb.setVisible(true);
    }//GEN-LAST:event_Fase2ActionPerformed
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
            java.util.logging.Logger.getLogger(EJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EJ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton Fase2;
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnAnalisisR;
    private javax.swing.JButton btnOf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtAnaliazr;
    private javax.swing.JTextArea txtAnalisisS;
    private javax.swing.JTextArea txtFile;
    // End of variables declaration//GEN-END:variables
}
