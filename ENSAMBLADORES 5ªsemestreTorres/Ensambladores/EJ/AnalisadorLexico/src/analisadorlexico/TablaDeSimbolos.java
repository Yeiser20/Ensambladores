
package analisadorlexico;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor D Archundia
 */
public class TablaDeSimbolos extends javax.swing.JFrame {

    /**
     * Creates new form TablaDeSimbolos
     */
    private final DefaultTableModel modelo;
    ArrayList<String> simbolos = new ArrayList<>();
    ArrayList<String> tipo = new ArrayList<>();
    ArrayList<String> valor = new ArrayList<>();
    ArrayList<String> tamaño = new ArrayList<>();
    String sim;
    String val;
    String tam;
    String tip;
    
    public TablaDeSimbolos() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        modelo = (DefaultTableModel) tblSimbolos.getModel();
    }
    
    private void analizaLexico() throws IOException{
        
        int contador = 0;
        
        String expr = (String) txtFile.getText();
        Tabla lexer = new Tabla(new StringReader(expr));
        String resultado = "LINEA" + contador + "\t\t\tSIMBOLO\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtResultado.setText(resultado);
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
                case Instruccion:
                    resultado += " S/n Operandos->\t\t" + lexer.lexeme + "\n";
                    break; 
                case Correcta:
                    resultado += " Correcta->\t\t\t" + lexer.lexeme + "\n";
                    break; 
                case Etiqueta:
                    resultado += " Etiqueta->\t\t\t" + lexer.lexeme + "\n";
                    break; 
                case Incorrecta:
                    resultado += " Inc no se requiere operandos->\t" + lexer.lexeme + "\n";
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
        private void analizarRenglones() throws IOException{
        
        int contador = 0;
        
        String expr = (String) txtFile.getText();
        Tabla lexer = new Tabla(new StringReader(expr));
        String resultado =  "\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtAnalizarSentencias.setText(resultado);
                return;
            }
            
            
            switch (token) {
                    case InstruccionCorr:
                    resultado += lexer.lexeme + "\n";
                    break; 
            }
        }
    }
        private void analizaResultado() throws IOException{
        
        int contador = 0;
        
        String expr = (String) txtAnalizarSentencias.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "LINEA" + contador + "\t\t\tSIMBOLO\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtSentenciastbl.setText(resultado);
                return;
            }
            switch (token) {
                case Linea:
                    contador++; 
                    resultado += "LINEA ->\t\t" + contador + "\n";
                    break;
                case Simbolo:
                    resultado += " Simbolo ->\t\t\t" + lexer.lexeme + "\n";
                    sim = lexer.lexeme;
                    tip="variable";
                    simbolos.add(sim);
                    tipo.add(tip);
                    break;  
                case Constante_Numerica_D:
                    resultado += " Constante NumericaDec ->\t\t" + lexer.lexeme + "\n";
                    val=lexer.lexeme;
                    valor.add(val);
                    break;  
                case Constante_Numerica_H:
                    resultado += " Constante NumericaHex->\t\t" + lexer.lexeme + "\n";
                    val=lexer.lexeme;
                    valor.add(val);
                    break; 
                case Constante_Caracter_CS:
                    resultado += " Constante Caracter CS->\t\t" + lexer.lexeme + "\n";
                    val=lexer.lexeme;
                    valor.add(val);
                    break;  
                case Constante_Caracter_CD:
                    resultado += " Constante Cadena->\t\t" + lexer.lexeme + "\n";
                    val=lexer.lexeme;
                    valor.add(val);
                    break; 
                case Pseudoinstruccion_D_W:
                    resultado += " Pseudoinstruccion WORD->\t\t" + lexer.lexeme + "\n";
                    tam=lexer.lexeme;
                    tamaño.add(tam);
                    break; 
                case Pseudoinstruccion_D_B:
                    resultado += " Pseudoinstruccion BYTE->\t\t" + lexer.lexeme + "\n";
                    tam=lexer.lexeme;
                    tamaño.add(tam);
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

        btnDesplegar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSimbolos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtFile = new javax.swing.JTextArea();
        btnFile = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        AnalisarInstrucciones = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAnalizarSentencias = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtSentenciastbl = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        btnDesplegar.setText("desplegarSentencias");
        btnDesplegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesplegarActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblSimbolos.setAutoCreateRowSorter(true);
        tblSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SIMBOLO", "TIPO", "TAMAÑO", "VALOR"
            }
        ));
        jScrollPane1.setViewportView(tblSimbolos);

        jLabel1.setText("TABLA DE SIMBOLOS");

        jButton1.setText("Desplejar Tabla");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpiar Tabla");

        jButton3.setText("Parte 3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Regresar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        txtFile.setColumns(20);
        txtFile.setRows(5);
        jScrollPane2.setViewportView(txtFile);

        btnFile.setText("ABRIR ARCHIVO");
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane3.setViewportView(txtResultado);

        AnalisarInstrucciones.setText("SENTENCIAS CORRECTAS");
        AnalisarInstrucciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnalisarInstruccionesActionPerformed(evt);
            }
        });

        txtAnalizarSentencias.setColumns(20);
        txtAnalizarSentencias.setRows(5);
        jScrollPane4.setViewportView(txtAnalizarSentencias);

        txtSentenciastbl.setColumns(20);
        txtSentenciastbl.setRows(5);
        jScrollPane5.setViewportView(txtSentenciastbl);

        jLabel2.setText("SEGUNDA FASE ");

        jLabel4.setText("SENTENCIAS CORRECTAS");

        jLabel5.setText("SENTENCIAS CORRECTAS ANALIZADAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 49, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(541, 541, 541))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(96, 96, 96)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(112, 112, 112)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(67, 67, 67))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addGap(169, 169, 169))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(524, 524, 524)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(534, 534, 534)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnFile))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AnalisarInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(AnalisarInstrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton3))
                        .addGap(44, 44, 44))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (modelo.getRowCount() == 0) {
            Object a[] = new Object[4];
            for (int i = 0; i < simbolos.size(); i++) {   
                a[0] = simbolos.get(i);
                a[1] = tipo.get(i);
                a[2] = tamaño.get(i);
                a[3] = valor.get(i);
                modelo.addRow(a);
            
            }
        }    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        
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
        
    }//GEN-LAST:event_btnFileActionPerformed

    private void AnalisarInstruccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnalisarInstruccionesActionPerformed
      
        try {
            analizaLexico();
            analizarRenglones();
            analizaResultado();
        } catch (IOException e) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE,null,e);
        }     
        
        
    }//GEN-LAST:event_AnalisarInstruccionesActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnDesplegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesplegarActionPerformed
        // TODO add your handling code here:
        try {
            analizaResultado();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDesplegarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      TerceraFase ter = new TerceraFase();
      ter.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnalisarInstrucciones;
    private javax.swing.JButton btnDesplegar;
    private javax.swing.JButton btnFile;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblSimbolos;
    private javax.swing.JTextArea txtAnalizarSentencias;
    private javax.swing.JTextArea txtFile;
    private javax.swing.JTextArea txtResultado;
    private javax.swing.JTextArea txtSentenciastbl;
    // End of variables declaration//GEN-END:variables
}
