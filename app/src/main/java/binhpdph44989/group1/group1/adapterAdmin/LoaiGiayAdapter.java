package binhpdph44989.group1.group1.adapterAdmin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.dao.LoaiGiayDAO;
import binhpdph44989.group1.group1.model.LoaiGiay;

public class LoaiGiayAdapter extends RecyclerView.Adapter<LoaiGiayAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LoaiGiay> list;
    private ArrayList<HashMap<String, Object>> listHM;

    private LoaiGiayDAO loaiGiayDAO;

    public LoaiGiayAdapter(Context context, ArrayList<LoaiGiay> list, LoaiGiayDAO loaiGiayDAO) {
        this.context = context;
        this.list = list;
        this.loaiGiayDAO = loaiGiayDAO;
    }

    public LoaiGiayAdapter(Context context, ArrayList<LoaiGiay> list, ArrayList<HashMap<String, Object>> listHM, LoaiGiayDAO loaiGiayDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.loaiGiayDAO = loaiGiayDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_loai_giay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiGiay loaiGiay = list.get(position);
        holder.txtMaLoai.setText("Mã loại giày: " + loaiGiay.getMaloai());
        holder.txtTenLoai.setText("Tên loại giày: " + loaiGiay.getTenloai());
        String trangThai = loaiGiay.getTrangthailoai() == 1 ? "Hết hàng" : "Còn hàng";
        holder.txtTrangThai.setText("Trạng thái: " + trangThai);

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    showDialog(list.get(adapterPosition));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaLoai, txtTenLoai, txtTrangThai;
        ImageView ivEdit;
        Spinner spnTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }

    private void showDialog(LoaiGiay loaiGiay) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_them_loai_giay, null);
        builder.setView(view);

        EditText edtTenLoai = view.findViewById(R.id.edtTenLoaiGiay);
        Spinner spnTrangThaiLoai = view.findViewById(R.id.spnTrangThai);

        ArrayList<String> trangThaiList = new ArrayList<>();
        trangThaiList.add("Còn hàng");
        trangThaiList.add("Hết hàng");

        // Thiết lập tên loại giày ban đầu cho EditText
        edtTenLoai.setText(loaiGiay.getTenloai());

        // Tạo một ArrayAdapter từ ArrayList
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, trangThaiList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTrangThaiLoai.setAdapter(spinnerAdapter);

        // Thiết lập trạng thái ban đầu của Spinner dựa trên dữ liệu hiện tại
        int position = loaiGiay.getTrangthailoai() == 1 ? 1 : 0; // 0: "Còn hàng", 1: "Hết hàng"
        spnTrangThaiLoai.setSelection(position);

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenLoai = edtTenLoai.getText().toString();
                int trangThaiIndex = spnTrangThaiLoai.getSelectedItemPosition();
                int trangthailoai = (trangThaiIndex == 0) ? 0 : 1;

                // Thực hiện cập nhật loại giày cho loaiGiay được chọn
                boolean updateResult = loaiGiayDAO.capNhatLoaiGiay(loaiGiay.getMaloai(), tenLoai, trangthailoai);
                if (updateResult) {
                    Toast.makeText(context, "Cập nhật loại giày thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(context, "Cập nhật loại giày thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    private void loadData() {
        list.clear();
        list.addAll(loaiGiayDAO.getDSLoaiGiay());
        notifyDataSetChanged();
    }
}