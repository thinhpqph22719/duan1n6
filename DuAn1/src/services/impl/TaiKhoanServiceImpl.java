package services.impl;

import models.NhanVien;
import repositories.TaiKhoanRepository;
import services.TaiKhoanService;
public class TaiKhoanServiceImpl implements TaiKhoanService {
    private TaiKhoanRepository TaiKhoanRepository = new TaiKhoanRepository();
    @Override
    public NhanVien login(String tenDangNhap, String matKhau) {
        return TaiKhoanRepository.login(tenDangNhap, matKhau);
    }

    @Override
    public boolean updateMK(String email, String mKM) {
    return  TaiKhoanRepository.updateMK(email, mKM);
    }


}
