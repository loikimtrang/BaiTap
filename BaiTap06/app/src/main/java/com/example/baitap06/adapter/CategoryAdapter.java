package com.example.baitap06.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baitap06.R;
import com.example.baitap06.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context context;
    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.tvNameCategory.setText(category.getName());

        Glide.with(context)
                .load(category.getImages())
                .into(holder.imageCate);
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public void updateCategories(List<Category> newCategories) {
        this.categoryList = newCategories;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageCate;
        public TextView tvNameCategory;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCate = itemView.findViewById(R.id.imageCate);
            tvNameCategory = itemView.findViewById(R.id.tvNameCategory);
            itemView.setOnClickListener(view -> {
                Toast.makeText(context, "Bạn đã chọn category: " + tvNameCategory.getText().toString(), Toast.LENGTH_SHORT).show();
            });
        }
    }
}

