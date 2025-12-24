package inventori;

import koneksi.koneksiDatabase;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Inventori extends javax.swing.JFrame {

    public Inventori() {
        initComponents();
        testKoneksiOtomatis(); // Test saat aplikasi dibuka
    }
    
    private void testKoneksiOtomatis() {
        // Test koneksi otomatis saat aplikasi start
        Connection conn = koneksiDatabase.getKoneksi();
        if (conn != null) {
            lblStatus.setText("Status: TERKONEKSI ✅");
            lblStatus.setForeground(new java.awt.Color(0, 153, 0)); // Hijau
        } else {
            lblStatus.setText("Status: TIDAK TERKONEKSI ❌");
            lblStatus.setForeground(new java.awt.Color(204, 0, 0)); // Merah
        }
    }
    
    private void initComponents() {
        // Komponen GUI
        lblStatus = new javax.swing.JLabel();
        btnTest = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistem Inventori Toko");
        
        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblStatus.setText("Status: Testing...");
        
        btnTest.setText("Test Koneksi Database");
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });
        
        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        
        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTest)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKeluar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTest)
                    .addComponent(btnKeluar))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        
        pack();
        setLocationRelativeTo(null); // Center window
    }
    
    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {
        Connection conn = koneksiDatabase.getKoneksi();
        if (conn != null) {
            JOptionPane.showMessageDialog(this, 
                "KONEKSI BERHASIL!\n\n" +
                "Database: inventory_toko\n" +
                "User: root\n" +
                "Status: Ready",
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);
            lblStatus.setText("Status: TERKONEKSI ✅");
            lblStatus.setForeground(new java.awt.Color(0, 153, 0));
        } else {
            JOptionPane.showMessageDialog(this, 
                "KONEKSI GAGAL!\n\n" +
                "Periksa:\n" +
                "1. MySQL server berjalan\n" +
                "2. Database 'inventory_toko' ada\n" +
                "3. Username/password benar",
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            lblStatus.setText("Status: TIDAK TERKONEKSI ❌");
            lblStatus.setForeground(new java.awt.Color(204, 0, 0));
        }
    }
    
    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    
    // Variables declaration
    private javax.swing.JButton btnTest;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JLabel lblStatus;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Set look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Use default
        }
        
        // Create and show the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventori().setVisible(true);
            }
        });
    }
}