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
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Victor D Archundia
 */
public class TerceraFase extends javax.swing.JFrame {

    /**
     * Creates new form TerceraFase
     */
    private final DefaultTableModel modelo;
    ArrayList<String> simbolos = new ArrayList<>();
    ArrayList<String> tipo = new ArrayList<>();
    ArrayList<String> valor = new ArrayList<>();
    ArrayList<String> tamaño = new ArrayList<>();
    ArrayList<String> direccion = new ArrayList<>();
    String sim;
    String val;
    String tam;
    String tip;
    String dir;
    
    public TerceraFase() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        modelo = (DefaultTableModel) tblSimb.getModel();
    }

    private void analizaResultado() throws IOException{
        
        int contador = 0;
        
        String expr = (String) txtResultado.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
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
                    dir="0000H";
                    simbolos.add(sim);
                    tipo.add(tip);
                    direccion.add(dir);
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
                case Constante:
                    resultado += " Constante Cadena->\t\t" + lexer.lexeme + "\n";
                    tip="CONSTANTE";
                    tipo.add(tip);
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
            private void analizarRenglonesCorr() throws IOException{
        
        int contador = 0;
        
        String expr = (String) txtFile.getText();
        Fase3 lexer = new Fase3(new StringReader(expr));
        String resultado =  "\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtResultado.setText(resultado);
                return;
            }
            
            switch (token) {
                    
                    case InstruccionCorr:
                    resultado +=lexer.lexeme + "\n";
                    break;
            }
        }
    }
        private void analizarRenglonesIncor() throws IOException{
        
        int contador = 0;
        
        String expr = (String) txtFile.getText();
        Fase3 lexer = new Fase3(new StringReader(expr));
        String resultado =  "\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtIncorrectas.setText(resultado);
                return;
            }
            
            switch (token) {
                    case Incorrecta:
                    resultado +=lexer.lexeme+"\n ";
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
        txtFile = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        btnAnalizar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSimb = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtIncorrectas = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("TERCERA FASE");

        txtFile.setColumns(20);
        txtFile.setRows(5);
        jScrollPane1.setViewportView(txtFile);

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane2.setViewportView(txtResultado);

        jButton1.setText("OpenFile");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        tblSimb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SIMBOLO", "TIPO", "TAMAÑO", "VALOR", "DIRECCION"
            }
        ));
        jScrollPane3.setViewportView(tblSimb);

        jButton3.setText("Dezplegar Tabla");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("CORRECTAS");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jLabel3.setText("DIRECCION");

        txtIncorrectas.setColumns(20);
        txtIncorrectas.setRows(5);
        jScrollPane5.setViewportView(txtIncorrectas);

        jLabel4.setText("INCORRECTAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(455, 455, 455)
                                        .addComponent(jLabel1)))
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 122, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(288, 288, 288))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(142, 142, 142)
                        .addComponent(jLabel2)
                        .addGap(210, 210, 210)
                        .addComponent(jLabel4)
                        .addGap(181, 181, 181))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
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
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        try {
         analizarRenglonesCorr();   
         analizarRenglonesIncor();  
         analizaResultado();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
  
        if (modelo.getRowCount() == 0) {
            Object a[] = new Object[5];
            for (int i = 0; i < simbolos.size(); i++) {   
                a[0] = simbolos.get(i);
                a[1] = tipo.get(i);
                a[2] = tamaño.get(i);
                a[3] = valor.get(i);
                a[4] = direccion.get(i);
                modelo.addRow(a);
            
            }
        }    
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tblSimb;
    private javax.swing.JTextArea txtFile;
    private javax.swing.JTextArea txtIncorrectas;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
