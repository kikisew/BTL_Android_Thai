package com.example.btl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.btl.ArticleByCategoryActivity;
import com.example.btl.DetailArticleActivity;
import com.example.btl.R;
import com.example.btl.adapter.ArticleAdapter;
import com.example.btl.adapter.CategoryAdapter;
import com.example.btl.model.Article;
import com.example.btl.model.Category;
import com.example.btl.util.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {
    private RecyclerView reCategory, reArticle;
    private List<Category> listCategory;
    private CategoryAdapter categoryAdapter;
    private List<Article> listArticle;
    private ArticleAdapter articleAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reCategory = view.findViewById(R.id.reCategory);
        reArticle = view.findViewById(R.id.reArticle);

        // set adapter cho list category
        categoryAdapter = new CategoryAdapter(new ArrayList<>(),getActivity());
        reCategory.setAdapter(categoryAdapter);
        reCategory.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false));

        // call api category rồi update giao diện list category
        APIUtils.getApiServiceInterface().getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                listCategory = response.body();
                Log.d("====== list category :", listCategory.get(0).getName());
                categoryAdapter.setmListCategory(listCategory);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

        // set adpater cho list Article
        articleAdapter = new ArticleAdapter(new ArrayList<>(),getActivity());
        reArticle.setAdapter(articleAdapter);
        reArticle.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        // Call api lấy về danh sách bài viết
        APIUtils.getApiServiceInterface().getAllArticles()
                .enqueue(new Callback<List<Article>>() {
                    @Override
                    public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                        listArticle = response.body();
                        articleAdapter.setmListArticle(listArticle);
                    }

                    @Override
                    public void onFailure(Call<List<Article>> call, Throwable t) {

                    }
                });


        // Bắt sự kiện click vào item category thì gọi intent chuyển sang ArticleByCategoryActivity
        categoryAdapter.setItemClickedListener(new CategoryAdapter.ItemClickedListener() {
            @Override
            public void onItemClicked(Category category) {
                Intent intent = new Intent(getActivity(), ArticleByCategoryActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });


        // Bắt sự kiện bấm vào xem chi tiết 1 bài báo
        articleAdapter.setItemClickedListener(new ArticleAdapter.ItemClickedListener() {
            @Override
            public void onItemClicked(Article article) {
                Intent intent = new Intent(getActivity(), DetailArticleActivity.class);
                intent.putExtra("article", article);
                startActivity(intent);
            }
        });
    }
}
