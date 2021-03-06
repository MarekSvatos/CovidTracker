package sva0164.covid_19_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import sva0164.covid_19_tracker.api.Article;
import sva0164.covid_19_tracker.api.DataFetcher;

public class News extends AppCompatActivity {

    List<Article> articles;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        try {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataFetcher dataFetcher = new DataFetcher();
        try {
            articles = dataFetcher.getNews().get();
        } catch (ExecutionException | InterruptedException e) {
            articles = null;
        }

        Preconditions.checkNotNull(articles);

        initRecyclerView();
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.news_recycler);
        NewsRecyclerViewAdapter recyclerViewAdapter = new NewsRecyclerViewAdapter(this, articles);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBackBtnClicked(View view) {
        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivity);
        finish();
    }


}