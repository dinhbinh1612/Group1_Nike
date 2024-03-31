package binhpdph44989.group1.group1.adapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.dao.ThanhVienDAO;
import binhpdph44989.group1.group1.model.QuanLy;
import binhpdph44989.group1.group1.model.KhachHang;

public class ThanhVienAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_QUANLY = 1;
    private static final int VIEW_TYPE_KHACHHANG = 2;

    private Context context;
    private ArrayList<Object> combinedList;
    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<Object> combinedList) {
        this.context = context;
        this.combinedList = combinedList;
    }


    @Override
    public int getItemViewType(int position) {
        if (combinedList.get(position) instanceof QuanLy) {
            return VIEW_TYPE_QUANLY;
        } else if (combinedList.get(position) instanceof KhachHang) {
            return VIEW_TYPE_KHACHHANG;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case VIEW_TYPE_QUANLY:
                view = inflater.inflate(R.layout.item_recycler_tai_khoan_admin, parent, false);
                return new QuanLyViewHolder(view);
            case VIEW_TYPE_KHACHHANG:
                view = inflater.inflate(R.layout.item_recycler_tai_khoan_user, parent, false);
                return new KhachHangViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof QuanLyViewHolder) {
            QuanLy quanLy = (QuanLy) combinedList.get(position);
            ((QuanLyViewHolder) holder).bind(quanLy);
        } else if (holder instanceof KhachHangViewHolder) {
            KhachHang khachHang = (KhachHang) combinedList.get(position);
            ((KhachHangViewHolder) holder).bind(khachHang);
        }
    }

    @Override
    public int getItemCount() {
        return combinedList.size();
    }

    public class QuanLyViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaQL, txtHoTen, txtLoaiTaiKhoan, txtTrangThai;

        public QuanLyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaQL = itemView.findViewById(R.id.txtMaQL);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtLoaiTaiKhoan = itemView.findViewById(R.id.txtLoaiTaiKhoan);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
        }

        public void bind(QuanLy quanLy) {
            txtMaQL.setText("Mã quản lý: QL" + quanLy.getMaql());
            txtHoTen.setText("Họ tên: " + quanLy.getHoten());
            txtLoaiTaiKhoan.setText("Loại tài khoản: " + quanLy.getLoaitaikhoan());
            String trangThai = quanLy.getTrangthaitk() == 1 ? "Hoạt động" : "Không hoạt động";
            txtTrangThai.setText("Trạng thái: " + trangThai);
        }
    }

    public class KhachHangViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaKH, txtHoTen, txtSDT, txtDiaChi, txtLoaiTaiKhoan, txtTrangThai;

        public KhachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaKH = itemView.findViewById(R.id.txtMaKH);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            txtDiaChi = itemView.findViewById(R.id.txtDiaChi);
            txtLoaiTaiKhoan = itemView.findViewById(R.id.txtLoaiTaiKhoan);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
        }

        public void bind(KhachHang khachHang) {
            txtMaKH.setText("Mã khách hàng: KH" + khachHang.getMakh());
            txtHoTen.setText("Họ tên: " + khachHang.getHoten());
            txtSDT.setText("Số điện thoại: " + khachHang.getSdt());
            txtDiaChi.setText("Địa chỉ: " + khachHang.getDiachi());
            txtLoaiTaiKhoan.setText("Loại tài khoản: " + khachHang.getLoaitaikhoan());
            String trangThai = khachHang.getTrangthaitk() == 1 ? "Hoạt động" : "Không hoạt động";
            txtTrangThai.setText("Trạng thái: " + trangThai);
        }
    }
}