import java.util.List;
import java.util.ArrayList;

public class Buku {
    private String judul;
    private String kodeBuku;
    private List<GenreBuku> daftarGenre; // many-to-many ke GenreBuku
    private Member peminjam;             // many-to-one ke Member, null = tersedia

    public Buku(String judul, String kodeBuku){
        this.judul = judul;
        this.kodeBuku = kodeBuku;
        this.daftarGenre = new ArrayList<>();
        this.peminjam = null;
    }

    public String getJudul(){
        return judul;
    }

    public String getKodeBuku(){
        return kodeBuku;
    }

    public void tambahGenre(GenreBuku g){
        if(!daftarGenre.contains(g)){
            daftarGenre.add(g);
            g.tambahBuku(this); // sinkronisasi dua arah
        }
    }

    public Member getPeminjam(){
        return peminjam;
    }

    public boolean isTersedia(){
        return peminjam == null;
    }

    void setPeminjam(Member m){
        this.peminjam = m;
    }

    public void tampilkanGenre(){
        System.out.print(judul + "- Genre:");
        for(int i = 0; i < daftarGenre.size()); i++){
            System.out.print(daftarGenre.get(i).getNameGenre());
            if(i < daftarGenre.size() - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public void tampilkanStatus(){
        if(isTersedia()){
            System.out.println(judul + " (" + kodeBuku + ") - Tersedia");
        } else {
            System.out.println(judul + " (" + kodeBuku + ") - Dipinjam oleh" + peminjam.getNama());
        }
    }
}