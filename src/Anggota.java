import java.util.ArrayList;
import java.util.List;

public abstract class Anggota implements Peminjam{
    protected String nama;
    protected String idAnggota;
    protected List<Buku> daftarPinjaman;

    public Anggota(String nama, String idAnggota) {
        this.nama = nama;
        this.idAnggota = idAnggota;
        this.daftarPinjaman = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public int getJumlahPinjam(){
        return daftarPinjaman.size();
    }

    // methode abstrack: setiap anggota wajib tentukan kuota sendiri 
    public abstract int getMaxPinjam();
    public abstract String getTipeAnggota();   

    @Override                         
    public void pinjamBuku(Buku b){
        // isi logic TETAP SAMA, tidak diubah
        if(!b.isTersedia()){
            System.out.println("Gagal: \"" + b.getJudul() + "\" sedang dipinjam oleh " + b.getPeminjam().getNama());
            return;
        }
        if(daftarPinjaman.size() >= getMaxPinjam()){
            System.out.println("Gagal: " + nama + " sudah mencapai batas maksimal " + getMaxPinjam() + " buku");
            return;
        }
        daftarPinjaman.add(b);
        b.setPeminjam(this);
        System.out.println(nama + " berhasil meminjam \"" + b.getJudul() + "\"");
    }

    @Override
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
         System.out.println("Daftar Pinjaman " + nama + " (" + getTipeAnggota() + ", maks " + getMaxPinjam() + "):");
        int i = 1;
        for(Buku b : daftarPinjaman){
            System.out.println(i + ". " + b.getKodeBuku() + " - " + b.getJudul());
            i++;
        }
    }
}
