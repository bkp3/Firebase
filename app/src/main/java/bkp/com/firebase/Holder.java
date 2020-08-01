package bkp.com.firebase;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class Holder extends RecyclerView.ViewHolder {

    private View view;

    public Holder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
    }

    public void setView(Context context, String name, String phone, String email){

        TextView nameTv = view.findViewById(R.id.name_d);
        TextView phoneTv = view.findViewById(R.id.phone_d);
        TextView emailTv = view.findViewById(R.id.email_d);

        nameTv.setText(name);
        phoneTv.setText(phone);
        emailTv.setText(email);

    }
}
