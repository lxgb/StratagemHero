package com.example.stratagemhero.stratagem_handler;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stratagemhero.R;
import com.example.stratagemhero.RecyclerViewStratagemInterface;

import java.util.ArrayList;

public class StratagemAdapter extends RecyclerView.Adapter<StratagemAdapter.StratagemViewHolder> {
    private final RecyclerViewStratagemInterface recyclerViewStratagemInterface;
    private final ArrayList<Stratagem> alStratagems;
    public StratagemAdapter(ArrayList<Stratagem> alStratagems, RecyclerViewStratagemInterface recyclerViewStratagemInterface) {
        this.alStratagems = alStratagems;
        this.recyclerViewStratagemInterface = recyclerViewStratagemInterface;
    }

    @NonNull
    @Override
    public StratagemAdapter.StratagemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_stratagem, parent, false);

        return new StratagemViewHolder(view, recyclerViewStratagemInterface);
    }


    // method to declare data display per card in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull StratagemAdapter.StratagemViewHolder holder, int position) {

        holder.tvStratagemName.setText(alStratagems.get(position).fetchStratagemName());
        holder.menuStratagemIcon.setImageResource(alStratagems.get(position).fetchStratagemIcon());
        holder.stratagemContainer.setBackgroundColor(Color.parseColor(alStratagems.get(position).fetchStratagemColor()));
        holder.menuComboBtn1.setImageResource(alStratagems.get(position).fetchComboImages()[0]);
        holder.menuComboBtn2.setImageResource(alStratagems.get(position).fetchComboImages()[1]);
        holder.menuComboBtn3.setImageResource(alStratagems.get(position).fetchComboImages()[2]);
        holder.menuComboBtn4.setImageResource(alStratagems.get(position).fetchComboImages()[3]);
        holder.menuComboBtn5.setImageResource(alStratagems.get(position).fetchComboImages()[4]);
        holder.menuComboBtn6.setImageResource(alStratagems.get(position).fetchComboImages()[5]);
        holder.menuComboBtn7.setImageResource(alStratagems.get(position).fetchComboImages()[6]);
        holder.menuComboBtn8.setImageResource(alStratagems.get(position).fetchComboImages()[7]);
    }

    // fetches constructor ArrayList length
    @Override
    public int getItemCount() { return alStratagems.size(); }

    public static class StratagemViewHolder extends RecyclerView.ViewHolder {
        TextView tvStratagemName;
        ImageView menuComboBtn1,
                menuComboBtn2,
                menuComboBtn3,
                menuComboBtn4,
                menuComboBtn5,
                menuComboBtn6,
                menuComboBtn7,
                menuComboBtn8;
        ImageView menuStratagemIcon;
        LinearLayout stratagemContainer;

        // bind elements per card to display
        public StratagemViewHolder(@NonNull View itemView, RecyclerViewStratagemInterface recyclerViewStratagemInterface) {
            super(itemView);

            tvStratagemName = itemView.findViewById(R.id.tvStratagemName);

            menuComboBtn1 = itemView.findViewById(R.id.menuComboBtn1);
            menuComboBtn2 = itemView.findViewById(R.id.menuComboBtn2);
            menuComboBtn3 = itemView.findViewById(R.id.menuComboBtn3);
            menuComboBtn4 = itemView.findViewById(R.id.menuComboBtn4);
            menuComboBtn5 = itemView.findViewById(R.id.menuComboBtn5);
            menuComboBtn6 = itemView.findViewById(R.id.menuComboBtn6);
            menuComboBtn7 = itemView.findViewById(R.id.menuComboBtn7);
            menuComboBtn8 = itemView.findViewById(R.id.menuComboBtn8);

            menuStratagemIcon = itemView.findViewById(R.id.menuStratagemIcon);
            stratagemContainer = itemView.findViewById(R.id.stratagemContainer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewStratagemInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewStratagemInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
