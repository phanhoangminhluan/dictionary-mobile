package com.example.dictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.model.BodyFavorite;
import com.example.dictionary.model.FavoriteWord;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {
    private ArrayList<FavoriteWord> favoriteWords;
    private FavoriteAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FavoriteAdapter(ArrayList<FavoriteWord> favoriteWords) {
        this.favoriteWords = favoriteWords;
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.word_favorite, parent, false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
        holder.txtWord.setText(favoriteWords.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return favoriteWords.size();
    }

    class FavoriteHolder extends RecyclerView.ViewHolder {
        TextView txtWord;


        public FavoriteHolder(View favoriteView) {
            super(favoriteView);
            txtWord = favoriteView.findViewById(R.id.txtWordFavorite);
            LinearLayout linearLayout = favoriteView.findViewById(R.id.wordFavorite);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onItemClickListener != null && position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(favoriteWords.get(position).getWord());
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        public void onItemClick(String word);


    }
}

