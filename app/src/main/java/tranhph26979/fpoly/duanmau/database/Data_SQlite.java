package tranhph26979.fpoly.duanmau.database;

public class Data_SQlite {
    public static final String INSERT_THUTHU="Insert into ThuThu (maTT,hoTen,matKhau) values"+"('admin','trafdual','admin'),"+
           "('tra','dualtamboi','admin')";
    public static final String INSERT_THANHVIEN="Insert into ThanhVien(hoTen,namSinh,gioitinh) values"+"('ngu','2003','nam'),"+"('cho','2011','nữ'),"+"('long','2002','nam'),"+"('dương','2003','nam'),"+"('trà','2003','nam'),"+"('thùy','2003','nữ'),"+"('hưng','2003','nữ')";
   public static final String INSERT_LOAISACH="Insert into LoaiSach(tenLoai) values"+"('Công nghệ thông tin'),"+"('Marketing'),"+"('Truyện tranh'),"+"('Văn học')";
    public static final String INSERT_SACH="Insert into Sach(tenSach,giaThue,maLoai) values"+"('Công nghệ thông tin',50000,1),"+"('Truyện tranh',35000,3)";
}
