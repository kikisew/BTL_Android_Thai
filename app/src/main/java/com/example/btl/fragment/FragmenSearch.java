package com.example.btl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl.DetailArticleActivity;
import com.example.btl.R;
import com.example.btl.adapter.ArticleAdapter;
import com.example.btl.model.Article;
import com.example.btl.util.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmenSearch extends Fragment {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<Article> listArticle;
    private ArticleAdapter articleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.recycleView);

        // set adpater cho list Article
        articleAdapter = new ArticleAdapter(new ArrayList<>(),getActivity());
        recyclerView.setAdapter(articleAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String keyword) {
                APIUtils.getApiServiceInterface().getArticlesByKey(keyword)
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

                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
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
