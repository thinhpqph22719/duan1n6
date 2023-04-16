package repositories;
import java.util.ArrayList;
import java.util.List;
import models.KhuyenMai;
import repositories.KhuyenMaiRepository;
import viewmodels.KhuyenMaiView;
import java.sql.*;
import utilities.DBConnect;
public class KhuyenMaiRepository {

  private final String select_All = "SELECT KhuyenMai.Id,KhuyenMai.Ten,SanPham.Ten,NgayBatDau,NgayKetThuc,GiamGia,KhuyenMai.TrangThai\n" +
"            FROM KhuyenMai JOIN SanPham ON KhuyenMai.SanPham_Id = SanPham.Id";
    private final String insert = "INSERT INTO KhuyenMai(Ten,NgayBatDau,NgayKetThuc,GiamGia,TrangThai,SanPham_Id) VALUES (?,?,?,?,?,?)";
    private final String update = "UPDATE KhuyenMai SET Ten = ?,NgayBatDau =?,NgayKetThuc=?,GiamGia=?,TrangThai = ?,SanPham_Id =? WHERE Id = ?";
    private final String delete = "DELETE FROM KhuyenMai WHERE Id LIKE ?";
    private final String search = "SELECT Id,Ten,NgayBatDau,NgayKetThuc,GiamGia,KhuyenMai.TrangThai,SanPham.Ten \n" +
"FROM KhuyenMai JOIN SanPham ON KhuyenMai.SanPham_Id = SanPham.Id\n" +
"WHERE Ten LIKE ? OR SanPham.Ten LIKE ?";
    public List<KhuyenMaiView> getList() {
        List<KhuyenMaiView> listKM = new ArrayList<>();
        try {
            ResultSet rs = DBConnect.Query(select_All);
            while (rs.next()) {
                KhuyenMaiView km = new KhuyenMaiView();
                km.setIdKhuyenMai(rs.getInt(1));
                km.setTenKhuyenMai(rs.getString(2));
                km.setTenSanPham(rs.getString(3));
                km.setNgayBatDau(rs.getDate(4));
                km.setNgayKetThuc(rs.getDate(5));
                km.setGiamGia(rs.getInt(6));
                km.setTrangThai(rs.getInt(7));
                listKM.add(km);
            }
            rs.getStatement().getConnection().close();
            return listKM;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean insert(KhuyenMai km) {
        int check = 0;
        try {
            check = DBConnect.Update(insert, km.getTenKhuyenMai(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getGiamGia(), km.getTrangThai(), km.getIdSanPham());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return check > 0;
    }

    public boolean update(String maKM, KhuyenMai km) {
        int check = 0;
        try {
            check = DBConnect.Update(update, km.getTenKhuyenMai(), km.getNgayBatDau(), km.getNgayKetThuc(), km.getGiamGia(), km.getTrangThai(), km.getIdSanPham(),maKM);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return check > 0;
    }

    public boolean delete(String maKM) {
         int check = 0;
        try {
            check = DBConnect.Update(delete,maKM);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return check > 0;
    }

    
    public List<KhuyenMaiView> getSearch(String tenKM, String tenSP) {
List<KhuyenMaiView> listKM = new ArrayList<>();
        try {
            ResultSet rs = DBConnect.Query(search,"%"+tenKM+"%","%"+tenSP+"%");
            while (rs.next()) {
                KhuyenMaiView km = new KhuyenMaiView();
                km.setIdKhuyenMai(rs.getInt(1));
                km.setTenKhuyenMai(rs.getString(2));
                km.setNgayBatDau(rs.getDate(3));
                km.setNgayKetThuc(rs.getDate(4));
                km.setGiamGia(rs.getInt(5));
                km.setTrangThai(rs.getInt(6));
                km.setTenSanPham(rs.getString(7));
                listKM.add(km);
            }
            rs.getStatement().getConnection().close();
            return listKM;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
