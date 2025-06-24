package com.example.weatherapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText city;
    private Button go;
    private TextView cityn,temp,min,max,humidity,feelslike, condition;
    private TextView mintext,maxtext,humtext,feelstext, conditiontext;
    private ImageView img, background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityn.setText("Loading......");
                String location = city.getText().toString();
                if(!location.isEmpty())
                {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try
                            {

                                runOnUiThread(new Runnable() {
                                    URL u = new URL("apiKey+location+restOfTheKey");

                                    char[] buffer= new char[100000];

                                    BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));

                                    int count = br.read(buffer);

                                    String s = new String(buffer,0,count);
                                    JSONObject j = new JSONObject(s);
                                    JSONObject ja =j.getJSONObject("main");
                                    double kelvin = ja.getDouble("temp");
                                    double min_kelvin = ja.getDouble("temp_min");
                                    double max_kelvin = ja.getDouble("temp_max");
                                    double feels_kelvin = ja.getDouble("feels_like");
                                    double humid = ja.getDouble("humidity");
                                    String desc = j.getJSONArray("weather").getJSONObject(0).getString("description");
                                    String icon = j.getJSONArray("weather").getJSONObject(0).getString("icon");

                                    @Override
                                    public void run() {
                                        kelvin-=273.15;
                                        min_kelvin-=273.15;
                                        max_kelvin-=273.15;
                                        feels_kelvin-=273.15;
                                        cityn.setText(location.toUpperCase());
                                        displayIcon(icon);
                                        changebackgroundandtextcolor(icon);
                                        temp.setText(String.format(Math.round(kelvin) + " °C"));
                                        min.setText(String.format(Math.round(min_kelvin) + " °C"));
                                        max.setText(String.format(Math.round(max_kelvin) + " °C"));
                                        humidity.setText(String.format(" "+ humid) + "%");
                                        feelslike.setText(String.format(Math.round(feels_kelvin) + " °C"));
                                        condition.setText(desc);
                                        if(location.toUpperCase().equals("MADRID"))
                                        {
                                            background.setImageResource(R.drawable.lamine);
                                        }
                                    }
                                });



                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
                }
                else {
                    cityn.setText("Error !!! ");
                }
            }
        });

    }
    private void findViews()
    {
        city = findViewById(R.id.city);
        go = findViewById(R.id.go);
        cityn = findViewById(R.id.cityname);
        temp = findViewById(R.id.current);
        img = findViewById(R.id.icon);
        min = findViewById(R.id.min);
        max = findViewById(R.id.max);
        humidity = findViewById(R.id.humidity);
        feelslike = findViewById(R.id.feelslike);
        condition = findViewById(R.id.condition);

        background = findViewById(R.id.bgimg);
        mintext = findViewById(R.id.mintext);
        maxtext = findViewById(R.id.maxtext);
        humtext = findViewById(R.id.humtext);
        feelstext = findViewById(R.id.feelstext);
        conditiontext = findViewById(R.id.conditiontxt);

    }
    private void displayIcon(String icon)
    {
        switch(icon)
        {
            case "01d":
                img.setImageResource(R.drawable.d1);
                break;
            case "01n":
                img.setImageResource(R.drawable.n1);
                break;
            case "02d":
                img.setImageResource(R.drawable.d2);
                break;
            case "02n":
                img.setImageResource(R.drawable.n2);
                break;
            case "03d":
            case "03n":
                img.setImageResource(R.drawable.d3);
                break;
            case "04d":
            case "04n":
                img.setImageResource(R.drawable.d4);
                break;
            case "09d":
            case "09n":
                img.setImageResource(R.drawable.d9);
                break;
            case "10d":
                img.setImageResource(R.drawable.d10);
                break;
            case "10n":
                img.setImageResource(R.drawable.n10);
                break;
            case "11d":
            case "11n":
                img.setImageResource(R.drawable.d11);
                break;
            case "13d":
            case "13n":
                img.setImageResource(R.drawable.d13);
                break;
            case "50d":
            case "50n":
                img.setImageResource(R.drawable.d50);
                break;
            default:
                img.setImageResource(R.drawable.blank);
                break;
        }
    }
    private void changebackgroundandtextcolor(String icon)
    {
        switch(icon)
        {
            case "01d":
            case "02d":
            case "03d":
            case "04d":
            case "09d":
            case "10d":
            case "11d":
            case "13d":
            case "50d":
                city.setTextColor(Color.BLACK);
                cityn.setTextColor(Color.BLACK);
                temp.setTextColor(Color.BLACK);
                min.setTextColor(Color.BLACK);
                max.setTextColor(Color.BLACK);
                humidity.setTextColor(Color.BLACK);
                feelslike.setTextColor(Color.BLACK);
                condition.setTextColor(Color.BLACK);
                mintext.setTextColor(Color.BLACK);
                maxtext.setTextColor(Color.BLACK);
                humtext.setTextColor(Color.BLACK);
                feelstext.setTextColor(Color.BLACK);
                conditiontext.setTextColor(Color.BLACK);
                background.setImageResource(R.drawable.sun);
                break;
            case "01n":
            case "02n":
            case "03n":
            case "04n":
            case "09n":
            case "10n":
            case "11n":
            case "13n":
            case "50n":
                city.setTextColor(Color.WHITE);
                cityn.setTextColor(Color.MAGENTA);
                temp.setTextColor(Color.WHITE);
                min.setTextColor(Color.WHITE);
                max.setTextColor(Color.WHITE);
                humidity.setTextColor(Color.WHITE);
                feelslike.setTextColor(Color.WHITE);
                condition.setTextColor(Color.WHITE);
                mintext.setTextColor(Color.WHITE);
                maxtext.setTextColor(Color.WHITE);
                humtext.setTextColor(Color.WHITE);
                feelstext.setTextColor(Color.WHITE);
                conditiontext.setTextColor(Color.WHITE);
                background.setImageResource(R.drawable.moon);
                break;
            default:
                background.setImageResource(R.drawable.blue);
                break;
        }
    }
}
