
package Proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class PEnsambladores extends javax.swing.JFrame {
    

    public List<String> ErroresSintacticos = new ArrayList<String>();
    public ArrayList<String> Etiquetas =  new ArrayList<String>();
    public ArrayList<clasificacionLineas> Lineas =  new ArrayList<clasificacionLineas>();
   tablaSimbolos ts = new tablaSimbolos();
           
    
   
    public PEnsambladores() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        BtnBuscarArchivo = new javax.swing.JButton();
        BtnAnalizar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Mongolian Baiti", 3, 24)); // NOI18N
        jLabel1.setText("ENSAMBLADORES");

        txtArchivo.setColumns(20);
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        BtnBuscarArchivo.setText("ARCHIVO");
        BtnBuscarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarArchivoActionPerformed(evt);
            }
        });

        BtnAnalizar.setText("ANALIZAR");
        BtnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnalizarActionPerformed(evt);
            }
        });

        BtnLimpiar.setText("LIMPIAR");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Simbolo", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Direccion", "Programa", "Codificacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 383, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(355, 355, 355))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(BtnBuscarArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnAnalizar)
                .addGap(50, 50, 50)
                .addComponent(BtnLimpiar)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnBuscarArchivo)
                    .addComponent(BtnAnalizar)
                    .addComponent(BtnLimpiar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBuscarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarArchivoActionPerformed
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
    }//GEN-LAST:event_BtnBuscarArchivoActionPerformed

    private void BtnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnalizarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tAnalisis = (DefaultTableModel)this.jTable2.getModel();
        tAnalisis.setNumRows(0);
        this.Etiquetas.clear();
        String ST = txtArchivo.getText().toUpperCase();
        this.txtArchivo.setText(ST);
        Sintax si = null;
        try {
            
            ST = this.AnalizadorCodigo(ST);
            LexerCup Lex = new LexerCup(new StringReader(ST));
            si = new Sintax(Lex);
            si.etiquetasEnsambaldor(Etiquetas);
            si.parse();
            for(int i = 0 ; i < si.Lineas.size() ; i++){
                this.Lineas.add((clasificacionLineas) si.Lineas.get(i));
            }

            Collections.sort(this.Lineas, new OrdenarLineas());
            String[] lineasCodigo = this.txtArchivo.getText().split("\n");
            /*
            for(int i = 0 ; i < this.Lineas.size() ; i++){
                String[] renglonesAnalisis = new String[2];
                renglonesAnalisis[0] = lineasCodigo[this.Lineas.get(i).getNumeroLinea()-1];
                renglonesAnalisis[1] = this.Lineas.get(i).getClasifiacion();
                tAnalisis.addRow(renglonesAnalisis);
            }*/
            for(int i = 0 ; i < lineasCodigo.length ; i++){
                String[] linea = new String[2];
                linea[1] = lineasCodigo[i];
                tAnalisis.addRow(linea);
            }
            for(int i = 0 ; i < this.Lineas.size() ; i++){
                tAnalisis.setValueAt(this.Lineas.get(i).getClasifiacion(), this.Lineas.get(i).getNumeroLinea()-1 , 2);
            }
            for(int i = 0 ; i < si.dM.getDirecciones().size() ; i++){
                String numeroHexadecimal = Integer.toHexString(si.dM.getDirecciones().get(i).getDireccion());
                String ceros = "";
                for(int j = 0 ; j < (4 - numeroHexadecimal.length()) ; j++){
                    ceros += '0';
                }
                numeroHexadecimal = ceros + numeroHexadecimal;
                tAnalisis.setValueAt(numeroHexadecimal, si.dM.getDirecciones().get(i).getLinea()-1, 0);
                if( i == (si.dM.getDirecciones().size()-1) ){
                  tAnalisis.setValueAt(numeroHexadecimal,si.dM.getDirecciones().get(i).getLinea(),0)  ;
                }
            }
            si.tS.setVisible(true);
        } catch (Exception ex) {

                
                String[] lineasCodigo = this.txtArchivo.getText().split("\n");
                for(int i = 0 ; i < lineasCodigo.length ; i++){
                    String[] linea = new String[2];
                    linea[1] = lineasCodigo[i];
                    tAnalisis.addRow(linea);
                }
                for(int i = 0 ; i < this.Lineas.size() ; i++){
                    tAnalisis.setValueAt(this.Lineas.get(i).getClasifiacion(), (this.Lineas.get(i).getNumeroLinea()-1) , 2);
                }
            
            for(int i = 0 ; i < si.dM.getDirecciones().size() ; i++){
                String numeroHexadecimal = Integer.toHexString(si.dM.getDirecciones().get(i).getDireccion());
                String ceros = "";
                for(int j = 0 ; j < (4 - numeroHexadecimal.length()) ; j++){
                    ceros += '0';
                }
                numeroHexadecimal = ceros + numeroHexadecimal;
                tAnalisis.setValueAt(numeroHexadecimal, si.dM.getDirecciones().get(i).getLinea()-1, 0);
                if( i == (si.dM.getDirecciones().size()-1) ){
                  tAnalisis.setValueAt(numeroHexadecimal,si.dM.getDirecciones().get(i).getLinea(),0)  ;
                }
            }
      
            
            si.tS.setVisible(true);
        } 
    }//GEN-LAST:event_BtnAnalizarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tSimbolos =  (DefaultTableModel)jTable1.getModel();
        DefaultTableModel tAnalisis =  (DefaultTableModel)this.jTable2.getModel();
        tAnalisis.setRowCount(0);
        this.txtArchivo.setText("");
        tSimbolos.setRowCount(0);
    }//GEN-LAST:event_BtnLimpiarActionPerformed
    
    private String AnalizadorCodigo(String Codigo) throws IOException, Exception{
        this.Lineas.clear();
        DefaultTableModel tSimbolos = (DefaultTableModel)jTable1.getModel();
        tSimbolos.setRowCount(0);
        DefaultTableModel tAnalisis = (DefaultTableModel)this.jTable2.getModel();
        tAnalisis.setRowCount(0);
        eliminarInutil e = new eliminarInutil();
        Codigo = e.eliminarInecesario(Codigo);
        LexerCup lexer = new LexerCup(new StringReader(Codigo));
        int lineacSegment = 0;
        
        while(true){
            Symbol s = lexer.next_token();
            if(s.sym == 0){
                String codigo = e.eliminarInecesario(Codigo);
                return codigo;
            }
            switch(s.sym){
                case 61:
                    this.Lineas.add(new clasificacionLineas(s.left+1,s.value.toString()));
                    break;
                case 49: case 45: case 50: case 46: case 44: case 47: case 48: case 41: case 52: case 43: case 53: case 51:
                case 42: case 40:
                    String [] Pseudo = new String[4];
                    Pseudo[0] = s.value.toString();
                    Pseudo[1] = "PSEUDOINSTRUCCION";
                    Pseudo[2] = s.value.toString();
                    Pseudo[3] = "";
                    tSimbolos.addRow(Pseudo);
                    if(s.value.equals("CODE SEGMENT")){
                        lineacSegment = s.left;
                    }
                    break;
                //REGISTROS
                case 14: case 23: case 32: case 16: case 25: case 15: case 30: case 27: case 24: case 28:  case 26:
                case 20: case 31: case 22: case 21: case 17: case 33: case 29: case 19: case 18:
                    String [] Registros = new String[4];
                    Registros[0] = s.value.toString();
                    Registros[1] = "REGISTROS";
                    Registros[2] = s.value.toString();
                    Registros[3] = "PALABRA";
                    tSimbolos.addRow(Registros);

                    break;
                //CONSTANTES
                case 57: case 55: case 56: case 59: case 58:
                    String [] Constante = new String[4];
                    if(s.sym == 55 || s.sym == 56){
                        Constante[0] = s.value.toString();
                        Constante[1] = "CONSTANTE";
                        Constante[2] = s.value.toString();
                        Constante[3] = "PALABRA";
                        tSimbolos.addRow(Constante);
                    }else{
                        Constante[0] = s.value.toString();
                        Constante[1] = "CONSTANTE";
                        Constante[2] = s.value.toString();
                        Constante[3] = "BYTE";
                        tSimbolos.addRow(Constante);
                    }

                    break;
                //INSTRUCCIONES
                case 4: case 2: case 10: case 9: case 5: case 12: case 6: case 3: case 13: case 11: case 7: case 8: 
                    String [] Instrucciones = new String[4];
                    Instrucciones[0] = s.value.toString();
                    Instrucciones[1] = "INSTRUCCIONES";
                    Instrucciones[2] = s.value.toString();
                    Instrucciones[3] = "PALABRA";
                    tSimbolos.addRow(Instrucciones);

                    break;
                //SIMBOLOS O VARIABLES
                case 54:
                    String [] Simbolo = new String[4];
                    Simbolo[0] = s.value.toString();
                    Simbolo[1] = "SIMBOLO";
                    Simbolo[2] = s.value.toString();
                    Simbolo[3] = "PALABRA";
                    tSimbolos.addRow(Simbolo);

                    break;
                case 62: case 64:
                    String auxEtiqeutas = "";
                    String [] Etiquetas = new String[4];
                    Etiquetas[0] = s.value.toString();
                    Etiquetas[1] = "ETIQUETA";
                    Etiquetas[2] = s.value.toString();
                    Etiquetas[3] = "";
                    tSimbolos.addRow(Etiquetas);
                    for(int i = 0 ; i < s.value.toString().length() ; i++){
                        if(s.value.toString().charAt(i) == ':'){
                            auxEtiqeutas += s.value.toString().charAt(i);
                            break;
                        }
                        auxEtiqeutas += s.value.toString().charAt(i); 
                    }
                    //this.Etiquetas.add(s.value.toString().substring(0,s.value.toString().length()-1));
                    this.Etiquetas.add(auxEtiqeutas.substring(0,auxEtiqeutas.length()-1));
                    break;

            }
    }
}

        /**
         *
         * @throws IOException
         * @throws Exception
         */
        public  void generarLexer() throws IOException, Exception{
    String ruta1 = "C:/Users/Vanessa Sandoval/Documents/NetBeansProjects/Ensambladores/src/Proyecto/Lexer.flex";
    String ruta2 = "C:/Users/Vanessa Sandoval/Documents/NetBeansProjects/Ensambladores/src/Proyecto/LexerCup.flex";
    String[] rutas = new String [7]; 
    rutas[0] = "-destdir";
    rutas[1] = "C:/Users/Vanessa Sandoval/Documents/NetBeansProjects/Ensambladores/src/Proyecto";
    rutas[2] ="-symbols";
    rutas[3] = "sym";
    rutas[4] = "-parser";
    rutas[5] = "Sintax";
    rutas[6] = "C:/Users/Vanessa Sandoval/Documents/NetBeansProjects/Ensambladores/src/Proyecto/Sintax.cup";
    File Archivo;
    Archivo = new File(ruta1);
    JFlex.Main.generate(Archivo);
    Archivo  = new File(ruta2);
    JFlex.Main.generate(Archivo);
    java_cup.Main.main(rutas);
}
    
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
            java.util.logging.Logger.getLogger(PEnsambladores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PEnsambladores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PEnsambladores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PEnsambladores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PEnsambladores().setVisible(true);

                } catch (Exception ex) {
                    Logger.getLogger(PEnsambladores.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAnalizar;
    private javax.swing.JButton BtnBuscarArchivo;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea txtArchivo;
    // End of variables declaration//GEN-END:variables
    } 
