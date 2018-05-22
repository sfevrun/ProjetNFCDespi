package com.example.techsupport.projetnfcdespi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 5/21/2018.
 */

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CarteDespi> cards;
    private Context context;


    public CardAdapter(Context context, List<CarteDespi> itemList) {
        this.cards= itemList;
        this.context = context;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v1 = inflater.inflate(R.layout.item_card, viewGroup, false);
        viewHolder = new CardViewOlder(v1);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardViewOlder vh1 = (CardViewOlder) holder;

        vh1.txtnom.setText(cards.get(position).getLastName());
        vh1.txtprenom.setText(cards.get(position).getFirstname());
        vh1.tel.setText(cards.get(position).getCellphone());
        String text =cards.get(position).getType() != 1 ? "Carte envoyee":"Carte recues" ;
        vh1.type.setText(text);
        vh1.email.setText(cards.get(position).getCourriel());
        //vh1.txtprenom.setText(cards.get(position).getFirstname());



    }
    @Override
    public int getItemCount() {
        return this.cards.size();
    }





}
