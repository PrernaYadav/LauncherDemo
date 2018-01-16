package com.example.prerna.launcherdemo;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Button btnAppear,btnYoutube,btnSpeechLogger,btnPortal,btnWebsite;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAppear=(Button)findViewById(R.id.btn_appear);
        btnYoutube=(Button)findViewById(R.id.btn_youtube);
        btnSpeechLogger=(Button)findViewById(R.id.btn_speech_logger);
        btnPortal=(Button)findViewById(R.id.btn_portal);
        btnWebsite=(Button)findViewById(R.id.btn_website);
        btnAppear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://appear.in/"));
                startActivity(intent);
            }
        });
        btnYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=8YaF2m7hCx0")));

            }
        });
        btnSpeechLogger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, 1);
                    btnSpeechLogger.setText("");
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        btnPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               btnPortal.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
               btnPortal.setText("");
            }
        });
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://sumansoft.business.site/")));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showApps(View v){
      /*  Intent i = new Intent(this, AppActivity.class);
        startActivity(i);*/

        Intent myIntent = new Intent(this, AppActivity.class);
        ActivityOptions options =
                ActivityOptions.makeCustomAnimation(this, R.anim.animation_one, R.anim.animation_two);
        this.startActivity(myIntent, options.toBundle());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    btnSpeechLogger.setText(text.get(0));
                }
                break;
            }

        }
    }
}