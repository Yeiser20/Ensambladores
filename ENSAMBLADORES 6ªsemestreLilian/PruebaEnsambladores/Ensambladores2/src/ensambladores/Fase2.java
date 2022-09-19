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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vanessa Sandoval
 */
public class Fase2 extends javax.swing.JFrame {
    
    public static LinkedList ListaTabla = new LinkedList();
    
    DefaultTableModel model;
    /**
     * Creates new form Fase2
     */
    public Fase2() {
        initComponents();
        this.setLocationRelativeTo(null);
        tabla();
        Fase2.llenadoLista();
    }
    
    public void tabla() {
        model=new DefaultTableModel ();
        model.addColumn("Simbolo");
        model.addColumn("Tipo");
        
        jTable1.setModel(model);
    }
    
    private void analisisSimbolos() throws IOException{
        String expr = (String) txtArchivo.getText();
        Lexer lexer = new Lexer (new StringReader(expr));
        String resultado = "";
        while (true){
            Tokens token = lexer.yylex();
            if (token==null){
                return;
            }
            
            switch (token){
                case Constante:
                    resultado += lexer.lexeme;
                    ListaTabla.add("Constante "+lexer.lexeme);
                    break;
                case Variable:
                    resultado += lexer.lexeme;
                    ListaTabla.add("Variable "+lexer.lexeme);
                    break;  
                case Etiqueta:
                    resultado += lexer.lexeme;
                    ListaTabla.add("Etiqueta "+lexer.lexeme);
                    break;
                default:
                    resultado += "";
                    break;
            }
        }
    }
    
    public static void  llenadoLista(){
        ListaTabla.add("Variable Var1");
        ListaTabla.add("Variable Var2");
        ListaTabla.add("Constante Const1");
        ListaTabla.add("Constante Const2");
        ListaTabla.add("Etiqueta Equ1");
        ListaTabla.add("Constante Const3");
        ListaTabla.add("Etiqueta Equ2");
        //analisisSimbolos();
    }
    
    private void Analiza() throws IOException{
        int cont = 1;
        
        String expr = (String) txtArchivo.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "Archivo " + cont + "\t\t_\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtAnalisis.setText(resultado);//.setText(resultado);
                return;
            }
            switch (token) {
                case Instruccion_0:
                    resultado += " < Correcta \t>" + lexer.lexeme + "\n";
                    break;
                case IIO:
                    resultado += " < Incorrecta, no se requiere operandos >\t\t" + lexer.lexeme + "\n";
                    break;
                case IIE:
                    resultado += " < Correcta >\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_E:
                    resultado += " < Incorrecta, se requiere de una etiqueta >\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_1:
                    resultado += " < Incorrecta, se requiere un operando >\t\t" + lexer.lexeme + "\n";
                    break;
                case II1:
                    resultado += " < Correcta >\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_II2:
                    resultado += " < Incorrecta, se requiere un operando >\t\t" + lexer.lexeme + "\n";
                    break;
                case II2:
                    resultado += " < Correcta >\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_II3:
                    resultado += " < Incorrecta, se requiere un operando >\t\t" + lexer.lexeme + "\n";
                    break;
                case II3:
                    resultado += " < Correcta >\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_II4:
                    resultado += " < Incorrecta, se requiere un operando >\t\t" + lexer.lexeme + "\n";
                    break;
                case II4:
                    resultado += " < Correcta >\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_I1:
                    resultado += " < Incorrecta, se requieren dos operandos >\t\t" + lexer.lexeme + "\n";
                    break;
                case I1:
                    resultado += " < Correcta >\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_I2:
                    resultado += " < Incorrecta, se requieren dos operandos >\t\t" + lexer.lexeme + "\n";
                    break;
                case I2:
                    resultado += " < Correcta >\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_I3:
                    resultado += " < Incorrecta, se requieren dos operandos >\t\t" + lexer.lexeme + "\n";
                    break;
                case I3:
                    resultado += " < Correcta >\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_I4:
                    resultado += " < Incorrecta, se requieren dos operandos >\t\t" + lexer.lexeme + "\n";
                    break;
                case I4:
                    resultado += " < Correcta >\t\t" + lexer.lexeme + "\n";
                    break;
                case Instruccion_I5:
                    resultado += " < Incorrecta, se requieren dos operandos >\t\t" + lexer.lexeme + "\n";
                    break;
                case I5:
                    resultado += " < Correcta >\t\t" + lexer.lexeme + "\n";
                    break;
                case ERROR:
                    resultado +=  "_"; //+ lexer.lexeme + " \n";
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
        txtAnalisis = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnArchivo = new javax.swing.JButton();
        btnAnalizar = new javax.swing.JButton();
        btnTabla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel1.setText("Análisis sintáctico y semántico del segmento de código");

        txtArchivo.setColumns(20);
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        txtAnalisis.setColumns(20);
        txtAnalisis.setRows(5);
        jScrollPane2.setViewportView(txtAnalisis);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tipo", "Simbolo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);

        btnArchivo.setText("Archivo");
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoActionPerformed(evt);
            }
        });

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        btnTabla.setText("Desplegar Tabla");
        btnTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))))
            .addGroup(layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(520, 520, 520))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablaActionPerformed
        // TODO add your handling code here:
        String insertar[] = new String[3];
        int subCadena = 0;
        for (int i = 0; i < Fase2.ListaTabla.size(); i++) {
        String fila = (String) Fase2.ListaTabla.get(i);
        String[] arrSplit_2 = fila.split(" ");
        for (int j = 0; j < 1; j++) {
        insertar[j] = arrSplit_2[j];
        subCadena += insertar[j].length();
        }
        insertar[1] = fila.substring(subCadena + 2, fila.length());
        model.addRow(insertar);
        subCadena = 0;
        }
    }//GEN-LAST:event_btnTablaActionPerformed

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_btnArchivoActionPerformed

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        /*String ST = txtLexico.getText();
        Sintax s = new Sintax(new ensambladores.LexerCup(new StringReader(ST)));
        
        try {
            s.parse();
            txtAnalisis.setText("Correcta");
            txtAnalisis.setForeground(Color.CYAN);
        } catch (Exception ex) {
            Symbol sym = s.getS(); */
            //txtAnalisis.setText("Incorrecto, no requiere operandos"/*+ (sym.right + 1) + " columna: " + (sym.left + 1) + ", Texto: \"" + sym.value "\""*/);
            //txtAnalisis.setText("Incorrecto, requiere de una etiqueta");
            //txtAnalisis.setForeground(Color.RED);
        //}
        try {
            // TODO add your handling code here:
            Analiza();
            analisisSimbolos();
        } catch (IOException ex) {
            Logger.getLogger(Fase2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalizarActionPerformed

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
            java.util.logging.Logger.getLogger(Fase2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fase2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fase2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fase2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fase2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnTabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtAnalisis;
    private javax.swing.JTextArea txtArchivo;
    // End of variables declaration//GEN-END:variables
}
