import java.util.ArrayList;
import java.util.List;

public class GenreBuku {
    private String kodeGenre;
    private String namaGenre;
    private List<Buku> daftarBuku;

    public GenreBuku(String kodeGenre, String namaGenre) {
        this.kodeGenre = kodeGenre;
        this.namaGenre = namaGenre;
        this.daftarBuku = new ArrayList<>();
    }

    public String getKodeGenre() {
        return kodeGenre;
    }

    public String getNamaGenre() {
        return namaGenre;
    }

    public void tambahBuku(Buku b) {
        if (!daftarBuku.contains(b)) {
            daftarBuku.add(b);
            b.tambahGenre(this);
        }
    }

    public void tampilkanBuku() {
        System.out.println("Daftar buku dengan genre" + namaGenre + ":");
        int i = 1;
        for (Buku b : daftarBuku) {
            System.out.println(i + ". " + b.getJudul() + " (" + b.getKodeBuku() + ")");
            i++;
        }
    }
}
