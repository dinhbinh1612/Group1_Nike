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
import binhpdph44989.group1.group1.dao.TopDAO;
import binhpdph44989.group1.group1.model.DonHang;
import binhpdph44989.group1.group1.model.Top;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Top> list;
    private TopDAO topDAO;

    public TopAdapter(Context context, ArrayList<Top> list, TopDAO topDAO) {
        this.context = context;
        this.list = list;
        this.topDAO = topDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_giay, parent, false);
        return new TopAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Top top = list.get(position);
        holder.txtMaGiay.setText("Mã giày: MG"+top.getMaGiay());
        holder.txtTenGiay.setText("Tên giày: "+top.getTenGiay());
        holder.txtLoaiGiay.setText("Loại giày: "+top.getLoaiGiay());
    }

    @Override
    public int getItemCount() {
       return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaGiay,txtTenGiay,txtLoaiGiay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaGiay = itemView.findViewById(R.id.txtMaGiay);
            txtTenGiay = itemView.findViewById(R.id.txtTenGiay);
            txtLoaiGiay = itemView.findViewById(R.id.txtLoaiGiay);

        }
    }
}
