package com.example.btl.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btl.R;
import com.example.btl.model.Article;
import com.example.btl.model.Category;

import java.text.SimpleDateFormat;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>{
    private List<Article> mListArticle;
    private Activity activity;

    public ArticleAdapter(List<Article> mListArticle, Activity activity) {
        this.mListArticle = mListArticle;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article,parent,false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = mListArticle.get(position);
        if(article!=null){
            // Glide load ảnh để hiển thị ra
            Glide.with(activity).load(article.getImg()).into(holder.imgArticle);
            holder.tvTitle.setText(article.getTitle());
            if(article.getTimestamps() !=null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(article.getTimestamps());
                holder.tvDate.setText(date);
            }
            else holder.tvDate.setText("05/22/2022");
        }
        holder.itemView.setOnClickListener(view -> {
            itemClickedListener.onItemClicked(mListArticle.get(holder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        if(mListArticle !=null) return mListArticle.size();
        return 0;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgArticle;
        private TextView tvTitle, tvDate;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArticle = itemView.findViewById(R.id.imgArticle);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    public void setmListArticle(List<Article> mListArticle) {
        this.mListArticle = mListArticle;
        notifyDataSetChanged();
    }

    public interface ItemClickedListener{
        void onItemClicked(Article article);
    }

    public ItemClickedListener itemClickedListener;

    public void setItemClickedListener(ItemClickedListener itemClickedListener) {
        this.itemClickedListener = itemClickedListener;
    }
}
