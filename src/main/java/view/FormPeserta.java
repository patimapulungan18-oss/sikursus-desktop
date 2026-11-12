package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import model.Peserta;
import dao.PesertaDAO;

public class FormPeserta extends JFrame {

    // Kombinasi Warna Tema
    private final Color warnaLatar = new Color(230, 242, 233); // Soft Sage Green
    private final Color warnaHeader = new Color(27, 94, 32);   // Deep Emerald Green
    private final Color warnaSimpan = new Color(27, 94, 32);   
    private final Color warnaReset = new Color(255, 255, 255); 

    private JPanel panelUtama;
    private JPanel panelHeader;
    private JPanel panelForm;
    private JPanel panelJenis;
    private JPanel panelFasilitas;
    private JPanel panelTombol;
    private JPanel panelCari;

    private JLabel lblJudul, lblNim, lblNama, lblJenisKelamin, lblProdi, lblNoHp, lblEmail;
    private JLabel lblKodeAkses, lblKursus, lblFasilitas, lblRingkasan, lblTabel, lblPencarian;

    private JTextField txtNim;
    private JTextField txtNama;
    private JRadioButton rbLaki;
    private JRadioButton rbPerempuan;
    private ButtonGroup buttonGroupJenisKelamin;
    private JTextField txtProdi;
    private JTextField txtNoHp;
    private JTextField txtEmail;
    private JTextField txtPencarian;
    
    private JPasswordField txtKodeAkses;
    private JComboBox<String> cmbKursus;
    private JCheckBox cbModul;
    private JCheckBox cbSertifikat;
    private JCheckBox cbRekaman;
    private JTextArea txtRingkasan;
    private JScrollPane scrollRingkasan;

    // Komponen Tabel untuk Database
    private JTable jTable1;
    private JScrollPane scrollTable;

    private JButton btnSimpan; 
    private JButton btnUbah;
    private JButton btnHapus;
    private JButton btnReset;
    private JButton btnCari;

    public FormPeserta() {
        initComponents();

        buttonGroupJenisKelamin.add(rbLaki);
        buttonGroupJenisKelamin.add(rbPerempuan);
        
        txtRingkasan.setEditable(false);
        getRootPane().setDefaultButton(btnSimpan);

        // Ukuran jendela disesuaikan agar muat dengan komponen pencarian dan tabel
        setMinimumSize(new Dimension(850, 1020));
        setSize(new Dimension(880, 1050));
        setLocationRelativeTo(null);
        
        // Memuat data ke tabel saat form pertama kali dibuka
        loadTable();
        
        // Memastikan jendela tampil di layar
        setVisible(true);
    }

