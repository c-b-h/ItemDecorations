package com.sheldonchen.itemdecorations.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.cv_linear_vertical).setOnClickListener(this);
        findViewById(R.id.cv_linear_horizontal).setOnClickListener(this);
        findViewById(R.id.cv_grid_vertical).setOnClickListener(this);
        findViewById(R.id.cv_grid_horizontal).setOnClickListener(this);
        findViewById(R.id.cv_staggered).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int viewId = v.getId();
        if(viewId == R.id.cv_linear_vertical) {
            startDemoAc(0);
        } else if(viewId == R.id.cv_linear_horizontal) {
            startDemoAc(1);
        } else if(viewId == R.id.cv_grid_vertical) {
            startDemoAc(2);
        } else if(viewId == R.id.cv_grid_horizontal) {
            startDemoAc(3);
        } else if(viewId == R.id.cv_staggered) {
            Toast.makeText(this, "后续将会优化对瀑布流布局的支持.", Toast.LENGTH_SHORT).show();
        }
    }

    private void startDemoAc(int type) {
        Intent intent = new Intent(this, DemoActivity.class);
        intent.putExtra(DemoActivity.ARG_TYPE, type);
        startActivity(intent);
    }
}
