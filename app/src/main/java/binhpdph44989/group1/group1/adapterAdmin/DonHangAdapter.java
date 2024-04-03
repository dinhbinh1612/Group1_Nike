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
import binhpdph44989.group1.group1.dao.DonHangDAO;
import binhpdph44989.group1.group1.model.DonHang;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder>{

    private Context context;
    private ArrayList<DonHang> list;
    private DonHangDAO donHangDAO;

    public DonHangAdapter(Context context, ArrayList<DonHang> list, DonHangDAO donHangDAO) {
        this.context = context;
        this.list = list;
        this.donHangDAO = donHangDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_don_hang, parent, false);
        return new DonHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang donHang = list.get(position);
        holder.txtMaDH.setText("Mã đơn hàng: MDH"+donHang.getMaDH());
        holder.txtHoTen.setText("Họ tên: "+donHang.getHoTen());
        holder.txtTenGiay.setText("Tên giày: "+donHang.getTenGiay());
        holder.txtNgayDat.setText("Ngày đặt: "+donHang.getNgayDat());
        holder.txtTongTien.setText("Tổng tiền: "+donHang.getTongTien());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaDH,txtHoTen,txtTenGiay,txtNgayDat,txtTongTien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaDH = itemView.findViewById(R.id.txtMaDH);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtTenGiay = itemView.findViewById(R.id.txtTenGiay);
            txtNgayDat = itemView.findViewById(R.id.txtNgayDat);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
        }
    }
}
