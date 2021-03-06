/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeanca
 */
public class PruebaMongo extends javax.swing.JFrame {

    private DB db;
    private DBCollection dbCollection;
    
    /**
     * Creates new form PruebaMongo
     */
    public PruebaMongo() {
        initComponents();
        mongoConexion("pruebaMongo", "juegos");
    }
    
    /**
     * Conecta a NetBeans con Mongo.
     */
    private void mongoConexion(String nombreBD, String nombreTabla) {
        Mongo mongo;
        try {
            mongo = new Mongo("localhost", 27017);  
            this.db = mongo.getDB(nombreBD);
            this.dbCollection = db.getCollection(nombreTabla);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ex.toString());
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
        juego = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tipo = new javax.swing.JTextField();
        insertar = new javax.swing.JButton();
        leer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tuplas = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Juego:");

        jLabel2.setText("Tipo:");

        insertar.setText("Insertar");
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });

        leer.setText("Leer");
        leer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leerActionPerformed(evt);
            }
        });

        tuplas.setColumns(20);
        tuplas.setRows(5);
        jScrollPane1.setViewportView(tuplas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(juego, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tipo))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(insertar)
                                .addGap(18, 18, 18)
                                .addComponent(leer)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(juego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertar)
                    .addComponent(leer))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActionPerformed
        // TODO add your handling code here:
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("nombre", "'" + this.juego.getText() + "'");
        basicDBObject.put("tipo", "'" + this.tipo.getText() + "'");
        this.dbCollection.insert(basicDBObject);
        JOptionPane.showMessageDialog(null, "Se ha insertado un juego.");
    }//GEN-LAST:event_insertarActionPerformed

    private void leerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leerActionPerformed
        // TODO add your handling code here:
        DBCursor dbCursor;
        BasicDBObject basicDBObject = new BasicDBObject();
        
        // Para el SELECT * FROM juegos
        if (this.juego.getText().equals("") && this.tipo.getText().equals("")) {
           dbCursor = this.dbCollection.find();
           
        // Para el SELECT * FROM juegos WHERE nombre = 'this.juego.getText()'
        } else if (!this.juego.getText().equals("") && this.tipo.getText().equals("")) {
            basicDBObject.put("nombre", "'" + this.juego.getText() + "'");
            dbCursor = this.dbCollection.find(basicDBObject);
        
        // Para el SELECT * FROM juegos WHERE tipo = 'this.tipo.getText()'
        } else if (this.juego.getText().equals("") && !this.tipo.getText().equals("")) {
            basicDBObject.put("tipo:", this.tipo.getText());
            dbCursor = this.dbCollection.find(basicDBObject);
        
        // Para el SELECT * FROM juegos WHERE nombre = tipo 'this.juego.getText()' AND tipo = 'this.tipo.getText()'
        } else {
            basicDBObject.put("nombre", "'" + this.juego.getText() + "'");
            basicDBObject.put("tipo", "'" + this.tipo.getText() + "'");
            dbCursor = this.dbCollection.find(basicDBObject);
        }
         
        // Ciclo que recupera las tuplas
        while(dbCursor.hasNext()){
            this.tuplas.append(dbCursor.next() + "\n");
        }    
    }//GEN-LAST:event_leerActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PruebaMongo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PruebaMongo().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton insertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField juego;
    private javax.swing.JButton leer;
    private javax.swing.JTextField tipo;
    private javax.swing.JTextArea tuplas;
    // End of variables declaration//GEN-END:variables
}
