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

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.dao.QuanLyDAO;
import binhpdph44989.group1.group1.model.LoaiGiay;
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
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    showDialog(list.get(adapterPosition));
                }
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    // Cập nhật trạng thái từ 0 sang 1
                    quanLy.setTrangthaitk(1);
                    // Thông báo cho adapter biết rằng chỉ có một item đã thay đổi và cần cập nhật lại giao diện
                    notifyItemChanged(adapterPosition);
                    Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaQl,txtHoTen,txtTaiKhoan,txtMatKhau,txtTrangThai;
        ImageView ivEdit,ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaQl = itemView.findViewById(R.id.txtMaQL);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtTaiKhoan = itemView.findViewById(R.id.txtTaiKhoan);
            txtMatKhau = itemView.findViewById(R.id.txtMatKhau);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
    private void showDialog(QuanLy quanLy) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_thanh_vien, null);
        builder.setView(view);

        TextView txtMaQL = view.findViewById(R.id.txtMaQL);
        EditText edtHoTen = view.findViewById(R.id.edtHoTen);
        EditText edtTaiKhoan = view.findViewById(R.id.edtTaiKhoan);
        EditText edtMatKhau = view.findViewById(R.id.edtMatKhau);
        TextView txtTrangThai = view.findViewById(R.id.txtTrangThai);


        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String edtHoten = edtHoTen.getText().toString();
                String edtTK = edtTaiKhoan.getText().toString();
                String edtMK = edtMatKhau.getText().toString();

                if (quanLyDAO != null) {
                    boolean check = quanLyDAO.capNhatAdmin(quanLy.getMaql(), edtHoten, edtTK, edtMK);
                    if (check) {
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        // Kiểm tra trạng thái cập nhật trước khi tải lại dữ liệu
                        if (list.contains(quanLy)) {
                            // Cập nhật dữ liệu trong list
                            quanLy.setHoten(edtHoten);
                            quanLy.setTaikhoan(edtTK);
                            quanLy.setMatkhau(edtMK);
                            notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Lỗi: Đối tượng DAO không khởi tạo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Đặt giá trị cho TextView ở đây
        txtMaQL.setText(String.valueOf(quanLy.getMaql()));
        edtHoTen.setText(quanLy.getHoten());
        edtTaiKhoan.setText(quanLy.getTaikhoan());
        edtMatKhau.setText(quanLy.getMatkhau());
        txtTrangThai.setText(String.valueOf(quanLy.getTrangthaitk()));

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
    private void loadData(){
        list.clear();
        list = quanLyDAO.getDSQuanLy();
        notifyDataSetChanged();
    }
}
