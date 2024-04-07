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
import binhpdph44989.group1.group1.model.Giay;

public class GiayAdapter extends RecyclerView.Adapter<GiayAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Giay> giayList;
    Giay giayDao;
    GiayAdapter giayAdapter;

    public GiayAdapter(Context context, ArrayList<Giay> giayList) {
        this.context = context;
        this.giayList = giayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview_giay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Giay giay = giayList.get(position);

        holder.tvMaGiay.setText("Mã giày: " + giay.getMagiay());
        holder.tvTenGiay.setText("Tên giày: " + giay.getTengiay());
        holder.tvGiaBan.setText("Giá bán: " + giay.getGiaban() + " VNĐ");
        holder.tvSoLuong.setText("Số lượng: " + giay.getSoluong());
        holder.tvSize.setText("Size: " + giay.getSize());

        int resID = context.getResources().getIdentifier(giay.getHinhanh(), "drawable", context.getPackageName());
        holder.imgAnhGiay.setImageResource(resID);

//        holder.imgSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Hiển thị dialog sửa và điền thông tin của đối tượng vào các EditText
//                showDialogToEditGiay(giay);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return giayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAnhGiay, imgSua;
        TextView tvMaGiay, tvTenGiay, tvGiaBan, tvSoLuong, tvSize;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnhGiay = itemView.findViewById(R.id.imgGiay);
            tvMaGiay = itemView.findViewById(R.id.tvMaGiay);
            tvTenGiay = itemView.findViewById(R.id.tvTenGiay);
            tvGiaBan = itemView.findViewById(R.id.tvGiaBan);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvSize = itemView.findViewById(R.id.tvSize);
            imgSua = itemView.findViewById(R.id.tvGiayEdit);

        }
    }

//    private void showDialogToEditGiay(Giay giay) {
//        Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.dialog_edit_giay);
//
//        EditText edtTenGiay = dialog.findViewById(R.id.edtTenGiayedit);
//        EditText edtHinhAnh = dialog.findViewById(R.id.edtHinhAnhedit);
//        EditText edtGiaBan = dialog.findViewById(R.id.edtGiaBanedit);
//        EditText edtSoLuong = dialog.findViewById(R.id.edtSoLuongedit);
//        EditText edtSize = dialog.findViewById(R.id.edtSizeedit);
//
//        // Điền thông tin của đối tượng vào các EditText
//        edtTenGiay.setText(giay.getTenGiay());
//        edtHinhAnh.setText(giay.getHinhAnh());
//        edtGiaBan.setText(String.valueOf(giay.getGiaBan()));
//        edtSoLuong.setText(String.valueOf(giay.getSoLuong()));
//        edtSize.setText(String.valueOf(giay.getSize()));
//
//        Button btnLuu = dialog.findViewById(R.id.btnLuuEdit);
//        Button btnHuyEdit = dialog.findViewById(R.id.btnHuyEdit);
//
//        btnLuu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Lấy thông tin từ các EditText
//                String tenGiay = edtTenGiay.getText().toString();
//                String hinhAnh = edtHinhAnh.getText().toString();
//                int giaBan = Integer.parseInt(edtGiaBan.getText().toString());
//                int soLuong = Integer.parseInt(edtSoLuong.getText().toString());
//                int size = Integer.parseInt(edtSize.getText().toString());
//
//                // Cập nhật thông tin của đối tượng trong cơ sở dữ liệu
//                giay.setTenGiay(tenGiay);
//                giay.setHinhAnh(hinhAnh);
//                giay.setGiaBan(giaBan);
//                giay.setSoLuong(soLuong);
//                giay.setSize(size);
//                boolean isUpdated = giayDao.capNhatGiay(giay);
//
//                if (isUpdated) {
//                    // Cập nhật lại RecyclerView sau khi cập nhật đối tượng thành công
////                    loadData();
//                    dialog.dismiss();
//                } else {
//                    Toast.makeText(context, "Cập nhật giày không thành công!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        btnHuyEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }

}
