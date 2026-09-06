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
}