    private void initComponents() {
        panelUtama = new JPanel(new BorderLayout());
        panelHeader = new JPanel(new BorderLayout());
        panelForm = new JPanel(new GridBagLayout());
        panelJenis = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        panelFasilitas = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 0));
        panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));

        lblJudul = new JLabel("Master Data Peserta", SwingConstants.CENTER);
        lblNim = new JLabel("Nim");
        lblNama = new JLabel("Nama Peserta");
        lblJenisKelamin = new JLabel("Jenis Kelamin");
        lblProdi = new JLabel("Program Studi");
        lblNoHp = new JLabel("Nomor Hp");
        lblEmail = new JLabel("Email");
        lblKodeAkses = new JLabel("Kode Akses");
        lblKursus = new JLabel("Kursus");
        lblFasilitas = new JLabel("Fasilitas");
        lblRingkasan = new JLabel("Ringkasan");
        lblTabel = new JLabel("Daftar Peserta (Database)");
        lblPencarian = new JLabel("Pencarian");

        txtNim = new JTextField();
        txtNama = new JTextField();
        rbLaki = new JRadioButton("Laki-Laki");
        rbPerempuan = new JRadioButton("Perempuan");
        buttonGroupJenisKelamin = new ButtonGroup();
        txtProdi = new JTextField();
        txtNoHp = new JTextField();
        txtEmail = new JTextField();
        txtPencarian = new JTextField();
        
        txtKodeAkses = new JPasswordField();
        cmbKursus = new JComboBox<>(new String[] {
            "-Pilih Kursus-",
            "Java Desktop",
            "Data Science",
            "Web Programming",
            "UI/UX Design"
        });
        
        cbModul = new JCheckBox("Modul Cetak");
        cbSertifikat = new JCheckBox("Sertifikat");
        cbRekaman = new JCheckBox("Akses Rekaman");
        
        txtRingkasan = new JTextArea(5, 20);
        scrollRingkasan = new JScrollPane(txtRingkasan);

        // Inisialisasi JTable
        String[] kolomTabel = {"NIM", "Nama", "JK", "Prodi", "No HP", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(kolomTabel, 0);
        jTable1 = new JTable(tableModel);
        
        // Tambahkan Mouse Listener untuk Klik Baris Tabel
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        
        scrollTable = new JScrollPane(jTable1);

        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnReset = new JButton("RESET");
        btnCari = new JButton("Cari");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SIKURSUS Desktop - Form Data Peserta (CRUD)");

        // Warna Latar Belakang
        panelUtama.setBackground(warnaLatar);
        panelHeader.setBackground(warnaHeader);
        panelForm.setBackground(warnaLatar);
        panelJenis.setBackground(warnaLatar);
        panelFasilitas.setBackground(warnaLatar);
        panelTombol.setBackground(warnaLatar);
        panelCari.setBackground(warnaLatar);

        // Header Style
        lblJudul.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setPreferredSize(new Dimension(100, 60));
        panelHeader.add(lblJudul, BorderLayout.CENTER);

        // Label Font Style
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Color warnaTeksLabel = new Color(27, 94, 32);
        
        JLabel[] allLabels = {lblNim, lblNama, lblJenisKelamin, lblProdi, lblNoHp, lblEmail, lblKodeAkses, lblKursus, lblFasilitas, lblRingkasan, lblTabel, lblPencarian};
        for (JLabel lbl : allLabels) {
            lbl.setFont(fontLabel);
            lbl.setForeground(warnaTeksLabel);
        }

        rbLaki.setBackground(warnaLatar);
        rbPerempuan.setBackground(warnaLatar);
        cbModul.setBackground(warnaLatar);
        cbSertifikat.setBackground(warnaLatar);
        cbRekaman.setBackground(warnaLatar);

        panelJenis.add(rbLaki);
        panelJenis.add(rbPerempuan);

        panelFasilitas.add(cbModul);
        panelFasilitas.add(cbSertifikat);
        panelFasilitas.add(cbRekaman);

        // Panel Pencarian
        txtPencarian.setPreferredSize(new Dimension(300, 30));
        btnCari.setBackground(new Color(25, 118, 210));
        btnCari.setForeground(Color.WHITE);
        btnCari.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCari.setFocusPainted(false);
        btnCari.setPreferredSize(new Dimension(90, 30));
        btnCari.addActionListener(evt -> btnCariActionPerformed());

        panelCari.add(txtPencarian);
        panelCari.add(btnCari);

        // Styling Tombol Simpan
        btnSimpan.setBackground(warnaSimpan);
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSimpan.setFocusPainted(false);
        btnSimpan.setPreferredSize(new Dimension(110, 36));
        btnSimpan.addActionListener(evt -> btnSimpanActionPerformed());

        // Styling Tombol Ubah
        btnUbah.setBackground(new Color(25, 118, 210)); // Biru
        btnUbah.setForeground(Color.WHITE);
        btnUbah.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnUbah.setFocusPainted(false);
        btnUbah.setPreferredSize(new Dimension(110, 36));
        btnUbah.addActionListener(evt -> btnUbahActionPerformed());

        // Styling Tombol Hapus
        btnHapus.setBackground(new Color(211, 47, 47)); // Merah
        btnHapus.setForeground(Color.WHITE);
        btnHapus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnHapus.setFocusPainted(false);
        btnHapus.setPreferredSize(new Dimension(110, 36));
        btnHapus.addActionListener(evt -> btnHapusActionPerformed());

        // Styling Tombol RESET
        btnReset.setBackground(warnaReset);
        btnReset.setForeground(Color.BLACK);
        btnReset.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(100, 36));
        btnReset.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        btnReset.addActionListener(evt -> btnResetActionPerformed());

        panelTombol.add(btnSimpan);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnReset);

        // Dimensi Input Field
        Dimension dimField = new Dimension(540, 30);
        txtNim.setPreferredSize(dimField);
        txtNama.setPreferredSize(dimField);
        txtProdi.setPreferredSize(dimField);
        txtNoHp.setPreferredSize(dimField);
        txtEmail.setPreferredSize(dimField);
        txtKodeAkses.setPreferredSize(dimField);
        cmbKursus.setPreferredSize(dimField);
        
        scrollRingkasan.setPreferredSize(new Dimension(540, 90));
        scrollTable.setPreferredSize(new Dimension(540, 130));

        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 24, 15, 24));

        // Menyusun komponen ke GridBagLayout
        addRow(0, lblNim, txtNim);
        addRow(1, lblNama, txtNama);
        addRow(2, lblJenisKelamin, panelJenis);
        addRow(3, lblProdi, txtProdi);
        addRow(4, lblNoHp, txtNoHp);
        addRow(5, lblEmail, txtEmail);
        addRow(6, lblKodeAkses, txtKodeAkses);
        addRow(7, lblKursus, cmbKursus);
        addRow(8, lblFasilitas, panelFasilitas);
        addRow(9, lblRingkasan, scrollRingkasan);
        addRow(10, lblTabel, new JLabel(""));
        addRow(11, lblPencarian, panelCari);
        addRow(12, new JLabel(""), scrollTable);

        // Tombol Aksi di Baris Bawah
        GridBagConstraints tombolConstraints = new GridBagConstraints();
        tombolConstraints.gridx = 1;
        tombolConstraints.gridy = 13;
        tombolConstraints.weightx = 1.0;
        tombolConstraints.fill = GridBagConstraints.HORIZONTAL;
        tombolConstraints.anchor = GridBagConstraints.CENTER;
        tombolConstraints.insets = new Insets(15, 0, 0, 0);
        panelForm.add(panelTombol, tombolConstraints);

        panelUtama.add(panelHeader, BorderLayout.NORTH);
        panelUtama.add(new JScrollPane(panelForm), BorderLayout.CENTER);
        setContentPane(panelUtama);
    }

    private void addRow(int row, JLabel label, java.awt.Component component) {
        GridBagConstraints cLabel = new GridBagConstraints();
        cLabel.gridx = 0;
        cLabel.gridy = row;
        cLabel.anchor = GridBagConstraints.NORTHWEST;
        cLabel.insets = new Insets(6, 0, 6, 20);
        panelForm.add(label, cLabel);

        GridBagConstraints cField = new GridBagConstraints();
        cField.gridx = 1;
        cField.gridy = row;
        cField.weightx = 1.0;
        cField.fill = GridBagConstraints.HORIZONTAL;
        cField.anchor = GridBagConstraints.NORTHWEST;
        cField.insets = new Insets(4, 0, 4, 0);
        panelForm.add(component, cField);
    }

    private String getJenisKelamin() {
        if (rbLaki.isSelected()) return "L";
        if (rbPerempuan.isSelected()) return "P";
        return "";
    }

    private void clearForm() {
        txtNim.setText("");
        txtNim.setEditable(true);
        txtNama.setText("");
        buttonGroupJenisKelamin.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtKodeAkses.setText("");
        cmbKursus.setSelectedIndex(0);
        cbModul.setSelected(false);
        cbSertifikat.setSelected(false);
        cbRekaman.setSelected(false);
        txtRingkasan.setText("");
        txtPencarian.setText("");
        jTable1.clearSelection();
        txtNim.requestFocus();
        loadTable();
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        try {
            PesertaDAO dao = new PesertaDAO();
            List<Peserta> list = dao.getAll();
            for (Peserta p : list) {
                Object[] row = {
                    p.getNim(),
                    p.getNama(),
                    p.getJenisKelamin(),
                    p.getProdi(),
                    p.getNoHp(),
                    p.getEmail()
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data tabel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method Event Aksi Pencarian
    private void btnCariActionPerformed() {
        String keyword = txtPencarian.getText().trim();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try {
            PesertaDAO dao = new PesertaDAO();
            List<Peserta> list = dao.search(keyword);
            for (Peserta p : list) {
                Object[] row = {
                    p.getNim(),
                    p.getNama(),
                    p.getJenisKelamin(),
                    p.getProdi(),
                    p.getNoHp(),
                    p.getEmail()
                };
                model.addRow(row);
            }
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan.", "Informasi Pencarian", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal melakukan pencarian: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable1.getSelectedRow();
        if (row != -1) {
            txtNim.setText(jTable1.getValueAt(row, 0).toString());
            txtNim.setEditable(false);
            txtNama.setText(jTable1.getValueAt(row, 1).toString());
            
            String jk = jTable1.getValueAt(row, 2).toString();
            if (jk.equals("L")) {
                rbLaki.setSelected(true);
            } else {
                rbPerempuan.setSelected(true);
            }
            
            txtProdi.setText(jTable1.getValueAt(row, 3).toString());
            txtNoHp.setText(jTable1.getValueAt(row, 4).toString());
            txtEmail.setText(jTable1.getValueAt(row, 5).toString());
        }
    }

    private void btnSimpanActionPerformed() {
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String prodi = txtProdi.getText().trim();
        String noHp = txtNoHp.getText().trim();
        String email = txtEmail.getText().trim();
        String jk = getJenisKelamin();

        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || jk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIM, nama, jenis kelamin, dan prodi wajib diisi.", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cmbKursus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih kursus terlebih dahulu.", "Validasi Kursus", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Peserta peserta = new Peserta(nim, nama, jk, prodi, noHp, email);
        PesertaDAO dao = new PesertaDAO();
        try {
            boolean berhasilSimpan = dao.insert(peserta);
            if (berhasilSimpan) {
                String kursusPilihan = cmbKursus.getSelectedItem().toString();
                String jkLengkap = jk.equals("L") ? "Laki-Laki" : "Perempuan";
                StringBuilder sb = new StringBuilder();
                sb.append("===== DATA PESERTA BERHASIL DISIMPAN =====\n");
                sb.append("NIM             : ").append(nim).append("\n");
                sb.append("Nama Peserta    : ").append(nama).append("\n");
                sb.append("Jenis Kelamin   : ").append(jkLengkap).append("\n");
                sb.append("Program Studi   : ").append(prodi).append("\n");
                sb.append("Nomor HP        : ").append(noHp).append("\n");
                sb.append("Email           : ").append(email).append("\n");
                sb.append("Kursus Diikuti  : ").append(kursusPilihan).append("\n");
                sb.append("==============================================\n");
                txtRingkasan.setText(sb.toString());

                loadTable();
                clearForm();
                JOptionPane.showMessageDialog(this, "Data peserta berhasil disimpan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage(), "Error Database", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnUbahActionPerformed() {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel yang ingin diubah terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String prodi = txtProdi.getText().trim();
        String noHp = txtNoHp.getText().trim();
        String email = txtEmail.getText().trim();
        String jk = getJenisKelamin();

        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || jk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIM, nama, jenis kelamin, dan prodi wajib diisi.", "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Peserta peserta = new Peserta(nim, nama, jk, prodi, noHp, email);
        PesertaDAO dao = new PesertaDAO();
        try {
            if (dao.update(peserta)) {
                loadTable();
                clearForm();
                JOptionPane.showMessageDialog(this, "Data peserta berhasil diubah.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengubah data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error Database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnHapusActionPerformed() {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel yang ingin dihapus terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String nim = jTable1.getValueAt(row, 0).toString();

            PesertaDAO dao = new PesertaDAO();
            try {
                if (dao.delete(nim)) {
                    loadTable();
                    clearForm();
                    JOptionPane.showMessageDialog(this, "Data peserta berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error Database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnResetActionPerformed() {
        int jawab = JOptionPane.showConfirmDialog(this, "Kosongkan seluruh data form?", "Konfirmasi Reset", JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            clearForm();
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FormPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new FormPeserta().setVisible(true);
        });
    }
}