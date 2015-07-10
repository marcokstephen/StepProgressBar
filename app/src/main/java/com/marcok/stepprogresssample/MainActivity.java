package com.marcok.stepprogresssample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.marcok.stepprogressbar.StepProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StepProgressBar mStepProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStepProgressBar = (StepProgressBar)findViewById(R.id.stepProgressBar);

        Button prev = (Button) findViewById(R.id.button_prev);
        Button next = (Button) findViewById(R.id.button_next);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_prev:
                mStepProgressBar.progressBackward();
                break;
            case R.id.button_next:
                mStepProgressBar.progressForward();
                break;
        }
    }
}
