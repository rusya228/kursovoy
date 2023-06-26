package com.example.sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class Page_sostav extends Activity {
    public static int idMatch;
    public static int idTeam;
    public String t2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_sostav);

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
                            TextView Name = (TextView) findViewById(R.id.igr);
                            TextView Age = (TextView) findViewById(R.id.age);
                            TextView Role = (TextView) findViewById(R.id.amp);
                            TextView Number = (TextView) findViewById(R.id.num);
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

                            t2 = adv[0].GuestTeamName;

                            TextView Status = (TextView) findViewById(R.id.status);
                            Status.setText(adv[0].Title);

                            TextView homeTeamGoal = (TextView) findViewById(R.id.HomeTeamGoal);
                            homeTeamGoal.setText(""+ adv[0].HomeTeamGoal);

                            TextView guestTeamGoal = (TextView) findViewById(R.id.GuestTeamGoal);
                            guestTeamGoal.setText(""+ adv[0].GuestTeamGoal);

                            TextView Date = (TextView) findViewById(R.id.date);
                            Date.setText(""+ adv[0].Date+ " " + adv[0].Time);

                            Name.append(adv[0].HomeTeamName + "\n\n");

                            Age.append("\n\n");

                            Role.append("\n\n");

                            Number.append("\n\n");
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                String error = e.toString();
            }
        });
    Sostav();
    }
    public class Sostav{

        private String Name;
        private String Age;
        private String Role;
        private int count;
        private String Number;

    }
    public void  Sostav(){
        OkHttpClient client = new OkHttpClient();
        String url = "https://smtpservers.ru/projects/Bardin/selectLinups?idMatch=" + idMatch ;
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
                    Sostav[] adv = gson.fromJson(json, Sostav[].class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView Name = (TextView) findViewById(R.id.igr);
                            TextView Age = (TextView) findViewById(R.id.age);
                            TextView Role = (TextView) findViewById(R.id.amp);
                            TextView Number = (TextView) findViewById(R.id.num);

                            for (int i = 0; i<adv.length;i++) {

                                if (i==adv[0].count){

                                    Name.append("\n"+ t2 + "\n\n");

                                    Age.append("\n\n\n");

                                    Role.append("\n\n\n");

                                    Number.append("\n\n\n");
                                }

                                Name.append(adv[i].Name);

                                Age.append(adv[i].Age);

                                Role.append(adv[i].Role);

                                Number.append(adv[i].Number);
                            }
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


    public void toMatchPage(View v){
        startActivity(new Intent(this, MatchPage.class));
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