package com.example.dictionary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;

import java.util.ArrayList;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextHolder> {
    private ArrayList<String> texts;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TextAdapter(ArrayList<String> texts) {
        this.texts = texts;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.text_fragment, parent, false);
        return new TextHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder holder, int position) {
        holder.txtText.setText(texts.get(position));
    }

    @Override
    public int getItemCount() {
        return texts.size();
    }

    class TextHolder extends RecyclerView.ViewHolder {
        TextView txtText;

        public TextHolder(@NonNull View itemView) {
            super(itemView);
            txtText = itemView.findViewById(R.id.txtText);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onItemClickListener != null && position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(txtText.getText().toString());
                    }
                }
            });
        }


    }
    public interface OnItemClickListener{
        public void onItemClick(String text);


    }

}
