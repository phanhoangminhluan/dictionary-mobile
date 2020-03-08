package com.example.dictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.model.Card;

import java.util.ArrayList;

public class CreateCardsAdapter extends RecyclerView.Adapter<CreateCardsAdapter.CreateCardsHolder> {
    private ArrayList<Card> cardsDetailModels;
    private  CreateCardsAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CreateCardsAdapter(ArrayList<Card> cardsDetailModels) {
        this.cardsDetailModels = cardsDetailModels;
    }

    @NonNull
    @Override
    public CreateCardsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.create_card, parent, false);
        return new CreateCardsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateCardsHolder holder, int position) {
        holder.edTerm.setText(cardsDetailModels.get(position).getTerm());
        holder.edDefinition.setText(cardsDetailModels.get(position).getDefinition());

    }

    @Override
    public int getItemCount() {
        return cardsDetailModels.size();
    }

    class CreateCardsHolder extends RecyclerView.ViewHolder{
        EditText edTerm;
        EditText edDefinition;
        ImageView imgDelete;
        ImageView imgSave;
        public CreateCardsHolder(View view){
            super(view);
            edTerm = view.findViewById(R.id.edTerm);
            edDefinition = view.findViewById(R.id.edDefinition);
            imgDelete = view.findViewById(R.id.iconDelete);
            imgSave = view.findViewById(R.id.iconSave);
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onItemClickListener != null && position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemDeleteClick(position);
                    }
                }
            });
            imgSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onItemClickListener != null && position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemSaveClick(edTerm.getText().toString(),edDefinition.getText().toString(), position);
                    }
                }
            });
        }

    }

    public interface OnItemClickListener{
        public void onItemDeleteClick(int position);
        public void onItemSaveClick(String term, String definition, int position);

    }


}
