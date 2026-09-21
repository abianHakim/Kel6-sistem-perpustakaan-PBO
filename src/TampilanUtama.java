import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class TampilanUtama extends JFrame {
    private List<Buku> semuaBuku = new ArrayList<>();
    private List<Anggota> semuaAnggota = new ArrayList<>();

    private DefaultListModel<String> modelDaftarBuku = new DefaultListModel<>();
    private JList<String> listBuku = new JList<>(modelDaftarBuku);
    private JComboBox<String> comboAnggota = new JComboBox<>();
    private JTextArea areaLog = new JTextArea();
    private JLabel labelKuota = new JLabel();

    public TampilanUtama(){
        setupData();
        setupTampilan();

        setTitle("Aplikasi Perpustakaan");
        setSize(600, 450);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupData(){
        GenreBuku g1 = new GenreBuku("G01", "Fiksi");
        GenreBuku g2 = new GenreBuku("G02", "Sains");

        Buku b1 = new Buku("Harry Potter", "BK001");
        b1.tambahGenre(g1);
        Buku b2 = new Buku("Sapiens", "BK002");
        b2.tambahGenre(g2);
        Buku b3 = new Buku("Bumi Manusia", "BK003");
        b3.tambahGenre(g1);
        Buku b4 = new Buku("Filosofi Teras", "BK004");
        b4.tambahGenre(g1);

        semuaBuku.add(b1);
        semuaBuku.add(b2);
        semuaBuku.add(b3);
        semuaBuku.add(b4);

        semuaAnggota.add(new Mahasiswa("Abian", "MB001", "J0403251069", "TI"));
        semuaAnggota.add(new Dosen("Pak Supardi", "DS001", "987654"));

        for(Anggota a : semuaAnggota){
            comboAnggota.addItem(a.getNama() + " (" + a.getTipeAnggota() + ")");
        }
    }

    private void setupTampilan(){
        getContentPane().setLayout(new BorderLayout(12, 12));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // ===== PANEL ATAS: pilih anggota + tombol aksi =====
        JPanel panelAtas = new JPanel(new BorderLayout(8, 8));
        panelAtas.setBorder(new TitledBorder("Aksi Peminjaman"));

        JPanel panelPilihAnggota = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPilihAnggota.add(new JLabel("Anggota:"));
        comboAnggota.addActionListener(e -> updateLabelKuota());
        panelPilihAnggota.add(comboAnggota);
        panelPilihAnggota.add(labelKuota);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton tombolPinjam = new JButton("Pinjam Buku");
        JButton tombolKembalikan = new JButton("Kembalikan Buku");
        tombolPinjam.setBackground(new Color(76, 175, 80));
        tombolPinjam.setForeground(Color.WHITE);
        tombolKembalikan.setBackground(new Color(255, 152, 0));
        tombolKembalikan.setForeground(Color.WHITE);
        panelTombol.add(tombolPinjam);
        panelTombol.add(tombolKembalikan);

        panelAtas.add(panelPilihAnggota, BorderLayout.NORTH);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        // ===== PANEL TENGAH: daftar buku =====
        listBuku.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollBuku = new JScrollPane(listBuku);
        scrollBuku.setBorder(new TitledBorder("Daftar Buku"));
        scrollBuku.setPreferredSize(new Dimension(260, 200));

        // ===== PANEL KANAN: log aktivitas =====
        areaLog.setEditable(false);
        areaLog.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollLog = new JScrollPane(areaLog);
        scrollLog.setBorder(new TitledBorder("Log Aktivitas"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollBuku, scrollLog);
        splitPane.setResizeWeight(0.4);

        add(panelAtas, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        // ===== AKSI TOMBOL =====
        tombolPinjam.addActionListener(e -> {
            int idxBuku = listBuku.getSelectedIndex();
            if(idxBuku == -1){
                tampilkanPeringatan("Pilih buku dulu sebelum meminjam.");
                return;
            }
            Buku bukuDipilih = semuaBuku.get(idxBuku);
            Anggota anggotaDipilih = semuaAnggota.get(comboAnggota.getSelectedIndex());

            int jumlahSebelum = anggotaDipilih.getJumlahPinjam();
            anggotaDipilih.pinjamBuku(bukuDipilih);
            boolean berhasil = anggotaDipilih.getJumlahPinjam() > jumlahSebelum;

            tambahLog(anggotaDipilih, bukuDipilih, "meminjam", berhasil);
            refreshDaftarBuku();
            updateLabelKuota();
        });

        tombolKembalikan.addActionListener(e -> {
            int idxBuku = listBuku.getSelectedIndex();
            if(idxBuku == -1){
                tampilkanPeringatan("Pilih buku dulu sebelum mengembalikan.");
                return;
            }
            Buku bukuDipilih = semuaBuku.get(idxBuku);
            Anggota anggotaDipilih = semuaAnggota.get(comboAnggota.getSelectedIndex());

            int jumlahSebelum = anggotaDipilih.getJumlahPinjam();
            anggotaDipilih.kembalikanBuku(bukuDipilih);
            boolean berhasil = anggotaDipilih.getJumlahPinjam() < jumlahSebelum;

            tambahLog(anggotaDipilih, bukuDipilih, "mengembalikan", berhasil);
            refreshDaftarBuku();
            updateLabelKuota();
        });

        refreshDaftarBuku();
        updateLabelKuota();
    }

    private void refreshDaftarBuku(){
        int idxTerpilih = listBuku.getSelectedIndex();
        modelDaftarBuku.clear();
        for(Buku b : semuaBuku){
            String status = b.isTersedia() ? "Tersedia" : "Dipinjam: " + b.getPeminjam().getNama();
            modelDaftarBuku.addElement(b.getKodeBuku() + " - " + b.getJudul() + "  [" + status + "]");
        }
        if(idxTerpilih != -1 && idxTerpilih < modelDaftarBuku.size()){
            listBuku.setSelectedIndex(idxTerpilih);
        }
    }

    private void updateLabelKuota(){
        int idx = comboAnggota.getSelectedIndex();
        if(idx == -1) return;
        Anggota a = semuaAnggota.get(idx);
        labelKuota.setText("  |  Kuota: " + a.getJumlahPinjam() + "/" + a.getMaxPinjam());
    }

    private void tambahLog(Anggota a, Buku b, String aksi, boolean berhasil){
        String waktu = java.time.LocalTime.now().withNano(0).toString();
        String status = berhasil ? "BERHASIL" : "GAGAL";
        areaLog.append(String.format("[%s] %s (%s) %s \"%s\" -> %s%n",
                waktu, a.getNama(), a.getTipeAnggota(), aksi, b.getJudul(), status));
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }

    private void tampilkanPeringatan(String pesan){
        JOptionPane.showMessageDialog(this, pesan, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}