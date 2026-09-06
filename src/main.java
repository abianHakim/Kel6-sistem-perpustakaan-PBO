public class main {
    public static void main(String[] args) {

        // 1. Buat genre
        GenreBuku g1 = new GenreBuku("G01", "Fiksi");
        GenreBuku g2 = new GenreBuku("G02", "Fantasi");
        GenreBuku g3 = new GenreBuku("G03", "Sains");

        // 2. Buat buku
        Buku b1 = new Buku("Harry Potter", "BK001");
        Buku b2 = new Buku("Bumi Manusia", "BK002");
        Buku b3 = new Buku("Sapiens", "BK003");

        // 3. Pasang genre ke buku (many-to-many, boleh lebih dari 1 genre)
        b1.tambahGenre(g1);
        b1.tambahGenre(g2); // Harry Potter = Fiksi + Fantasi
        b2.tambahGenre(g1); // Bumi Manusia = Fiksi
        b3.tambahGenre(g3); // Sapiens = Sains

        // 4. Buat member
        Member m1 = new Member("Abian", "MB001");
        Member m2 = new Member("Rheika", "MB002");

        System.out.println("=== Percobaan Peminjaman ===");
        m1.pinjamBuku(b1); // berhasil
        m2.pinjamBuku(b1); // gagal, sudah dipinjam m1
        m1.pinjamBuku(b3); // berhasil
        m2.pinjamBuku(b2); // berhasil

        System.out.println("\n=== Status Semua Buku ===");
        b1.tampilkanStatus();
        b2.tampilkanStatus();
        b3.tampilkanStatus();

        System.out.println("\n=== Genre per Buku ===");
        b1.tampilkanGenre();
        b2.tampilkanGenre();
        b3.tampilkanGenre();

        System.out.println("\n=== Daftar Pinjaman per Member ===");
        m1.tampilkanPinjaman();
        m2.tampilkanPinjaman();

        System.out.println("\n=== Pengembalian ===");
        m1.kembalikanBuku(b1);
        m2.pinjamBuku(b1); // sekarang berhasil

        System.out.println("\n=== Buku per Genre ===");
        g1.tampilkanBuku();
        g2.tampilkanBuku();
        g3.tampilkanBuku();
    }
}
