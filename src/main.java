public class main {
    public static void main(String[] args) {

        GenreBuku g1 = new GenreBuku("G01", "Fiksi");
        GenreBuku g2 = new GenreBuku("G02", "Fantasi");
        GenreBuku g3 = new GenreBuku("G03", "Sains");

        Buku b1 = new Buku("Harry Potter", "BK001");
        Buku b2 = new Buku("Bumi Manusia", "BK002");
        Buku b3 = new Buku("Sapiens", "BK003");
        Buku b4 = new Buku("Filosofi Teras", "BK004");

        b1.tambahGenre(g1);
        b1.tambahGenre(g2);
        b2.tambahGenre(g1);
        b3.tambahGenre(g3);
        b4.tambahGenre(g1);

        // Polymorphism: dua jenis Anggota, kuota beda otomatis
        Anggota m1 = new Mahasiswa("Abian", "MB001", "J0403251069", "TI");
        Anggota d1 = new Dosen("Pak Supardi", "DS001", "987654");

        System.out.println("=== Percobaan Peminjaman ===");
        m1.pinjamBuku(b1);
        m1.pinjamBuku(b2);
        m1.pinjamBuku(b3);
        m1.pinjamBuku(b4); // gagal, mahasiswa maks 3

        d1.pinjamBuku(b4); // dosen masih bisa

        System.out.println("\n=== Daftar Pinjaman ===");
        m1.tampilkanPinjaman();
        d1.tampilkanPinjaman();
    }
}