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

public class TermFlashcardAdapter  extends RecyclerView.Adapter<TermFlashcardAdapter.TermHolder>  {
    private ArrayList<Card> texts;
    private TermFlashcardAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(TermFlashcardAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TermFlashcardAdapter(ArrayList<Card> texts) {
        this.texts = texts;
    }

    @NonNull
    @Override
    public TermHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.term_card, parent, false);
        return new TermHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermHolder holder, int position) {
        holder.txtTermCard.setText(texts.get(position).getDefinition());

    }

    @Override
    public int getItemCount() {
        return texts.size();
    }

    class TermHolder extends RecyclerView.ViewHolder {
        TextView txtTermCard;
        ImageView btnZoom;

//TOdo: b1
        public TermHolder(@NonNull View itemView) {
            super(itemView);
            txtTermCard = itemView.findViewById(R.id.txtTermCard);
            btnZoom = itemView.findViewById(R.id.btnZoom);
                btnZoom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if(onItemClickListener != null && position != RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(txtTermCard.getText().toString());
                        }
                    }
                });

        }
    }

    public interface OnItemClickListener{
        public void onItemClick(String text);


    }

}
