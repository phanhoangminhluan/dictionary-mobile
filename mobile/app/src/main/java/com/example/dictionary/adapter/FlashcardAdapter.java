package com.example.dictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.model.BodyGetDetailFlashcardModel;

import java.util.ArrayList;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardHolder> {
    private ArrayList<BodyGetDetailFlashcardModel> bodyGetDetailFlashcardModels;
    private FlashcardAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FlashcardAdapter(ArrayList<BodyGetDetailFlashcardModel> bodyGetDetailFlashcardModels) {
        this.bodyGetDetailFlashcardModels = bodyGetDetailFlashcardModels;
    }

    @NonNull
    @Override
    public FlashcardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_flashcard, parent, false);
        return new FlashcardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashcardHolder holder, int position) {
        holder.txtName.setText(bodyGetDetailFlashcardModels.get(position).getName());
        holder.txtUsername.setText(bodyGetDetailFlashcardModels.get(position).getUsername());
        holder.txtCardsCount.setText(bodyGetDetailFlashcardModels.get(position).getCards().size()+ " terms");


    }

    @Override
    public int getItemCount() {
        return bodyGetDetailFlashcardModels.size();
    }

    class FlashcardHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtUsername;
        TextView txtCardsCount;

        public FlashcardHolder(View flashcardView) {
            super(flashcardView);
            txtName = flashcardView.findViewById(R.id.txtName);
            txtUsername = flashcardView.findViewById(R.id.txtUsername);
            txtCardsCount = flashcardView.findViewById(R.id.txtCardsCount);
            LinearLayout linearLayout = flashcardView.findViewById(R.id.homeToFlashDetail);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onItemClickListener != null && position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(bodyGetDetailFlashcardModels.get(position).getId());
                    }
                }
            });

        }


    }
    public interface OnItemClickListener{
        public void onItemClick(String id);


    }
}
