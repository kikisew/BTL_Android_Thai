package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.btl.adapter.ArticleAdapter;
import com.example.btl.model.Article;
import com.example.btl.model.Category;
import com.example.btl.util.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleByCategoryActivity extends AppCompatActivity {
    private TextView tvCategory;
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private List<Article> articleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_by_category);
        tvCategory = findViewById(R.id.tvCategory);
        recyclerView = findViewById(R.id.reArticle);

        Intent intentFromFragmentHome = getIntent();
        Category category = (Category) intentFromFragmentHome.getSerializableExtra("category");
        String categoryName = category.getName();
        String id = category.getId();

        tvCategory.setText(categoryName);

        articleAdapter = new ArticleAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        APIUtils.getApiServiceInterface().getArticleBySlug(id)
                .enqueue(new Callback<List<Article>>() {
                    @Override
                    public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                        articleList = response.body();
                        articleAdapter.setmListArticle(articleList);
                    }

                    @Override
                    public void onFailure(Call<List<Article>> call, Throwable t) {

                    }
                });

        // Bắt sự kiện bấm vào xem chi tiết 1 bài báo
        articleAdapter.setItemClickedListener(new ArticleAdapter.ItemClickedListener() {
            @Override
            public void onItemClicked(Article article) {
                Intent intent = new Intent(ArticleByCategoryActivity.this, DetailArticleActivity.class);
                intent.putExtra("article", article);
                startActivity(intent);
            }
        });

    }
}