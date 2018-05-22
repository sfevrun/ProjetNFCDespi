package com.example.techsupport.projetnfcdespi;

import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 5/21/2018.
 */

public class CardViewOlder extends RecyclerView.ViewHolder  implements View.OnClickListener{
    public TextView txtprenom;
    public TextView txtnom;
   // public TextView txtfonction;
    public TextView tel;
    public TextView type;
    public TextView email;


    public CardViewOlder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        txtprenom = (TextView)itemView.findViewById(R.id.txtprenoml);
        txtnom = (TextView)itemView.findViewById(R.id.txtnoml);

        //txtfonction = (TextView)itemView.findViewById(R.id.tell);
        tel = (TextView)itemView.findViewById(R.id.tell);
        type = (TextView)itemView.findViewById(R.id.txtTypel);
        email = (TextView)itemView.findViewById(R.id.emaill);

    }




    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}