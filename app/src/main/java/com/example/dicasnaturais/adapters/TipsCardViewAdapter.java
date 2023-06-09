package com.example.dicasnaturais.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dicasnaturais.R;
import com.example.dicasnaturais.fragments.dialogs.ShowTipsDialogFragment;
import com.example.dicasnaturais.models.Tip;

import java.util.List;

public class TipsCardViewAdapter extends RecyclerView.Adapter<TipsCardViewAdapter.ViewHolder> {
    private FragmentManager manager;
    private List<Tip> tips;

    public TipsCardViewAdapter(FragmentManager manager, List<Tip> tips) {
        this.manager = manager;
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

        String description = tip.getDescription();
        int limit = 120;

        holder.title.setText(tip.getTitle());
        holder.description.setText(
                description.length() < limit
                ? description
                : description.substring(0, limit) + " [ ... ]"
        );
        holder.category.setText(tip.getCategory());

        holder.show.setOnClickListener((view -> {
            ShowTipsDialogFragment dialog = new ShowTipsDialogFragment(tip);
            dialog.show(manager, "ShowTipsDialogFragment");
        }));
    }

    @Override
    public int getItemCount() {
        return tips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView category;
        private TextView show;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show = itemView.findViewById(R.id.show);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            category = itemView.findViewById(R.id.category);
        }
    }
}
