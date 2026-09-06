import java.util.List;
import java.util.ArrayList;

public class Member {
    private String nama;
    private String idMember;
    private List<Buku> daftarPinjaman; // one-to-many ke Bukugi

    public Member(String nama, String idMember){
        this.nama = nama;
        this.idMember = idMember;
        this.daftarPinjaman = new ArrayList<>();
    }

    public String getNama(){
        return nama;
    }

    public String getIdMember(){
        return idMember;
    }

    public void pinjamBuku(Buku b){
        if(b.isTersedia()){
            daftarPinjaman.add(b);
            b.setPeminjam(this);
            System.out.println(nama + " berhasil meminjam \"" + b.getJudul() + "\"");
        } else {
            System.out.println("Gagal: \"" + b.getJudul() + "\" sedang dipinjam oleh " + b.getPeminjam().getNama());
        }
    }

    public void kembalikanBuku(Buku b){
        if(daftarPinjaman.contains(b)){
            daftarPinjaman.remove(b);
            b.setPeminjam(null);
            System.out.println(nama + " mengembalikan \"" + b.getJudul() + "\"");
        } else {
            System.out.println(nama + " tidak sedang meminjam \"" + b.getJudul() + "\"");
        }
    }

    public void tampilkanPinjaman(){
        System.out.println("Daftar Pinjaman " + nama + ":");
        int i = 1;
        for(Buku b : daftarPinjaman){
            System.out.println(i + ". " + b.getKodeBuku() + " - " + b.getJudul());
            i++;
        }
    }
}
