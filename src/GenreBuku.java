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
}

