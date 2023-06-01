package com.example.dicasnaturais.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dicasnaturais.R;
import com.example.dicasnaturais.models.Tip;

import java.util.List;

public class TipsCardViewAdapter extends RecyclerView.Adapter<TipsCardViewAdapter.ViewHolder> {
    private List<Tip> tips;

    public TipsCardViewAdapter(List<Tip> tips) {
        this.tips = tips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tip_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tip tip = tips.get(position);
        holder.title.setText(tip.getTitle());
        holder.description.setText(tip.getDescription());
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
//            itemView.setOnClickListener();
        }
    }
}
