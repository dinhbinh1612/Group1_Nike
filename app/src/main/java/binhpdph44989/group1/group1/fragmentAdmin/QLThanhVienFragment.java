package binhpdph44989.group1.group1.fragmentAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import binhpdph44989.group1.group1.R;
import binhpdph44989.group1.group1.activity.TKKhachHangActivity;
import binhpdph44989.group1.group1.activity.TKQuanLyActivity;

public class QLThanhVienFragment extends Fragment {

    ImageView ivAdmin,ivUser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_tai_khoan,container,false);
        ivAdmin = view.findViewById(R.id.ivAdmin);
        ivUser = view.findViewById(R.id.ivUser);

        ivAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TKQuanLyActivity.class);
                startActivity(intent);
            }
        });

        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TKKhachHangActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
