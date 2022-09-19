
package ensambladores2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.TableColumn;


public class Lista extends javax.swing.JFrame {
    String nArchivo;
    String rArchivo;
    String codigo = null;
    private File arcOpen;
    private JFileChooser file;
    private String txtseparacion="";
    private String txtidentificar="";
    private String txtanalisis="";
    private String textoDir="";
    private Map<Integer,String> mapSep;//fa1
    private Map<Integer,palabra> mapIde;//fa2
    private Map<Integer,linea>mapAna;//fa3
    private Map<Integer, lineaDir> faDir;//direcciones
    private Map<Integer,tablaSim> tablaSimbolos;

    public Lista() {
        initComponents();
        txt_nomArchivo.setEnabled(false);
        txt_archivolectura.setEnabled(false);
    }

    public Lista(String nomArchivo, String rutaArchivo) throws IOException {
        initComponents();
        nArchivo = nomArchivo;
        rArchivo = rutaArchivo;
        DesplegarCodigo(nomArchivo, rutaArchivo);
    }

    /*Desplegamos archivo original*/
    public void DesplegarCodigo(String nomArchivo, String rutaArchivo) throws IOException {
        File archivo;
        FileReader fr;
        if (rutaArchivo.contains("asm")) {
            try {
                System.out.println(rutaArchivo);
                archivo = new File(rutaArchivo);
                fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String auxiliar;
                while ((auxiliar = br.readLine()) != null) {
                    if (codigo == null) {
                        codigo = auxiliar;
                    } else {
                        codigo = codigo + "\n" + auxiliar;
                    }
                }
                txt_nomArchivo.setText(nomArchivo);
                txt_archivolectura.setText(codigo);
            } catch (FileNotFoundException ex) {
                txt_archivolectura.setText("Error al abrir archivo. Regrese e intente nuevamente");
            }
        }
    }
    
