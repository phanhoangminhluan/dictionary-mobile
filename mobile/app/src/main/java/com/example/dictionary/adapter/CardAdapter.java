package com.example.dictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.model.Card;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {
    private ArrayList<Card> cardsDetailModels;
    private CardAdapter.OnItemClickistener onItemClickListener;


    public CardAdapter(ArrayList<Card> cardsDetailModels) {
        this.cardsDetailModels = cardsDetailModels;
    }

    public void setOnItemClickListener(OnItemClickistener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.term_definition_flashcard_detail,parent,false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        holder.txtTerm.setText(cardsDetailModels.get(position).getTerm());
        holder.txtDefinition.setText(cardsDetailModels.get(position).getDefinition());

    }

    @Override
    public int getItemCount() {
        return cardsDetailModels.size();
    }

    class CardHolder extends RecyclerView.ViewHolder{
        TextView txtTerm;
        TextView txtDefinition;
        ImageView imgEdit;
        ImageView imgDelete;

        public CardHolder(View cardView ){
            super(cardView);
            txtTerm = cardView.findViewById(R.id.txtTerm);
            txtDefinition = cardView.findViewById(R.id.txtDefinition);
            imgEdit =  cardView.findViewById(R.id.imgUpdateFlashcard);
            imgDelete = cardView.findViewById(R.id.imgDeleteFlashcard);
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onItemClickListener != null && position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClickUpdate(cardsDetailModels.get(position).getId(),cardsDetailModels.get(position).getTerm(), cardsDetailModels.get(position).getDefinition());
                    }
                }
            });
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onItemClickListener != null && position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClickDelete(cardsDetailModels.get(position).getId(), cardsDetailModels.get(position).getTerm());
                    }
                }
            });

        }

    }
    //Todo: binterface set onClickListener
    public interface OnItemClickistener{
        public void onItemClickUpdate(String id, String term, String definition);
        public void onItemClickDelete(String id, String term);

    }
}
