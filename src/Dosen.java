public class Dosen extends Anggota {
    private String nidn;

    public Dosen(String nama, String idAnggota, String nidn){
        super(nama, idAnggota);
        this.nidn = nidn;
    }

    public String getNidn(){
        return nidn;
    }

    @Override
    public int getMaxPinjam(){
        return 10;
    }
}