package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btl.model.Article;
import com.example.btl.model.Category;

import java.text.SimpleDateFormat;

public class DetailArticleActivity extends AppCompatActivity {
    private TextView tvTitle, tvDate,tvContent;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);

        tvTitle = findViewById(R.id.tvTitle);
        tvDate = findViewById(R.id.tvDate);
        tvContent = findViewById(R.id.tvContent);
        img = findViewById(R.id.imageView);

        Intent intentFromFragmentHome = getIntent();
        Article article = (Article) intentFromFragmentHome.getSerializableExtra("article");

        tvTitle.setText(article.getTitle());
        if(article.getCreatedAt() !=null) {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            String date = sdf.format(article.getCreatedAt());
            String date = article.getCreatedAt().toString();

            tvDate.setText(date);
        }
        else tvDate.setText("05/22/2022");

        Glide.with(this).load(article.getImg()).into(img);

        tvContent.setText(article.getContent());

    }
}