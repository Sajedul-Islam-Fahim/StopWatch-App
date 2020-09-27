package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        private  int seconds =0;
        private  boolean running;
        private boolean wasrunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null)
        {
           seconds= savedInstanceState.getInt("seconds");
           running= savedInstanceState.getBoolean("running");
           wasrunning=savedInstanceState.getBoolean("wasrunning");
        }
        runtimer();
    }
    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("seconds",seconds);
        bundle.putBoolean("running",running);
        bundle.putBoolean("wasrunning",wasrunning);

    }

    protected  void onPause() {

        super.onPause();
        wasrunning=running;
        running=false;
    }
    @Override
    protected  void  onResume() {
        super.onResume();
        if (wasrunning)
        {
            running=true;
        }

    }


    public void onClickStart(View view) {
        running=true;
    }

    public void onClickStop(View view) {
    running=false;
    }

    public void onClickReset(View view) {
running=false;
seconds=0;
    }
    private void runtimer(){
       final TextView timeView=findViewById(R.id.time);
      final   Handler handler =new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int mins= (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,mins,secs);
                timeView.setText(time);
                if (running)
                {
                    seconds++;
                }
                handler.postDelayed(this,100);
            }
        });

    }

}
