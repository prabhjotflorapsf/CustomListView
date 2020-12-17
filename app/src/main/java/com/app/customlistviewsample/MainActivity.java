package com.app.customlistviewsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.customlistviewsample.adapter.MyListAdapter;
import com.app.customlistviewsample.model.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Activity mActivity;
    private TextView tvLoading;
    private ListView mListView;
    private List<Repository> mList;
    private Webservices mInterface_method = AppController.getRetroInstance().create(Webservices.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = MainActivity.this;

        tvLoading = findViewById(R.id.tvLoading);
        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();

        Call<List<Repository>> call = mInterface_method.getRepositories();
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful()) {
                    tvLoading.setVisibility(View.GONE);
                    mListView.setVisibility(View.VISIBLE);
                    List<Repository> list = response.body();
                    MyListAdapter myListAdapter = new MyListAdapter(list, R.layout.listview_item, mActivity);
                    mListView.setAdapter(myListAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}