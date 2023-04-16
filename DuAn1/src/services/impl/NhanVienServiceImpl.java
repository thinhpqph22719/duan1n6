package services.impl;

import java.util.List;
import models.NhanVien;
import repositories.NhanVienRepository;
import services.NhanVienService;
import viewmodels.NhanVienViewModel;

public class NhanVienServiceImpl implements NhanVienService {

    private final NhanVienRepository nhanVienRepository = new NhanVienRepository();

    @Override
    public List<NhanVienViewModel> getList(int trangThai) {
        return nhanVienRepository.getList(trangThai);
    }

    @Override
    public String add(NhanVien nv) {
        if (nhanVienRepository.add(nv)) {
            return "Thêm thành công!";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(String maNv, NhanVien nv) {
        if (nhanVienRepository.update(maNv, nv)) {
            return "Sửa thành công!";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(String maNv) {
        if (nhanVienRepository.delete(maNv)) {
            return "Xóa thành công!";
        } else {
            return "Xóa thất bại";
        }
    }

    @Override
    public List<NhanVienViewModel> getSearch(String tenNV, String sdt, int trangThai) {
        return nhanVienRepository.getSearch(tenNV, sdt, trangThai);
    }

}