  //generado automaticamente para interfaz
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_archivolectura = new javax.swing.JTextArea();
        txt_nomArchivo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_Salir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        separar = new javax.swing.JButton();
        identificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_archivolectura2 = new javax.swing.JTextArea();
        analisis = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtlecturaA = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setMinimumSize(new java.awt.Dimension(1000, 450));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 450));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 450));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 450));

        txt_archivolectura.setEditable(false);
        txt_archivolectura.setColumns(20);
        txt_archivolectura.setForeground(new java.awt.Color(0, 0, 0));
        txt_archivolectura.setRows(5);
        jScrollPane1.setViewportView(txt_archivolectura);

        txt_nomArchivo.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Nombre del archivo:");

        btn_Salir.setBackground(new java.awt.Color(255, 0, 0));
        btn_Salir.setFont(new java.awt.Font("Arial Black", 3, 13)); // NOI18N
        btn_Salir.setForeground(new java.awt.Color(0, 0, 0));
        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Código Original");

        separar.setBackground(new java.awt.Color(153, 255, 153));
        separar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        separar.setForeground(new java.awt.Color(0, 0, 0));
        separar.setText("Separar");
        separar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                separarActionPerformed(evt);
            }
        });

        identificar.setBackground(new java.awt.Color(255, 255, 204));
        identificar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        identificar.setForeground(new java.awt.Color(0, 0, 0));
        identificar.setText("Identificar");
        identificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificarActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Análisis");

        txt_archivolectura2.setEditable(false);
        txt_archivolectura2.setColumns(20);
        txt_archivolectura2.setForeground(new java.awt.Color(0, 0, 0));
        txt_archivolectura2.setRows(5);
        jScrollPane2.setViewportView(txt_archivolectura2);

        analisis.setBackground(new java.awt.Color(255, 153, 153));
        analisis.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        analisis.setForeground(new java.awt.Color(0, 0, 0));
        analisis.setText("Análisis");
        analisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analisisActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Símbolo", "Tipo", "Valor", "Tamaño", "Direccion"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Elementos separados/identificados");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tabla de símbolos");

        txtlecturaA.setEditable(false);
        jScrollPane6.setViewportView(txtlecturaA);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(separar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190)
                        .addComponent(identificar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(238, 238, 238)
                        .addComponent(analisis, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_nomArchivo)
                                .addGap(49, 49, 49)
                                .addComponent(btn_Salir)
                                .addGap(38, 38, 38))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel5)
                                .addGap(163, 163, 163)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(90, 90, 90))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nomArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(separar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(identificar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(310, 310, 310))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(analisis, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void separarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_separarActionPerformed
        txtseparacion="";
        System.out.println("Nombre de archivo:"+nArchivo);
        try {
            separacion f1 = new separacion(this);
            mapSep=new HashMap<Integer,String>();
            mapSep=f1.faseSeparacion(rArchivo);/*Datos separados*/
            txtseparacion=f1.imprimeElementos((HashMap)mapSep);
            txt_archivolectura2.setText(txtseparacion);
        } catch (IOException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_separarActionPerformed

    private void identificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificarActionPerformed
        txtidentificar="";
        try {
            separacion f1 = new separacion(this);
            mapIde=new HashMap<Integer,palabra>();
            mapIde= f1.F2((HashMap) mapSep);
            txtidentificar= f1.identificacionF2((HashMap)mapIde);
            txt_archivolectura2.setText(txtidentificar);
        } catch (IOException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_identificarActionPerformed

    private void analisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analisisActionPerformed
        txtanalisis="";
        String txtprueba = "Hola";

            FaseAnalisis fa = new FaseAnalisis(this);
            mapAna=new HashMap<Integer,linea>();
            mapAna= fa.Identificacion((HashMap) mapIde);/*Identificando y análizando STACK Y DATA SEGMENT*/
            System.out.println("prueba aqui4");
            txtanalisis= fa.imprimeTexto((HashMap)mapAna);
            txtlecturaA.setText(txtanalisis);
            tablaSimbolos=fa.getTablaSimbolos();
            //llenaTablaSimbolos((HashMap)tablaSimbolos);
            llenaTablaDir((HashMap) tablaSimbolos);
            faDir = new HashMap<Integer,lineaDir>();
            faDir = fa.Dir();
            textoDir = fa.imprimeTextoDir((HashMap) faDir);
            txtlecturaA.setText(textoDir);
    }//GEN-LAST:event_analisisActionPerformed

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
            java.util.logging.Logger.getLogger(Lista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lista().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analisis;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton identificar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton separar;
    private javax.swing.JTextArea txt_archivolectura;
    private javax.swing.JTextArea txt_archivolectura2;
    private javax.swing.JTextField txt_nomArchivo;
    private javax.swing.JTextPane txtlecturaA;
    // End of variables declaration//GEN-END:variables

    private void llenaTablaSimbolos(HashMap mapTS) {
        for(int i=1;i<=mapTS.size();i++){
            tablaSim ts = (tablaSim) mapTS.get(i);
            jTable2.setValueAt(ts.getSimbolo(), i-1, 0);
            jTable2.setValueAt(ts.getTipo(), i-1, 1);
            jTable2.setValueAt(ts.getTamanio(), i-1, 2);
            jTable2.setValueAt(ts.getValor(), i-1, 3);
        }
    }
    
    private void llenaTablaDir(HashMap t){
       // jTable1.addColumn(aColumn);
        for(int i=1;i<=t.size();i++){
            tablaSim ts = (tablaSim) t.get(i);
            jTable2.setValueAt(ts.getSimbolo(), i-1, 0);
            jTable2.setValueAt(ts.getTipo(), i-1, 1);
            jTable2.setValueAt(ts.getTamanio(), i-1, 2);
            jTable2.setValueAt(ts.getValor(), i-1, 3);
            jTable2.setValueAt(ts.getDireccion(), i-1, 4);
        }
    }

    
}
