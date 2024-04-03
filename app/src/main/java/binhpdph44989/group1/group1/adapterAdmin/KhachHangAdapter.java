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
import binhpdph44989.group1.group1.dao.KhachHangDAO;
import binhpdph44989.group1.group1.dao.QuanLyDAO;
import binhpdph44989.group1.group1.model.KhachHang;
import binhpdph44989.group1.group1.model.QuanLy;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder>{

    private Context context;
    private ArrayList<KhachHang> list;
    private KhachHangDAO khachHangDAO;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> list, KhachHangDAO khachHangDAO) {
        this.context = context;
        this.list = list;
        this.khachHangDAO = khachHangDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_tai_khoan_user, parent, false);
        return new KhachHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KhachHang khachHang = list.get(position);
        holder.txtMaKH.setText("Mã khách hàng: MK"+khachHang.getMakh());
        holder.txtHoTen.setText("Họ tên: "+khachHang.getHoten());
        holder.txtSDT.setText("Số điện thoại: "+khachHang.getSdt());
        holder.txtDiaChi.setText("Địa chỉ: "+khachHang.getDiachi());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaKH,txtHoTen,txtSDT,txtDiaChi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaKH = itemView.findViewById(R.id.txtMaKH);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtSDT = itemView.findViewById(R.id.txtSDT);
            txtDiaChi = itemView.findViewById(R.id.txtDiaChi);
        }
    }
}
