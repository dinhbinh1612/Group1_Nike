package binhpdph44989.group1.group1.adapterAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.dao.QuanLyDAO;
import binhpdph44989.group1.group1.model.QuanLy;

public class QuanLyAdapter extends RecyclerView.Adapter<QuanLyAdapter.ViewHolder>{

    private Context context;
    private ArrayList<QuanLy> list;
    private QuanLyDAO quanLyDAO;

    public QuanLyAdapter(Context context, ArrayList<QuanLy> list, QuanLyDAO quanLyDAO) {
        this.context = context;
        this.list = list;
        this.quanLyDAO = quanLyDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_tai_khoan_admin, parent, false);
        return new QuanLyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuanLy quanLy = list.get(position);
        holder.txtMaQl.setText("Mã đơn hàng: MDH"+quanLy.getMaql());
        holder.txtHoTen.setText("Họ tên: "+quanLy.getHoten());
        holder.txtTaiKhoan.setText("Tài khoản: "+quanLy.getTaikhoan());
        holder.txtMatKhau.setText("Mật khẩu: "+quanLy.getMatkhau());
        String trangThai = quanLy.getTrangthaitk() == 0 ? "Hoạt động" : "Không hoạt động";
        holder.txtTrangThai.setText("Trạng thái: "+trangThai);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaQl,txtHoTen,txtTaiKhoan,txtMatKhau,txtTrangThai;
        ImageView ivEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaQl = itemView.findViewById(R.id.txtMaQL);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtTaiKhoan = itemView.findViewById(R.id.txtTaiKhoan);
            txtMatKhau = itemView.findViewById(R.id.txtMatKhau);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }
}
