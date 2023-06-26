package com.example.sport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatchPage extends Activity {

    public static int idMatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchpage);
//        LinearLayout Main = findViewById(R.id.Main);
//        LinearLayout Date = findViewById(R.id.Date);
//        LinearLayout Count = findViewById(R.id.Count);
//        LinearLayout TeamOne = findViewById(R.id.TeamOne);
//        LinearLayout TeamTwo = findViewById(R.id.TeamTwo);
//        LinearLayout TeamMain = findViewById(R.id.TeamMain);
//        LinearLayout Teamoneimage  = TeamOne.findViewById(R.id.teamoneimage);
//        LinearLayout Teamtwoimage  = TeamTwo.findViewById(R.id.teamtwoimage);
//
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int screenHeight =displaymetrics.heightPixels; //высота экрана в пикселях
//        int screenWeight = displaymetrics.widthPixels;
//
//        LinearLayout.LayoutParams main  = (LinearLayout.LayoutParams) Main.getLayoutParams();
//        main.height = (int) (screenHeight/4);
//
//        LinearLayout.LayoutParams toh = (LinearLayout.LayoutParams) TeamOne.getLayoutParams();
//        toh.height = (int) (screenHeight/3);
//
//        TextView datetext  = findViewById(R.id.date);
//        datetext.setTextSize(screenHeight/120);
//
//
////        LinearLayout.LayoutParams toi  = (LinearLayout.LayoutParams) Teamoneimage.getLayoutParams();
////        toi.height = (int) (screenHeight/6);
//        LinearLayout.LayoutParams count  = (LinearLayout.LayoutParams) Count.getLayoutParams();
//        LinearLayout.LayoutParams to  = (LinearLayout.LayoutParams) TeamOne.getLayoutParams();
//        to.weight = (int) ((screenWeight - count.weight)/2);
//
////        LinearLayout.LayoutParams count  = (LinearLayout.LayoutParams) Count.getLayoutParams();
////        count.weight = (int) (screenWeight/10);
//
//
//        LinearLayout.LayoutParams tt  = (LinearLayout.LayoutParams) TeamTwo.getLayoutParams();
//        tt.weight = (int) ((screenWeight - count.weight)/2);




        OkHttpClient client = new OkHttpClient();
        String url = "https://smtpservers.ru/projects/Bardin/matchPage?idMatch=" + idMatch;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                if (json.equals("500")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Объявления отсутствуют!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Gson gson = new Gson();
                    Match[] adv = gson.fromJson(json, Match[].class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ImageView imageView = (ImageView) findViewById(R.id.imageView);
                            String imageUrl = "https://smtpservers.ru/projects/Bardin/uploads/"+ adv[0].HomePhotopath;
                            Picasso.get()
                                    .load(imageUrl)
                                    .into(imageView);

                            ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
                            String imageUrl2 = "https://smtpservers.ru/projects/Bardin/uploads/"+ adv[0].GuestPhotopath;
                            Picasso.get()
                                    .load(imageUrl2)
                                    .into(imageView2);

                            TextView titleString = (TextView) findViewById(R.id.team1);
                            titleString.setText(adv[0].HomeTeamName);

                            TextView Team2Name = (TextView) findViewById(R.id.team2);
                            Team2Name.setText(adv[0].GuestTeamName);

                            TextView Status = (TextView) findViewById(R.id.status);
                            Status.setText(adv[0].Title);

                            TextView homeTeamGoal = (TextView) findViewById(R.id.HomeTeamGoal);
                            homeTeamGoal.setText(""+ adv[0].HomeTeamGoal);

                            TextView guestTeamGoal = (TextView) findViewById(R.id.GuestTeamGoal);
                            guestTeamGoal.setText(""+ adv[0].GuestTeamGoal);

                            TextView Date = (TextView) findViewById(R.id.date);
                            Date.setText(""+ adv[0].Date+ " " + adv[0].Time);

                            TextView GuestTeamGoal = (TextView) findViewById(R.id.g2);
                            GuestTeamGoal.setText(adv[0].GuestTeamGoal);

                            TextView HomeTeamShotsOnGoal = (TextView) findViewById(R.id.bvs1);
                            HomeTeamShotsOnGoal.setText(adv[0].HomeTeamShotsOnGoal);

                            TextView TeamHomePenalties = (TextView) findViewById(R.id.u1);
                            TeamHomePenalties.setText(adv[0].TeamHomePenalties);

                            TextView TeamHomePowerPlayGoals = (TextView) findViewById(R.id.gvb1);
                            TeamHomePowerPlayGoals.setText(adv[0].TeamHomePowerPlayGoals);

                            TextView TeamHomeHits = (TextView) findViewById(R.id.sp1);
                            TeamHomeHits.setText(adv[0].TeamHomeHits);

                            TextView TeamHomeBlockedShots = (TextView) findViewById(R.id.bb1);
                            TeamHomeBlockedShots.setText(adv[0].TeamHomeBlockedShots);

                            TextView TeamHomeGoalkeeperSaves = (TextView) findViewById(R.id.sv1);
                            TeamHomeGoalkeeperSaves.setText(adv[0].TeamHomeGoalkeeperSaves);

                            TextView TeamGuestShotsOnGoal = (TextView) findViewById(R.id.bvs2);
                            TeamGuestShotsOnGoal.setText(adv[0].TeamGuestShotsOnGoal);

                            TextView TeamGuestBlockedShots = (TextView) findViewById(R.id.bb2);
                            TeamGuestBlockedShots.setText(adv[0].TeamGuestBlockedShots);

                            TextView TeamGuestGoalkeeperSaves = (TextView) findViewById(R.id.sv2);
                            TeamGuestGoalkeeperSaves.setText(adv[0].TeamGuestGoalkeeperSaves);

                            TextView TeamGuestPenalties = (TextView) findViewById(R.id.u2);
                            TeamGuestPenalties.setText(adv[0].TeamGuestPenalties);

                            TextView TeamGuestHits = (TextView) findViewById(R.id.sp2);
                            TeamGuestHits.setText(adv[0].TeamGuestHits);

                            TextView HomeTeamGoal = (TextView) findViewById(R.id.g1);
                            HomeTeamGoal.setText(adv[0].HomeTeamGoal);
                        }
                    });


                }
            }


            @Override
            public void onFailure(Call call, IOException e) {
                String error = e.toString();
            }
        });
    }
    public class Match{
        private String HomeTeamName;
        private String GuestTeamName;
        private String HomePhotopath;
        private String GuestPhotopath;
        private String HomeTeamGoal;
        private String GuestTeamGoal;
        private String HomeTeamShotsOnGoal;
        private String TeamHomeBlockedShots;
        private String TeamHomeGoalkeeperSaves;
        private String TeamHomePenalties;
        private String TeamHomePowerPlayGoals;
        private String TeamHomeHits;
        private String TeamGuestShotsOnGoal;
        private String TeamGuestBlockedShots;
        private String TeamGuestGoalkeeperSaves;
        private String TeamGuestPenalties;
        private String TeamGuestHits;
        private int IdMatch;
        private String Date;
        private String Time;
        private String Title;
    }
        public void toSostav(View v){
            Page_sostav.idMatch=idMatch;
            startActivity(new Intent(this, Page_sostav.class));
            overridePendingTransition(0,0);
        }
        public void toTable(View v){
        startActivity(new Intent(this, Page_table.class));
        overridePendingTransition(0,0);
        }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, KHL.class));
    }
}




