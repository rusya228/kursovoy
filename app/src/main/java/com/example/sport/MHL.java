package com.example.sport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MHL extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mhl);
        refreshGridView();
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        GridView gridView = findViewById(R.id.gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), idList.get(position), Toast.LENGTH_SHORT).show();
                        MatchPage.idMatch = Integer.parseInt(idList.get(position));
                        tomatchpage();

                    }
                });
            }


        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshGridView();
            }
        });


    }
    public void onClick(View v) {

            if (v.getId()== R.id.khl_btn_m){
                Intent intent1 = new Intent(MHL.this, KHL.class);
                startActivity(intent1);}

            if( v.getId()== R.id.whl_btn_m){
                Intent intent2 = new Intent(MHL.this, WHL.class);
                startActivity(intent2);}

        }
    private SwipeRefreshLayout swipeRefreshLayout;
    private GridView mGridView;
    private CustomGridAdapter mAdapter;
    private Boolean Upload = false;
    ArrayList<String> idList = new ArrayList<>();

    public void refreshGridView(){
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(null);
        idList.clear();
        OkHttpClient client = new OkHttpClient();
        String url = "https://smtpservers.ru/projects/Bardin/selectMatch?idLegue=3";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);

        mGridView = findViewById(R.id.gridView);

        Context context = this;
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if (json.equals("500")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Матчи отсутствуют!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Gson gson = new Gson();
                   Match[] adv = gson.fromJson(json, Match[].class);
                    String[][] array = new String[adv.length][6];
                    for(int i = 0; i < adv.length; i++){
                        idList.add(String.valueOf(adv[i].IdMatch));
                        array[i][0] = adv[i].HomeTeamName;
                        array[i][1] = adv[i].GuestTeamName;
                        array[i][2] = adv[i].HomePhotopath;
                        array[i][3] = adv[i].GuestPhotopath;
                        array[i][4] = adv[i].HomeTeamGoal;
                        array[i][5] = adv[i].GuestTeamGoal;
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new CustomGridAdapter(context, array);
                            mGridView.setAdapter(mAdapter);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                String error = e.toString();
            }
        });
        if(Upload) {
            swipeRefreshLayout.setRefreshing(false);
        }else{
            Upload = true;
        }
    }
    public class Match{
        private String HomeTeamName;
        private String GuestTeamName;
        private String HomePhotopath;
        private String GuestPhotopath;
        private String HomeTeamGoal;
        private String GuestTeamGoal;
        private int IdMatch;
    }
    public void tomatchpage(){
        startActivity(new Intent(this, MatchPage.class ));
    }

}

