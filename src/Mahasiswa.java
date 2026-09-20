public class Mahasiswa extends Anggota {
    private String nim;
    private String prodi;

    public Mahasiswa(String nama, String idAnggota, String nim, String prodi){
        super(nama, idAnggota);
        this.nim = nim;
        this.prodi = prodi;
    }

    public String getNim(){
        return nim;
    }

    public String getProdi(){
        return prodi;
    }

    @Override
    public String getTipeAnggota() {
        return "Mahasiswa";
    }

    @Override
    public int getMaxPinjam(){
        return 3;
    }
}